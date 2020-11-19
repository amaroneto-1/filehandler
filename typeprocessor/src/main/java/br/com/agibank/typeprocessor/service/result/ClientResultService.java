package br.com.agibank.typeprocessor.service.result;

import br.com.agibank.typeprocessor.dto.ResultadoDTO;
import br.com.agibank.typeprocessor.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClientResultService extends ResultService<Cliente> {
    @Override
    public List<ResultadoDTO> getResult() {
        Long quantidade = this.elementSet.parallelStream().map(Cliente::getCnpj).distinct().count();
        return Collections.singletonList(new ResultadoDTO("cliente", quantidade.toString()));
    }
}
