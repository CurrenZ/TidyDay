package com.czeng.tidyday.GoalDataBase;

import android.provider.BaseColumns;

public class GoalContract {

    public static final class GoalEntry implements BaseColumns{
        public static final String TABLE_NAME = "Goal_Table";
        public static final String COL_TITLE = "title";
        public static final String COL_TYPE = "type";
        public static final String COL_REPEAT = "repeat";
        public static final String COL_DAYTOGGLE = "dayToggle";
        public static final String COL_WEEkTOGGLE = "weekToggle";
        public static final String COL_MONTHTMODE = "monthMode";
        public static final String COL_CAL = "calenderCache";
        public static final String COL_YEAR_REPEAT = "Year_Repeat";
    }
}