package com.czeng.tidyday.MemoRecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.czeng.tidyday.R;

public class MemoHolder extends RecyclerView.ViewHolder{

    TextView titletxt;
    TextView subtitletxt;
    ImageView imageView;

    public MemoHolder(View itemView) {
        super(itemView);

        this.titletxt = (TextView) itemView.findViewById(R.id.card_tittle);
        this.subtitletxt = (TextView) itemView.findViewById(R.id.card_sub_tittle);
        this.imageView = (ImageView) itemView.findViewById(R.id.card_image);
    }
}
