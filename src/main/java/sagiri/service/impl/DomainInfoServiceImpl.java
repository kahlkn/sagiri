package sagiri.service.impl;

import artoria.beans.BeanUtils;
import artoria.common.PageResult;
import artoria.common.Paging;
import artoria.thread.SimpleThreadFactory;
import artoria.time.DateUtils;
import artoria.util.CollectionUtils;
import artoria.util.PagingUtils;
import artoria.util.ThreadUtils;
import com.alibaba.fastjson.JSON;
import kuroko.whois.SimpleWhoisProvider;
import kuroko.whois.WhoisObject;
import kuroko.whois.WhoisProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sagiri.persistence.entity.DomainInfo;
import sagiri.persistence.entity.DomainInfoQuery;
import sagiri.persistence.mapper.DomainInfoMapper;
import sagiri.pojo.dto.DomainInfoDTO;
import sagiri.pojo.dto.DomainInfoQueryDTO;
import sagiri.service.DomainInfoService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

import static artoria.common.Constants.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * DomainInfoServiceImpl.
 * @author Kahle
 * @date 2019-11-08T09:48:18.613+0800
 */
@Slf4j
@Service
public class DomainInfoServiceImpl implements DomainInfoService {

    @Autowired
    private DomainInfoMapper domainInfoMapper;

    @Override
    public void initialize(String domainSuffix, Integer length, Boolean existNumber) {
        if (existNumber == null) { existNumber = false; }
        if (!domainSuffix.startsWith(DOT)) {
            domainSuffix = DOT + domainSuffix;
        }
        List<List<String>> table = new ArrayList<>(length);
        int[] counter = new int[length];
        int maxCount = 26 + (existNumber ? TEN : ZERO);
        int maxIndex = length - ONE;
        for (int i = ZERO; i < length; i++) {
            counter[i] = ZERO;
            List<String> list = new ArrayList<>();
            Collections.addAll(list, "a","b","c","d","e"
                    ,"f","g","h","i","j","k","l","m","n","o","p"
                    ,"q","r","s","t","u","v","w","x","y","z");
            if (existNumber) {
                Collections.addAll(list, "0","1"
                        ,"2","3","4","5","6","7","8","9");
            }
            table.add(list);
        }
        List<DomainInfo> domainList = new ArrayList<>();
        while (counter[ZERO] < maxCount) {
            StringBuilder domainName = new StringBuilder();
            for (int i = ZERO; i < length; i++) {
                List<String> list = table.get(i);
                String charStr = list.get(counter[i]);
                domainName.append(charStr);
            }
            domainName.append(domainSuffix);
            DomainInfo domainInfo = new DomainInfo();
            domainInfo.setDomainName(domainName.toString());
            domainInfo.setDomainLength(length);
            domainInfo.setDomainSuffix(domainSuffix.startsWith(DOT) ? domainSuffix.substring(ONE) : domainSuffix);
            domainInfo.setStatus(ZERO);
            domainInfo.setCreateDate(new Date());
            domainInfo.setCreateUser(SYSTEM);
            domainInfo.setUpdateDate(new Date());
            domainInfo.setUpdateUser(SYSTEM);
            domainInfo.setAliveFlag(ONE);
            domainList.add(domainInfo);
            // Counter to count.
            counter[maxIndex]++;
            for (int i = maxIndex; i >= ZERO; i--) {
                if (i == ZERO && counter[i] == maxCount) {
                    continue;
                }
                if (counter[i] == maxCount) {
                    counter[i] = ZERO;
                    counter[i - ONE]++;
                }
            }
            // Try batch insert.
            if (domainList.size() >= ONE_HUNDRED) {
                try {
                    int effect = domainInfoMapper.insertBatch(domainList);
                    log.info(
                            "Try batch insert {} pieces of domain, successful {} pieces of domain. "
                            , domainList.size(), effect
                    );
                    domainList.clear();
                }
                catch (Exception e) {
                    log.info("Try batch insert fail. ", e);
                }
            }
        }

    }

