package com.unconcerned.fedoraidlegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class combat extends AppCompatActivity implements FragmentMultipleChoice.FragmentMCListener, FragmentTrueFalse.FragmentTFListener, fragmenttextanswer.FragmentTAListener {
    private static final String FILE_NAME = "FedoraGameSaveFile.txt";
    private long[] tipCounter = new long[1];
    private int[] tipPower = new int[1];
    private double[] tipSpeed = new double[1];
    private int[] tipDuration = new int[1];
    private long[] lastTipDate = new long[1];
    private boolean[] ownedArray = new boolean[30];
    private boolean[] ownedHandArray = new boolean[10];
    private int[] equipedHat = new int[1];
    private boolean[] campaignArray = new boolean[30];

    private combatmonster combatCharacter;
    private ArrayList<combatmonster> combatList = new ArrayList<combatmonster>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);

        //Adds Monsters to the List
        combatList.add(new combatmonster("Samurai Mystery Man", "Power Level:\n???", "You feel a dark presence coming from this mysterious man.", R.drawable.testmonsterimage,
                new MCquiz("You stumble upon this mysterious man and he just stares at you, after realizing that he might be evil you decide to:", "Give him the good ole 1, 2",
                        "Just keep staring at him", "Roast this fool", "1")));

        combatList.add(new combatmonster("Old Man", "Power Level:\nNewb", "You stumble upon an old man on the side of the road, he looks hungry.", R.drawable.testmonsterimage,
                new MCquiz("'SOOO I hear you're trying to defeat all evil, how about some money for an innocent old man like myself??'", "Give him some spare change",
                        "Yell 'No way old man, get a job!'", "Send the old man to the shadow realm...", "3")));

        combatList.add(new combatmonster("Dog Guardian", "Power Level:\nTitan", "On your journey to peace you encounter a giant frickin dog man.", R.drawable.testmonsterimage,
                new TAquiz("He asks you, whats the integral of 1?'", "x")));


        TextView questionText = (TextView) findViewById(R.id.name);
        TextView rankText = (TextView) findViewById(R.id.rank);
        TextView descriptionText = (TextView) findViewById(R.id.descriptionText);
        ImageView characterImage = (ImageView) findViewById(R.id.monsterImage);

        //Gets the passed character
        Intent intent = getIntent();
        int index = intent.getIntExtra("characterIndex", 0);
        combatCharacter = combatList.get(index);

        //Sets the character stats
        questionText.setText(combatCharacter.getName());
        rankText.setText(combatCharacter.getPower());
        descriptionText.setText(combatCharacter.getDescription());
        characterImage.setImageResource(combatCharacter.getImage());

        //Checks quiz type
        if (combatCharacter.getQuizType().equals("MC")) {
            MCquiz q = (MCquiz) combatCharacter.getQuiz();
            FragmentMultipleChoice frag = new FragmentMultipleChoice(combatCharacter.getQuiz().getQuestion(), q.getAnswers()[0],
                    q.getAnswers()[1], q.getAnswers()[2]);
            FragmentManager fragmentManager = getSupportFragmentManager();
            //Set Quiz Text?
            //frag.setQuizText(combatCharacter.getQuiz().getQuestion(), q.getAnswers()[0], q.getAnswers()[1], q.getAnswers()[2]);
            fragmentManager.beginTransaction().replace(R.id.fragment, frag).commit();
        } else if(combatCharacter.getQuizType().equals("TF")) {
            TFquiz q = (TFquiz) combatCharacter.getQuiz();
            FragmentTrueFalse frag = new FragmentTrueFalse(combatCharacter.getQuiz().getQuestion());
            FragmentManager fragmentManager = getSupportFragmentManager();
            //Set Quiz Text?
            //frag.setQuizText(combatCharacter.getQuiz().getQuestion(), q.getAnswers()[0], q.getAnswers()[1], q.getAnswers()[2]);
            fragmentManager.beginTransaction().replace(R.id.fragment, frag).commit();
        } else {
            TAquiz q = (TAquiz) combatCharacter.getQuiz();
            fragmenttextanswer frag = new fragmenttextanswer(combatCharacter.getQuiz().getQuestion());
            FragmentManager fragmentManager = getSupportFragmentManager();
            //Set Quiz Text?
            //frag.setQuizText(combatCharacter.getQuiz().getQuestion(), q.getAnswers()[0], q.getAnswers()[1], q.getAnswers()[2]);
            fragmentManager.beginTransaction().replace(R.id.fragment, frag).commit();
        }

        Button backB = (Button) findViewById(R.id.backB);
        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public void onInputMCSent(int i) {
        if (i == 0){
            Toast.makeText(combat.this, "You didn't choose anything...",
                    Toast.LENGTH_LONG).show();
        }
        else {
            if (combatCharacter.isCorrect(i)){
                //WIN
                Toast.makeText(combat.this, "Correct!",
                        Toast.LENGTH_LONG).show();
            } else {
                //LOSE
                Toast.makeText(combat.this, "Wrong!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onInputTFSent(int i) {
        if (i == 0){
            Toast.makeText(combat.this, "You didn't choose anything...",
                    Toast.LENGTH_LONG).show();
        }
        else {
            if (combatCharacter.isCorrect(i)){
                //WIN
                Toast.makeText(combat.this, "Correct!",
                        Toast.LENGTH_LONG).show();
            } else {
                //LOSE
                Toast.makeText(combat.this, "Wrong!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onInputTASent(String i) {
        if (i.equals("")){
            Toast.makeText(combat.this, "You didn't put anything...",
                    Toast.LENGTH_LONG).show();
        }
        else {
            if (combatCharacter.isCorrect(i)){
                //WIN
                Toast.makeText(combat.this, "Correct!",
                        Toast.LENGTH_LONG).show();
            } else {
                //LOSE
                Toast.makeText(combat.this, "Wrong!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    //saves data
    public void save() {
        FileOutputStream fos = null;

        try {
            String ownedString = "";
            for (int i = 0; i < 29; i++){
                ownedString += Boolean.toString(ownedArray[i])+" ";
            }
            ownedString += Integer.toString(equipedHat[0]);
            String ownedHandString = "";
            for (int i = 0; i < 10; i++){
                ownedHandString += Boolean.toString(ownedHandArray[i])+" ";
            }
            String campaignString = "";
            for (int i = 0; i < campaignArray.length; i++){
                campaignString += Boolean.toString(campaignArray[i])+" ";
            }
            String text = Integer.toString(tipPower[0])
                    +" "+Double.toString(tipSpeed[0])
                    +" "+Integer.toString(tipDuration[0])
                    +" "+Long.toString(tipCounter[0])
                    +" "+Long.toString(lastTipDate[0])
                    +" "+ownedString
                    +" "+ownedHandString
                    +" "+campaignString;
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Loads in data
    public void load() {
        FileInputStream fis = null;

        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            int currentVersionCode = BuildConfig.VERSION_CODE;

            if (prefs.getInt("LASTVERSION", 0) < currentVersionCode) {
                tipPower[0] = 1;
                tipSpeed[0] = 0;
                tipDuration[0] = 1;
                tipCounter[0] = 0;
                lastTipDate[0] = 0;
                ownedArray[0] = true;
                for (int i = 1; i < 30; i++){
                    ownedArray[i] = false;
                }
                equipedHat[0] = 0;
                for (int i = 0; i < 10; i++){
                    ownedHandArray[i] = false;
                }
                for (int i = 0; i < 30; i++) {
                    campaignArray[i] = false;
                }
                prefs.edit().putInt("LASTVERSION", currentVersionCode).apply();
            } else {
                fis = openFileInput(FILE_NAME);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String text2;

                while ((text2 = br.readLine()) != null) {
                    sb.append(text2).append("\n");
                }
                String loadedText = sb.toString();
                Scanner reader = new Scanner(loadedText);
                tipPower[0] = Integer.parseInt(reader.next());
                tipSpeed[0] = Double.parseDouble(reader.next());
                tipDuration[0] = Integer.parseInt(reader.next());
                tipCounter[0] = Long.parseLong(reader.next());
                lastTipDate[0] = Long.parseLong(reader.next());
                for (int i = 0; i < 29; i++){
                    ownedArray[i] = Boolean.parseBoolean(reader.next());
                }
                String dummy = reader.next();
                if (dummy.equals("false")){
                    equipedHat[0] = 0;
                } else {
                    equipedHat[0] = Integer.parseInt(dummy);
                }

                for (int i = 0; i < 10; i++){
                    ownedHandArray[i] = Boolean.parseBoolean(reader.next());
                }
                int i = 0;
                while (reader.hasNext()){
                    campaignArray[i] = Boolean.parseBoolean(reader.next());
                    i++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
