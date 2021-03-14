package sagiri.content.service.impl;

import artoria.beans.BeanUtils;
import artoria.common.PageResult;
import artoria.common.Paging;
import artoria.data.RecombineUtils;
import artoria.exception.VerifyUtils;
import artoria.user.UserInfo;
import artoria.util.PagingUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sagiri.content.persistence.entity.Article;
import sagiri.content.persistence.mapper.ArticleMapper;
import sagiri.content.service.ArticleCategoryService;
import sagiri.content.service.ArticleRelationService;
import sagiri.content.service.ArticleService;
import sagiri.content.service.dto.ArticleCategoryDTO;
import sagiri.content.service.dto.ArticleDTO;
import sagiri.content.service.dto.ArticleRelationDTO;
import sagiri.system.common.util.UserUtils;

import java.util.Date;
import java.util.List;

import static artoria.common.Constants.*;
import static sagiri.content.common.ContentErrorCode.*;
import static sagiri.system.common.SystemErrorCode.E10110001;

/**
 * ArticleServiceImpl.
 * @author Kahle
 * @date 2020-11-30T16:17:49.778+0800
 */
@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleCategoryService articleCategoryService;
    @Autowired
    private ArticleRelationService articleRelationService;

    @Override
    public void add(ArticleDTO articleDTO) {
        // 参数校验
        VerifyUtils.notNull(articleDTO, E10110001);
        VerifyUtils.notBlank(articleDTO.getTitle(), E12110022);
        VerifyUtils.notNull(articleDTO.getType(), E12110023);
        VerifyUtils.notBlank(articleDTO.getAuthorId(), E12110025);
        VerifyUtils.notBlank(articleDTO.getContent(), E12110026);
        // 默认值处理
        if (articleDTO.getStatus() == null) {
            articleDTO.setStatus(ZERO);
        }
        // 当前登录人和时间
        UserInfo userInfo = UserUtils.getUserInfo();
        String loginId = userInfo.getId();
        Date nowDate = new Date();
        // 转换和处理
        Article article = BeanUtils.beanToBean(articleDTO, Article.class);
//        if (article.getStatus() == ONE) {
            article.setReleaseTime(nowDate);
//        }
        article.setCreatorId(loginId);
        article.setCreateTime(nowDate);
        article.setUpdaterId(loginId);
        article.setUpdateTime(nowDate);
        article.setAliveFlag(ONE);
        // 新增
        int effect = articleMapper.insertSelective(article);
        VerifyUtils.isTrue(effect == ONE, E12110041);
        articleDTO.setId(article.getId());
    }

    @Override
    public void edit(ArticleDTO articleDTO) {
        // 参数校验
        VerifyUtils.notNull(articleDTO, E10110001);
        Long articleId = articleDTO.getId();
        VerifyUtils.notNull(articleId, E12110021);
        // 当前登录人和时间
        UserInfo userInfo = UserUtils.getUserInfo();
        String loginId = userInfo.getId();
        Date nowDate = new Date();
        // 转换和处理
        Article article = BeanUtils.beanToBean(articleDTO, Article.class);
//        if (article.getStatus() == ONE) {
//            article.setReleaseTime(nowDate);
//        }
        article.setType(null);
        article.setUpdaterId(loginId);
        article.setUpdateTime(nowDate);
        // 编辑
        int effect = articleMapper.updateByPrimaryKeySelective(article);
        VerifyUtils.isTrue(effect == ONE, E12110042);
    }

    @Override
    public void delete(Long articleId) {
        // 参数校验
        VerifyUtils.notNull(articleId, E12110021);
        // 删除
        int effect = articleMapper
                .deleteByPrimaryKey(articleId, UserUtils.getUserInfo().getId());
        VerifyUtils.isTrue(effect == ONE, E12110043);
    }

    @Override
    public ArticleDTO detail(Long articleId) {
        ArticleDTO articleDTO = findById(articleId);
        List<ArticleRelationDTO> relationList = articleRelationService.findListByArticleId(articleId, ONE);
        List<Long> categoryIdList =
                RecombineUtils.listToListProperty(relationList, "targetId", Long.class);
        List<ArticleCategoryDTO> categoryList = articleCategoryService.findListByIdList(categoryIdList);
        StringBuilder builder = new StringBuilder();
        for (ArticleCategoryDTO categoryDTO : categoryList) {
            if (categoryDTO == null) { continue; }
            builder.append(categoryDTO.getName()).append(COMMA);
        }
        int length = builder.length();
        if (length > ZERO) { builder.deleteCharAt(length - ONE); }
        articleDTO.setAllowFeed(ZERO);
        articleDTO.setAllowPing(ONE);
        articleDTO.setAllowComment(ZERO);
        articleDTO.setCategories(builder.toString());
        articleDTO.setTags("测试标签1,测试标签2,标签3,可爱,世界第一,元气满满");
        return articleDTO;
    }

    @Override
    public ArticleDTO findById(Long articleId) {
        // 参数校验
        VerifyUtils.notNull(articleId, E12110021);
        // 查询
        Article article = articleMapper.queryByPrimaryKey(articleId);
        return BeanUtils.beanToBean(article, ArticleDTO.class);
    }

    @Override
    public PageResult<List<ArticleDTO>> pagingList(ArticleDTO articleDTO) {
        // 参数预处理
        if (articleDTO == null) { articleDTO = new ArticleDTO(); }
        Paging paging = articleDTO.getPaging();
        if (paging == null) { paging = new Paging(); }
        // 转换和查询
        Article article = BeanUtils.beanToBean(articleDTO, Article.class);
        PagingUtils.startPage(paging);
        List<Article> articleList = articleMapper.querySelective(article);
        return PagingUtils.handleResult(articleList, ArticleDTO.class);
    }

}
