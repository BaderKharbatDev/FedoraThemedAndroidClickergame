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

public class FragmentTrueFalse extends Fragment {
    private FragmentTFListener listener;
    private TextView questionText;
    private Button enterButton;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;

    private String question;
    private String choice1Text;
    private String choice2Text;

    public FragmentTrueFalse() {
        //Empty
    }

    @SuppressLint("ValidFragment")
    public FragmentTrueFalse(String question) {
        this.question = question;
    }

    public interface FragmentTFListener {
        void onInputTFSent(int i);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragmenttruefalse, container, false);
        questionText = v.findViewById(R.id.name);
        radioButton1 = v.findViewById(R.id.choice1);
        radioButton2 = v.findViewById(R.id.choice2);
        setQuizText(question);
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
                    default:
                        index = 0;
                        break;
                }
                listener.onInputTFSent(index);
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
        if (context instanceof FragmentTFListener) {
            listener = (FragmentTFListener) context;
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
