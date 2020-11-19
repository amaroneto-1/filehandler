package br.com.agibank.typeprocessor.service.commandselector;

import br.com.agibank.typeprocessor.model.Cliente;
import br.com.agibank.typeprocessor.model.Vendedor;
import br.com.agibank.typeprocessor.service.processor.ClientProcessor;
import br.com.agibank.typeprocessor.service.processor.SellerProcessor;
import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SellerServiceCommand extends ServiceCommandAbstract<Vendedor> {
	@Autowired private SellerProcessor sellerProcessor;

	@Override
	public String getName() {
		return Vendedor.class.getName();
	}

	@Override
	public void handle(Vendedor vendedor, String key) {
		sellerProcessor.process(vendedor, key);
	}
}