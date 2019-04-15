package com.unconcerned.fedoraidlegame;

import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
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

public class HandShop extends AppCompatActivity {
    private static final String FILE_NAME = "FedoraGameSaveFile.txt";
    private long[] tipCounter = new long[1];
    private int[] tipPower = new int[1];
    private double[] tipSpeed = new double[1];
    private int[] tipDuration = new int[1];
    private long[] lastTipDate = new long[1];
    private boolean[] ownedArray = new boolean[30];
    private boolean[] ownedHandArray = new boolean[10];
    private boolean[] campaignArray = new boolean[30];
    private int[] equipedHat = new int[1];


    TextView countText;

    private RecyclerView mRecyclerView;
    private HandAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Hand> handList = new ArrayList<Hand>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_shop);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new HandAdapter(handList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new HandAdapter.OnItemClickListener() {
            @Override
            public void checkGet(int position) {
                if (handList.get(position).getCost() <= tipCounter[0] && !(handList.get(position).isOwnedBool())){
                    tipCounter[0] += -1*handList.get(position).getCost();
                    if (tipSpeed[0] > handList.get(position).getSpeed() || tipSpeed[0] == 0){
                        tipSpeed[0] = handList.get(position).getSpeed();
                    }
                    handList.get(position).setOwnedBool(true);
                    handList.get(position).changeGetImage();
                    ownedHandArray[position] = true;
                    save();
                    updateCountText();
                    mAdapter.notifyItemChanged(position);
                } else {
                    if (handList.get(position).isOwnedBool()){
                        Toast.makeText(HandShop.this, "You already own this hand!",
                                Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(HandShop.this, "Not enough Moolah $$$",
                                Toast.LENGTH_SHORT).show();
                    }
                }
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

    private void creatList() {
        //List of Hands
        handList.add(new Hand("Normal Hand", "Old Faithful.",3.0, 200, R.drawable.hand0, false));
        handList.add(new Hand("Italian Hand", "PASTA LASAGNA.",2.7, 1000, R.drawable.hand1, false));
        handList.add(new Hand("Circle Hand", "We all know you looked...",2.5, 10000, R.drawable.hand2, false));
        handList.add(new Hand("Animal Hand", "You know EXACTLY what this is.",2.0, 50000, R.drawable.testimage, false));
        handList.add(new Hand("Oven-Mit Hand", "For pickin up the smokin babes.",1.7, 100000, R.drawable.testimage, false));
        handList.add(new Hand("Peace Hand", "For those who are W O K E.",1.5, 300000, R.drawable.testimage, false));
        handList.add(new Hand("Wolf-Pack Hand", "Represent.",1.2, 1000000, R.drawable.testimage, false));
        handList.add(new Hand("Transmission Hand", "Over 9X10^3.",1.0, 3000000, R.drawable.testimage, false));
        handList.add(new Hand("Boi Hand", "Servin up the spiciest of tips.",.5, 10000000, R.drawable.testimage, false));
        handList.add(new Hand("Infinity Hand", "Is this even possible to get?",.1, 100000000, R.drawable.testimage, false));
    }

    @Override
    protected void onStart(){
        super.onStart();
        load();
        updateCountText();
        //Creates the list of hats
        creatList();
        //sets owned status of hats
        setOwnedArray();
    }

    private void setOwnedArray() {
        for (int i = 0; i < handList.size(); i++){
            if (ownedHandArray[i]){
                handList.get(i).setOwnedBool(true);
            } else {
                handList.get(i).setOwnedBool(false);
            }
        }
    }

    private void updateCountText() {
        //Shows count text
        countText = (TextView) findViewById(R.id.counterText);

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
}
