package com.merchant.service.impl.output;

import com.merchant.data.MappingData;
import com.merchant.util.RomanToDecimal;

import java.util.ArrayList;

public class QuestionHowMany extends Question {
    @Override
    public void handle(MappingData data, String query) {
        try {
            if (isValidinput(query)) {
                ArrayList<String> tokenValue = splitQuery(query);
                StringBuilder tokenValueToRoman = new StringBuilder();
                String element = null;
                for (String aTokenValue : tokenValue) {
                    if (data.getConstantAssign().get(aTokenValue) != null) {
                        tokenValueToRoman.append(data.getConstantAssign().get(aTokenValue));
                    } else if (data.getCreditLiterals().get(aTokenValue) != null) {
                        element = aTokenValue;
                    } else {
                        System.err.println(query + " : I have no idea what you are talking about");
                    }
                }

                float elementValue = (new RomanToDecimal().romanToDecimal(tokenValueToRoman.toString()) * data.getCreditLiterals().get(element));
                tokenValue.add("is");
                tokenValue.add(Float.toString(elementValue));
                tokenValue.add("Credits");

                System.out.println(query + " " + outputFormatter(tokenValue));
            } else {
                System.err.println(query + " : I have no idea what you are talking about");
            }
        } catch (Exception e) {
            System.err.println(query + " : I have no idea what you are talking about");
        }
    }

}
