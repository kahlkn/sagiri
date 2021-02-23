package sagiri.content.service;

import artoria.common.PageResult;
import sagiri.content.service.dto.ArticleDTO;

import java.util.List;

/**
 * ArticleService.
 * @author Kahle
 * @date 2020-11-30T16:17:49.778+0800
 */
public interface ArticleService {

    /**
     * 增加文章
     */
    void addArticle(ArticleDTO articleDTO);

    /**
     * 编辑文章
     */
    void editArticle(ArticleDTO articleDTO);

    /**
     * 删除文章
     */
    void deleteArticle(ArticleDTO articleDTO);

    /**
     * 编辑文章
     */
    PageResult<List<ArticleDTO>> queryArticleList(ArticleDTO articleDTO);

}
