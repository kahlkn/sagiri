package sagiri.service;

import artoria.common.PageResult;
import sagiri.pojo.dto.DomainInfoDTO;
import sagiri.pojo.dto.DomainInfoQueryDTO;

import java.util.List;

/**
 * DomainInfoService.
 * @author Kahle
 * @date 2019-11-08T09:48:18.613+0800
 */
public interface DomainInfoService {

    void initialize(String domainSuffix, Integer length, Boolean existNumber);

    void updateDomain(String domainName);

    void updateAllDomain();

    PageResult<List<DomainInfoDTO>> queryDomainInfoList(DomainInfoQueryDTO domainInfoQueryDTO);

}
