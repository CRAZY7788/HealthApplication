package com.example.HealthApplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Used to operate database
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "selfRecord";

    public DatabaseHelper(Context context){
        super(context,"Self.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "create table if not exists" + TABLE_NAME + "(Id integer primary key, Date text,Value integer";
        db.execSQL(createTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public void onOpen(SQLiteDatabase mDatabase){
        super.onOpen(mDatabase);
    }
}
