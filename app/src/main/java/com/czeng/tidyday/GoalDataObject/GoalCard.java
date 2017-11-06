package com.czeng.tidyday.GoalDataObject;


import com.czeng.tidyday.R;

public class GoalCard {
    private int id;
    private String title;
    private String type;
    private String repeat;
    private String daytoggle;
    private String weektoggle;
    private String monthmode;
    private String calcache;

    public GoalCard(int id, String title, String type, String repeat, String daytoggle, String weektoggle, String monthmode, String calcache) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.repeat = repeat;
        this.daytoggle = daytoggle;
        this.weektoggle = weektoggle;
        this.monthmode = monthmode;
        this.calcache = calcache;
    }

    public GoalCard(int id, String title, String type) {
        this.title = title;
        this.type = type;
        this.id = id;
    }

    public GoalCard(String title, String type) {
        this.title = title;
        this.type = type;
    }

    public String getRepeat() {
        return repeat;
    }

    public String getDaytoggle() {
        return daytoggle;
    }

    public String getWeektoggle() {
        return weektoggle;
    }

    public String getMonthmode() {
        return monthmode;
    }

    public String getCalcache() {
        return calcache;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public void setDaytoggle(String daytoggle) {
        this.daytoggle = daytoggle;
    }

    public void setWeektoggle(String weektoggle) {
        this.weektoggle = weektoggle;
    }

    public void setMonthmode(String monthmode) {
        this.monthmode = monthmode;
    }

    public void setCalcache(String calcache) {
        this.calcache = calcache;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }
}
