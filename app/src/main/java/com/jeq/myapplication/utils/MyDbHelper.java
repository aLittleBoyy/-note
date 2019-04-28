package com.jeq.myapplication.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jeq.myapplication.data.SQLiteSchema;

/**
 * The type My db helper.
 */
public class MyDbHelper extends SQLiteOpenHelper {


    /**
     * Instantiates a new My db helper.
     *
     * @param context the context
     */
    public MyDbHelper(Context context) {
        super(context, SQLiteSchema.DB_NAME, null, SQLiteSchema.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * create table test (
         * id integer primary key autoincrement,
         * name varchar(20),
         * age integer,
         * describe varchar(200),
         * appriase archar(200)
         * )
         */
        db.execSQL(
                "create table " + SQLiteSchema.Table.TABLE_NAME + "("
                        +SQLiteSchema.Colmuns.ID +" integer primary key autoincrement, "
                        +SQLiteSchema.Colmuns.NAME+" varchar(20), "
                        +SQLiteSchema.Colmuns.AGE+" integer,"
                        +SQLiteSchema.Colmuns.DESCRIBE+" varchar(200), "
                        +SQLiteSchema.Colmuns.APPRIASE+" varchar(200)"+
                        ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
