package br.com.agibank.typeprocessor.service.instancechain.generic;

import br.com.agibank.typeprocessor.exceptions.HandlerNotFoundException;
import br.com.agibank.typeprocessor.model.Entity;
import br.com.agibank.typeprocessor.service.instancechain.ClientHandler;
import br.com.agibank.typeprocessor.service.instancechain.SaleHandler;
import br.com.agibank.typeprocessor.service.instancechain.SellerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.logging.Handler;

@Service
public class InstanceClient {
	private InstanceProcessor<Entity> instanceProcessor;

	@Autowired
	public InstanceClient(Set<InstanceHandler> instanceSet) {
		buildProcessorChain(instanceSet);
	}

	private void buildProcessorChain(Set<InstanceHandler> instanceSet) {
		instanceProcessor = new InstanceProcessor();
		instanceProcessor.addNextHandler(getInstance(instanceSet, ClientHandler.class));
		instanceProcessor.addNextHandler(getInstance(instanceSet, SellerHandler.class));
		instanceProcessor.addNextHandler(getInstance(instanceSet, SaleHandler.class));
	}

	InstanceHandler getInstance(Set<InstanceHandler> instanceSet, Class classe){
		for(InstanceHandler instanceHandler : instanceSet)
			if(classe.isInstance(instanceHandler))
				return instanceHandler;
		return null;
	}

	protected Entity getEntityInstancePrivate(String line, String key){
		try {
			return instanceProcessor.getInstance(line, key);
		}catch(HandlerNotFoundException e){
			e.printStackTrace();
			return null;
		}
	}

	public Entity getEntityInstance(String line, String key){
		return this.getEntityInstancePrivate(line, key);
	}
	
}