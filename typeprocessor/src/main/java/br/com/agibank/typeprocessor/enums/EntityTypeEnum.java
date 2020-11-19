package br.com.agibank.typeprocessor.enums;


import br.com.agibank.typeprocessor.util.Constants;

public enum EntityTypeEnum {

    CLIENTE(Constants.ID_CLIENTE,   "[0-9]{3}ç[0-9]{14}ç[a-zA-Z ,.'-]+ç[a-zA-Z ,.'-]+"),
    VENDEDOR(Constants.ID_VENDEDOR, "[0-9]{3}ç[0-9]{11}ç[a-zA-Z ,.'-]+ç[0-9]+"),
    VENDA(Constants.ID_VENDA, "[0-9]{3}ç[0-9]+ç\\\\[([0-9]+-[0-9]+-[0-9]*[.][0-9]+)+\\\\]ç[a-zA-Z ,.'-]+");//não terminado

    private String id;
    private String pattern;


    private EntityTypeEnum(String id, String pattern) {
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
