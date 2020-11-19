package br.com.agibank.typeprocessor.service.commandselector;

import br.com.agibank.typeprocessor.model.Cliente;
import br.com.agibank.typeprocessor.service.processor.ClientProcessor;
import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClientServiceCommand extends ServiceCommandAbstract<Cliente> {
	@Autowired private ClientProcessor clientProcessor;

	@Override
	public String getName() {
		return Cliente.class.getName();
	}

	@Override
	public void handle(Cliente cliente, String key) {
		clientProcessor.process(cliente, key);
	}
}