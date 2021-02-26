package sagiri.personal.service.impl;

import artoria.beans.BeanUtils;
import artoria.exception.VerifyUtils;
import artoria.user.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sagiri.personal.persistence.entity.WebAddressCategory;
import sagiri.personal.persistence.mapper.WebAddressCategoryMapper;
import sagiri.personal.service.WebAddressCategoryService;
import sagiri.personal.service.dto.WebAddressCategoryDTO;
import sagiri.system.common.util.UserUtils;

import java.util.Date;
import java.util.List;

import static artoria.common.Constants.ONE;
import static sagiri.personal.common.PersonalErrorCode.*;
import static sagiri.system.common.SystemErrorCode.E10110001;

/**
 * WebAddressCategoryServiceImpl.
 * @author Kahle
 * @date 2021-02-26T11:04:49.714+0800
 */
@Slf4j
@Service
public class WebAddressCategoryServiceImpl implements WebAddressCategoryService {

    @Autowired
    private WebAddressCategoryMapper webAddressCategoryMapper;

    @Override
    public void add(WebAddressCategoryDTO categoryDTO) {
        // 参数校验
        VerifyUtils.notNull(categoryDTO, E10110001);
        VerifyUtils.notBlank(categoryDTO.getName(), E12210122);
        // 当前登录人和时间
        UserInfo userInfo = UserUtils.getUserInfo();
        String loginId = userInfo.getId();
        Date nowDate = new Date();
        // 转换和处理
        WebAddressCategory webAddressCategory =
                BeanUtils.beanToBean(categoryDTO, WebAddressCategory.class);
        webAddressCategory.setCreatorId(loginId);
        webAddressCategory.setCreateTime(nowDate);
        webAddressCategory.setUpdaterId(loginId);
        webAddressCategory.setUpdateTime(nowDate);
        webAddressCategory.setAliveFlag(ONE);
        // 新增
        int effect = webAddressCategoryMapper.insertSelective(webAddressCategory);
        VerifyUtils.isTrue(effect == ONE, E12210141);
    }

    @Override
    public void edit(WebAddressCategoryDTO categoryDTO) {
        // 参数校验
        VerifyUtils.notNull(categoryDTO, E10110001);
        VerifyUtils.notNull(categoryDTO.getId(), E12210121);
        // 当前登录人和时间
        UserInfo userInfo = UserUtils.getUserInfo();
        String loginId = userInfo.getId();
        Date nowDate = new Date();
        // 转换和处理
        WebAddressCategory webAddressCategory =
                BeanUtils.beanToBean(categoryDTO, WebAddressCategory.class);
        webAddressCategory.setUpdaterId(loginId);
        webAddressCategory.setUpdateTime(nowDate);
        // 编辑
        int effect = webAddressCategoryMapper
                .updateByPrimaryKeySelective(webAddressCategory);
        VerifyUtils.isTrue(effect == ONE, E12210142);
    }

    @Override
    public void delete(Long categoryId) {
        // 参数校验
        VerifyUtils.notNull(categoryId, E12210121);
        // 删除
        int effect = webAddressCategoryMapper
                .deleteByPrimaryKey(categoryId, UserUtils.getUserInfo().getId());
        VerifyUtils.isTrue(effect == ONE, E12210143);
    }

    @Override
    public WebAddressCategoryDTO findById(Long categoryId) {
        // 参数校验
        VerifyUtils.notNull(categoryId, E12210121);
        // 查询
        WebAddressCategory webAddressCategory =
                webAddressCategoryMapper.queryByPrimaryKey(categoryId);
        return BeanUtils.beanToBean(webAddressCategory, WebAddressCategoryDTO.class);
    }

    @Override
    public List<WebAddressCategoryDTO> selectList(WebAddressCategoryDTO categoryDTO) {
        // 参数预处理
        if (categoryDTO == null) { categoryDTO = new WebAddressCategoryDTO(); }
        // 转换和查询
        WebAddressCategory categoryQuery =
                BeanUtils.beanToBean(categoryDTO, WebAddressCategory.class);
        List<WebAddressCategory> categoryList =
                webAddressCategoryMapper.querySelective(categoryQuery);
        return BeanUtils.beanToBeanInList(categoryList, WebAddressCategoryDTO.class);
    }

}
