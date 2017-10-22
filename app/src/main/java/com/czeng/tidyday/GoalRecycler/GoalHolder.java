package com.czeng.tidyday.GoalRecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.czeng.tidyday.R;

public class GoalHolder extends RecyclerView.ViewHolder{

    TextView titletxt;
    TextView subtitletxt;

    public GoalHolder(View itemView) {
        super(itemView);

        this.titletxt = (TextView) itemView.findViewById(R.id.card_tittle);
        this.subtitletxt = (TextView) itemView.findViewById(R.id.card_sub_tittle);
    }
}
