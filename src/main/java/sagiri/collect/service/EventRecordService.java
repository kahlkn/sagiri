package sagiri.collect.service;

import sagiri.collect.service.dto.EventRecordDTO;

/**
 * EventRecordService.
 * @author Kahle
 * @date 2020-09-15T10:55:27.628+0800
 */
public interface EventRecordService {

    /**
     * 增加事件记录
     */
    void addEventRecord(EventRecordDTO eventRecordDTO);

}
