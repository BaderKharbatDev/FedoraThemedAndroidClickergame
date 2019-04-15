package com.unconcerned.fedoraidlegame;

import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class Casino extends AppCompatActivity {
    private static final String FILE_NAME = "FedoraGameSaveFile.txt";
    private long[] tipCounter = new long[1];
    private int[] tipPower = new int[1];
    private double[] tipSpeed = new double[1];
    private int[] tipDuration = new int[1];
    private long[] lastTipDate = new long[1];
    private boolean[] ownedArray = new boolean[30];
    private boolean[] ownedHandArray = new boolean[10];
    private boolean[] campaignArray = new boolean[30];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casino);

        Button wagerButton = (Button) findViewById(R.id.executebutton);
        Button wagerAll = (Button) findViewById(R.id.wagerAll);
        final ImageView wagerImage = (ImageView) findViewById(R.id.casinoImage);
        final TextView wagerNumber = (TextView) findViewById(R.id.wageNumber);

        wagerImage.setImageResource(R.drawable.casinodefault);

        wagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Wager
                Long number = Long.parseLong(wagerNumber.getText().toString());
                Random rand = new Random();
                int n = rand.nextInt(2);
                if (n == 0){
                    tipCounter[0] += number;
                    wagerNumber.setText("");
                    wagerImage.setImageResource(R.drawable.casinowin);
                } else {
                    tipCounter[0] += -1*number;
                    wagerNumber.setText("");
                    wagerImage.setImageResource(R.drawable.casinofail);
                }
                save();
                updateCountText();

            }
        });

        wagerAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Wager All set
                wagerNumber.setText(Long.toString(tipCounter[0]));
            }
        });


        ImageButton backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void updateCountText() {
        //Shows count text
        TextView countText = (TextView) findViewById(R.id.counterText);

        TextView counterText = (TextView) findViewById(R.id.countText);
        String str = Long.toString(tipCounter[0]);
        int len = str.length();

        if (tipCounter[0] >= 1000000000) {
            len += -9;
            str = str.substring(0,len) + "."
                    + str.charAt(len) + " B";
        } else if (tipCounter[0] >= 1000000) {
            len += -6;
            str = str.substring(0,len) + "."
                    + str.charAt(len) + " M";
        } else if(tipCounter[0] >= 1000){
            len += -3;
            str = str.substring(0,len) + ","
                    + str.substring(len, len+3);
        }
        countText.setText(str);
    }

    @Override
    protected void onStart(){
        super.onStart();
        load();
        updateCountText();
    }

    public void save() {
        FileOutputStream fos = null;

        try {
            String ownedString = "";
            for (int i = 0; i < 30; i++){
                ownedString += Boolean.toString(ownedArray[i])+" ";
            }
            String ownedHandString = "";
            for (int i = 0; i < 10; i++){
                ownedHandString += Boolean.toString(ownedHandArray[i])+" ";
            }
            String campaignString = "";
            for (int i = 0; i < 30; i++){
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

    public void load() {
        FileInputStream fis = null;

        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            int currentVersionCode = BuildConfig.VERSION_CODE;

            if (prefs.getInt("LASTVERSION", 0) < currentVersionCode) {
                tipPower[0] = 1;
                tipSpeed[0] = 3.0;
                tipDuration[0] = 1;
                tipCounter[0] = 0;
                lastTipDate[0] = 0;
                ownedArray[0] = true;
                for (int i = 1; i < 30; i++){
                    ownedArray[i] = false;
                }
                ownedHandArray[0] = true;
                for (int i = 1; i < 10; i++){
                    ownedHandArray[i] = false;
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
                for (int i = 0; i < 30; i++){
                    ownedArray[i] = Boolean.parseBoolean(reader.next());
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
