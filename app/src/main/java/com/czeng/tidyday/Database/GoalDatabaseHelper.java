package com.czeng.tidyday.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.czeng.tidyday.Cards.GoalCard;

import java.util.ArrayList;
import java.util.List;

public class GoalDatabaseHelper extends SQLiteOpenHelper{
    private GoalDatabaseHelper dbHelper;
    private SQLiteDatabase db;

    private static final int VERSION = 2;
    private static final String DATABASE_NAME = "goal.db";
    private static final String CREATE_TABLE = "CREATE TABLE " + GoalContract.GoalEntry.TABLE_NAME + " (" +
            GoalContract.GoalEntry._ID + " INTEGER NOT NULL," +
            GoalContract.GoalEntry.COL_TITLE + " TEXT NOT NULL, " +
            GoalContract.GoalEntry.COL_SUBTITLE + " TEXT NOT NULL, " +
            GoalContract.GoalEntry.COL_TYPE + " TEXT NOT NULL, " +
            "); ";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + GoalContract.GoalEntry.TABLE_NAME;

    public GoalDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public GoalDatabaseHelper open() throws SQLException{
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addGoalCard(GoalCard goalCard){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GoalContract.GoalEntry._ID, goalCard.getId());
        values.put(GoalContract.GoalEntry.COL_TITLE, goalCard.getTitle());
        values.put(GoalContract.GoalEntry.COL_SUBTITLE, goalCard.getSubtitle());
        values.put(GoalContract.GoalEntry.COL_TYPE, goalCard.getType());

        db.insert(GoalContract.GoalEntry.TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<GoalCard> getAllGoalCard(){
        // array of columns to fetch
        String[] columns = {
                GoalContract.GoalEntry._ID,
                GoalContract.GoalEntry.COL_TITLE,
                GoalContract.GoalEntry.COL_SUBTITLE,
                GoalContract.GoalEntry.COL_TYPE,
        };
        // sorting orders
        String sortOrder =
                GoalContract.GoalEntry.COL_TITLE + " ASC";
        ArrayList<GoalCard> goalCardArrayList = new ArrayList<GoalCard>();

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(GoalContract.GoalEntry.TABLE_NAME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                GoalCard goalCard = new GoalCard();
                goalCard.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(GoalContract.GoalEntry._ID))));
                goalCard.setTitle(cursor.getString(cursor.getColumnIndex(GoalContract.GoalEntry.COL_TITLE)));
                goalCard.setSubtitle(cursor.getString(cursor.getColumnIndex(GoalContract.GoalEntry.COL_SUBTITLE)));
                goalCard.setType(cursor.getString(cursor.getColumnIndex(GoalContract.GoalEntry.COL_TYPE)));
                // Adding user record to list
                goalCardArrayList.add(goalCard);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return goalCardArrayList;
    }
}
