package com.merchant.service.impl.output;

import com.merchant.data.MappingData;
import com.merchant.util.RomanToDecimal;

import java.util.ArrayList;

public class QuestionCompareable extends Question {
    @Override
    public void handle(MappingData data, String query) throws Exception {

        String[] queries = query.split("than");
        if (queries.length == 2) {
            ArrayList<String> tokenValue = splitQuery(query);
            StringBuilder query1 = new StringBuilder();
            StringBuilder query2 = new StringBuilder();
            StringBuilder queryRoman1 = new StringBuilder();
            StringBuilder queryRoman2 = new StringBuilder();
            boolean isAppendForQuery1 = true;
            String element = null;
            String element2 = null;
            for (String aTokenValue : tokenValue) {
                if (isAppendForQuery1) {
                    element = decodeQuery1(data, query1, queryRoman1, element, aTokenValue);
                } else {
                    element2 = decodeQuery1(data, query2, queryRoman2, element2, aTokenValue);
                }
                if (aTokenValue.equals("than")) {
                    isAppendForQuery1 = false;
                }
            }

            float elementValue1 = new RomanToDecimal().romanToDecimal(queryRoman1.toString());
            float elementValue2 = new RomanToDecimal().romanToDecimal(queryRoman2.toString());
            if (element != null) {
                elementValue1 = (new RomanToDecimal().romanToDecimal(queryRoman1.toString()) * data.getCreditLiterals().get(element));
            }
            if (element2 != null) {
                elementValue2 = (new RomanToDecimal().romanToDecimal(queryRoman2.toString()) * data.getCreditLiterals().get(element2));
            }
            if (elementValue1 > elementValue2) {
                System.out.println(query + " " + query1.toString() + "greater than " + query2);
            } else if(elementValue1 < elementValue2) {
                System.out.println(query + " " + query1.toString() + "smaller than " + query2);
            }else{
                System.out.println(query + " " + query1.toString() + "equals " + query2);
            }

        } else {
            System.err.println(query + " : I have no idea what you are talking about");
        }
    }

    private String decodeQuery1(MappingData data, StringBuilder query1, StringBuilder queryRoman1, String element, String aTokenValue) {
        if (data.getConstantAssign().get(aTokenValue) != null) {
            query1.append(aTokenValue).append(" ");
            queryRoman1.append(data.getConstantAssign().get(aTokenValue));

        } else if (data.getCreditLiterals().get(aTokenValue) != null) {
            element = aTokenValue;
        }
        return element;
    }
}
