package br.com.agibank.typeprocessor.service.instancechain;

import br.com.agibank.typeprocessor.exceptions.HandlerNotFoundException;
import br.com.agibank.typeprocessor.model.Cliente;
import br.com.agibank.typeprocessor.model.Entity;
import br.com.agibank.typeprocessor.service.factory.ClientFactory;
import br.com.agibank.typeprocessor.service.instancechain.generic.InstanceHandler;
import br.com.agibank.typeprocessor.service.instancechain.util.KafkaErrorSenderService;
import br.com.agibank.typeprocessor.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientHandler extends InstanceHandler<Entity> {

    @Autowired private KafkaErrorSenderService kafkaErrorSenderService;
    @Autowired private ClientFactory clientFactory;

    @Override
    public Entity handle(String line, String key)  throws HandlerNotFoundException {
        try {
            if(line.startsWith(Constants.ID_CLIENTE))
                return clientFactory.getInstance(line);
        }catch(Exception e){
            kafkaErrorSenderService.sendErrorToTopic(line, key, e);
            return null;
        }
        return getNextHandler().handle(line, key);
    }


}
