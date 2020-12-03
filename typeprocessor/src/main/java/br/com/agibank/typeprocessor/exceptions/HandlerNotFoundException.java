package br.com.agibank.typeprocessor.exceptions;

public class HandlerNotFoundException extends Exception{
	public HandlerNotFoundException(){
		super("O handler para este tipo não foi implementado");
	}
	public HandlerNotFoundException(String msg){
		super(msg);
	}
}