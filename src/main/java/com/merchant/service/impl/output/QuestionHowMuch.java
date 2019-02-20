package com.merchant.service.impl.output;

import com.merchant.data.MappingData;
import com.merchant.util.RomanToDecimal;

import java.util.ArrayList;

public class QuestionHowMuch extends Question {
    @Override
    public void handle(MappingData data, String query) {
        try {
            if (isValidinput(query)) {
                StringBuilder tokenValueToRoman = new StringBuilder();
                ArrayList<String> tokenValue = splitQuery(query);
                for (String aTokenValue : tokenValue) {
                    tokenValueToRoman.append(data.getConstantAssign().get(aTokenValue));
                }
                float value = new RomanToDecimal().romanToDecimal(tokenValueToRoman.toString());
                tokenValue.add("is");
                tokenValue.add(Float.toString(value));
                System.out.println(query + " " + outputFormatter(tokenValue));
            } else {
                System.err.println(query + " : I have no idea what you are talking about");
            }
        } catch (Exception e) {
            System.err.println(query + " : I have no idea what you are talking about");
        }
    }

}
