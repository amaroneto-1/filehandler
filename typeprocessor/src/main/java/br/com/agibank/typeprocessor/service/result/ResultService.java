package br.com.agibank.typeprocessor.service.result;

import br.com.agibank.typeprocessor.dto.ResultadoDTO;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class ResultService<T> {

    protected String key;
    protected Set<T> elementSet;

    public abstract List<ResultadoDTO> getResult();

    public ResultService() {
        this.key = "";
        this.elementSet = new HashSet<>();
    }


    public void verifyKey(String key){
        if(this.key.isEmpty())
            this.key = key;
        else
        if(!this.key.equals(key)) {
            initialize();
            this.key = key;
        }
    }

    public void initialize() {
        this.elementSet.clear();
    }

    public void addElement(T elem){
        this.elementSet.add(elem);
    }
}
