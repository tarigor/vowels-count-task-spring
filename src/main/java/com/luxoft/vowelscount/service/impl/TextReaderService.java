package com.luxoft.vowelscount.service.impl;


import com.luxoft.vowelscount.exceptions.ServiceExceptions;
import com.luxoft.vowelscount.service.ITextReaderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@PropertySource(value = "application.properties")
public class TextReaderService implements ITextReaderService {

    @Value("${input.file.name}")
    private String inputFile;

    public String readTextFromResources() throws ServiceExceptions {
        String textContent = "";
        try {
            textContent = Files.readString(Paths.get("src/main/resources/" + inputFile).toAbsolutePath());
        } catch (IOException e) {
            throw new ServiceExceptions(e);
        }
        return textContent;
    }
}
