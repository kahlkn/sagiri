package sagiri.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import sagiri.service.AsyncService;
import sagiri.service.DomainInfoService;

@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {

    @Autowired
    private DomainInfoService domainInfoService;

    @Async
    @Override
    public void addDomainInfoByName(String domainName, boolean saveRegistered, boolean saveUnregistered) {

        domainInfoService.addDomainInfoByName(domainName, saveRegistered, saveUnregistered);
    }

}
