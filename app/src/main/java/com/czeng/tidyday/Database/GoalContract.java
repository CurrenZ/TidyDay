package com.czeng.tidyday.Database;

import android.provider.BaseColumns;

public class GoalContract {
    public static final class GoalEntry implements BaseColumns {

        public static final String TABLE_NAME = "goal_table";
        public static final String COL_TITLE = "Title";
        public static final String COL_SUBTITLE = "Subtitle";
        public static final String COL_TYPE = "Type";
    }
}
