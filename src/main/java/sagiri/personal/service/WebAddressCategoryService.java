package sagiri.personal.service;

import sagiri.personal.service.dto.WebAddressCategoryDTO;

import java.util.List;

/**
 * WebAddressCategoryService.
 * @author Kahle
 * @date 2021-02-26T11:04:49.714+0800
 */
public interface WebAddressCategoryService {

    /**
     * 增加网址分类
     */
    void add(WebAddressCategoryDTO categoryDTO);

    /**
     * 编辑网址分类
     */
    void edit(WebAddressCategoryDTO categoryDTO);

    /**
     * 删除网址分类
     */
    void delete(Long categoryId);

    /**
     * 根据分类ID查询网址分类
     */
    WebAddressCategoryDTO findById(Long categoryId);

    /**
     * 条件查询网址分类的选择列表
     */
    List<WebAddressCategoryDTO> selectList(WebAddressCategoryDTO categoryDTO);

}
