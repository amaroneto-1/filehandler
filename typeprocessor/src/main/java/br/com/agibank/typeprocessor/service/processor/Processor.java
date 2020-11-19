package br.com.agibank.typeprocessor.service.processor;

import br.com.agibank.typeprocessor.dto.ResultadoDTO;
import br.com.agibank.typeprocessor.model.Cliente;
import br.com.agibank.typeprocessor.model.Entity;
import br.com.agibank.typeprocessor.service.result.ResultService;
import br.com.agibank.typeprocessor.util.UtilService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface Processor<T extends Entity> {
    public abstract void process(T t, String key);
}
