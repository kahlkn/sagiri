package sagiri.content.service.impl;

import artoria.beans.BeanUtils;
import artoria.common.PageResult;
import artoria.common.Paging;
import artoria.exception.VerifyUtils;
import artoria.identifier.IdentifierUtils;
import artoria.user.UserInfo;
import artoria.util.PagingUtils;
import artoria.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sagiri.content.persistence.entity.Article;
import sagiri.content.persistence.mapper.ArticleMapper;
import sagiri.content.service.ArticleService;
import sagiri.content.service.dto.ArticleDTO;
import sagiri.system.common.UserUtils;

import java.util.Date;
import java.util.List;

import static artoria.common.Constants.*;
import static artoria.common.InternalErrorCode.INTERNAL_SERVER_BUSY;
import static artoria.common.InternalErrorCode.PARAMETER_IS_REQUIRED;

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

    @Override
    public void addArticle(ArticleDTO articleDTO) {
        VerifyUtils.notNull(articleDTO, PARAMETER_IS_REQUIRED);
        VerifyUtils.notBlank(articleDTO.getTitle(), PARAMETER_IS_REQUIRED);
        VerifyUtils.notNull(articleDTO.getType(), PARAMETER_IS_REQUIRED);
        VerifyUtils.notBlank(articleDTO.getCategory(), PARAMETER_IS_REQUIRED);
        VerifyUtils.notBlank(articleDTO.getAuthorId(), PARAMETER_IS_REQUIRED);
        VerifyUtils.notBlank(articleDTO.getAuthorName(), PARAMETER_IS_REQUIRED);
        VerifyUtils.notBlank(articleDTO.getContent(), PARAMETER_IS_REQUIRED);
        if (StringUtils.isBlank(articleDTO.getId())) {
            articleDTO.setId(IdentifierUtils.nextStringIdentifier());
        }
        if (articleDTO.getStatus() == null) {
            articleDTO.setStatus(ZERO);
        }
        //
        UserInfo userInfo = UserUtils.getUserInfo();
        String loginId = userInfo.getId();
        Date nowDate = new Date();
        //
        Article article = BeanUtils.beanToBean(articleDTO, Article.class);
        if (article.getStatus() == ONE) {
            article.setReleaseTime(nowDate);
        }
        article.setCreatorId(loginId);
        article.setCreateTime(nowDate);
        article.setUpdaterId(loginId);
        article.setUpdateTime(nowDate);
        article.setAliveFlag(ONE);
        //
        int effect = articleMapper.insertSelective(article);
        VerifyUtils.isTrue(effect == ONE, INTERNAL_SERVER_BUSY);
    }

    @Override
    public void editArticle(ArticleDTO articleDTO) {
        VerifyUtils.notNull(articleDTO, PARAMETER_IS_REQUIRED);
        VerifyUtils.notBlank(articleDTO.getId(), PARAMETER_IS_REQUIRED);
        //
        Date nowDate = new Date();
        //
        Article article = BeanUtils.beanToBean(articleDTO, Article.class);
        if (article.getStatus() == ONE) {
            article.setReleaseTime(nowDate);
        }
        article.setUpdaterId(SYSTEM);
        article.setUpdateTime(nowDate);
        //
        article.setNumberId(null);
        article.setType(null);
        int effect = articleMapper.updateByIdSelective(article);
        VerifyUtils.isTrue(effect == ONE, INTERNAL_SERVER_BUSY);
    }

    @Override
    public void deleteArticle(ArticleDTO articleDTO) {
        VerifyUtils.notNull(articleDTO, PARAMETER_IS_REQUIRED);
        VerifyUtils.notBlank(articleDTO.getId(), PARAMETER_IS_REQUIRED);
        //
        Article article = BeanUtils.beanToBean(articleDTO, Article.class);
        article.setUpdaterId(SYSTEM);
        int effect = articleMapper.deleteSelective(article);
        VerifyUtils.isTrue(effect == ONE, INTERNAL_SERVER_BUSY);
    }

    @Override
    public PageResult<List<ArticleDTO>> queryArticleList(ArticleDTO articleDTO) {
        if (articleDTO == null) { articleDTO = new ArticleDTO(); }
        Paging paging = articleDTO.getPaging();
        if (paging == null) { paging = new Paging(); }
        Article article = BeanUtils.beanToBean(articleDTO, Article.class);
        PagingUtils.startPage(paging);
        List<Article> articleList = articleMapper.querySelective(article);
        return PagingUtils.handleResult(articleList, ArticleDTO.class);
    }

}
