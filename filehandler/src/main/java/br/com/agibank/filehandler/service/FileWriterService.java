package br.com.agibank.filehandler.service;

import br.com.agibank.filehandler.dto.ResultDTO;
import br.com.agibank.filehandler.util.Constants;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileWriterService {
    private String idFilesReaded;

    public String getIdFilesReaded() {
        return idFilesReaded;
    }

    public void write(HashSet<ResultDTO> resultDTOList) throws IOException {

        String path= System.getProperty("user.home") + Constants.FILE.OUT_DIRECTORY;
        String filePath = path + UUID.randomUUID() + Constants.FILE.OUT_EXTENSION;
        FileWriter arq = new FileWriter(filePath);

        PrintWriter gravarArq = new PrintWriter(arq);
        resultDTOList.stream().forEach(resultDTO -> {
            gravarArq.printf("%s  -  %s \n", resultDTO.getType(), resultDTO.getValue());
        });
        arq.close();

        System.out.printf("Arquivo salvo!\n");
    }
}
