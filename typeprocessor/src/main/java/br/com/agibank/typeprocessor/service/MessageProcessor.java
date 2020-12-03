package br.com.agibank.typeprocessor.service;

import br.com.agibank.typeprocessor.exceptions.InstanceException;
import br.com.agibank.typeprocessor.model.Entity;
import br.com.agibank.typeprocessor.service.commandselector.ServiceCommandBuilder;
import br.com.agibank.typeprocessor.service.instancechain.generic.InstanceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MessageProcessor {
    @Autowired private InstanceClient instanceClient;
    @Autowired private ServiceCommandBuilder serviceCommandBuilder;

    public void processMessage(String message, String key) {
        Entity elem = this.instanceClient.getEntityInstance(message, key);
        if(Objects.nonNull(elem))
            this.serviceCommandBuilder.execute(elem.getClass().getName(), elem, key);
    }
}
