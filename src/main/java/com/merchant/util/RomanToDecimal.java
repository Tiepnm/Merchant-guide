package com.merchant.util;

import com.merchant.error.ErrorRomanException;
import com.merchant.glossary.ErrorCode;
import com.merchant.glossary.Roman;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RomanToDecimal {

    protected static final Set<Character> THREE_TIMES_REPEATED_CHARACTERS = Stream.of('I', 'X', 'C', 'M').collect(Collectors.toSet());
    protected static final Set<Character> NOT_SUBTRACTED_CHARACTERS = Stream.of('V', 'L', 'D', 'M').collect(Collectors.toSet());
    protected int REPEAT_TIMES_ALLOW = 3;

    private final String ROMAN_REGEX = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";

    public RomanToDecimal() {

    }

    public Float romanToDecimal(String romanNumber) {

        if (Pattern.matches(ROMAN_REGEX, romanNumber)) {
            char[] charArray = romanNumber.toCharArray();
            char previousChar = ' ';

            int characterRepeatCount = 1;
            Float total = 0.0f;
            int previousCharacterOrdinal = Integer.MAX_VALUE;
            int currentCharacterOrdinal;

            for (int i = 0; i < charArray.length; i++) {
                char currentChar = charArray[i];
                int currentRomanCharNumericValue = Roman.valueOf(String.valueOf(currentChar)).getValue();

                if (previousChar != ' ') {
                    previousCharacterOrdinal = Roman.valueOf(String.valueOf(previousChar)).ordinal();
                }
                currentCharacterOrdinal = Roman.valueOf(String.valueOf(currentChar)).ordinal();

                if (currentChar == previousChar && ++characterRepeatCount < REPEAT_TIMES_ALLOW + 1 && THREE_TIMES_REPEATED_CHARACTERS.contains(currentChar)) {
                    total += currentRomanCharNumericValue;
                } else if (previousCharacterOrdinal < currentCharacterOrdinal && !NOT_SUBTRACTED_CHARACTERS.contains(previousChar)) {
                    int previousRomanCharNumericValue = Roman.valueOf(String.valueOf(previousChar)).getValue();
                    if (previousCharacterOrdinal + 2 >= currentCharacterOrdinal) {
                        total += currentRomanCharNumericValue - (2 * previousRomanCharNumericValue);
                        characterRepeatCount = 1;
                    }
                } else {
                    characterRepeatCount = 1;
                    total += currentRomanCharNumericValue;
                }
                previousChar = currentChar;
                if (total == -1)
                    break;
            }
            return total;
        } else {
            throw new ErrorRomanException(ErrorCode.TEXT_IS_NOT_ROMAN.getValue());
        }
    }
}
