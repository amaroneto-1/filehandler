package br.com.agibank.typeprocessor.service.processor;

import br.com.agibank.typeprocessor.config.KafkaConstants;
import br.com.agibank.typeprocessor.dto.ResultadoDTO;
import br.com.agibank.typeprocessor.model.Cliente;
import br.com.agibank.typeprocessor.service.KafkaProducerService;
import br.com.agibank.typeprocessor.service.result.ClientResultService;
import br.com.agibank.typeprocessor.service.result.ResultService;
import br.com.agibank.typeprocessor.util.Constants;
import br.com.agibank.typeprocessor.util.UtilService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ClientProcessor implements Processor<Cliente> {
    @Autowired private KafkaProducerService kafkaProducerService;
    @Autowired private UtilService utilService;
    @Autowired private ClientResultService clientResultService;

    @Override
    public void process(Cliente cliente, String key) {
        try {
            clientResultService.verifyKey(key);
            clientResultService.addElement(cliente);
            kafkaProducerService.sendToTopic(KafkaConstants.RESULTS_TOPIC, String.valueOf(key), utilService.writeAsString(clientResultService.getResult()));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
