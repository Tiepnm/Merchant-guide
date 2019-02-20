package com.merchant.service.impl.processor;

import com.merchant.data.MappingData;
import com.merchant.glossary.LineFilter;
import com.merchant.glossary.SentenceType;
import com.merchant.service.ProcessorService;
import com.merchant.service.impl.output.*;

import java.util.Map;

public class OutPutProcess implements ProcessorService<MappingData> {
    private ProcessorService<MappingData> chain;
    private LineFilter lineFilter = new LineFilter();

    @Override
    public void setNextChange(ProcessorService<MappingData> nextChange) {
        this.chain = nextChange;
    }

    public void handle(MappingData data) throws Exception {
        processReplyForQuestion(data);

    }

    public void processReplyForQuestion(MappingData data) throws Exception {
        Map<String, String> map = data.getQuestionAndReply();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            processReply(data, entry.getKey());
        }
    }

    private void processReply(MappingData data, String query) throws Exception {
        QuestionContext context = new QuestionContext();
        Question question = new QuestionHowMuch();
        SentenceType lineType = this.lineFilter.getLineType(query);
        switch (lineType) {
            case QUESTION_HOW_MANY:
                question = new QuestionHowMany();
                break;
            case QUESTION_HOW_MUCH:
                question = new QuestionHowMuch();
                break;
            case DOES_QUESTION:
                question = new QuestionCompareable();
                break;
            case IS_QUESTION:
                question = new QuestionCompareable();
                break;
        }
        context.setQuestion(question);
        context.handle(data, query);
    }


}
