package br.com.agibank.typeprocessor.service.factory;

import br.com.agibank.typeprocessor.enums.EntityTypeEnum;
import br.com.agibank.typeprocessor.exceptions.InstanceException;
import br.com.agibank.typeprocessor.model.Cliente;
import br.com.agibank.typeprocessor.util.Constants;
import org.springframework.stereotype.Service;

@Service
public class ClientFactory extends Factory<Cliente> {
    @Override
    public Cliente getInstance(String line) throws InstanceException {
        String[] data = this.verifyAndGetDataArray(line, "ç", 4, EntityTypeEnum.CLIENTE);
        return new Cliente(data);
    }
}
