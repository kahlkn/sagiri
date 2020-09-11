package sagiri.tool.service;

import sagiri.tool.service.dto.RandomStringDTO;
import sagiri.tool.service.dto.RandomStringInputDTO;

public interface RandomStringService {

    RandomStringDTO generate(RandomStringInputDTO randomStringInputDTO);

}
