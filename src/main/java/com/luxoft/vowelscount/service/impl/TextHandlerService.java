package com.luxoft.vowelscount.service.impl;

import com.luxoft.vowelscount.entity.Result;
import com.luxoft.vowelscount.service.ITextHandlerService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TextHandlerService implements ITextHandlerService {
    private final String PUNCT_REGEX = "[^a-zA-Z0-9_-]";
    private final String VOWEL_REGEX = "(?i)[aeiouAEIOU]";

    /**
     * gets words separately from the text file without punctuation signs and deletes the spaces
     *
     * @param text an input text file
     * @return list of words from text
     */
    @Override
    public List<String> getWordsFromTextWithoutPunctuations(String text) {
        return Arrays.asList(text.split(" ")).stream()
                .map(w -> w.replaceAll(PUNCT_REGEX, "").trim())
                .collect(Collectors.toList());
    }

    /**
     * counts the number of letters in each single words from list
     *
     * @param words an input list of words
     * @return list of {@link Result} object
     */
    @Override
    public List<Result> getListOfVowelsAndLength(List<String> words) {
        return words.stream()
                .map(w -> new Result(Stream.of(w.split("")).filter(this::checkForVowel).collect(Collectors.toList()), w.length()))
                .collect(Collectors.toList());
    }

    /**
     * calculates an average amount of vowels in a such way that counts an average value
     * from the first word up to each in list separately. The last one consists
     * an average amount of vowels in all text.
     *
     * @param listOfVowelsAndLength an input list of {@link Result} object consist list of vowels and length of word
     * @return list of {@link Result} object consist list of vowels, length of word, an average amount of vowels in
     * all words before includes itself
     */
    @Override
    public List<Result> getAvgOfVowels(List<Result> listOfVowelsAndLength) {
        double sum = 0;
        for (int i = 0; i < listOfVowelsAndLength.size(); i++) {
            for (String s : listOfVowelsAndLength.get(i).getVowelList()) {
                sum = s.length() + sum;
            }
            double avg = sum / (i + 1);
            listOfVowelsAndLength.get(i).setAvgVowels(avg);
        }
        return listOfVowelsAndLength;
    }

    /**
     * converts the list of vowels in each {@link Result} to set of vowels as per task conditions.
     *
     * @param listOfVowelsAndLengthAndAvg an input list of {@link Result} objects
     * @return the list of {@link Result} objects
     */
    @Override
    public List<Result> getSetOfVowelsWithLengthAndAvg(List<Result> listOfVowelsAndLengthAndAvg) {
        return listOfVowelsAndLengthAndAvg.stream()
                .map(r -> new Result(Set.copyOf(r.getVowelList()), r.getWordLength(), r.getAvgVowels()))
                .collect(Collectors.toList());
    }

    private boolean checkForVowel(String s) {
        Pattern r = Pattern.compile(VOWEL_REGEX);
        return r.matcher(s).find();
    }
}
