package sagiri.content.service.impl;

import artoria.beans.BeanUtils;
import artoria.exception.VerifyUtils;
import artoria.user.UserInfo;
import artoria.util.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sagiri.content.persistence.entity.ArticleCategory;
import sagiri.content.persistence.mapper.ArticleCategoryMapper;
import sagiri.content.service.ArticleCategoryService;
import sagiri.content.service.dto.ArticleCategoryDTO;
import sagiri.system.common.util.UserUtils;

import java.util.Date;
import java.util.List;

import static artoria.common.Constants.ONE;
import static sagiri.content.common.ContentErrorCode.*;
import static sagiri.system.common.SystemErrorCode.E10110001;

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

    @Override
    public void add(ArticleCategoryDTO categoryDTO) {
        // 参数校验
        VerifyUtils.notNull(categoryDTO, E10110001);
        VerifyUtils.notBlank(categoryDTO.getDescription(), E12110123);
        VerifyUtils.notBlank(categoryDTO.getName(), E12110122);
        // 当前登录人和时间
        UserInfo userInfo = UserUtils.getUserInfo();
        String loginId = userInfo.getId();
        Date nowDate = new Date();
        // 转换和处理
        ArticleCategory articleCategory =
                BeanUtils.beanToBean(categoryDTO, ArticleCategory.class);
        articleCategory.setCreatorId(loginId);
        articleCategory.setCreateTime(nowDate);
        articleCategory.setUpdaterId(loginId);
        articleCategory.setUpdateTime(nowDate);
        articleCategory.setAliveFlag(ONE);
        // 新增
        int effect = articleCategoryMapper.insertSelective(articleCategory);
        VerifyUtils.isTrue(effect == ONE, E12110141);
    }

    @Override
    public void edit(ArticleCategoryDTO categoryDTO) {
        // 参数校验
        VerifyUtils.notNull(categoryDTO, E10110001);
        VerifyUtils.notNull(categoryDTO.getId(), E12110121);
        // 当前登录人和时间
        UserInfo userInfo = UserUtils.getUserInfo();
        String loginId = userInfo.getId();
        Date nowDate = new Date();
        // 转换和处理
        ArticleCategory articleCategory =
                BeanUtils.beanToBean(categoryDTO, ArticleCategory.class);
        articleCategory.setUpdaterId(loginId);
        articleCategory.setUpdateTime(nowDate);
        // 编辑
        int effect = articleCategoryMapper.updateByPrimaryKeySelective(articleCategory);
        VerifyUtils.isTrue(effect == ONE, E12110142);
    }

    @Override
    public void delete(Long categoryId) {
        // 参数校验
        VerifyUtils.notNull(categoryId, E12110121);
        // 删除
        int effect = articleCategoryMapper
                .deleteByPrimaryKey(categoryId, UserUtils.getUserInfo().getId());
        VerifyUtils.isTrue(effect == ONE, E12110143);
    }

    @Override
    public ArticleCategoryDTO findById(Long categoryId) {
        // 参数校验
        VerifyUtils.notNull(categoryId, E12110121);
        // 查询
        ArticleCategory articleCategory = articleCategoryMapper.queryByPrimaryKey(categoryId);
        return BeanUtils.beanToBean(articleCategory, ArticleCategoryDTO.class);
    }

    @Override
    public List<ArticleCategoryDTO> findListByIdList(List<Long> categoryIdList) {
        // 参数校验
        VerifyUtils.notEmpty(categoryIdList, E12110121);
        // 查询
        categoryIdList = CollectionUtils.removeDuplicate(categoryIdList);
        List<ArticleCategory> categoryList = articleCategoryMapper.queryByPrimaryKeyList(categoryIdList);
        return BeanUtils.beanToBeanInList(categoryList, ArticleCategoryDTO.class);
    }

    @Override
    public List<ArticleCategoryDTO> selectList(ArticleCategoryDTO categoryDTO) {
        // 参数预处理
        if (categoryDTO == null) { categoryDTO = new ArticleCategoryDTO(); }
        // 转换和查询
        ArticleCategory articleCategory = BeanUtils.beanToBean(categoryDTO, ArticleCategory.class);
        List<ArticleCategory> categoryList = articleCategoryMapper.findList(articleCategory);
        return BeanUtils.beanToBeanInList(categoryList, ArticleCategoryDTO.class);
    }

}
