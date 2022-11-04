package com.luxoft.vowelscount.service.impl;

import com.luxoft.vowelscount.entity.Result;
import com.luxoft.vowelscount.exceptions.ServiceExceptions;
import com.luxoft.vowelscount.service.ITextWriterService;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TextWriterService implements ITextWriterService {
    private final String OUTPUT_FILE_NAME = "OUTPUT.TXT";
    private final String PATH = "src/main/resources/";

    @Override
    public void writeResultToFile(List<Result> resultList) {
        try {

            FileWriter writer = new FileWriter(PATH + OUTPUT_FILE_NAME);
            for (Result result : resultList) {
                writer.write(result.toStringFinal() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            new ServiceExceptions(e);
        }
    }
}
