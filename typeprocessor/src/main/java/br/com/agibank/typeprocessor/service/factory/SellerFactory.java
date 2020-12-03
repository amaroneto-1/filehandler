package br.com.agibank.typeprocessor.service.factory;

import br.com.agibank.typeprocessor.enums.EntityTypeEnum;
import br.com.agibank.typeprocessor.exceptions.InstanceException;
import br.com.agibank.typeprocessor.model.Venda;
import br.com.agibank.typeprocessor.model.Vendedor;
import br.com.agibank.typeprocessor.util.Constants;
import org.springframework.stereotype.Service;

@Service
public class SellerFactory extends Factory<Vendedor> {

    @Override
    public Vendedor getInstance(String line) throws InstanceException {
        String[] data = this.verifyAndGetDataArray(line, "ç", 4, EntityTypeEnum.VENDEDOR);
        return new Vendedor(data);
    }
}
