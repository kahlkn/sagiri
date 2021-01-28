package sagiri.content.service.impl;

import sagiri.content.persistence.mapper.ArticleCategoryMapper;
import sagiri.content.service.ArticleCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
