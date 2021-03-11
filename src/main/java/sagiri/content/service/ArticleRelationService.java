package sagiri.content.service;

import sagiri.content.service.dto.ArticleRelationDTO;

import java.util.List;

/**
 * ArticleRelationService.
 * @author Kahle
 * @date 2021-03-05T10:23:44.708+0800
 */
public interface ArticleRelationService {

    /**
     * 批量增加文章关系
     */
    void batchAdd(Long articleId, Integer type, List<Long> targetIdList);

    /**
     * 批量编辑文章关系
     */
    void batchEdit(Long articleId, Integer type, List<Long> targetIdList);

    /**
     * 批量删除文章关系
     */
    int batchDelete(Long articleId, Integer type, List<Long> targetIdList);

    /**
     * 根据 文章ID 和 文章关系类型 查找 文章关系列表
     */
    List<ArticleRelationDTO> findListByArticleId(Long articleId, Integer type);

    /**
     * 根据 文章ID集合 和 文章关系类型 批量查找 文章关系列表
     */
    List<ArticleRelationDTO> findListByArticleIdList(List<Long> articleIdList, Integer type);

}
