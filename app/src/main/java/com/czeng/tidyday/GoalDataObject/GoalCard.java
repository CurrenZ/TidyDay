package com.czeng.tidyday.GoalDataObject;


import com.czeng.tidyday.R;

public class GoalCard {
    private String title;
    private String subtitle;
    private String type;
    private int id;

    public GoalCard(int id, String title, String subtitle, String type) {
        this.title = title;
        this.subtitle = subtitle;
        this.type = type;
        this.id = id;
    }

    public GoalCard(String title, String subtitle, String type) {
        this.title = title;
        this.subtitle = subtitle;
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setType(String type) {
        this.type = type;
    }
}
