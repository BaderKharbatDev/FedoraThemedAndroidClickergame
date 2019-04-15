package com.unconcerned.fedoraidlegame;

import android.os.Parcel;
import android.os.Parcelable;

public class MCquiz extends Quiz {
    private String[] answers = new String[3];

    public MCquiz(String question, String a1, String a2, String a3, String answer) {
        super(question, answer);
        this.answers[0] = a1;
        this.answers[1] = a2;
        this.answers[2] = a3;
    }

    public String[] getAnswers() {
        return answers;
    }

    @Override
    public boolean isCorrect(Object userAnswer){
        if ((Integer) userAnswer == Integer.parseInt((String) super.getAnswer())){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getQuizKind() {
        return "MC";
    }
}
