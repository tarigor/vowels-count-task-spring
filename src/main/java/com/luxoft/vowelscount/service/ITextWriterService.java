package com.luxoft.vowelscount.service;

import com.luxoft.vowelscount.entity.Result;

import java.util.List;

public interface ITextWriterService {
    void writeResultToFile(List<Result> resultList);
}
