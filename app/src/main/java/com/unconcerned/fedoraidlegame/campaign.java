package com.unconcerned.fedoraidlegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class campaign extends AppCompatActivity {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign);

        ImageButton backButton = (ImageButton) findViewById(R.id.backButton);
        //backButton.setImageResource(R.drawable.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageButton b1 = (ImageButton) findViewById(R.id.button);
        if (campaignArray[0] == false){ b1.setBackgroundResource(R.drawable.monstericon); }
        else { b1.setBackgroundResource(R.drawable.monstericonsaved); }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = 2;
                int buttonNumber = 0;
                if (getOwnedHats() < n) {
                    Toast.makeText(campaign.this, "You need to own atleast " + n + " fedoras before facing this foe!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(campaign.this, combat.class);
                    i.putExtra("characterIndex", buttonNumber);
                    startActivity(i);
                }
            }
        });

        ImageButton b2 = (ImageButton) findViewById(R.id.button2);
        if (campaignArray[1] == false){ b2.setBackgroundResource(R.drawable.monstericon); }
        else { b2.setBackgroundResource(R.drawable.monstericonsaved); }
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = 4;
                int buttonNumber = 1;
                if (!campaignArray[buttonNumber - 1]){
                    Toast.makeText(campaign.this, "Can't Skip, Gotta Beat the Newb Behind Me.",
                            Toast.LENGTH_LONG).show();
                } else if (getOwnedHats() < n) {
                    Toast.makeText(campaign.this, "You need to own atleast " + n + " fedoras before facing this foe!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(campaign.this, combat.class);
                    i.putExtra("characterIndex", buttonNumber);
                    startActivity(i);
                }
            }
        });

        ImageButton b3 = (ImageButton) findViewById(R.id.button3);
        if (campaignArray[2] == false){ b3.setBackgroundResource(R.drawable.monstericon); }
        else { b3.setBackgroundResource(R.drawable.monstericonsaved); }
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = 5;
                int buttonNumber = 2;
                if (!campaignArray[buttonNumber - 1]){
                    Toast.makeText(campaign.this, "Can't Skip, Gotta Beat the Newb Behind Me.",
                            Toast.LENGTH_LONG).show();
                } else if (getOwnedHats() < n) {
                    Toast.makeText(campaign.this, "You need to own atleast " + n + " fedoras before facing this foe!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(campaign.this, combat.class);
                    i.putExtra("characterIndex", buttonNumber);
                    startActivity(i);
                }
            }
        });

        ImageButton b4 = (ImageButton) findViewById(R.id.button4);
        if (campaignArray[3] == false){ b4.setBackgroundResource(R.drawable.monstericon); }
        else { b4.setBackgroundResource(R.drawable.monstericonsaved); }
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = 7;
                int buttonNumber = 3;
                if (!campaignArray[buttonNumber - 1]){
                    Toast.makeText(campaign.this, "Can't Skip, Gotta Beat the Newb Behind Me.",
                            Toast.LENGTH_LONG).show();
                } else if (getOwnedHats() < n) {
                    Toast.makeText(campaign.this, "You need to own atleast " + n + " fedoras before facing this foe!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(campaign.this, combat.class);
                    i.putExtra("characterIndex", buttonNumber);
                    startActivity(i);
                }
            }
        });

        ImageButton b5 = (ImageButton) findViewById(R.id.button5);
        if (campaignArray[4] == false){ b5.setBackgroundResource(R.drawable.monstericon); }
        else { b5.setBackgroundResource(R.drawable.monstericonsaved); }
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = 9;
                int buttonNumber = 4;
                if (!campaignArray[buttonNumber - 1]){
                    Toast.makeText(campaign.this, "Can't Skip, Gotta Beat the Newb Behind Me.",
                            Toast.LENGTH_LONG).show();
                } else if (getOwnedHats() < n) {
                    Toast.makeText(campaign.this, "You need to own atleast " + n + " fedoras before facing this foe!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(campaign.this, combat.class);
                    i.putExtra("characterIndex", buttonNumber);
                    startActivity(i);
                }
            }
        });

        ImageButton b6 = (ImageButton) findViewById(R.id.button7);
        if (campaignArray[5] == false){ b6.setBackgroundResource(R.drawable.monstericon); }
        else { b6.setBackgroundResource(R.drawable.monstericonsaved); }
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = 11;
                int buttonNumber = 5;
                if (!campaignArray[buttonNumber - 1]){
                    Toast.makeText(campaign.this, "Can't Skip, Gotta Beat the Newb Behind Me.",
                            Toast.LENGTH_LONG).show();
                } else if (getOwnedHats() < n) {
                    Toast.makeText(campaign.this, "You need to own atleast " + n + " fedoras before facing this foe!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(campaign.this, combat.class);
                    i.putExtra("characterIndex", buttonNumber);
                    startActivity(i);
                }
            }
        });

        ImageButton b7 = (ImageButton) findViewById(R.id.button8);
        if (campaignArray[6] == false){ b7.setBackgroundResource(R.drawable.monstericon); }
        else { b7.setBackgroundResource(R.drawable.monstericonsaved); }
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = 13;
                int buttonNumber = 6;
                if (!campaignArray[buttonNumber - 1]){
                    Toast.makeText(campaign.this, "Can't Skip, Gotta Beat the Newb Behind Me.",
                            Toast.LENGTH_LONG).show();
                } else if (getOwnedHats() < n) {
                    Toast.makeText(campaign.this, "You need to own atleast " + n + " fedoras before facing this foe!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(campaign.this, combat.class);
                    i.putExtra("characterIndex", buttonNumber);
                    startActivity(i);
                }
            }
        });

        ImageButton b8 = (ImageButton) findViewById(R.id.button9);
        if (campaignArray[7] == false){ b8.setBackgroundResource(R.drawable.monstericon); }
        else { b8.setBackgroundResource(R.drawable.monstericonsaved); }
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = 15;
                int buttonNumber = 7;
                if (!campaignArray[buttonNumber - 1]){
                    Toast.makeText(campaign.this, "Can't Skip, Gotta Beat the Newb Behind Me.",
                            Toast.LENGTH_LONG).show();
                } else if (getOwnedHats() < n) {
                    Toast.makeText(campaign.this, "You need to own atleast " + n + " fedoras before facing this foe!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(campaign.this, combat.class);
                    i.putExtra("characterIndex", buttonNumber);
                    startActivity(i);
                }
            }
        });

        ImageButton b9 = (ImageButton) findViewById(R.id.button10);
        if (campaignArray[8] == false){ b9.setBackgroundResource(R.drawable.monstericon); }
        else { b9.setBackgroundResource(R.drawable.monstericonsaved); }
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = 18;
                int buttonNumber = 8;
                if (!campaignArray[buttonNumber - 1]){
                    Toast.makeText(campaign.this, "Can't Skip, Gotta Beat the Newb Behind Me.",
                            Toast.LENGTH_LONG).show();
                } else if (getOwnedHats() < n) {
                    Toast.makeText(campaign.this, "You need to own atleast " + n + " fedoras before facing this foe!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(campaign.this, combat.class);
                    i.putExtra("characterIndex", buttonNumber);
                    startActivity(i);
                }
            }
        });

        ImageButton b10 = (ImageButton) findViewById(R.id.button11);
        if (campaignArray[9] == false){ b10.setBackgroundResource(R.drawable.monstericon); }
        else { b10.setBackgroundResource(R.drawable.monstericonsaved); }
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = 19;
                int buttonNumber = 9;
                if (!campaignArray[buttonNumber - 1]){
                    Toast.makeText(campaign.this, "Can't Skip, Gotta Beat the Newb Behind Me.",
                            Toast.LENGTH_LONG).show();
                } else if (getOwnedHats() < n) {
                    Toast.makeText(campaign.this, "'Come back when you have all the hats'",
                            Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(campaign.this, combat.class);
                    i.putExtra("characterIndex", buttonNumber);
                    startActivity(i);
                }
            }
        });
    }

    private int getOwnedHats() {
        int n = 0;
        for (int i = 0; i < ownedArray.length; i++){
            if (ownedArray[i] == true){
                n++;
            }
        }
        return n;
    }

    public void rewardPlayer(int i) {
        campaignArray[i] = true;
        //give player stuff


        save();
    }

    //does this first
    @Override
    protected void onStart() {
        super.onStart();
        load();
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

    @Override
    public void onBackPressed() {
        finish();
    }
}
