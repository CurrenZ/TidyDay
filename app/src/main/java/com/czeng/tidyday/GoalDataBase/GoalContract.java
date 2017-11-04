package com.czeng.tidyday.GoalDataBase;

import android.provider.BaseColumns;

public class GoalContract {

    public static final class GoalEntry implements BaseColumns{
        public static final String TABLE_NAME = "Goal_Table";
        public static final String COL_TITLE = "title";
        public static final String COL_SUBTITLE = "subtitle";
        public static final String COL_TYPE = "type";
    }
}