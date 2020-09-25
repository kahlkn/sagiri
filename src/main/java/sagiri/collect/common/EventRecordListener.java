package sagiri.collect.common;

import artoria.beans.BeanUtils;
import artoria.message.Message;
import artoria.message.TargetedMessageListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sagiri.collect.service.EventRecordService;
import sagiri.collect.service.dto.EventRecordDTO;

import java.util.Map;

@Slf4j
@Component
public class EventRecordListener implements TargetedMessageListener {

    @Autowired
    private EventRecordService eventRecordService;

    @Override
    public String getDestination() {

        return "event_record";
    }

    @Override
    public Map<String, Object> getParameters() {

        return null;
    }

    @Override
    public void onMessage(Message message) {
        try {
            Object body = message.getBody();
            EventRecordDTO eventRecordDTO =
                    BeanUtils.beanToBean(body, EventRecordDTO.class);
            Map<String, Object> properties = eventRecordDTO.getProperties();
            String clientUserAgent = (String) properties.get("clientUserAgent");

            eventRecordDTO.setClientId((String) properties.get("clientId"));
            eventRecordDTO.setClientAppId((String) properties.get("clientAppId"));
            eventRecordDTO.setClientDeviceId((String) properties.get("clientDeviceId"));
            eventRecordDTO.setClientUserAgent(clientUserAgent);
            eventRecordDTO.setClientNetAddress((String) properties.get("clientNetAddress"));
//            eventRecordDTO.setClientDeviceName();
            eventRecordDTO.setInterfaceId((String) properties.get("interfaceId"));
            eventRecordDTO.setServerId((String) properties.get("serverId"));
            eventRecordDTO.setServerAppId((String) properties.get("serverAppId"));
            properties.remove("clientAppId");
            properties.remove("clientNetAddress");
            properties.remove("clientUserAgent");
            properties.remove("interfaceId");
            properties.remove("serverId");
            properties.remove("serverAppId");
            eventRecordDTO.setPropertiesJson(JSON.toJSONString(properties));
            eventRecordService.addEventRecord(eventRecordDTO);
        }
        catch (Exception e) {
            log.error("Save event record error. ", e);
        }
    }

}
