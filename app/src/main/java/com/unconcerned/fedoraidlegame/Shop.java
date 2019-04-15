package com.unconcerned.fedoraidlegame;

import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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

public class Shop extends AppCompatActivity {
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



    TextView countText;

    private RecyclerView mRecyclerView;
    private HatAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Hat> hatList = new ArrayList<Hat>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new HatAdapter(hatList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new HatAdapter.OnItemClickListener() {
            @Override
            public void checkGet(int position) {
                if (hatList.get(position).getCost() <= tipCounter[0] && hatList.get(position).isOwnedBool() == false){
                    tipCounter[0] += -1*hatList.get(position).getCost();

                    if (tipPower[0] < hatList.get(position).getPower()){
                        tipPower[0] = hatList.get(position).getPower();
                    }
                    hatList.get(position).setOwnedBool(true);
                    hatList.get(position).changeGetImage();
                    ownedArray[position] = true;
                    save();
                    updateCountText();
                    mAdapter.notifyItemChanged(position);
                } else {
                    if (hatList.get(position).isOwnedBool() == true){
                        Toast.makeText(Shop.this, "You already own this hat!",
                                Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(Shop.this, "Not enough Moolah $$$",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void equipStuff(int position) {
                if ( hatList.get(position).isOwnedBool() ) {
                    for (int i = 0; i < hatList.size(); i++){
                        hatList.get(i).setEquiped(false);
                    }
                    hatList.get(position).setEquiped(true);

                    for (int i = 0; i < hatList.size(); i++){
                        mAdapter.notifyItemChanged(i);
                    }

                    //changed currentHat resource
                    equipedHat[0] = position;
                    save();
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
        //List of Hats
        hatList.add(new Hat("Simple Fedora", "The key to a women's heart.",1, 0, R.drawable.testimage, true, false));
        hatList.add(new Hat("Pink Fedora", "A tribute to chin chin.",2, 100, R.drawable.hat1, false, false));
        hatList.add(new Hat("Nacho Bowl Fedora", "Good for parties!",3, 300, R.drawable.hat2, false, false));
        hatList.add(new Hat("Great Fedora", "Make Fedoras Great Again.",5, 1000, R.drawable.hat3, false, false));
        hatList.add(new Hat("Boss Fedora", "Juice Em...",15, 10000, R.drawable.hat4, false,false));
        hatList.add(new Hat("Undercover Fedora", "Linux Beast.",20, 17000, R.drawable.hat5, false, false));
        hatList.add(new Hat("Sorting Fedora", "Its thinking...",30, 20000, R.drawable.hat6, false, false));
        hatList.add(new Hat("Senpai Fedora", "SASUKEEEEEE!.",45, 26000, R.drawable.hat7, false, false));
        hatList.add(new Hat("Gangster Fedora", "De Donde Eres Hombre?",69, 41000, R.drawable.hat8, false, false));
        hatList.add(new Hat("Double Fedora", "Double Trouble!.",75, 50000, R.drawable.hat9, false, false));
        hatList.add(new Hat("Big Fedora", "B I G Y O S H I",80, 80000, R.drawable.hat10, false, false));
        hatList.add(new Hat("Balanced Fedora", "Like All Things Should Be.",100, 100000, R.drawable.hat11, false, false));
        hatList.add(new Hat("Fedora", "Description.",150, 200000, R.drawable.testimage, false, false));
        hatList.add(new Hat("Super Fedora", "Description.",175, 250000, R.drawable.testimage, false, false));
        hatList.add(new Hat("Fedora", "Description.",200, 300000, R.drawable.testimage, false, false));
        hatList.add(new Hat("Fedora", "Description.",  500, 1000000, R.drawable.testimage, false, false));
        hatList.add(new Hat("Fedora", "Description.",  700, 2000000, R.drawable.testimage, false, false));
        hatList.add(new Hat("Fedora", "Description.",  1000, 10000000, R.drawable.testimage, false, false));
        hatList.add(new Hat("Fedora of Hope", "This hat speaks for itself.",  10000, 100000000, R.drawable.hatfinal, false, false));
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
        for (int i = 1; i < hatList.size(); i++){
            if (ownedArray[i]){
                hatList.get(i).setOwnedBool(true);
            } else {
                hatList.get(i).setOwnedBool(false);
            }

            if (equipedHat[0] == i){
                hatList.get(i).setEquiped(true);
            }
        }
        if (equipedHat[0] == 0){
            hatList.get(0).setEquiped(true);
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
                equipedHat[0] = 0;
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
                for (int i = 0; i < 29; i++){
                    ownedArray[i] = Boolean.parseBoolean(reader.next());
                }
                equipedHat[0] = reader.nextInt();
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
