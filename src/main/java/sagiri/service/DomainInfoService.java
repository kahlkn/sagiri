package sagiri.service;

/**
 * DomainInfoService.
 * @author Kahle
 * @date 2019-11-08T09:48:18.613+0800
 */
public interface DomainInfoService {

    void initialize(String domainSuffix, Integer length, Boolean existNumber);

    void updateDomain(String domainName);

    void updateAllDomain();

}
