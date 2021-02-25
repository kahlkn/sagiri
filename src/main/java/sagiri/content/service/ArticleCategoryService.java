package sagiri.content.service;

import sagiri.content.service.dto.ArticleCategoryDTO;

import java.util.List;

/**
 * ArticleCategoryService.
 * @author Kahle
 * @date 2020-11-30T16:23:04.911+0800
 */
public interface ArticleCategoryService {

    /**
     * 增加文章分类
     */
    void add(ArticleCategoryDTO categoryDTO);

    /**
     * 编辑文章分类
     */
    void edit(ArticleCategoryDTO categoryDTO);

    /**
     * 删除文章分类
     */
    void delete(Long categoryId);

    /**
     * 根据文章分类ID查询文章分类
     */
    ArticleCategoryDTO findById(Long categoryId);

    /**
     * 条件查询文章分类的选择列表
     */
    List<ArticleCategoryDTO> selectList(ArticleCategoryDTO categoryDTO);

}
