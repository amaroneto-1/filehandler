package br.com.agibank.typeprocessor.service.result;

import br.com.agibank.typeprocessor.dto.ResultadoDTO;
import br.com.agibank.typeprocessor.model.Venda;
import br.com.agibank.typeprocessor.model.Vendedor;
import br.com.agibank.typeprocessor.util.Constants;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class SaleResultService extends ResultService<Venda> {
    private Map<String, BigDecimal> totalVendasVendedor;
    private String piorVendedor;
    private Venda vendaMaisCara;

    public SaleResultService(){
        totalVendasVendedor = new HashMap<>();
    }

    public Venda getVendaMaisCara() {
        return vendaMaisCara;
    }

    public String getPiorVendedor() {
        return piorVendedor;
    }

    public void clearCounters(){
        this.vendaMaisCara = null;
        this.piorVendedor = null;
        this.totalVendasVendedor.clear();
    }

    private void updateVendaMaisCara(Venda v){
        if(Objects.isNull(this.vendaMaisCara))
            this.vendaMaisCara = v;
        else
        if(this.vendaMaisCara.getTotalVenda().compareTo(v.getTotalVenda()) == -1)
            this.vendaMaisCara = v;
    }

    private void updatePiorVendedor(){
        Map.Entry<String, BigDecimal> min = null;
        for (Map.Entry<String, BigDecimal> entry : totalVendasVendedor.entrySet()) {
            if (min == null || entry.getValue().compareTo(min.getValue()) == -1) {
                min = entry;
            }
        }
        this.piorVendedor = min.getKey();
    }

    private void updateTotalVendasVendedor(Venda v){

        BigDecimal totalVenda = v.getTotalVenda();

        if(this.totalVendasVendedor.containsKey(v.getNomeVendedor())){
            BigDecimal bigDecimal = this.totalVendasVendedor.get(v.getNomeVendedor());
            totalVendasVendedor.put(v.getNomeVendedor(), bigDecimal.add(totalVenda));
        }else{
            this.totalVendasVendedor.put(v.getNomeVendedor(), totalVenda);
        }
    }

    public void updateValues(Venda v){
        this.updateVendaMaisCara(v);
        this.updateTotalVendasVendedor(v);
        this.updatePiorVendedor();
    }

    @Override
    public void initialize(){
        super.initialize();
        this.clearCounters();
    }

    @Override
    public void addElement(Venda elem) {
        super.addElement(elem);
        this.updateValues(elem);
    }

    @Override
    public List<ResultadoDTO> getResult() {
        List<ResultadoDTO> resultados = new ArrayList<>();
        resultados.add(new ResultadoDTO(Constants.RESULTADO.VENDA_MAIS_CARA, this.getVendaMaisCara().getId()));
        resultados.add(new ResultadoDTO(Constants.RESULTADO.PIOR_VENDEDOR, this.getPiorVendedor()));
        return resultados;
    }
}
