package com.czeng.tidyday.Cards;


import com.czeng.tidyday.R;

public class MemoCard {
    private String title;
    private String subtitle;
    private int type_image;

    public MemoCard(String title, String subtitle, String type) {
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
            case "MT":
                this.type_image = R.drawable.ic_date_range;
                break;
            case "MW":
                this.type_image = R.drawable.ic_edit;
                break;
            default:
                this.type_image = R.drawable.tidy_day;
        }
    }
}
