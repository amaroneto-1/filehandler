package br.com.agibank.typeprocessor.model;


import br.com.agibank.typeprocessor.util.Constants;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Cliente.class, name = Constants.ID_CLIENTE),
        @JsonSubTypes.Type(value = Vendedor.class, name = Constants.ID_VENDEDOR),
        @JsonSubTypes.Type(value = Venda.class, name = Constants.ID_VENDA),
})
public abstract class Entity {
    protected String type;
}
