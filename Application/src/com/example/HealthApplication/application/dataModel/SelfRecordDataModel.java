package com.example.HealthApplication.application.dataModel;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.HealthApplication.application.interfaces.dmaInterface.ISelfRecordDataModel;
import com.example.HealthApplication.database.DatabaseHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class SelfRecordDataModel implements ISelfRecordDataModel{

    Context mContext;
    DatabaseHelper mHelper;
    SQLiteDatabase mDatabase;
    boolean mIsTableCreated;
    private static final String TAG = "foaming";

    private final String[] ORDER_COLUMNS = new String[] {"Id", "Date","Value"};

    public SelfRecordDataModel(Context context){
        this.mContext = context;

        mHelper = new DatabaseHelper(context);
    }


    @Override
    public void setData(int id,int value, String date) {
        insertData(id,date,value);
    }

    @Override
    public int getSingleDayValue(String date) {
        SQLiteDatabase sqLiteDatabase = mHelper.getReadableDatabase();
        return 0;
    }

    public List<SelfFoamingStruct> getAllDate(){
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = mHelper.getReadableDatabase();
            // select * from Orders
            cursor = db.query(mHelper.TABLE_NAME, ORDER_COLUMNS, null, null, null, null, null);

            if (cursor.getCount() > 0) {
                List<SelfFoamingStruct> orderList = new ArrayList<>(cursor.getCount());
                while (cursor.moveToNext()) {
                    orderList.add(parseStruct(cursor));
                }
                return orderList;
            }
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return null;
    }


    /**
     * 将查找到的数据转换成Order类
     */
    private SelfFoamingStruct parseStruct(Cursor cursor){
        SelfFoamingStruct selfFoamingStruct = new SelfFoamingStruct();
        selfFoamingStruct.id = (cursor.getInt(cursor.getColumnIndex("Id")));
        selfFoamingStruct.date = (cursor.getString(cursor.getColumnIndex("Date")));
        selfFoamingStruct.value = (cursor.getInt(cursor.getColumnIndex("Value")));
        return selfFoamingStruct;
    }




//    public void createFoamTable(){
////        mDatabase.execSQL("create table foamingRecord(id INTEGER PRIMARY KEY,date text, value INTEGER");
//        SQLiteDatabase sqLiteDatabase = mHelper.getWritableDatabase();
//        String createTableSql = "create table if not exists" + tableName + "(Id integer primary key, Date text,Value integer";
//        sqLiteDatabase.execSQL(createTableSql);
//
//
//
//        System.out.print("success");
//    }

    public void insertData(int id,String date, int value){
        SQLiteDatabase sqLiteDatabase = mHelper.getWritableDatabase();
        sqLiteDatabase.beginTransaction();

        sqLiteDatabase.execSQL("insert into" + mHelper.TABLE_NAME +"(Id, Date, Value) values ("+ id + "," + date +","+value+")");
        sqLiteDatabase.setTransactionSuccessful();
    }

}
