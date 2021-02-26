package sagiri.personal.service;

import artoria.common.PageResult;
import sagiri.personal.service.dto.WebAddressDTO;

import java.util.List;

/**
 * WebAddressService.
 * @author Kahle
 * @date 2021-02-26T11:04:30.110+0800
 */
public interface WebAddressService {

    /**
     * 增加网址
     */
    void add(WebAddressDTO webAddressDTO);

    /**
     * 编辑网址
     */
    void edit(WebAddressDTO webAddressDTO);

    /**
     * 删除网址
     */
    void delete(Long webAddressId);

    /**
     * 根据网址ID查询网址
     */
    WebAddressDTO findById(Long webAddressId);

    /**
     * 分页条件查询网址列表
     */
    PageResult<List<WebAddressDTO>> pagingList(WebAddressDTO webAddressDTO);

    void importBookmarks(String bookmarksHtml);

}
