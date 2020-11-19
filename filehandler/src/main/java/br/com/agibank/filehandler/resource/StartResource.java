package br.com.agibank.filehandler.resource;

import br.com.agibank.filehandler.service.FileReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("start")
public class StartResource {
    @Autowired private FileReaderService fileReaderService;

    @GetMapping
    public void start(){
        fileReaderService.readFiles();
    }

}
