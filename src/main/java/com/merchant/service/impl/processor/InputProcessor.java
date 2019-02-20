package com.merchant.service.impl.processor;

import com.merchant.data.MappingData;
import com.merchant.glossary.LineFilter;
import com.merchant.glossary.SentenceType;
import com.merchant.service.ProcessorService;
import com.merchant.util.RomanToDecimal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InputProcessor implements ProcessorService<String> {

    private ProcessorService<MappingData> chain;
    private LineFilter lineFilter = new LineFilter();

    @Override
    public void setNextChange(ProcessorService nextChange) {
        this.chain = nextChange;
    }

    public void handle(String filePath) throws Exception {
        List<String> inputs = getDataFromFile(filePath);
        MappingData mappingData = processHandleData(inputs);
        if (this.chain != null && mappingData != null) {
            this.chain.handle(mappingData);
        }
    }

    private MappingData processHandleData(List<String> lines) {
        try {
            MappingData data = new MappingData();
            for (String line : lines) {
                processLine(data, line);
            }
            return data;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private void processLine(MappingData data, String line) {
        try {
            String arr[] = line.split(LineFilter.patternSplitSpace);
            SentenceType lineType = this.lineFilter.getLineType(line);
            switch (lineType) {
                case ASSIGNED:
                    processLineAssignLine(data, arr);
                    break;
                case CREDITS:
                    processCreditLine(data, line);
                    break;
                case QUESTION_HOW_MANY:
                case QUESTION_HOW_MUCH:
                case DOES_QUESTION:
                case IS_QUESTION:
                case NOMATCH:
                    data.getQuestionAndReply().put(line, "");
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(line + " 'is not correct'");
        }
    }

    private List<String> getDataFromFile(String filePath) throws IOException {
        List<String> inputs = new ArrayList<>();
        BufferedReader bufferedReader;
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream in = classLoader.getResourceAsStream(filePath);
        bufferedReader = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            inputs.add(line);
        }
        return inputs;
    }


    //Store Map<Symbol,Roman> and Map<Symbol,Value>
    private void processLineAssignLine(MappingData data, String[] arr) {
        data.getConstantAssign().put(arr[0], arr[arr.length - 1]);
        float integerValue = new RomanToDecimal().romanToDecimal(arr[arr.length - 1]);
        data.getTokenIntegerValue().put(arr[0], integerValue);
    }

    private void processCreditLine(MappingData data, String query) {
        String array[] = query.split(LineFilter.patternSplitSpace);
        int splitIndex = 0;
        int creditValue = 0;
        String element = null;
        String[] symbols = null;
        for (int i = 0; i < array.length; i++) {
            if (array[i].toLowerCase().equals("credits")) {
                creditValue = Integer.parseInt(array[i - 1]);
            }
            if (array[i].toLowerCase().equals("is")) {
                splitIndex = i - 1;
                element = array[i - 1];
            }
            symbols = java.util.Arrays.copyOfRange(array, 0, splitIndex);
        }

        StringBuilder romanNumber = new StringBuilder();
        for (int j = 0; j < symbols.length; j++) {
            romanNumber.append(data.getConstantAssign().get(symbols[j]));
        }
        float valueOfElementInDecimal = new RomanToDecimal().romanToDecimal(romanNumber.toString());
        data.getCreditLiterals().put(element, creditValue / valueOfElementInDecimal);
    }
}
