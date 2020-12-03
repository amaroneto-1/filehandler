package br.com.agibank.typeprocessor.service.factory;

import br.com.agibank.typeprocessor.enums.EntityTypeEnum;
import br.com.agibank.typeprocessor.exceptions.InstanceException;
import br.com.agibank.typeprocessor.model.Entity;
import br.com.agibank.typeprocessor.util.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Factory<T extends Entity> {

    protected String[] verifyAndGetDataArray(String line, String token, int lenght, EntityTypeEnum type) throws InstanceException {
        String[] data = line.split(token);

        if(data.length < lenght)
            throw new InstanceException(Constants.ERROR.ROW_INVALID_FORMAT);

        Pattern pattern = Pattern.compile(type.getPattern());
        Matcher matcher = pattern.matcher(line);

        System.out.println("matches:"+matcher.matches());
        if(!matcher.matches())
            throw new InstanceException(Constants.ERROR.ROW_INVALID_FORMAT_REGEX);

        return data;
    }

    public abstract T getInstance(String line) throws InstanceException;
}
