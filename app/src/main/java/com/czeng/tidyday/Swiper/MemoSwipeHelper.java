package com.czeng.tidyday.Swiper;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.czeng.tidyday.MemoRecycler.MemoAdapter;

public class MemoSwipeHelper extends ItemTouchHelper.SimpleCallback{

    MemoAdapter adapter;

    public MemoSwipeHelper(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    public MemoSwipeHelper(MemoAdapter adapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT);
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.dismissMemoCard(viewHolder.getAdapterPosition());
    }
}
