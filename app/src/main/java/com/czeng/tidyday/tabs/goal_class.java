package com.czeng.tidyday.tabs;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.czeng.tidyday.GoalDataBase.GoalDataSource;
import com.czeng.tidyday.GoalDataBase.GoalDatabaseHelper;
import com.czeng.tidyday.GoalDataObject.GoalCard;
import com.czeng.tidyday.GoalRecycler.GoalAdapter;
import com.czeng.tidyday.R;
import com.czeng.tidyday.Swiper.GoalSwipeHelper;

import java.util.ArrayList;

public class goal_class extends Fragment{

    RecyclerView rv;
    GoalAdapter adapter;
    ArrayList<GoalCard> goalCards;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_layout, container, false);


        // RECYCLER
        rv = (RecyclerView) rootView.findViewById(R.id.Cards_recycler);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        // DATA
        GoalDataSource dataSource = new GoalDataSource(getActivity());
        dataSource.open();
//        dataSource.clear();
//        dataSource.create();
        goalCards = dataSource.getGoalEntry();
        dataSource.close();

        // ADAPTER
        adapter = new GoalAdapter(getActivity(), goalCards);
        rv.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new GoalSwipeHelper(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rv);

        return rootView;
    }
}
