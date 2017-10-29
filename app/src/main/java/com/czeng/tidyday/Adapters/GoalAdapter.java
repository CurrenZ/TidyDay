package com.czeng.tidyday.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.czeng.tidyday.Cards.GoalCard;
import com.czeng.tidyday.R;

import java.util.ArrayList;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.GoalViewHolder>{

    private ArrayList<GoalCard> listGoalCard;
    public ImageView overflow;
    private Context mContext;
    private ArrayList<GoalCard> FilteredList;

    public GoalAdapter(ArrayList<GoalCard> listGoalCard, Context mContext) {
        this.listGoalCard = listGoalCard;
        this.mContext = mContext;
        this.FilteredList = listGoalCard;
    }

    @Override
    public GoalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);
        return new GoalViewHolder(itemView);
    }

    public class GoalViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public TextView tv_subtitle;
        public ImageView iv_typeImage;
        public GoalViewHolder(View view) {
            super(view);
            tv_title = (TextView) view.findViewById(R.id.card_tittle);
            tv_subtitle = (TextView) view.findViewById(R.id.card_sub_tittle);
            iv_typeImage = (ImageView) view.findViewById(R.id.card_image);
        }
    }

    @Override
    public void onBindViewHolder(GoalViewHolder holder, int position) {
        holder.tv_title.setText(listGoalCard.get(position).getTitle());
        holder.tv_subtitle.setText(listGoalCard.get(position).getSubtitle());
        int type_image = R.drawable.tidy_day;
        switch (listGoalCard.get(position).getType()){
            case "GG":
                type_image = R.drawable.ic_thumb_up;
                break;
            case "QB":
                type_image = R.drawable.ic_pan;
                break;
            case "OR":
                type_image = R.drawable.ic_event_available;
                break;
            default:
                type_image = R.drawable.tidy_day;
        }
        holder.iv_typeImage.setImageResource(type_image);
    }

    @Override
    public int getItemCount() {
        return FilteredList.size();
    }
}
