package sagiri.service;

import sagiri.pojo.dto.RandomStringDTO;
import sagiri.pojo.dto.RandomStringInputDTO;

public interface RandomStringService {

    RandomStringDTO generate(RandomStringInputDTO randomStringInputDTO);

}
