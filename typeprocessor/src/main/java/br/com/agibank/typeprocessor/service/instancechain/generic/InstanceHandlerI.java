package br.com.agibank.typeprocessor.service.instancechain.generic;

import br.com.agibank.typeprocessor.exceptions.HandlerNotFoundException;

public interface InstanceHandlerI<T> {
	public void setNextHandler(InstanceHandler<T> handler);
	public T handle(String line, String key) throws HandlerNotFoundException;
}