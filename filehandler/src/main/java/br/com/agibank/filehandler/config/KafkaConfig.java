package br.com.agibank.filehandler.config;

import br.com.agibank.filehandler.util.Constants;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.event.ListenerContainerIdleEvent;
import org.springframework.kafka.listener.AbstractConsumerSeekAware;
import org.springframework.kafka.listener.ListenerUtils;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Autowired
    MyListener myListener;


    @Bean
    public ConsumerFactory<String, String> getConsumer() {
        Map prop = new HashMap<String, Object>();
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.URI_BOOTSTRAP_SERVER);
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        return new DefaultKafkaConsumerFactory<>(prop);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(getConsumer());
        factory.getContainerProperties().setMessageListener(myListener);
        factory.getContainerProperties().setIdleEventInterval(6000L);
        return factory;
    }

    @Bean
    public static ProducerFactory<String, String> getProducer() {

        Map<String, Object> prop = new HashMap<>();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.URI_BOOTSTRAP_SERVER);
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        ////SAFE PRODUCER
        prop.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        prop.put(ProducerConfig.ACKS_CONFIG, "all");
        prop.put(ProducerConfig.RETRIES_CONFIG, Integer.toString(Integer.MAX_VALUE));
        prop.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5");

        //HIGH THROUGHPUT
        prop.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
        prop.put(ProducerConfig.LINGER_MS_CONFIG, "200");
        prop.put(ProducerConfig.BATCH_SIZE_CONFIG, Integer.toString(32 * 1024));

        return new DefaultKafkaProducerFactory<>(prop);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplateProducer() {
        return new KafkaTemplate<String, String>(getProducer());
    }

}