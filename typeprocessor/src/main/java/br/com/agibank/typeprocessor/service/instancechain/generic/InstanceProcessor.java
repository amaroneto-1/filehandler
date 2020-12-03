package br.com.agibank.typeprocessor.service.instancechain.generic;

import br.com.agibank.typeprocessor.exceptions.HandlerNotFoundException;
import br.com.agibank.typeprocessor.model.Entity;

import java.util.Objects;

public class InstanceProcessor<T> {
	private InstanceHandler<T> handler;
	
	public void addNextHandler(InstanceHandler<T> handler){
		if(! (this.handler instanceof InstanceHandler)){
			this.handler = handler;
			this.handler.setNextHandler(null);
		}else{
			InstanceHandler auxTratador = this.handler;
			while(Objects.nonNull(auxTratador.getNextHandler()))
				auxTratador = auxTratador.getNextHandler();
			auxTratador.setNextHandler(handler);
		}
	}

	public T getInstance(String line, String key) throws HandlerNotFoundException {
		return handler.handle(line, key);
	}
}