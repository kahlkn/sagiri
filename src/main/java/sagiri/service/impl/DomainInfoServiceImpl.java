package sagiri.service.impl;

import artoria.beans.BeanUtils;
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
import sagiri.persistence.mapper.DomainInfoMapper;
import sagiri.service.DomainInfoService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static artoria.common.Constants.*;

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
        DomainInfo query = new DomainInfo();
        int pageNum = 1, pageSize = 200;
        while (true) {
            PagingUtils.startPage(pageNum, pageSize);
            List<DomainInfo> list = domainInfoMapper.querySelective(query);
            if (CollectionUtils.isEmpty(list)) { break; }
            for (DomainInfo domainInfo : list) {
                if (domainInfo == null) { continue; }
                String domainName = domainInfo.getDomainName();
                try {
                    updateDomain(domainName);
                }
                catch (Exception e) {
                    log.info("Update \"" + domainName + "\" failed. ", e);
                }
                ThreadUtils.sleepQuietly(THREE_HUNDRED);
            }
            pageNum++;
        }
    }

}
