package br.com.agibank.filehandler.service;

import br.com.agibank.filehandler.config.KafkaConstants;
import br.com.agibank.filehandler.dto.ResultDTO;
import br.com.agibank.filehandler.util.Constants;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileReaderService {
    private String idFilesReaded;
    @Autowired private KafkaProducerService kafkaProducerService;

    public String getIdFilesReaded() {
        return idFilesReaded;
    }

    public void readFiles(){
        this.idFilesReaded = UUID.randomUUID().toString();

        String path= System.getProperty("user.home") + Constants.FILE.IN_DIRECTORY;
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(p-> p.getFileName().toString().contains(Constants.FILE.IN_EXTENSION))
                    .forEach(p-> this.readBuffer(p, idFilesReaded));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readBuffer(Path path, String key){
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(p->{
                kafkaProducerService.sendToTopic(KafkaConstants.FILE_TOPIC, key, p);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