    private WhoisProvider whoisProvider = new SimpleWhoisProvider();
    @Override
    public void updateDomain(String domainName) {
        WhoisObject whoisObject = whoisProvider.findWhois(domainName);
        if (whoisObject == null) { return; }
        List<String> domainStatuses = whoisObject.getDomainStatuses();
        List<String> nameServers = whoisObject.getNameServers();
        whoisObject.setDomainStatuses(null);
        whoisObject.setNameServers(null);
        DomainInfo domainInfo = BeanUtils.beanToBean(whoisObject, DomainInfo.class);
        domainInfo.setDomainStatuses(JSON.toJSONString(domainStatuses));
        domainInfo.setNameServers(JSON.toJSONString(nameServers));
        domainInfo.setRawData((String) whoisObject.rawData());
        domainInfo.setRemark(EMPTY_STRING);
        // 0 初始，1 正常
        domainInfo.setStatus(ONE);
        domainInfo.setUpdateDate(new Date());
        domainInfo.setUpdateUser(SYSTEM);
        int effect = domainInfoMapper.updateByPrimaryKeySelective(domainInfo);
        log.info("Update \"{}\" >> result: {}. ", domainName, effect == ONE);
    }

    @Override
    public void updateAllDomain() {
        String threadNamePrefix = "update-domain-executor";
        ExecutorService pool = null;
        final int refreshRate = 0;
        final int pageSize = 500;
        final int poolSize = 50;
        try {
            ThreadFactory threadFactory = new SimpleThreadFactory(threadNamePrefix, Boolean.TRUE);
            pool = new ThreadPoolExecutor(
                    poolSize, poolSize, ZERO, MILLISECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory
            );
            final CyclicBarrier cyclicBarrier = new CyclicBarrier(poolSize);
            for (int i = ONE; i <= poolSize; i++) {
                final int pageIndex = i;
                pool.submit(new Runnable() {
                    @Override
                    public void run() {
                        for (int count = 0; ; count++) {
                            int pageNum = pageIndex + count * poolSize;
                            DomainInfoQueryDTO domainInfoQuery = new DomainInfoQueryDTO();
                            domainInfoQuery.setPaging(new Paging(pageNum, pageSize));
                            PageResult<List<DomainInfoDTO>> listPageResult = queryDomainInfoList(domainInfoQuery);
                            if (listPageResult == null
                                    || CollectionUtils.isEmpty(listPageResult.getData())) {
                                break;
                            }
                            Date endUpdateDate = DateUtils.create().addDay(ZERO - refreshRate).getDate();
                            List<DomainInfoDTO> resultData = listPageResult.getData();
                            for (DomainInfoDTO domainInfo : resultData) {
                                if (domainInfo == null) { continue; }
                                Integer status = domainInfo.getStatus();
                                Date updateDate = domainInfo.getUpdateDate();
                                if (updateDate.getTime() > endUpdateDate.getTime()
                                        && status != ZERO) {
                                    continue;
                                }
                                String domainName = domainInfo.getDomainName();
                                try {
                                    updateDomain(domainName);
                                }
                                catch (Exception e) {
                                    log.info("Update \"" + domainName + "\" failed. ", e);
                                }
                                ThreadUtils.sleepQuietly(THREE_HUNDRED);
                            }
                        }
                        try {
                            cyclicBarrier.await();
                        }
                        catch (Exception e) {
                            log.info("Cyclic barrier await error. ", e);
                        }
                    }
                });
            }
            try {
                cyclicBarrier.await();
            }
            catch (Exception e) {
                log.info("Cyclic barrier await error. ", e);
            }
            log.info("End of update all domain. ");
        }
        finally {
            if (pool != null) {
                pool.shutdownNow();
            }
        }
    }

    @Override
    public PageResult<List<DomainInfoDTO>> queryDomainInfoList(DomainInfoQueryDTO domainInfoQueryDTO) {
        DomainInfoQuery domainInfoQuery = BeanUtils.beanToBean(domainInfoQueryDTO, DomainInfoQuery.class);
        Paging paging = domainInfoQueryDTO.getPaging();
        if (paging == null) { paging = new Paging(); }
        PagingUtils.startPage(paging);
        List<DomainInfo> domainInfoList = domainInfoMapper.queryDomainInfoList(domainInfoQuery);
        return PagingUtils.handleResult(domainInfoList, DomainInfoDTO.class);
    }

}
