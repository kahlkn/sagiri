package sagiri.tools.service.impl;

import artoria.beans.BeanUtils;
import artoria.common.PageResult;
import artoria.common.Paging;
import artoria.exception.BusinessException;
import artoria.spring.ApplicationContextUtils;
import artoria.util.CollectionUtils;
import artoria.util.PagingUtils;
import artoria.util.StringUtils;
import artoria.util.ThreadUtils;
import com.alibaba.fastjson.JSON;
import misaka.whois.SimpleWhoisProvider;
import misaka.whois.WhoisObject;
import misaka.whois.WhoisProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import sagiri.tools.persistence.entity.DomainInfo;
import sagiri.tools.persistence.entity.DomainInfoQuery;
import sagiri.tools.persistence.mapper.DomainInfoMapper;
import sagiri.tools.service.dto.DomainInfoDTO;
import sagiri.tools.service.dto.DomainInfoQueryDTO;
import sagiri.tools.service.DomainInfoService;

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
        Date registryExpirationTime = whoisObject.getRegistryExpirationTime();
        String registryDomainId = whoisObject.getRegistryDomainId();
        boolean isUnregistered = registryExpirationTime == null && StringUtils.isBlank(registryDomainId);
        boolean needSave = (isUnregistered && saveUnregistered) || (!isUnregistered && saveRegistered);
        log.info("{} >> {}", domainName, needSave);
        if (!needSave) { return; }
        List<String> domainStatuses = whoisObject.getDomainStatuses();
        List<String> nameServers = whoisObject.getNameServers();
        whoisObject.setDomainStatuses(null);
        whoisObject.setNameServers(null);
        DomainInfoDTO domainInfoDTO =
                BeanUtils.beanToBean(whoisObject, DomainInfoDTO.class);
        if (CollectionUtils.isNotEmpty(domainStatuses)) {
            domainInfoDTO.setDomainStatuses(JSON.toJSONString(domainStatuses));
        }
        if (CollectionUtils.isNotEmpty(nameServers)) {
            domainInfoDTO.setNameServers(JSON.toJSONString(nameServers));
        }
        domainInfoDTO.setRawData((String) whoisObject.rawData());
        addDomainInfo(domainInfoDTO);
    }

    @Async
    @Override
    public void asyncAddDomainInfoByName(String domainName, boolean saveRegistered, boolean saveUnregistered) {
        try {
            addDomainInfoByName(domainName, saveRegistered, saveUnregistered);
        }
        catch (Exception e) {
            log.info("An error occurred while processing \"{}\". ", domainName);
        }
    }

    @Override
    public void batchDomainChecking(List<List<String>> table, List<String> suffixes, Long sleepTime) {
        boolean needSleep = sleepTime != null && sleepTime > ZERO;
        Integer length = table.size();
        int[] counter = new int[length];
        int maxIndex = length - ONE;
        int maxCount = ZERO;
        for (int i = ZERO; i < length; i++) {
            counter[i] = ZERO;
        }
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
        List<String> tempList = new ArrayList<>();
        for (String suffix : suffixes) {
            if (StringUtils.isBlank(suffix)) {
                continue;
            }
            if (!suffix.startsWith(DOT)) {
                suffix = DOT + suffix;
            }
            tempList.add(suffix);
        }
        suffixes.clear();
        suffixes.addAll(tempList);
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
                DomainInfoService domainInfoService = ApplicationContextUtils.getBean(DomainInfoService.class);
                domainInfoService.asyncAddDomainInfoByName(domainName, Boolean.FALSE, Boolean.TRUE);
                if (needSleep) { ThreadUtils.sleepQuietly(sleepTime); }
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
    public void batchDomainChecking(Integer length, Boolean existNumber, Long sleepTime, String... suffixes) {
        if (existNumber == null) { existNumber = false; }
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
        List<String> suffixList = new ArrayList<>(Arrays.asList(suffixes));
        batchDomainChecking(table, suffixList, sleepTime);
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
