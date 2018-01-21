package com.czeng.tidyday.GoalDataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GoalDatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "goal.db";
    public static final int VERSION = 1;


    public static final String CREATE_TABLE = "CREATE TABLE " + GoalContract.GoalEntry.TABLE_NAME + " (" +
            GoalContract.GoalEntry._ID + " INTEGER PRIMARY KEY," +
            GoalContract.GoalEntry.COL_TITLE + " TEXT, " +
            GoalContract.GoalEntry.COL_TYPE + " TEXT, " +
            GoalContract.GoalEntry.COL_REPEAT + " TEXT, " +
            GoalContract.GoalEntry.COL_DAYTOGGLE + " TEXT, " +
            GoalContract.GoalEntry.COL_WEEkTOGGLE + " TEXT, " +
            GoalContract.GoalEntry.COL_MONTHTMODE + " TEXT, " +
            GoalContract.GoalEntry.COL_CAL + " TEXT, " +
            GoalContract.GoalEntry.COL_YEAR_REPEAT + " TEXT " +
            "); ";

    public static final String DELETE_TABLES = "DROP TABLE IF EXISTS " + GoalContract.GoalEntry.TABLE_NAME + ";";

    public GoalDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.i(DATABASE_NAME, GoalContract.GoalEntry.TABLE_NAME + " has been created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLES);
        onCreate(db);
    }
}
