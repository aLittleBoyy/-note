package com.jeq.myapplication.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DB {

    public static SQLiteDatabase dbtabase(Context context){
        MyDbHelper myDbHelper = new MyDbHelper(context);
        SQLiteDatabase database = myDbHelper.getReadableDatabase();
        return database;
    }
}
