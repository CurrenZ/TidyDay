package com.czeng.tidyday.MemoRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.czeng.tidyday.MemoDataObject.MemoCard;
import com.czeng.tidyday.R;

import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoHolder>{

    Context c;
    ArrayList<MemoCard> memoCards;

    public MemoAdapter(Context c, ArrayList<MemoCard> memoCards) {
        this.c = c;
        this.memoCards = memoCards;
    }

    @Override
    public MemoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        MemoHolder holder = new MemoHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MemoHolder holder, int position) {
        holder.titletxt.setText(memoCards.get(position).getTitle());
        holder.subtitletxt.setText(memoCards.get(position).getSubtitle());
        //holder.img.getImageResource(goalCards.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return memoCards.size();
    }

    // DISMISS
     public void dismissMemoCard(int pos){
         memoCards.remove(pos);
         this.notifyItemRemoved(pos);
     }

    // MOVE
    public void moveMemoCard(int oldpos, int newpos){
        this.notifyItemMoved(oldpos, newpos);
    }

    // ADD
    public void addGoalCard(){

    }
}
