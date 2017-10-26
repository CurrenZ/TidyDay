package com.czeng.tidyday.GoalDataObject;


import com.czeng.tidyday.R;

public class GoalCard {
    private String title;
    private String subtitle;
    private int type_image;

    public GoalCard(String title, String subtitle, String type) {
        this.title = title;
        this.subtitle = subtitle;
        setCardImage(type);

    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getType_image() {
        return type_image;
    }

    private void setCardImage(String type){
        switch (type){
            case "GG":
                this.type_image = R.drawable.ic_thumb_up;
                break;
            case "QB":
                this.type_image = R.drawable.ic_pan;
                break;
            case "OR":
                this.type_image = R.drawable.ic_event_available;
                break;
            default:
                this.type_image = R.drawable.tidy_day;
        }
    }
}
