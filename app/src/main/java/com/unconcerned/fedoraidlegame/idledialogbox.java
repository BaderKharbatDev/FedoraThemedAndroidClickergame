package com.unconcerned.fedoraidlegame;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

@SuppressLint("ValidFragment")
public class idledialogbox extends AppCompatDialogFragment {
    private long collected;

    @SuppressLint("ValidFragment")
    public idledialogbox(long collected){
        this.collected = collected;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("")
                .setMessage("You've earned some moolah since last playing:  +" + collected)
                .setPositiveButton("Collect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
