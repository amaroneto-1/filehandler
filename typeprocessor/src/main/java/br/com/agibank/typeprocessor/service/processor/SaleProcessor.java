package br.com.agibank.typeprocessor.service.processor;

import br.com.agibank.typeprocessor.config.KafkaConstants;
import br.com.agibank.typeprocessor.dto.ResultadoDTO;
import br.com.agibank.typeprocessor.model.Cliente;
import br.com.agibank.typeprocessor.model.Venda;
import br.com.agibank.typeprocessor.model.Vendedor;
import br.com.agibank.typeprocessor.service.KafkaProducerService;
import br.com.agibank.typeprocessor.service.result.SaleResultService;
import br.com.agibank.typeprocessor.util.Constants;
import br.com.agibank.typeprocessor.util.UtilService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class SaleProcessor implements Processor<Venda> {
    @Autowired private KafkaProducerService kafkaProducerService;
    @Autowired private UtilService utilService;
    @Autowired private SaleResultService saleResultService;

    @Override
    public void process(Venda venda, String key) {
        try {
            saleResultService.verifyKey(key);
            saleResultService.addElement(venda);
            kafkaProducerService.sendToTopic(KafkaConstants.RESULTS_TOPIC, String.valueOf(key), utilService.writeAsString(saleResultService.getResult()));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
