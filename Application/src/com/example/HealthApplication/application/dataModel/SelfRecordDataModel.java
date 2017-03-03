package com.example.HealthApplication.application.dataModel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.HealthApplication.application.interfaces.dmaInterface.ISelfRecordDataModel;
import com.example.HealthApplication.database.DatabaseHelper;

import java.sql.Date;

/**
 *
 */

public class SelfRecordDataModel implements ISelfRecordDataModel{

    Context mContext;
    DatabaseHelper mHelper;
    SQLiteDatabase mDatabase;

    public SelfRecordDataModel(Context context){
        this.mContext = context;
        mHelper = new DatabaseHelper(context,"Self.db",null,1);
        mDatabase = mHelper.getWritableDatabase();

        //TODO:
        mDatabase.execSQL("create table student(id INTEGER PRIMARY KEY autoincrement,name text);");
    }


    @Override
    public void setData(double data, Date date, int clock) {
        mDatabase.
    }

    @Override
    public double getSingleDataByDateAndTime(Date date, int clock) {
        return 0;
    }

    @Override
    public double getAllDateSumByDate(Date date) {
        return 0;
    }
}
