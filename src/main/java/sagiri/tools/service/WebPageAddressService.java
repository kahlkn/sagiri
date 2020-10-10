package sagiri.tools.service;

import artoria.common.Input;
import artoria.common.PageResult;
import artoria.common.Result;
import sagiri.tools.persistence.entity.WebPageAddress;

import java.util.List;

/**
 * WebPageAddressService.
 * @author Kahle
 * @date 2020-10-10T18:21:40.507+0800
 */
public interface WebPageAddressService {

    void importBookmarks(String bookmarksHtml);

}
