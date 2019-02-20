package com.merchant.service.impl.output;

import com.merchant.data.MappingData;
import com.merchant.glossary.LineFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Question {
    public abstract void handle(MappingData data, String query) throws Exception;

    ArrayList<String> splitQuery(String query) {
        ArrayList<String> queryArray = new ArrayList<>(Arrays.asList(query.split(LineFilter.patternSplitSpace)));
        int startIndex = 0, endIndex = 0;
        for (int i = 0; i < queryArray.size(); i++) {
            if (queryArray.get(i).toLowerCase().equals("is")) {
                startIndex = i + 1;
            } else if (queryArray.get(i).toLowerCase().equals("?")) {
                endIndex = i;

            }
        }
        String[] array = queryArray.toArray(new String[queryArray.size()]);
        return new ArrayList<>(Arrays.asList(java.util.Arrays.copyOfRange(array, startIndex, endIndex)));

    }

     String outputFormatter(ArrayList<String> output) {
        return output.toString().replace(",", "").replace("[", "").replace("]", "");
    }

    static boolean isValidinput(String query) {
        Pattern regex = Pattern.compile("[$&+,:;=@#|]");
        Matcher matcher = regex.matcher(query);
        if (matcher.find()) {
            return false;
        } else {
            return true;
        }

    }
}
