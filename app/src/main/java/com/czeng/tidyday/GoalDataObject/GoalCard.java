package com.czeng.tidyday.GoalDataObject;


public class GoalCard {
    String title;
    String subtitle;

    public GoalCard(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }
}
