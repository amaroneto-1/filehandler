package br.com.agibank.typeprocessor.service.processor;

import br.com.agibank.typeprocessor.config.KafkaConstants;
import br.com.agibank.typeprocessor.dto.ResultadoDTO;
import br.com.agibank.typeprocessor.model.Cliente;
import br.com.agibank.typeprocessor.model.Vendedor;
import br.com.agibank.typeprocessor.service.KafkaProducerService;
import br.com.agibank.typeprocessor.service.result.ClientResultService;
import br.com.agibank.typeprocessor.service.result.SellerResultService;
import br.com.agibank.typeprocessor.util.Constants;
import br.com.agibank.typeprocessor.util.UtilService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class SellerProcessor implements Processor<Vendedor> {

    @Autowired private KafkaProducerService kafkaProducerService;
    @Autowired private UtilService utilService;
    @Autowired private SellerResultService sellerResultService;

    @Override
    public void process(Vendedor vendedor, String key) {
        try {
            sellerResultService.verifyKey(key);
            sellerResultService.addElement(vendedor);
            kafkaProducerService.sendToTopic(KafkaConstants.RESULTS_TOPIC, String.valueOf(key), utilService.writeAsString(sellerResultService.getResult()));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
