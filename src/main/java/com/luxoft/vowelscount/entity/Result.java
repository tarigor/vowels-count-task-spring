package com.luxoft.vowelscount.entity;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class Result {
    Set<String> vowelsSet;
    List<String> vowelList;
    double wordLength;
    double avgVowels;

    public Result(List<String> vowelList, double wordLength) {
        this.vowelList = vowelList;
        this.wordLength = wordLength;
    }

    public Result(List<String> vowelList, double wordLength, double avgVowels) {
        this.vowelList = vowelList;
        this.wordLength = wordLength;
        this.avgVowels = avgVowels;
    }

    public Result(Set<String> vowelsSet, double wordLength, double avgVowels) {
        this.vowelsSet = vowelsSet;
        this.wordLength = wordLength;
        this.avgVowels = avgVowels;
    }

    @Override
    public String toString() {
        return "(" + vowelList +
                ", " + wordLength +
                ") -> " + avgVowels;
    }

    public String toStringFinal() {
        return "(" + vowelsSet +
                ", " + wordLength +
                ") -> " + avgVowels;
    }

}
