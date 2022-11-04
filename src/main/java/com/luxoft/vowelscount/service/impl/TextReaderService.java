package com.luxoft.vowelscount.service.impl;


import com.luxoft.vowelscount.service.ITextReaderService;
import com.luxoft.vowelscount.exceptions.ServiceExceptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@PropertySource(value = "application.properties")
public class TextReaderService implements ITextReaderService {

    @Value("${input.file.name}")
    private String inputFile;

    public String readTextFromResources() throws ServiceExceptions {
        String textContent = "";
        System.out.println("file name -> " + inputFile);
        System.out.println("i'm here");
        try {
            System.out.println("i'm here");
            textContent = Files.readString(Paths.get("src/main/resources/"+inputFile).toAbsolutePath());
        } catch (IOException e) {
            throw new ServiceExceptions(e);
        }
        return textContent;
    }
}
