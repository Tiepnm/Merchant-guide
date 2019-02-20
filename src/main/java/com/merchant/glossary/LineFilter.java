package com.merchant.glossary;


public class LineFilter {

    private SentenceType type;
    private String pattern;
    private LineFilter[] linefilter;

    public static String patternAssigned = "^([A-Za-z]+) is ([I|V|X|L|C|D|M])$";
    public static String patternCredits = "^([A-Za-z]+)([A-Za-z\\s]*) is ([0-9]+) ([c|C]redits)$";
    public static String patternHowMuch = "^how much is (([A-Za-z\\s])+)\\?$";
    public static String patternHowMany = "^how many [c|C]redits is (([A-Za-z\\s])+)\\?$";
    public static String patternDoesQuestion = "^[d|D]oes (([A-Za-z\\s])+)\\?$";
    public static String patternIsQuestion = "^[I|i]s (([A-Za-z\\s])+)\\?$";
    public static String patternSplitSpace = "((?<=:)|(?=:))|( )";

    public LineFilter() {
        this.linefilter = new LineFilter[7];
        this.linefilter[0] = new LineFilter(SentenceType.ASSIGNED, patternAssigned);
        this.linefilter[1] = new LineFilter(SentenceType.CREDITS, patternCredits);
        this.linefilter[2] = new LineFilter(SentenceType.QUESTION_HOW_MUCH, patternHowMuch);
        this.linefilter[3] = new LineFilter(SentenceType.QUESTION_HOW_MANY, patternHowMany);
        this.linefilter[4] = new LineFilter(SentenceType.QUESTION_HOW_MANY, patternHowMany);
        this.linefilter[5] = new LineFilter(SentenceType.DOES_QUESTION, patternDoesQuestion);
        this.linefilter[6] = new LineFilter(SentenceType.IS_QUESTION, patternIsQuestion);
    }

    public LineFilter(SentenceType type, String pattern) {
        this.type = type;
        this.pattern = pattern;
    }

    public String getPattern() {
        return this.pattern;

    }

    public SentenceType getType() {
        return type;
    }

    public SentenceType getLineType(String line) {
        line = line.trim();
        SentenceType result = SentenceType.NOMATCH;

        boolean matched = false;

        for (int i = 0; i < linefilter.length && !matched; i++) {
            if (line.matches(linefilter[i].getPattern())) {
                matched = true;
                result = linefilter[i].getType();
            }

        }

        return result;

    }
}
