package sagiri.tools.service.impl;

import artoria.beans.BeanUtils;
import artoria.util.CollectionUtils;
import artoria.common.Paging;
import artoria.util.PagingUtils;
import artoria.common.Input;
import artoria.common.PageResult;
import artoria.common.Result;
import artoria.exception.VerifyUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sagiri.tools.persistence.entity.WebPageAddress;
import sagiri.tools.persistence.mapper.WebPageAddressMapper;
import sagiri.tools.service.WebPageAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static artoria.common.Constants.ONE;
import static artoria.common.Constants.ZERO;
import static artoria.common.InternalErrorCode.PARAMETER_IS_REQUIRED;

/**
 * WebPageAddressServiceImpl.
 * @author Kahle
 * @date 2020-10-10T18:21:40.507+0800
 */
@Slf4j
@Service
public class WebPageAddressServiceImpl implements WebPageAddressService {

    @Autowired
    private WebPageAddressMapper webPageAddressMapper;

    @Override
    public void importBookmarks(String bookmarksHtml) {
        Document document = Jsoup.parse(bookmarksHtml);
        Elements dlElements = document.getElementsByTag("dl");
        Element dlElement = dlElements.get(ONE);

        Elements children = dlElement.children();
        for (Element child : children) {
            System.out.println(child.text());
        }
    }

}
