package br.com.agibank.typeprocessor.service.factory;

import br.com.agibank.typeprocessor.enums.EntityTypeEnum;
import br.com.agibank.typeprocessor.exceptions.InstanceException;
import br.com.agibank.typeprocessor.model.Cliente;
import br.com.agibank.typeprocessor.model.Venda;
import br.com.agibank.typeprocessor.util.Constants;
import org.springframework.stereotype.Service;

@Service
public class SaleFactory extends Factory<Venda> {

    @Override
    public Venda getInstance(String line) throws InstanceException {
        String[] data = this.verifyAndGetDataArray(line, "ç", 4, EntityTypeEnum.VENDA);
        return new Venda(data);
    }
}
