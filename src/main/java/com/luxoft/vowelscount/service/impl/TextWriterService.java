package com.luxoft.vowelscount.service.impl;

import com.luxoft.vowelscount.entity.Result;
import com.luxoft.vowelscount.exceptions.ServiceExceptions;
import com.luxoft.vowelscount.service.ITextWriterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@PropertySource(value = "application.properties")
public class TextWriterService implements ITextWriterService {
    @Value("${output.file.name}")
    private String outputFile;
    @Value("${source.path}")
    private String path;

    @Override
    public void writeResultToFile(List<Result> resultList) {
        try {

            FileWriter writer = new FileWriter(path + outputFile);
            for (Result result : resultList) {
                writer.write(result.toStringFinal() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            new ServiceExceptions(e);
        }
    }
}
