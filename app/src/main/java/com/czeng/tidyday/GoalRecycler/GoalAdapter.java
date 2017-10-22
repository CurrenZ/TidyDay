package com.czeng.tidyday.GoalRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.czeng.tidyday.GoalDataObject.GoalCard;
import com.czeng.tidyday.R;

import java.util.ArrayList;

public class GoalAdapter extends RecyclerView.Adapter<GoalHolder>{

    Context c;
    ArrayList<GoalCard> goalCards;

    public GoalAdapter(Context c, ArrayList<GoalCard> goalCards) {
        this.c = c;
        this.goalCards = goalCards;
    }

    @Override
    public GoalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        GoalHolder holder = new GoalHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(GoalHolder holder, int position) {
        holder.titletxt.setText(goalCards.get(position).getTitle());
        holder.subtitletxt.setText(goalCards.get(position).getSubtitle());
        //holder.img.getImageResource(goalCards.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return goalCards.size();
    }

    // DISMISS
     public void dismissGoalCard(int pos){
         goalCards.remove(pos);
         this.notifyItemRemoved(pos);
     }

     // MOVE
    public void moveGoalCard(int oldpos, int newpos){
        this.notifyItemMoved(oldpos, newpos);
    }

    // ADD
    public void addGoalCard(){

    }
}
