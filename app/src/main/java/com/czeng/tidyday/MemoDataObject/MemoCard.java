package com.czeng.tidyday.MemoDataObject;


public class MemoCard {
    String title;
    String subtitle;

    public MemoCard(String title, String subtitle) {
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
