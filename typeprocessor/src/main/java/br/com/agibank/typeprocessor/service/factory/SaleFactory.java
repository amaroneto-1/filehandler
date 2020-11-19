package br.com.agibank.typeprocessor.service.factory;

import br.com.agibank.typeprocessor.enums.EntityTypeEnum;
import br.com.agibank.typeprocessor.exceptions.InstanceException;
import br.com.agibank.typeprocessor.model.Venda;
import br.com.agibank.typeprocessor.util.Constants;
import org.springframework.stereotype.Service;

@Service
public class SaleFactory extends Factory<Venda> {

    @Override
    public void verify(String line, String[] data) throws InstanceException {
        if(data.length < 4)
            throw new InstanceException(Constants.ERROR.ROW_INVALID_FORMAT);

        this.verify(line, EntityTypeEnum.VENDA.getPattern());
    }

    @Override
    public Venda getInstance(String line) throws InstanceException{
        String[] data = line.split("ç");
        this.verify(line, data);
        return new Venda(data);
    }
}
