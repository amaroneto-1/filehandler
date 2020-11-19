package br.com.agibank.typeprocessor.service;

import br.com.agibank.typeprocessor.exceptions.InstanceException;
import br.com.agibank.typeprocessor.model.Entity;
import br.com.agibank.typeprocessor.service.commandselector.ServiceCommandBuilder;
import br.com.agibank.typeprocessor.service.factory.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageProcessor {
    @Autowired private FactoryService factoryService;
    @Autowired private ServiceCommandBuilder serviceCommandBuilder;

    public void processMessage(String message, String key) {
        System.out.println(message);
        try {
            Entity elem = this.factoryService.getEntity(message, key);
            this.serviceCommandBuilder.execute(elem.getClass().getName(), elem, key);
        } catch (InstanceException ex) {
            //BY PASS - Já foi enviado ao tópico de log do kafka
        }
    }
}
