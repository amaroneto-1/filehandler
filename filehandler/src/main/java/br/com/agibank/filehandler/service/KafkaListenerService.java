package br.com.agibank.filehandler.service;

import br.com.agibank.filehandler.config.KafkaConstants;
import br.com.agibank.filehandler.dto.ResultDTO;
import br.com.agibank.filehandler.util.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaListenerService {
    @Autowired private CoordinatorResultsService coordinatorResultsService;

    @KafkaListener(topics = KafkaConstants.RESULTS_TOPIC, groupId = KafkaConstants.GROUP_ID_RESULTS)
    public void consume(@Payload String message, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            List<ResultDTO> resultDTO = objectMapper.readValue(message, objectMapper.getTypeFactory().constructCollectionType(List.class, ResultDTO.class));
            this.coordinatorResultsService.addResult(resultDTO);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = KafkaConstants.LOG_TOPIC, groupId = KafkaConstants.GROUP_ID_RESULTS)
    public void consumelog(@Payload String message, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        System.out.println(message);
    }

}
