package br.com.agibank.typeprocessor.enums;


import br.com.agibank.typeprocessor.util.Constants;

public enum EntityTypeEnum {

    CLIENTE(Constants.ID_CLIENTE,   ".*"),
    VENDEDOR(Constants.ID_VENDEDOR, ".*"),
    VENDA(Constants.ID_VENDA, ".*");

    //TESTAR PARA CONCLUIR
    //CLIENT  - "[0-9]{3}ç[0-9]{14}ç[a-zA-Z ,.'-]+ç[a-zA-Z ,.'-]+" - OK
    //VENDEDOR - "[0-9]{3}ç[0-9]{11}ç[a-zA-Z ,.'-]+ç[0-9]+" - OK
    //VENDA(Constants.ID_VENDA, "[0-9]{3}ç[0-9]+ç\\\\[([0-9]+-[0-9]+-[0-9]*[.][0-9]+)+\\\\]ç[a-zA-Z ,.'-]+" - CORRIGIR ITENS DA VENDA

    private String id;
    private String pattern;


    EntityTypeEnum(String id, String pattern) {
        this.id = id;
        this.pattern = pattern;
    }

    public String getId() {
        return id;
    }

    public String getPattern() {
        return pattern;
    }
}
