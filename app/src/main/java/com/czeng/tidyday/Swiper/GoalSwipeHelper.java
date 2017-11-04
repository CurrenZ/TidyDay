package com.czeng.tidyday.Swiper;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.czeng.tidyday.GoalRecycler.GoalAdapter;

public class GoalSwipeHelper extends ItemTouchHelper.SimpleCallback{

    GoalAdapter adapter;

    public GoalSwipeHelper(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    public GoalSwipeHelper(GoalAdapter adapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT);
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        adapter.moveGoalCard(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.dismissGoalCardByID(viewHolder.getAdapterPosition());
    }
}
