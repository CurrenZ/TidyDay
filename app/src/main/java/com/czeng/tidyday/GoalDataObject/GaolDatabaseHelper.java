package com.czeng.tidyday.GoalDataObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GaolDatabaseHelper extends SQLiteOpenHelper{

    private static final String TAG = "GoalDbHelper";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "Goal_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "title";
    private static final String COL3 = "subtitle";
    private static final String COL4 = "type";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COL1 + " INTEGER PRIMARY KEY," +
            COL2 + " TEXT," +
            COL3 + " TEXT," +
            COL4 + " TEXT)";

    private static final String DROP_TABLE = "DROP IF TABLE EXISTS " + TABLE_NAME;

    public GaolDatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
    }

    public boolean addGoalData(String title, String subtitle, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, title);
        contentValues.put(COL3, subtitle);
        contentValues.put(COL4, type);

        Log.d(TAG, "addGoalData: Adding " + title + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }
        else {return true;}
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemID(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + title + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateEntry(String newTitle, String newSubtitle, int id, String oldTitle, String oldSubtitle){
        SQLiteDatabase db = this.getWritableDatabase();
        String title_update = "UPDATE " + TABLE_NAME + " SET "
                + COL2 + " = '" + newTitle
                + "' WHERE " + COL1 + " = '" + id + "'"
                + " AND " + COL2 + " = '" + oldTitle + "'";

        db.execSQL(title_update);
        Log.d(TAG, "updateEntry: title updated: " + title_update);
        Log.d(TAG, "updateName: Setting title to " + newTitle);

        String sub_title_update = "UPDATE " + TABLE_NAME + " SET "
                + COL3 + " = '" + newSubtitle
                + "' WHERE " + COL1 + " = '" + id + "'"
                + " AND " + COL3 + " = '" + oldSubtitle + "'";

        db.execSQL(sub_title_update);
        Log.d(TAG, "updateEntry: title updated: " + sub_title_update);
        Log.d(TAG, "updateName: Setting title to " + newSubtitle);

    }

    public void deleteEntry(int id, String title){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + title + "'";
        Log.d(TAG, "deleteEntry: query: " + query);
        Log.d(TAG, "deleteEntry: Deleting " + title + " from database.");
        db.execSQL(query);
    }


}
