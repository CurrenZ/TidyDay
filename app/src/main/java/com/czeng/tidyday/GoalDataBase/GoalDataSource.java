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
            GoalContract.GoalEntry.COL_TYPE,
            GoalContract.GoalEntry.COL_REPEAT,
            GoalContract.GoalEntry.COL_DAYTOGGLE,
            GoalContract.GoalEntry.COL_WEEkTOGGLE,
            GoalContract.GoalEntry.COL_MONTHTMODE,
            GoalContract.GoalEntry.COL_CAL,
            GoalContract.GoalEntry.COL_YEAR_REPEAT
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
        Log.i(GoalDatabaseHelper.DATABASE_NAME, GoalContract.GoalEntry.TABLE_NAME + " has been cleared.");
    }

    public void create(){
        database.execSQL(GoalDatabaseHelper.CREATE_TABLE);
        Log.i(GoalDatabaseHelper.DATABASE_NAME, GoalContract.GoalEntry.TABLE_NAME + " has been created.");
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
            String type = cursor.getString(cursor.getColumnIndex(GoalContract.GoalEntry.COL_TYPE));
            String repeat = cursor.getString(cursor.getColumnIndex(GoalContract.GoalEntry.COL_REPEAT));
            String daytoggle = cursor.getString(cursor.getColumnIndex(GoalContract.GoalEntry.COL_DAYTOGGLE));
            String weektoggle = cursor.getString(cursor.getColumnIndex(GoalContract.GoalEntry.COL_WEEkTOGGLE));
            String monthmode = cursor.getString(cursor.getColumnIndex(GoalContract.GoalEntry.COL_MONTHTMODE));
            String cal = cursor.getString(cursor.getColumnIndex(GoalContract.GoalEntry.COL_CAL));
            String yearrepeat = cursor.getString(cursor.getColumnIndex(GoalContract.GoalEntry.COL_YEAR_REPEAT));
            goalCard = new GoalCard(id, title, type, repeat, daytoggle, weektoggle, monthmode, cal, yearrepeat);
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

    public void insertGoal (int id, String title, String type){
        ContentValues values = new ContentValues();
        values.put(GoalContract.GoalEntry._ID, id);
        values.put(GoalContract.GoalEntry.COL_TITLE, title);
        values.put(GoalContract.GoalEntry.COL_TYPE, type);

        long insertGoal = database.insert(GoalContract.GoalEntry.TABLE_NAME, null, values);
        Log.i(GoalDatabaseHelper.DATABASE_NAME, "added name id:" + insertGoal);
    }

    public void insertGoal (int id, String title, String type, String repeat, String daytoggle, String weektoggle, String monthmode, String cal, String yearrepeat){
        ContentValues values = new ContentValues();
        values.put(GoalContract.GoalEntry._ID, id);
        values.put(GoalContract.GoalEntry.COL_TITLE, title);
        values.put(GoalContract.GoalEntry.COL_TYPE, type);
        values.put(GoalContract.GoalEntry.COL_REPEAT, repeat);
        values.put(GoalContract.GoalEntry.COL_DAYTOGGLE, daytoggle);
        values.put(GoalContract.GoalEntry.COL_WEEkTOGGLE, weektoggle);
        values.put(GoalContract.GoalEntry.COL_MONTHTMODE, monthmode);
        values.put(GoalContract.GoalEntry.COL_CAL, cal);
        values.put(GoalContract.GoalEntry.COL_YEAR_REPEAT, yearrepeat);

        long insertGoal = database.insert(GoalContract.GoalEntry.TABLE_NAME, null, values);
        Log.i(GoalDatabaseHelper.DATABASE_NAME, "added name id:" + insertGoal);
    }

    public boolean deleteGoalByID (int id){
        return database.delete(GoalContract.GoalEntry.TABLE_NAME, GoalContract.GoalEntry._ID + "=" + String.valueOf(id), null) > 0;
    }
}
