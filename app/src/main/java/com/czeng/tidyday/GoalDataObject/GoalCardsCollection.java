package com.czeng.tidyday.GoalDataObject;


import java.util.ArrayList;

public class GoalCardsCollection {

    public static ArrayList<GoalCard> getGoalCards(){

        ArrayList<GoalCard> goalCards = new ArrayList<>();

        GoalCard goal = new GoalCard("Work Out", "Today at 3:30 - 4:30 PM", "GG");
        goalCards.add(goal);

        goal = new GoalCard("Quit Smoke", "You are getting there!", "QB");
        goalCards.add(goal);

        goal = new GoalCard("Buy Eggs and Milk", "Today at 6:00 PM", "OR");
        goalCards.add(goal);

        return goalCards;
    }
}
