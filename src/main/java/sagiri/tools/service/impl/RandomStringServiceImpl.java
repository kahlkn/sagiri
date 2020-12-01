package sagiri.tools.service.impl;

import artoria.beans.BeanUtils;
import artoria.util.RandomUtils;
import artoria.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sagiri.tools.service.RandomStringService;
import sagiri.tools.service.dto.RandomStringDTO;
import sagiri.tools.service.dto.RandomStringInputDTO;

import static artoria.common.Constants.*;

@Slf4j
@Service
public class RandomStringServiceImpl implements RandomStringService {

    @Override
    public RandomStringDTO generate(RandomStringInputDTO randomStringInputDTO) {
        RandomStringDTO result =
                BeanUtils.beanToBean(randomStringInputDTO, RandomStringDTO.class);
        Integer length = result.getLength();
        if (length == null || length < SIX) {
            result.setLength(length = SIXTEEN);
        }
        String candidate = result.getCandidate();
        if (StringUtils.isBlank(candidate)) {
            candidate = EMPTY_STRING;
            Boolean upperCase = result.getUpperCase();
            if (upperCase != null && upperCase) {
                candidate += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            }
            Boolean lowerCase = result.getLowerCase();
            if (lowerCase != null && lowerCase) {
                candidate += "abcdefghijklmnopqrstuvwxyz";
            }
            Boolean number = result.getNumber();
            if (number != null && number) {
                candidate += "0123456789";
            }
            String specialty = result.getSpecialty();
            if (StringUtils.isNotBlank(specialty)) {
                candidate += specialty;
            }
        }
        if (StringUtils.isBlank(candidate)) {
            return result;
        }
        char[] chars = candidate.toCharArray();
        result.setResult(
                RandomUtils.nextString(chars, length)
        );
        return result;
    }

}
