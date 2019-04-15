package com.unconcerned.fedoraidlegame;

import android.os.Parcel;
import android.os.Parcelable;

public class TFquiz extends Quiz {

    public TFquiz(String question, String answer) {
        super(question, answer);
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
        return "TF";
    }
}
