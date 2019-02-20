package com.merchant.service;

import java.io.BufferedReader;
import java.io.IOException;

public interface ProcessorService<T> {
    void setNextChange(ProcessorService<T> nextChange);
    public void handle(T data) throws Exception;
}
