package br.com.agibank.typeprocessor.service.commandselector;

import br.com.agibank.typeprocessor.model.Entity;

public abstract class ServiceCommandAbstract<T extends Entity>{
	public abstract String getName();
	public abstract void handle(T param, String key);
}