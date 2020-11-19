package br.com.agibank.typeprocessor.service.factory;

import br.com.agibank.typeprocessor.config.KafkaConstants;
import br.com.agibank.typeprocessor.dto.ErrorDTO;
import br.com.agibank.typeprocessor.exceptions.InstanceException;
import br.com.agibank.typeprocessor.model.Entity;
import br.com.agibank.typeprocessor.service.KafkaProducerService;
import br.com.agibank.typeprocessor.service.factory.ClientFactory;
import br.com.agibank.typeprocessor.service.factory.SaleFactory;
import br.com.agibank.typeprocessor.service.factory.SellerFactory;
import br.com.agibank.typeprocessor.util.Constants;
import br.com.agibank.typeprocessor.util.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class FactoryService {
    @Autowired private ClientFactory clientFactory;
    @Autowired private SellerFactory sellerFactory;
    @Autowired private SaleFactory saleFactory;
    @Autowired private KafkaProducerService kafkaProducerService;
    @Autowired private UtilService utilService;

    public Entity getEntity(String line, String key) throws InstanceException {
        try{

            if(line.startsWith(Constants.ID_CLIENTE))
                return clientFactory.getInstance(line);

            if(line.startsWith(Constants.ID_VENDEDOR))
                return sellerFactory.getInstance(line);

            if(line.startsWith(Constants.ID_VENDA))
                return saleFactory.getInstance(line);

            throw new InstanceException("Não foi possível gerar a instância:"+line);
        }catch(Exception e){
            try{
                kafkaProducerService.sendToTopic(KafkaConstants.LOG_TOPIC, key, utilService.writeAsString(new ErrorDTO(line, e.getMessage())));
            }catch(Exception e2){e2.printStackTrace();}

            throw new InstanceException("Não foi possível gerar a instância:"+line, e);
        }
    }
}
