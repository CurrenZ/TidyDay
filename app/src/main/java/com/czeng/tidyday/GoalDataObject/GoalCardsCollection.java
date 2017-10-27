package com.czeng.tidyday.GoalDataObject;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class GoalCardsCollection{

    //private static GaolDatabaseHelper mDatabaseHelper;

    public static ArrayList<GoalCard> getGoalCards(){

        ArrayList<GoalCard> goalCards = new ArrayList<>();
        GoalCard goal;

        //mDatabaseHelper = new GaolDatabaseHelper();
//        Cursor data = mDatabaseHelper.getData();
//        ArrayList<String> list_title = new ArrayList<>();
//        ArrayList<String> list_subtitle = new ArrayList<>();
//        ArrayList<String> list_type = new ArrayList<>();
//        int entry_num = 0;
//        while (data.moveToNext()){
//            entry_num ++;
//            list_title.add(data.getString(1));
//            list_subtitle.add(data.getString(2));
//            list_type.add(data.getString(3));
//        }
//
//        while (entry_num < 0){
//            goal = new GoalCard(list_title.get(entry_num), list_subtitle.get(entry_num), list_type.get(entry_num));
//            goalCards.add(goal);
//        }


        goal = new GoalCard("Work Out", "Today at 3:30 - 4:30 PM", "GG");
        goalCards.add(goal);

        goal = new GoalCard("Quit Smoke", "You are getting there!", "QB");
        goalCards.add(goal);

        goal = new GoalCard("Buy Eggs and Milk", "Today at 6:00 PM", "OR");
        goalCards.add(goal);

        return goalCards;
    }
}
