package com.unconcerned.fedoraidlegame;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

public class TAquiz extends Quiz {

    public TAquiz(String question, String answer) {
        super(question, answer);
    }


    @Override
    public boolean isCorrect(Object userAnswer){
        if (((String) userAnswer).equalsIgnoreCase(super.getAnswer())){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getQuizKind() {
        return "TA";
    }
}
