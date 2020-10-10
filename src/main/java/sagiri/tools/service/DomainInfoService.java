package sagiri.tools.service;

import artoria.common.PageResult;
import sagiri.tools.service.dto.DomainInfoDTO;
import sagiri.tools.service.dto.DomainInfoQueryDTO;

import java.util.List;

/**
 * DomainInfoService.
 * @author Kahle
 * @date 2019-11-08T09:48:18.613+0800
 */
public interface DomainInfoService {

    /**
     * 增加一条域名信息.
     */
    void addDomainInfo(DomainInfoDTO domainInfoDTO);

    /**
     * 编辑一条域名信息.
     */
    void editDomainInfo(DomainInfoDTO domainInfoDTO);

    void addDomainInfoByName(String domainName, boolean saveRegistered, boolean saveUnregistered);

    void asyncAddDomainInfoByName(String domainName, boolean saveRegistered, boolean saveUnregistered);

    void batchDomainChecking(List<List<String>> table, List<String> suffixes, Long sleepTime);

    void batchDomainChecking(Integer length, Boolean existNumber, Long sleepTime, String... suffixes);

    PageResult<List<DomainInfoDTO>> queryDomainInfoList(DomainInfoQueryDTO domainInfoQueryDTO);

}
