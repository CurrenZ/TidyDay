package com.czeng.tidyday.GoalRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.czeng.tidyday.GoalDataBase.GoalDataSource;
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
        switch (goalCards.get(position).getType()){
            case "GG":
                holder.imageView.setImageResource(R.drawable.ic_thumb_up);
                break;
            case "QB":
                holder.imageView.setImageResource(R.drawable.ic_pan);
                break;
            case "OR":
                holder.imageView.setImageResource(R.drawable.ic_event_available);
                break;
            default:
                holder.imageView.setImageResource(R.drawable.tidy_day);
        }

    }

    @Override
    public int getItemCount() {
        return goalCards.size();
    }

    // DISMISS
     public void dismissGoalCard(int pos){
         String delete_title = goalCards.get(pos).getTitle();
         GoalDataSource dataSource = new GoalDataSource(c);
         dataSource.open();
         dataSource.deleteGoal(delete_title);
         dataSource.close();
         goalCards.remove(pos);
         this.notifyItemRemoved(pos);
     }

    public void dismissGoalCardByID(int pos){
        int delete_id = goalCards.get(pos).getId();
        GoalDataSource dataSource = new GoalDataSource(c);
        dataSource.open();
        if (dataSource.deleteGoalByID(delete_id)){
            goalCards.remove(pos);
            this.notifyItemRemoved(pos);
        }
        dataSource.close();

    }

     // MOVE
    public void moveGoalCard(int oldpos, int newpos){
        this.notifyItemMoved(oldpos, newpos);
    }
}
