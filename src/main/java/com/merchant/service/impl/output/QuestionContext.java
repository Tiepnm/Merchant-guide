package com.merchant.service.impl.output;

import com.merchant.data.MappingData;

public class QuestionContext extends Question {
    private Question question;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public void handle(MappingData data, String query) throws Exception {
        this.question.handle(data, query);
    }
}
