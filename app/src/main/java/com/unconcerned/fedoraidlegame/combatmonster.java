package com.unconcerned.fedoraidlegame;

import android.os.Parcel;
import android.os.Parcelable;

public class combatmonster {
    private String name;
    private String power;
    private String description;
    private int image;
    private Quiz quiz;


    public combatmonster(String name, String power, String description, int image, Quiz quiz) {
        this.name = name;
        this.power = power;
        this.description = description;
        this.image = image;
        this.quiz = quiz;
    }

    public String getName() {
        return name;
    }

    public String getPower() {
        return power;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public boolean isCorrect(Object userAnswer){
        if (quiz.isCorrect(userAnswer)){
            return true;
        } else {
            return false;
        }
    }

    public String getQuizType(){
        return this.quiz.getQuizKind();
    }


/**
    //public enum QuizType {MULTIPLE_CHOICE, TRUE_FALSE, TEXT_ANSWER}
    public interface Quiz{
        boolean isCorrect(Object i);
    }

    public class MCquiz implements Quiz {
        private String[] answers = new String[3];
        private int answer;
        private String question;

        public MCquiz(String question, String a1, String a2, String a3, int answer) {
            this.question = question;
            this.answers[0] = a1;
            this.answers[1] = a2;
            this.answers[2] = a3;
            this.answer = answer;
        }

        public String[] getAnswers() {
            return answers;
        }

        public int getAnswer() {
            return answer;
        }

        public String getQuestion() {
            return question;
        }

        @Override
        public boolean isCorrect(Object userAnswer){
            if ((Integer) userAnswer == answer){
                return true;
            } else {
                return false;
            }
        }
    }

    public class TFquiz implements Quiz {
        private Boolean answer;
        private String question;

        public TFquiz(String question, boolean answer) {
            this.question = question;
            this.answer = answer;
        }

        public Boolean getAnswer() {
            return answer;
        }

        public String getQuestion() {
            return question;
        }

        @Override
        public boolean isCorrect(Object userAnswer){
            int ansInt = answer  ? 1 : 0;
            if ((Integer) userAnswer == ansInt){
                return true;
            } else {
                return false;
            }
        }
    }

    public class TAquiz implements Quiz {
        private String question;
        private int image;
        private String answer;

        public TAquiz(String question, int image, String answer) {
            this.question = question;
            this.image = image;
            this.answer = answer;
        }

        public String getQuestion() {
            return question;
        }

        public int getImage() {
            return image;
        }

        public String getAnswer() {
            return answer;
        }

        @Override
        public boolean isCorrect(Object userAnswer){
            if (((String) userAnswer).equalsIgnoreCase(answer)){
                return true;
            } else {
                return false;
            }
        }
    }
        */
}
