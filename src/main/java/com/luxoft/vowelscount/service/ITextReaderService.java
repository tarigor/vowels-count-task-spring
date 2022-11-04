package com.luxoft.vowelscount.service;


import com.luxoft.vowelscount.exceptions.ServiceExceptions;

public interface ITextReaderService {
    String readTextFromResources() throws ServiceExceptions;
}
