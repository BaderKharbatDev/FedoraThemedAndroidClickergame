package com.unconcerned.fedoraidlegame;

import android.provider.ContactsContract;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class HatAdapter extends RecyclerView.Adapter<HatAdapter.HatViewHolder> {
    private ArrayList<Hat> mHatList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void checkGet(int position);

        void equipStuff(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    public static class HatViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;
        public ImageView mGetImage;
        public ImageView mEquipImage;

        public HatViewHolder(View itemView, final OnItemClickListener listener){
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.text1);
            mTextView2 = itemView.findViewById(R.id.text2);
            mTextView3 = itemView.findViewById(R.id.text3);
            mTextView4 = itemView.findViewById(R.id.text4);
            mGetImage = itemView.findViewById(R.id.image_get);
            mEquipImage = itemView.findViewById(R.id.equipButton);

            mGetImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.checkGet(position);
                        }
                    }
                }
            });

            mEquipImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.equipStuff(position);
                        }
                    }
                }
            });
        }


    }

    public HatAdapter(ArrayList<Hat> hatList){
        mHatList = hatList;
    }

    @NonNull
    @Override
    public HatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hat_item, parent, false);
        HatViewHolder evh = new HatViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull HatViewHolder hatViewHolder, int i) {
        Hat currentHat = mHatList.get(i);
        hatViewHolder.mImageView.setImageResource(currentHat.getImageLocation());
        if (mHatList.get(i).isOwnedBool()){
            hatViewHolder.mEquipImage.setImageResource(mHatList.get(i).changeEquipImage());
        } else {
            hatViewHolder.mEquipImage.setImageResource(android.R.color.transparent);
        }
        hatViewHolder.mTextView1.setText(currentHat.getText1());
        hatViewHolder.mTextView2.setText(currentHat.getText2());
        if (currentHat.isOwnedBool()){
            hatViewHolder.mTextView3.setText("");
            hatViewHolder.mTextView4.setText("");
        } else {
            hatViewHolder.mTextView3.setText(getCountText(currentHat.getCost()));
            hatViewHolder.mTextView4.setText("[+"+Integer.toString(currentHat.getPower())+"]");
        }
        hatViewHolder.mGetImage.setImageResource(currentHat.changeGetImage());
    }

    @Override
    public int getItemCount() {
        return mHatList.size();
    }

    private String getCountText(long l) {
        String str = Long.toString(l);
        int len = str.length();

        if (l == 0){return "";}

        if (l >= 1000000000) {
            len += -9;
            str = str.substring(0,len) + "."
                    + str.charAt(len) + " B";
        } else if (l >= 1000000) {
            len += -6;
            str = str.substring(0,len) + "."
                    + str.charAt(len) + " M";
        } else if(l >= 1000){
            len += -3;
            str = str.substring(0,len) + ","
                    + str.substring(len, len+3);
        }
        return "$"+str;
    }
}
