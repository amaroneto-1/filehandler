package br.com.agibank.typeprocessor.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@NoArgsConstructor
public class ItemVenda {
    private String idItem;
    private String qtdItem;
    private BigDecimal precoItem;

    private BigDecimal getPrecoItemInString(String stringValue){
        return new BigDecimal(stringValue).setScale(2, RoundingMode.FLOOR);
    }

    public ItemVenda(String s) {
        String [] valores = s.split("-");
        this.idItem = valores[0];
        this.qtdItem = valores[1];
        this.precoItem = getPrecoItemInString(valores[2]);
    }
}
