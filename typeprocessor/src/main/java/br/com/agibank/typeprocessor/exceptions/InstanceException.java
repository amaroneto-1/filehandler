package br.com.agibank.typeprocessor.exceptions;

public class InstanceException extends Exception{
    public InstanceException() {
        super();
    }

    public InstanceException(String msg) {
        super(msg);
    }

    public InstanceException(String msg, Throwable e) {
        super(msg, e);
    }
}
