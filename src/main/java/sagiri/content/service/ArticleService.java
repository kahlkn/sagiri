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
    void add(ArticleDTO articleDTO);

    /**
     * 编辑文章
     */
    void edit(ArticleDTO articleDTO);

    /**
     * 删除文章
     */
    void delete(Long articleId);

    /**
     * 文章详情
     */
    ArticleDTO detail(Long articleId);

    /**
     * 根据文章ID查询文章
     */
    ArticleDTO findById(Long articleId);

    /**
     * 分页条件查询文章列表
     */
    PageResult<List<ArticleDTO>> pagingList(ArticleDTO articleDTO);

}
