package com.example.dailytask;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tasks.db";
    public static final String TABLE_NAME = "tasks";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TASK = "task";
    public static final String COLUMN_DESCRIPTION = "des";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASK + COLUMN_DESCRIPTION + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}
