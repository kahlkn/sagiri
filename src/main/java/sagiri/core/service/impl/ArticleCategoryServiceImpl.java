package sagiri.core.service.impl;

import artoria.beans.BeanUtils;
import artoria.util.CollectionUtils;
import artoria.common.Paging;
import artoria.util.PagingUtils;
import artoria.common.Input;
import artoria.common.PageResult;
import artoria.common.Result;
import artoria.exception.VerifyUtils;
import sagiri.core.persistence.entity.ArticleCategory;
import sagiri.core.persistence.mapper.ArticleCategoryMapper;
import sagiri.core.service.ArticleCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static artoria.common.InternalErrorCode.PARAMETER_IS_REQUIRED;

/**
 * ArticleCategoryServiceImpl.
 * @author Kahle
 * @date 2020-11-30T16:23:04.911+0800
 */
@Slf4j
@Service
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;

}
