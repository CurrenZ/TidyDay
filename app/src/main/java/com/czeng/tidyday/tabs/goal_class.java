package com.czeng.tidyday.tabs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.czeng.tidyday.Adapters.GoalAdapter;
import com.czeng.tidyday.Cards.GoalCard;
import com.czeng.tidyday.Database.GoalContract;
import com.czeng.tidyday.Database.GoalDatabaseHelper;
import com.czeng.tidyday.R;

import java.util.ArrayList;

public class goal_class extends Fragment{


    private RecyclerView rv;
    private ArrayList<GoalCard> listGoalCard;
    private GoalAdapter adapter;
    private GoalDatabaseHelper databaseHelper;
    private SQLiteDatabase mSQLiteDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_layout, container, false);

        // DATA
        listGoalCard = new ArrayList<>();
        databaseHelper = new GoalDatabaseHelper(getContext());

        // ADAPTER
        adapter = new GoalAdapter(listGoalCard, getContext());

        // RECYCLER
        rv = (RecyclerView) rootView.findViewById(R.id.Cards_recycler);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());

        getDataFromSQLite();

        return rootView;
    }

    @SuppressLint("StaticFieldLeak")
    private void getDataFromSQLite(){
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                listGoalCard.clear();
                listGoalCard.addAll(databaseHelper.getAllGoalCard());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
