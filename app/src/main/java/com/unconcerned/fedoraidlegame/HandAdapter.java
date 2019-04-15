package com.unconcerned.fedoraidlegame;

import android.provider.ContactsContract;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class HandAdapter extends RecyclerView.Adapter<HandAdapter.HandViewHolder> {
    private ArrayList<Hand> mHandList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void checkGet(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class HandViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;
        public ImageView mGetImage;

        public HandViewHolder(View itemView, final OnItemClickListener listener){
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.text1);
            mTextView2 = itemView.findViewById(R.id.text2);
            mTextView3 = itemView.findViewById(R.id.text3);
            mTextView4 = itemView.findViewById(R.id.text4);
            mGetImage = itemView.findViewById(R.id.image_get);

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
        }


    }

    public HandAdapter(ArrayList<Hand> handList){
        mHandList = handList;
    }

    @NonNull
    @Override
    public HandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hat_item, parent, false);
        HandViewHolder evh = new HandViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull HandViewHolder handViewHolder, int i) {
        Hand currentHand = mHandList.get(i);
        handViewHolder.mImageView.setImageResource(currentHand.getImageLocation());
        handViewHolder.mTextView1.setText(currentHand.getText1());
        handViewHolder.mTextView2.setText(currentHand.getText2());
        if (currentHand.isOwnedBool()){
            handViewHolder.mTextView3.setText("");
            handViewHolder.mTextView4.setText("");
        } else {
            handViewHolder.mTextView3.setText(getCountText(currentHand.getCost()));
            handViewHolder.mTextView4.setText("[Tips every "+Double.toString(currentHand.getSpeed())+"s]");
        }
        handViewHolder.mGetImage.setImageResource(currentHand.changeGetImage());
    }

    @Override
    public int getItemCount() {
        return mHandList.size();
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
