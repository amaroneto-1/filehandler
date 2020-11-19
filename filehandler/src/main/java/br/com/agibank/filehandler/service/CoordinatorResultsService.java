package br.com.agibank.filehandler.service;

import br.com.agibank.filehandler.dto.ResultDTO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CoordinatorResultsService {
    private HashSet<ResultDTO> results;
    private String keySaved;

    public CoordinatorResultsService() {
        this.results = new HashSet<>();
        this.keySaved = "";
    }

    private void clearResultList(){
        this.results.clear();
    }

    public HashSet<ResultDTO> getResults(){
        return results;
    }

    public void addResult(List<ResultDTO> resultDTOList){
        resultDTOList.stream().forEach(p-> this.getResults().remove(p));
        this.getResults().addAll(resultDTOList);
    }

    public void markAsSavedResult(String key){
        this.keySaved = key;
        this.clearResultList();
    }

    public boolean isNotSavedResult(String key){
        return this.keySaved != key;
    }
}
