package br.com.agibank.typeprocessor.service;


import br.com.agibank.typeprocessor.config.KafkaConstants;
import br.com.agibank.typeprocessor.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    @Autowired private MessageProcessor messageProcessor;

    @KafkaListener(topics = KafkaConstants.FILE_TOPIC, groupId = KafkaConstants.GROUP_ID_TYPE_PROCESSOR)
    public void consume(@Payload String message, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        messageProcessor.processMessage(message,key);
    }
}