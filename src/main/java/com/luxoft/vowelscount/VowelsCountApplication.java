package com.luxoft.vowelscount;

import com.luxoft.vowelscount.entity.Result;
import com.luxoft.vowelscount.exceptions.ServiceExceptions;
import com.luxoft.vowelscount.service.impl.TextHandlerService;
import com.luxoft.vowelscount.service.impl.TextReaderService;
import com.luxoft.vowelscount.service.impl.TextWriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import java.util.List;

@SpringBootApplication
public class VowelsCountApplication {

    public static void main(String[] args) throws ServiceExceptions {
        ApplicationContext ctx = SpringApplication.run(VowelsCountApplication.class, args);

        TextReaderService textReaderService = ctx.getBean(TextReaderService.class);
        TextHandlerService textHandlerService = ctx.getBean(TextHandlerService.class);
        TextWriterService textWriterService = ctx.getBean(TextWriterService.class);

        String text = textReaderService.readTextFromResources();
        List<String> words = textHandlerService.getWordsFromTextWithoutPunctuations(text);
        List<Result> listOfVowelsAndLength = textHandlerService.getListOfVowelsAndLength(words);
        List<Result> listOfVowelsAndLengthAndAvg = textHandlerService.getAvgOfVowels(listOfVowelsAndLength);
        List<Result> setOfVowelsAndLengthAndAvg = textHandlerService.getSetOfVowelsWithLengthAndAvg(listOfVowelsAndLengthAndAvg);

        textWriterService.writeResultToFile(setOfVowelsAndLengthAndAvg);
    }

}
