package sagiri.tools.service.impl;

import artoria.beans.BeanUtils;
import artoria.util.CollectionUtils;
import artoria.common.Paging;
import artoria.util.PagingUtils;
import artoria.common.Input;
import artoria.common.PageResult;
import artoria.common.Result;
import artoria.exception.VerifyUtils;
import sagiri.tools.persistence.entity.WebPageAddressCategory;
import sagiri.tools.persistence.mapper.WebPageAddressCategoryMapper;
import sagiri.tools.service.WebPageAddressCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static artoria.common.InternalErrorCode.PARAMETER_IS_REQUIRED;

/**
 * WebPageAddressCategoryServiceImpl.
 * @author Kahle
 * @date 2020-10-10T18:22:02.946+0800
 */
@Slf4j
@Service
public class WebPageAddressCategoryServiceImpl implements WebPageAddressCategoryService {

    @Autowired
    private WebPageAddressCategoryMapper webPageAddressCategoryMapper;

}
