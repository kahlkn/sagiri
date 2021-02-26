package sagiri.personal.service.impl;

import artoria.beans.BeanUtils;
import artoria.common.PageResult;
import artoria.common.Paging;
import artoria.exception.VerifyUtils;
import artoria.user.UserInfo;
import artoria.util.PagingUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sagiri.personal.persistence.entity.WebAddress;
import sagiri.personal.persistence.mapper.WebAddressMapper;
import sagiri.personal.service.WebAddressService;
import sagiri.personal.service.dto.WebAddressDTO;
import sagiri.system.common.util.UserUtils;

import java.util.Date;
import java.util.List;

import static artoria.common.Constants.ONE;
import static sagiri.personal.common.PersonalErrorCode.*;
import static sagiri.system.common.SystemErrorCode.E10110001;

/**
 * WebPageAddressServiceImpl.
 * @author Kahle
 * @date 2021-02-26T11:04:30.110+0800
 */
@Slf4j
@Service
public class WebAddressServiceImpl implements WebAddressService {

    @Autowired
    private WebAddressMapper webAddressMapper;

    @Override
    public void add(WebAddressDTO webAddressDTO) {
        // 参数校验
        VerifyUtils.notNull(webAddressDTO, E10110001);
        VerifyUtils.notBlank(webAddressDTO.getName(), E12210023);
        VerifyUtils.notBlank(webAddressDTO.getAddress(), E12210024);
        // 当前登录人和时间
        UserInfo userInfo = UserUtils.getUserInfo();
        String loginId = userInfo.getId();
        Date nowDate = new Date();
        // 转换和处理
        WebAddress webAddress =
                BeanUtils.beanToBean(webAddressDTO, WebAddress.class);
        webAddress.setCreatorId(loginId);
        webAddress.setCreateTime(nowDate);
        webAddress.setUpdaterId(loginId);
        webAddress.setUpdateTime(nowDate);
        webAddress.setAliveFlag(ONE);
        // 新增
        int effect = webAddressMapper.insertSelective(webAddress);
        VerifyUtils.isTrue(effect == ONE, E12210041);
    }

    @Override
    public void edit(WebAddressDTO webAddressDTO) {
        // 参数校验
        VerifyUtils.notNull(webAddressDTO, E10110001);
        VerifyUtils.notNull(webAddressDTO.getId(), E12210021);
        // 当前登录人和时间
        UserInfo userInfo = UserUtils.getUserInfo();
        String loginId = userInfo.getId();
        Date nowDate = new Date();
        // 转换和处理
        WebAddress webAddress =
                BeanUtils.beanToBean(webAddressDTO, WebAddress.class);
        webAddress.setUpdaterId(loginId);
        webAddress.setUpdateTime(nowDate);
        // 编辑
        int effect = webAddressMapper.updateByPrimaryKeySelective(webAddress);
        VerifyUtils.isTrue(effect == ONE, E12210042);
    }

    @Override
    public void delete(Long webAddressId) {
        // 参数校验
        VerifyUtils.notNull(webAddressId, E12210021);
        // 删除
        int effect = webAddressMapper
                .deleteByPrimaryKey(webAddressId, UserUtils.getUserInfo().getId());
        VerifyUtils.isTrue(effect == ONE, E12210043);
    }

    @Override
    public WebAddressDTO findById(Long webAddressId) {
        // 参数校验
        VerifyUtils.notNull(webAddressId, E12210021);
        // 查询
        WebAddress webPageAddress = webAddressMapper.queryByPrimaryKey(webAddressId);
        return BeanUtils.beanToBean(webPageAddress, WebAddressDTO.class);
    }

    @Override
    public PageResult<List<WebAddressDTO>> pagingList(WebAddressDTO webAddressDTO) {
        // 参数预处理
        if (webAddressDTO == null) { webAddressDTO = new WebAddressDTO(); }
        Paging paging = webAddressDTO.getPaging();
        if (paging == null) { paging = new Paging(); }
        // 转换和查询
        WebAddress webAddress =
                BeanUtils.beanToBean(webAddressDTO, WebAddress.class);
        PagingUtils.startPage(paging);
        List<WebAddress> addressList = webAddressMapper.querySelective(webAddress);
        return PagingUtils.handleResult(addressList, WebAddressDTO.class);
    }

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
