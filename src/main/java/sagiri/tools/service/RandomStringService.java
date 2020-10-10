package sagiri.tools.service;

import sagiri.tools.service.dto.RandomStringDTO;
import sagiri.tools.service.dto.RandomStringInputDTO;

public interface RandomStringService {

    RandomStringDTO generate(RandomStringInputDTO randomStringInputDTO);

}
