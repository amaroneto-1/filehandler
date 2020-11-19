package br.com.agibank.typeprocessor.model;

import br.com.agibank.typeprocessor.enums.EntityTypeEnum;
import br.com.agibank.typeprocessor.exceptions.InstanceException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Venda extends Entity{
    private String id;
    private List<ItemVenda> itemVendas;
    private String nomeVendedor;
    private BigDecimal totalVenda;

    //Ex.: 003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name
    public Venda(String[] dataVenda) throws InstanceException {
        this.itemVendas = new ArrayList<>();
        String[] itensVenda = dataVenda[2].substring(1, dataVenda[2].length()-2).split(",");
        this.type = dataVenda[0];
        this.id = dataVenda[1];
        this.nomeVendedor = dataVenda[3];

        for(String itemVenda : itensVenda)
            this.itemVendas.add(new ItemVenda(itemVenda));

    }

    public BigDecimal getTotalVenda(){
        if(this.getItemVendas().isEmpty())
            return BigDecimal.ZERO;

        return this.getItemVendas().parallelStream()
                .map(p-> p.getPrecoItem().multiply(new BigDecimal(p.getQtdItem()).setScale(2, RoundingMode.FLOOR)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
