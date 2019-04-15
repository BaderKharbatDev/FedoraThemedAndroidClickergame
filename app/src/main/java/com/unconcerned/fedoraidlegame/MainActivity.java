package com.unconcerned.fedoraidlegame;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.icu.text.RelativeDateTimeFormatter;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {
    private static final String FILE_NAME = "FedoraGameSaveFile.txt";
    private long[] tipCounter = new long[1];
    private int[] tipPower = new int[1];
    private double[] tipSpeed = new double[1];
    private int[] tipDuration = new int[1];
    private long[] lastTipDate = new long[1];
    private boolean[] ownedArray = new boolean[30];
    private boolean[] ownedHandArray = new boolean[10];
    private int[] hatImage = new int[1];
    private int[] handImage = new int[1];
    private int[] equipedHat = new int[1];
    private boolean[] campaignArray = new boolean[30];

    //Add all hat resource names here
    private int[][] hatImageArray = {
            {R.drawable.fedorahatup0, R.drawable.fedorahatdown0}, {R.drawable.fedorahatup1, R.drawable.fedorahatdown1}, {R.drawable.fedorahatup2, R.drawable.fedorahatdown2},
            {R.drawable.fedorahatup3, R.drawable.fedorahatdown3}, {R.drawable.fedorahatup4, R.drawable.fedorahatdown4}, {R.drawable.fedorahatup5, R.drawable.fedorahatdown5},
            {R.drawable.fedorahatup6, R.drawable.fedorahatdown6}, {R.drawable.fedorahatup7, R.drawable.fedorahatdown7}, {R.drawable.fedorahatup8, R.drawable.fedorahatdown8},
            {R.drawable.fedorahatup9, R.drawable.fedorahatdown9}, {R.drawable.fedorahatup10, R.drawable.fedorahatdown10}, {R.drawable.fedorahatup11, R.drawable.fedorahatdown11},
            {R.drawable.fedorahatup0, R.drawable.fedorahatdown0}, {R.drawable.fedorahatup0, R.drawable.fedorahatdown0}, {R.drawable.fedorahatup0, R.drawable.fedorahatdown0},
            {R.drawable.fedorahatup0, R.drawable.fedorahatdown0}, {R.drawable.fedorahatup0, R.drawable.fedorahatdown0}, {R.drawable.fedorahatup0, R.drawable.fedorahatdown0},
            {R.drawable.fedorahatuplast, R.drawable.fedorahatdownlast}, {R.drawable.fedorahatuplast, R.drawable.fedorahatdownlast}, {R.drawable.fedorahatup0, R.drawable.fedorahatdown0},
            {R.drawable.fedorahatup0, R.drawable.fedorahatdown0}, {R.drawable.fedorahatup0, R.drawable.fedorahatdown0}, {R.drawable.fedorahatup0, R.drawable.fedorahatdown0},
            {R.drawable.fedorahatup0, R.drawable.fedorahatdown0}, {R.drawable.fedorahatup0, R.drawable.fedorahatdown0}, {R.drawable.fedorahatup0, R.drawable.fedorahatdown0},
            {R.drawable.fedorahatup0, R.drawable.fedorahatdown0}, {R.drawable.fedorahatup0, R.drawable.fedorahatdown0}, {R.drawable.fedorahatup0, R.drawable.fedorahatdown0},
    };
    private int[][] handImageArray = {
            {R.drawable.fedorahandup0, R.drawable.fedorahanddown0}, {R.drawable.fedorahandup1, R.drawable.fedorahanddown1}, {R.drawable.fedorahandup2, R.drawable.fedorahanddown2},
            {R.drawable.fedorahandup0, R.drawable.fedorahanddown0}, {R.drawable.fedorahandup0, R.drawable.fedorahanddown0}, {R.drawable.fedorahandup0, R.drawable.fedorahanddown0},
            {R.drawable.fedorahandup0, R.drawable.fedorahanddown0}, {R.drawable.fedorahandup0, R.drawable.fedorahanddown0}, {R.drawable.fedorahandup0, R.drawable.fedorahanddown0},
            {R.drawable.fedorahandup0, R.drawable.fedorahanddown0}
    };

    //On create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onStart();

        //On button press
        final ImageButton countButton = (ImageButton) findViewById(R.id.countButton);
        final ImageView hatImageOverlay = (ImageView) findViewById(R.id.hatImage);
        final ImageView handImageOverlay = (ImageView) findViewById(R.id.handImage);

        countButton.setBackgroundResource(R.drawable.fedoratipguyup);
        hatImageOverlay.setBackgroundResource(hatImageArray[hatImage[0]][0]);
        handImageOverlay.setBackgroundResource(handImageArray[handImage[0]][0]);


        countButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    countButton.setBackgroundResource(R.drawable.fedoratipguydown);
                    hatImageOverlay.setBackgroundResource(hatImageArray[hatImage[0]][1]);
                    handImageOverlay.setBackgroundResource(handImageArray[handImage[0]][1]);

                    tipCounter[0] += tipPower[0];
                    updateCountText();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    countButton.setBackgroundResource(R.drawable.fedoratipguyup);
                    hatImageOverlay.setBackgroundResource(hatImageArray[hatImage[0]][0]);
                    handImageOverlay.setBackgroundResource(handImageArray[handImage[0]][0]);
                    plusAnimation();
                }
                return true;
            }
        });

        //Campaign Button Press
        final ImageButton campaign = (ImageButton) findViewById(R.id.fightButton);
        campaign.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    campaign.setBackgroundResource(R.drawable.campaignbuttondown);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    campaign.setBackgroundResource(R.drawable.campaignbuttonup);
                    save();
                    Intent intent = new Intent(MainActivity.this, campaign.class);
                    startActivity(intent);
                }

                return true;
            }
        });

        //Shop Button Press
        final ImageButton shopButton = (ImageButton) findViewById(R.id.shopButton);
        shopButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    shopButton.setBackgroundResource(R.drawable.fedorashopdown);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    shopButton.setBackgroundResource(R.drawable.fedorashop);
                    save();
                    Intent intent = new Intent(MainActivity.this, Shop.class);
                    startActivity(intent);
                }

                return true;
            }
        });

        //HandShop Button
        final ImageButton handShop = (ImageButton) findViewById(R.id.handShop);
        handShop.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    handShop.setBackgroundResource(R.drawable.handshopdown);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    handShop.setBackgroundResource(R.drawable.handshop);
                    save();
                    Intent intent = new Intent(MainActivity.this, HandShop.class);
                    startActivity(intent);
                }

                return true;
            }
        });

        //Casino Button
        final ImageButton casinoButton = (ImageButton) findViewById(R.id.casinoButton);
        casinoButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    casinoButton.setBackgroundResource(R.drawable.fiftydown);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    casinoButton.setBackgroundResource(R.drawable.fifty);
                    save();
                    Intent intent = new Intent(MainActivity.this, Casino.class);
                    startActivity(intent);
                }

                return true;
            }
        });

        //Handles giving players moolah after opening the app
        final Handler handlerIdle = new Handler();
        handlerIdle.postDelayed(new Runnable() {
            @Override
            public void run() {
                givePlayerTips();
            }
        }, 750);

        //Handles the idle feature
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                countButton.setBackgroundResource(R.drawable.fedoratipguydown);
                hatImageOverlay.setBackgroundResource(hatImageArray[hatImage[0]][1]);
                handImageOverlay.setBackgroundResource(handImageArray[handImage[0]][1]);
                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        countButton.setBackgroundResource(R.drawable.fedoratipguyup);
                        hatImageOverlay.setBackgroundResource(hatImageArray[hatImage[0]][0]);
                        handImageOverlay.setBackgroundResource(handImageArray[handImage[0]][0]);
                    }
                }, 200);

                tipCounter[0] += tipPower[0];
                plusAnimation();
                updateCountText();

                handler.postDelayed(this, (long)(tipSpeed[0] * 1000));
            }
        };

        if (tipSpeed[0] != 0) { runnable.run(); }
    }

    //Shows the tipping power as you gain more points
    public void plusAnimation() {
        final TextView plusText = (TextView) findViewById(R.id.plustext);
        plusText.setText("+"+tipPower[0]);
        ObjectAnimator yslide = ObjectAnimator.ofFloat(plusText,"y", 500f);
        yslide.setDuration(300);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(yslide);
        animatorSet.start();


        //Handles giving players moolah after opening the app
        final Handler handlerIdle = new Handler();
        handlerIdle.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator yslide = ObjectAnimator.ofFloat(plusText,"y",550f);
                yslide.setDuration(1);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(yslide);
                animatorSet.start();
                plusText.setText("");

            }
        }, 300);
    }

    //Updates the countText
    private void updateCountText() {
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
        counterText.setText(str);
    }

     //Loads in which hat is bought
     private void loadHat() {
        /**
         int hatpos =0;
         for (int i = ownedArray.length - 1; i > -1; i--){
             if (ownedArray[i] == true){
                 hatpos = i;
                 break;
             }
         }
         */
         int handpos = 0;
         for (int i = ownedHandArray.length - 1; i > -1; i--){
             if (ownedHandArray[i] == true){
                 handpos = i;
                 break;
             }
         }
         hatImage[0] = equipedHat[0];
         handImage[0] = handpos;

         final ImageButton countButton = (ImageButton) findViewById(R.id.countButton);
         final ImageView hatImageOverlay = (ImageView) findViewById(R.id.hatImage);
         final ImageView handImageOverlay = (ImageView) findViewById(R.id.handImage);

         countButton.setBackgroundResource(R.drawable.fedoratipguyup);
         hatImageOverlay.setBackgroundResource(hatImageArray[hatImage[0]][0]);
         handImageOverlay.setBackgroundResource(handImageArray[handImage[0]][0]);
     }

     //Gives player idle points
     private void givePlayerTips() {
         if(lastTipDate[0] != 0 && tipSpeed[0] > 0){
             Date d1 = new Date(lastTipDate[0]);
             Date d2 = new Date();
             long seconds = (d2.getTime()-d1.getTime())/1000;
             int collected = (int) ((tipPower[0]/tipSpeed[0])*seconds);
             openPlayerTipDialog(collected*3/4);
             tipCounter[0] += collected*3/4;
         }
         updateCountText();
     }

     //opens dialog box, probably wont be used
     private void openPlayerTipDialog(long collected) {
        idledialogbox ib = new idledialogbox(collected);
        ib.show(getSupportFragmentManager(), "");
     }

    //does this first
    @Override
    protected void onStart(){
        super.onStart();
        load();
        updateCountText();
        loadHat();
    }

    //On stop
    @Override
    protected void onStop(){
        super.onStop();
        lastTipDate[0] = System.currentTimeMillis();
        save();
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

    //Makes sure nothing happens when you press the back button
    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
}
