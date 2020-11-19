package br.com.agibank.typeprocessor.model;

import br.com.agibank.typeprocessor.exceptions.InstanceException;
import lombok.Data;

@Data
public class Vendedor extends Entity{
    private String cpf;
    private String nome;
    private String salario;

    public Vendedor(String[] data) throws InstanceException {
        this.type = data[0];
        this.cpf = data[1];
        this.nome = data[2];
        this.salario = data[3];
    }
}
