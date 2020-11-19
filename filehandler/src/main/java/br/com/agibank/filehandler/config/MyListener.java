package br.com.agibank.filehandler.config;

import br.com.agibank.filehandler.dto.ResultDTO;
import br.com.agibank.filehandler.service.CoordinatorResultsService;
import br.com.agibank.filehandler.service.FileReaderService;
import br.com.agibank.filehandler.service.FileWriterService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.event.ListenerContainerIdleEvent;
import org.springframework.kafka.listener.AbstractConsumerSeekAware;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;

@Service
class MyListener extends AbstractConsumerSeekAware implements MessageListener<String, String> {
    @Autowired private CoordinatorResultsService coordinatorResultsService;
    @Autowired private FileWriterService fileWriterService;

    @Override
    public void onMessage(ConsumerRecord<String, String> record) {

    }

    @EventListener
    public void listen(ListenerContainerIdleEvent event) {
        HashSet<ResultDTO> resultados = coordinatorResultsService.getResults();
        if(coordinatorResultsService.isNotSavedResult(fileWriterService.getIdFilesReaded()) && !resultados.isEmpty())
            try {
                fileWriterService.write(resultados);
                coordinatorResultsService.markAsSavedResult(fileWriterService.getIdFilesReaded());
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}