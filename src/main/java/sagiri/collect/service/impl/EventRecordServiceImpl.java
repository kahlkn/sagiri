package sagiri.collect.service.impl;

import artoria.beans.BeanUtils;
import artoria.exception.BusinessException;
import artoria.exception.VerifyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sagiri.collect.persistence.entity.EventRecord;
import sagiri.collect.persistence.mapper.EventRecordMapper;
import sagiri.collect.service.EventRecordService;
import sagiri.collect.service.dto.EventRecordDTO;

import java.util.Date;

import static artoria.common.Constants.ONE;
import static artoria.common.Constants.SYSTEM;
import static artoria.common.InternalErrorCode.PARAMETER_IS_REQUIRED;

/**
 * EventRecordServiceImpl.
 * @author Kahle
 * @date 2020-09-15T10:55:27.628+0800
 */
@Slf4j
@Service
public class EventRecordServiceImpl implements EventRecordService {

    @Autowired
    private EventRecordMapper eventRecordMapper;

    @Override
    public void addEventRecord(EventRecordDTO eventRecordDTO) {
        VerifyUtils.notNull(eventRecordDTO, PARAMETER_IS_REQUIRED);
        EventRecord eventRecord =
                BeanUtils.beanToBean(eventRecordDTO, EventRecord.class);
        eventRecord.setCreatorId(SYSTEM);
        eventRecord.setCreateTime(new Date());
        eventRecord.setUpdaterId(SYSTEM);
        eventRecord.setUpdateTime(new Date());
        eventRecord.setAliveFlag(ONE);
        int effect = eventRecordMapper.insertSelective(eventRecord);
        if (effect != ONE) {
            throw new BusinessException("新增事件记录失败！");
        }
    }

}
