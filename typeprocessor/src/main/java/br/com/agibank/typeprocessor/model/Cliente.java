package br.com.agibank.typeprocessor.model;

import br.com.agibank.typeprocessor.exceptions.InstanceException;
import lombok.Data;

@Data
public class Cliente extends Entity{
    private String cnpj;
    private String nome;
    private String area;

    public Cliente(String[] data) throws InstanceException {
        this.type = data[0];
        this.cnpj = data[1];
        this.nome = data[2];
        this.area = data[3];
    }
}
