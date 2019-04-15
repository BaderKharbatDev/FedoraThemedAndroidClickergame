package com.unconcerned.fedoraidlegame;

import android.os.Parcelable;

//public enum QuizType {MULTIPLE_CHOICE, TRUE_FALSE, TEXT_ANSWER}
abstract class Quiz {
    private String question;
    private String answer;

    public Quiz(String question, String answer){
        this.question = question;
        this.answer = answer;
    }

    abstract boolean isCorrect(Object i);
    abstract String getQuizKind();
    public String getQuestion() {
        return this.question;
    }
    public String getAnswer(){
        return this.answer;
    }
}

