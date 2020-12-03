package br.com.agibank.typeprocessor.service.instancechain;

import br.com.agibank.typeprocessor.exceptions.HandlerNotFoundException;
import br.com.agibank.typeprocessor.model.Cliente;
import br.com.agibank.typeprocessor.model.Entity;
import br.com.agibank.typeprocessor.model.Vendedor;
import br.com.agibank.typeprocessor.service.factory.ClientFactory;
import br.com.agibank.typeprocessor.service.factory.SellerFactory;
import br.com.agibank.typeprocessor.service.instancechain.generic.InstanceHandler;
import br.com.agibank.typeprocessor.service.instancechain.util.KafkaErrorSenderService;
import br.com.agibank.typeprocessor.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerHandler extends InstanceHandler<Entity> {

    @Autowired private KafkaErrorSenderService kafkaErrorSenderService;
    @Autowired private SellerFactory sellerFactory;

    @Override
    public Entity handle(String line, String key) throws HandlerNotFoundException {
        try {
            if(line.startsWith(Constants.ID_VENDEDOR))
                return sellerFactory.getInstance(line);
        }catch(Exception e){
            kafkaErrorSenderService.sendErrorToTopic(line, key, e);
            return null;
        }
        return getNextHandler().handle(line, key);
    }


}
