package br.com.agibank.typeprocessor.service.instancechain.generic;

public abstract class InstanceHandler<T> implements InstanceHandlerI<T>{
	private InstanceHandler<T> nextHandler;

	public InstanceHandler<T> getNextHandler() {
		return nextHandler;
	}

	@Override
	public void setNextHandler(InstanceHandler<T> handler){
		this.nextHandler = handler;
	}

}