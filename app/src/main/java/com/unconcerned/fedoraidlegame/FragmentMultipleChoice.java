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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class FragmentMultipleChoice extends Fragment {
    private FragmentMCListener listener;
    private TextView questionText;
    private Button enterButton;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;

    private String question;
    private String choice1Text;
    private String choice2Text;
    private String choice3Text;

    public FragmentMultipleChoice() {
        //Empty
    }

    @SuppressLint("ValidFragment")
    public FragmentMultipleChoice(String question, String choice1Text, String choice2Text, String choice3Text) {
        this.question = question;
        this.choice1Text = choice1Text;
        this.choice2Text = choice2Text;
        this.choice3Text = choice3Text;
    }

    public interface FragmentMCListener {
        void onInputMCSent(int i);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentmultiplechoice, container, false);
        questionText = v.findViewById(R.id.name);
        radioButton1 = v.findViewById(R.id.choice1);
        radioButton2 = v.findViewById(R.id.choice2);
        radioButton3 = v.findViewById(R.id.choice3);
        setQuizText(question, choice1Text, choice2Text, choice3Text);
        radioGroup = v.findViewById(R.id.RadioGroup);
        enterButton = v.findViewById(R.id.enterButton);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int input = radioGroup.getCheckedRadioButtonId();
                int index;
                switch(input) {
                    case (R.id.choice1):
                        index = 1;
                        break;
                    case (R.id.choice2):
                        index = 2;
                        break;
                    case (R.id.choice3):
                        index = 3;
                        break;
                    default:
                        index = 0;
                        break;
                }
                listener.onInputMCSent(index);
            }
        });
        return v;
    }

    public void setQuizText(String question, String choice1Text, String choice2Text, String choice3Text){
        questionText.setText(question);
        radioButton1.setText(choice1Text);
        radioButton2.setText(choice2Text);
        radioButton3.setText(choice3Text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentMCListener) {
            listener = (FragmentMCListener) context;
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
