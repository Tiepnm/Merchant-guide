package com.merchant.data;

import java.util.HashMap;
import java.util.Map;

public final class MappingData {

    //mapping symbol, roman
    private final Map<String, String> constantAssign = new HashMap<String, String>();
    //mapping symbol,value of roman
    private final Map<String, Float> tokenIntegerValue = new HashMap<String, Float>();
    //store question
    private final Map<String, String> questionAndReply = new HashMap<String, String>();
    //store value of metal such as sliver, gold, iron
    private final Map<String, Float> creditLiterals = new HashMap<String, Float>();

    public Map<String, String> getConstantAssign() {
        return constantAssign;
    }


    public Map<String, Float> getTokenIntegerValue() {
        return tokenIntegerValue;
    }

    public Map<String, String> getQuestionAndReply() {
        return questionAndReply;
    }
    public Map<String, Float> getCreditLiterals() {
        return creditLiterals;
    }


}
