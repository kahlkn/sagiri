package sagiri.service.impl;

import artoria.beans.BeanUtils;
import artoria.common.PageResult;
import artoria.common.Paging;
import artoria.exception.BusinessException;
import artoria.util.CollectionUtils;
import artoria.util.PagingUtils;
import artoria.util.StringUtils;
import artoria.util.ThreadUtils;
import com.alibaba.fastjson.JSON;
import kurumi.whois.SimpleWhoisProvider;
import kurumi.whois.WhoisObject;
import kurumi.whois.WhoisProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sagiri.persistence.entity.DomainInfo;
import sagiri.persistence.entity.DomainInfoQuery;
import sagiri.persistence.mapper.DomainInfoMapper;
import sagiri.pojo.dto.DomainInfoDTO;
import sagiri.pojo.dto.DomainInfoQueryDTO;
import sagiri.service.AsyncService;
import sagiri.service.DomainInfoService;

import java.util.*;

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
    @Autowired
    private AsyncService asyncService;

    @Override
    public void addDomainInfo(DomainInfoDTO domainInfoDTO) {
        DomainInfo domainInfo =
                BeanUtils.beanToBean(domainInfoDTO, DomainInfo.class);
        if (domainInfo.getStatus() == null) {
            domainInfo.setStatus(ZERO);
        }
        if (StringUtils.isBlank(domainInfo.getCreateUser())) {
            domainInfo.setCreateUser(SYSTEM);
            domainInfo.setCreateDate(new Date());
        }
        if (StringUtils.isBlank(domainInfo.getUpdateUser())) {
            domainInfo.setUpdateUser(SYSTEM);
            domainInfo.setUpdateDate(new Date());
        }
        domainInfo.setAliveFlag(ONE);
        int effect = domainInfoMapper.insertSelective(domainInfo);
        if (effect != ONE) {
            throw new BusinessException("Insert selective failure. ");
        }
    }

    @Override
    public void editDomainInfo(DomainInfoDTO domainInfoDTO) {
        DomainInfo domainInfo =
                BeanUtils.beanToBean(domainInfoDTO, DomainInfo.class);
        if (StringUtils.isBlank(domainInfo.getUpdateUser())) {
            domainInfo.setUpdateUser(SYSTEM);
            domainInfo.setUpdateDate(new Date());
        }
        int effect = domainInfoMapper.updateByPrimaryKeySelective(domainInfo);
        if (effect != ONE) {
            throw new BusinessException("Update by primary key selective failure. ");
        }
    }

    private WhoisProvider whoisProvider = new SimpleWhoisProvider();
    @Override
    public void addDomainInfoByName(String domainName, boolean saveRegistered, boolean saveUnregistered) {
        if (!saveRegistered && !saveUnregistered) { return; }
        WhoisObject whoisObject = whoisProvider.findWhois(domainName);
        if (whoisObject == null) {
            throw new BusinessException("Domain name cannot be found. ");
        }
        String registrantId = whoisObject.getRegistrantId();
        String registrant = whoisObject.getRegistrant();
        boolean isUnregistered = StringUtils.isBlank(registrantId) && StringUtils.isBlank(registrant);
        boolean needSave = (isUnregistered && saveUnregistered) || (!isUnregistered && saveRegistered);
        log.info("{} >> {}", domainName, needSave);
        if (!needSave) { return; }
        List<String> domainStatuses = whoisObject.getDomainStatuses();
        List<String> nameServers = whoisObject.getNameServers();
        whoisObject.setDomainStatuses(null);
        whoisObject.setNameServers(null);
        DomainInfoDTO domainInfoDTO =
                BeanUtils.beanToBean(whoisObject, DomainInfoDTO.class);
        domainInfoDTO.setDomainStatuses(JSON.toJSONString(domainStatuses));
        domainInfoDTO.setNameServers(JSON.toJSONString(nameServers));
        domainInfoDTO.setRawData((String) whoisObject.rawData());
        addDomainInfo(domainInfoDTO);
    }

    @Override
    public void batchCheckDomainUnregistered(List<List<String>> table, List<String> suffixes, Long sleepTime) {
        Integer length = table.size();
        int[] counter = new int[length];
        for (int i = ZERO; i < length; i++) {
            counter[i] = ZERO;
        }
        int maxIndex = length - ONE;
        int maxCount = ZERO;
        for (List<String> list : table) {
            if (CollectionUtils.isEmpty(list)) {
                throw new BusinessException("Columns in a table cannot be empty. ");
            }
            if (maxCount == ZERO) {
                maxCount = list.size();
                continue;
            }
            if (list.size() != maxCount) {
                throw new BusinessException("Each column should have the same length. ");
            }
        }
        //
        while (counter[ZERO] < maxCount) {
            StringBuilder bodyBuilder = new StringBuilder();
            for (int i = ZERO; i < length; i++) {
                List<String> list = table.get(i);
                String charStr = list.get(counter[i]);
                bodyBuilder.append(charStr);
            }
            String body = bodyBuilder.toString();
            // Do something.
            for (String suffix : suffixes) {
                String domainName = body + suffix;
//                asyncService.addDomainInfoByName(domainName, Boolean.FALSE, Boolean.TRUE);
                asyncService.addDomainInfoByName(domainName, Boolean.TRUE, Boolean.TRUE);
                if (sleepTime != null && sleepTime > ZERO) {
                    ThreadUtils.sleepQuietly(sleepTime);
                }
            }
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
        }
        //
    }

    @Override
    public void batchCheckDomainUnregistered(Integer length, Boolean existNumber, Long sleepTime, String... suffixes) {
        if (existNumber == null) { existNumber = false; }
//        if (!domainSuffix.startsWith(DOT)) {
//            domainSuffix = DOT + domainSuffix;
//        }
        List<List<String>> table = new ArrayList<>(length);
        for (int i = ZERO; i < length; i++) {
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
        batchCheckDomainUnregistered(table, Arrays.asList(suffixes), sleepTime);
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
