package br.com.agibank.typeprocessor.service.factory;

import br.com.agibank.typeprocessor.exceptions.InstanceException;
import br.com.agibank.typeprocessor.model.Entity;
import br.com.agibank.typeprocessor.util.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Factory<T extends Entity> {
    protected abstract void verify(String line, String[] data) throws InstanceException;

    public abstract T getInstance(String line) throws InstanceException;

    protected static void verify(String line, String patternStr) throws InstanceException {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(line);

        /*if(!matcher.matches());
            throw new InstanceException(Constants.ERROR.ROW_INVALID_FORMAT_REGEX);*/
    }

}
