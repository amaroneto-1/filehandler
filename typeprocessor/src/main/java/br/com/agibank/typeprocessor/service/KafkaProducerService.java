package br.com.agibank.typeprocessor.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Autowired private KafkaTemplate<String, String> producer;

    public void sendToTopic(String topic,String key, String item) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, item);
        producer.send(record);
    }
}
