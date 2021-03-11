package sagiri.content.service.impl;

import artoria.beans.BeanUtils;
import artoria.data.RecombineUtils;
import artoria.exception.VerifyUtils;
import artoria.user.UserInfo;
import artoria.util.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sagiri.content.persistence.entity.ArticleRelation;
import sagiri.content.persistence.mapper.ArticleRelationMapper;
import sagiri.content.service.ArticleRelationService;
import sagiri.content.service.dto.ArticleRelationDTO;
import sagiri.system.common.util.UserUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static artoria.common.Constants.ONE;
import static artoria.common.Constants.ZERO;
import static java.util.Collections.singletonList;
import static sagiri.content.common.ContentErrorCode.*;

/**
 * ArticleRelationServiceImpl.
 * @author Kahle
 * @date 2021-03-05T10:23:44.708+0800
 */
@Slf4j
@Service
public class ArticleRelationServiceImpl implements ArticleRelationService {

    @Autowired
    private ArticleRelationMapper articleRelationMapper;

    @Override
    public void batchAdd(Long articleId, Integer type, List<Long> targetIdList) {
        // 参数校验和处理
        VerifyUtils.notEmpty(targetIdList, E12110222);
        VerifyUtils.notNull(articleId, E12110021);
        VerifyUtils.notNull(type, E12110221);
        targetIdList = CollectionUtils.removeDuplicate(targetIdList);
        // 当前登录人和时间
        UserInfo userInfo = UserUtils.getUserInfo();
        String loginId = userInfo.getId();
        Date nowDate = new Date();
        // 对象构建
        List<ArticleRelation> list = new ArrayList<>();
        for (Long targetId : targetIdList) {
            if (targetId == null) { continue; }
            ArticleRelation relation = new ArticleRelation();
            relation.setArticleId(articleId);
            relation.setType(type);
            relation.setTargetId(targetId);
            relation.setCreatorId(loginId);
            relation.setCreateTime(nowDate);
            relation.setUpdaterId(loginId);
            relation.setUpdateTime(nowDate);
            relation.setAliveFlag(ONE);
            list.add(relation);
        }
        if (CollectionUtils.isEmpty(list)) { return; }
        // 新增
        int effect = articleRelationMapper.insertBatch(list);
        VerifyUtils.isTrue(effect == list.size(), E12110241);
    }

    @Override
    public void batchEdit(Long articleId, Integer type, List<Long> targetIdList) {
        // 参数校验和处理
        VerifyUtils.notNull(targetIdList, E12110222);
        VerifyUtils.notNull(articleId, E12110021);
        VerifyUtils.notNull(type, E12110221);
        targetIdList = CollectionUtils.removeDuplicate(targetIdList);
        // 查询旧的
        List<ArticleRelationDTO> relationList = findListByArticleId(articleId, type);
        List<Long> oldTargetIdList =
                RecombineUtils.listToListProperty(relationList, "targetId", Long.class);
        // 新增
        List<Long> willAdd = new ArrayList<>(targetIdList);
        willAdd.removeAll(oldTargetIdList);
        if (CollectionUtils.isNotEmpty(willAdd)) {
            batchAdd(articleId, type, willAdd);
        }
        // 删除
        List<Long> willDelete = new ArrayList<>(oldTargetIdList);
        willAdd.removeAll(targetIdList);
        if (CollectionUtils.isNotEmpty(willDelete)) {
            batchDelete(articleId, type, willDelete);
        }
    }

    @Override
    public int batchDelete(Long articleId, Integer type, List<Long> targetIdList) {
        // 参数校验和处理
        VerifyUtils.notNull(articleId, E12110021);
        if (type != null && type < ZERO) { type = ZERO; }
        if (CollectionUtils.isNotEmpty(targetIdList)) {
            CollectionUtils.removeDuplicate(targetIdList);
        } else { targetIdList = null; }
        // 当前登录人
        UserInfo userInfo = UserUtils.getUserInfo();
        String loginId = userInfo.getId();
        // 删除
        return articleRelationMapper.batchDelete(articleId, type, targetIdList, loginId);
    }

    @Override
    public List<ArticleRelationDTO> findListByArticleId(Long articleId, Integer type) {
        VerifyUtils.notNull(articleId, E12110021);
        return findListByArticleIdList(singletonList(articleId), type);
    }

    @Override
    public List<ArticleRelationDTO> findListByArticleIdList(List<Long> articleIdList, Integer type) {
        VerifyUtils.notEmpty(articleIdList, E12110021);
        if (type != null && type < ZERO) { type = ZERO; }
        articleIdList = CollectionUtils.removeDuplicate(articleIdList);
        List<ArticleRelation> list = articleRelationMapper.findListByArticleIdList(articleIdList, type);
        return BeanUtils.beanToBeanInList(list, ArticleRelationDTO.class);
    }

}
