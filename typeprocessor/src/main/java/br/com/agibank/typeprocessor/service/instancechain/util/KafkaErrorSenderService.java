package br.com.agibank.typeprocessor.service.instancechain.util;

import br.com.agibank.typeprocessor.config.KafkaConstants;
import br.com.agibank.typeprocessor.dto.ErrorDTO;
import br.com.agibank.typeprocessor.service.KafkaProducerService;
import br.com.agibank.typeprocessor.util.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaErrorSenderService {
    @Autowired private UtilService utilService;
    @Autowired private KafkaProducerService kafkaProducerService;

    public void sendErrorToTopic(String line, String key, Exception e) {
        try{
            kafkaProducerService.sendToTopic(KafkaConstants.LOG_TOPIC, key, utilService.writeAsString(new ErrorDTO(line, e.getMessage())));
        }catch(Exception e2){e2.printStackTrace();}
    }
}
