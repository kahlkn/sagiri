package sagiri.service;

/**
 * AsyncService.
 * @author Kahle
 */
public interface AsyncService {

    void addDomainInfoByName(String domainName, boolean saveRegistered, boolean saveUnregistered);

}
