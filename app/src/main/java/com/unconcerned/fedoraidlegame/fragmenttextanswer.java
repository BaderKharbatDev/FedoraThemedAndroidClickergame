package com.unconcerned.fedoraidlegame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class fragmenttextanswer extends Fragment {
    private FragmentTAListener listener;
    private TextView questionText;
    private EditText textAnswer;
    private String question;
    private Button enterButton;


    public fragmenttextanswer() {
        //Empty
    }

    @SuppressLint("ValidFragment")
    public fragmenttextanswer(String question) {
        this.question = question;
    }

    public interface FragmentTAListener {
        void onInputTASent(String i);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragmenttextanswer, container, false);
        questionText = v.findViewById(R.id.name);
        setQuizText(question);
        textAnswer = v.findViewById(R.id.textAnswer);
        enterButton = v.findViewById(R.id.enterButton);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = textAnswer.getText().toString();
                listener.onInputTASent(input);
            }
        });
        return v;
    }

    public void setQuizText(String question){
        questionText.setText(question);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentTAListener) {
            listener = (FragmentTAListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
