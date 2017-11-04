package com.czeng.tidyday.GoalDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.czeng.tidyday.GoalDataObject.GoalCard;

import java.util.ArrayList;

import javax.sql.DataSource;

public class GoalDataSource {
    public SQLiteOpenHelper dbHelper;
    public SQLiteDatabase database;

    public static final String [] GoalCol = {
            GoalContract.GoalEntry._ID,
            GoalContract.GoalEntry.COL_TITLE,
            GoalContract.GoalEntry.COL_SUBTITLE,
            GoalContract.GoalEntry.COL_TYPE
    };

    public GoalDataSource(Context context){
        dbHelper = new GoalDatabaseHelper(context);
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
        Log.i(GoalDatabaseHelper.DATABASE_NAME, "database opened");
    }

    public boolean isOpen () {
        return database.isOpen();
    }

    public void close() {
        database.close();
        Log.i(GoalDatabaseHelper.DATABASE_NAME, "database closed");
    }

    public void clear(){
        database.execSQL(GoalDatabaseHelper.DELETE_TABLES);
    }

    public void create(){
        database.execSQL(GoalDatabaseHelper.CREATE_TABLE);
    }

    public ArrayList<GoalCard> getGoalEntry(){
        ArrayList<GoalCard> list_goalCards;
        GoalCard goalCard;

        Cursor cursor = database.query(GoalContract.GoalEntry.TABLE_NAME, GoalCol, null, null, null, null, null);
        Log.i(GoalDatabaseHelper.DATABASE_NAME, "returned: " + cursor.getCount() + " row(s) .");

        list_goalCards = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(GoalContract.GoalEntry._ID));
            String title = cursor.getString(cursor.getColumnIndex(GoalContract.GoalEntry.COL_TITLE));
            String subtitle = cursor.getString(cursor.getColumnIndex(GoalContract.GoalEntry.COL_SUBTITLE));
            String type = cursor.getString(cursor.getColumnIndex(GoalContract.GoalEntry.COL_TYPE));
            goalCard = new GoalCard(id, title, subtitle, type);
            list_goalCards.add(goalCard);
        }
        return list_goalCards;

    }

//    public void insertGoal (String title, String subtitle, String type){
//        ContentValues values = new ContentValues();
//        values.put(GoalContract.GoalEntry.COL_TITLE, title);
//        values.put(GoalContract.GoalEntry.COL_SUBTITLE, subtitle);
//        values.put(GoalContract.GoalEntry.COL_TYPE, type);
//
//        long insertGoal = database.insert(GoalContract.GoalEntry.TABLE_NAME, null, values);
//        Log.i(GoalDatabaseHelper.DATABASE_NAME, "added name id:" + insertGoal);
//    }

    public void insertGoal (int id, String title, String subtitle, String type){
        ContentValues values = new ContentValues();
        values.put(GoalContract.GoalEntry._ID, id);
        values.put(GoalContract.GoalEntry.COL_TITLE, title);
        values.put(GoalContract.GoalEntry.COL_SUBTITLE, subtitle);
        values.put(GoalContract.GoalEntry.COL_TYPE, type);

        long insertGoal = database.insert(GoalContract.GoalEntry.TABLE_NAME, null, values);
        Log.i(GoalDatabaseHelper.DATABASE_NAME, "added name id:" + insertGoal);
    }

    public void deleteGoal (String title){
        String whereClause = GoalContract.GoalEntry.COL_TITLE + "=" + "?";
        String[] whereArgs = {title};
        int deleteID = database.delete(GoalContract.GoalEntry.TABLE_NAME, whereClause, whereArgs);
        Log.i(GoalDatabaseHelper.DATABASE_NAME, "Deleted title id:" + deleteID);
    }

    public boolean deleteGoalByID (int id){
        return database.delete(GoalContract.GoalEntry.TABLE_NAME, GoalContract.GoalEntry._ID + "=" + String.valueOf(id), null) > 0;
    }
}
