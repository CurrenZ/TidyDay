package com.czeng.tidyday.tools;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.czeng.tidyday.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private String current_card_type = "";
    private List<String> Titleset;
    private List<String> Subtitleset;
    private int[] Imageset;

    static class MyViewHolder extends RecyclerView.ViewHolder{
        CardView mCardView;
        TextView card_title_tv, card_subtitle_tv;
        ImageView card_image;
        MyViewHolder(View v){
            super(v);

            mCardView = (CardView) v.findViewById(R.id.single_card);
            card_image = (ImageView) v.findViewById(R.id.image_in_card);
            card_title_tv = (TextView) v.findViewById(R.id.card_tittle);
            card_subtitle_tv = (TextView) v.findViewById(R.id.card_sub_tittle);
        }
    }

    public MyAdapter(String card_type, List<String> cards_tittle, List<String> cards_detail){
        //Imageset = card_images;
        current_card_type = card_type;
        Titleset = cards_tittle;
        Subtitleset = cards_detail;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_card, parent, false);
        if (Objects.equals(current_card_type, "memo_card")) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.memo_card, parent, false);
        }
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        holder.card_title_tv.setText(Titleset.get(position));
        holder.card_subtitle_tv.setText(Subtitleset.get(position));
    }

    @Override
    public int getItemCount() { return Titleset.size(); }


    //DISMISS
    public void dismissCard(int pos){
        //Titleset.remove(pos);
    }
}
