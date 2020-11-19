package br.com.agibank.typeprocessor.service.result;

import br.com.agibank.typeprocessor.dto.ResultadoDTO;
import br.com.agibank.typeprocessor.model.Cliente;
import br.com.agibank.typeprocessor.model.Vendedor;
import br.com.agibank.typeprocessor.util.Constants;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SellerResultService extends ResultService<Vendedor> {
    @Override
    public List<ResultadoDTO> getResult() {
        Long quantidade = this.elementSet.parallelStream().map(Vendedor::getCpf).distinct().count();
        return Collections.singletonList(new ResultadoDTO(Constants.RESULTADO.NUMERO_VENDEDORES, quantidade.toString()));
    }
}
