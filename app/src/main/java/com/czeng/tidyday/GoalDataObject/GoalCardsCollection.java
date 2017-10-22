package com.czeng.tidyday.GoalDataObject;


import java.util.ArrayList;

public class GoalCardsCollection {
    public static ArrayList<GoalCard> getGoalCards(){
        ArrayList<GoalCard> goalCards = new ArrayList<>();

        GoalCard goal = new GoalCard("Goal 1", "goal 1");
        goalCards.add(goal);

        goal = new GoalCard("Goal 1", "goal 1");
        goalCards.add(goal);

        goal = new GoalCard("Goal 2", "goal 2");
        goalCards.add(goal);

        goal = new GoalCard("Goal 4", "goal 4");
        goalCards.add(goal);

        return goalCards;
    }
}
