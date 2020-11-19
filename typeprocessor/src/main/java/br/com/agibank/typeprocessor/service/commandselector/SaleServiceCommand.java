package br.com.agibank.typeprocessor.service.commandselector;

import br.com.agibank.typeprocessor.model.Cliente;
import br.com.agibank.typeprocessor.model.Venda;
import br.com.agibank.typeprocessor.service.processor.ClientProcessor;
import br.com.agibank.typeprocessor.service.processor.SaleProcessor;
import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SaleServiceCommand extends ServiceCommandAbstract<Venda> {
	@Autowired private SaleProcessor saleProcessor;

	@Override
	public String getName() {
		return Venda.class.getName();
	}

	@Override
	public void handle(Venda venda, String key) {
		saleProcessor.process(venda, key);
	}
}