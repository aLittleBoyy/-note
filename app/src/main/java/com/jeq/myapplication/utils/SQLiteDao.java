package com.jeq.myapplication.utils;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jeq.myapplication.data.MyData;
import com.jeq.myapplication.data.SQLiteSchema;

import java.util.ArrayList;
import java.util.List;

import static com.jeq.myapplication.activity.MainActivity.TAG;

/**
 * The type Sq lite dao.
 */
public class SQLiteDao {

    private static MyData myData;
    private static List<MyData> data;


    /**
     * Insert my data.
     * 添加数据
     *
     * @param name     the name
     * @param age      the age
     * @param database the database test.db
     * @return the my data (name, age)
     */
    public static MyData insert(String name, int age, SQLiteDatabase database){
        ContentValues values = new ContentValues();
        values.put(SQLiteSchema.Colmuns.NAME, name);
        values.put(SQLiteSchema.Colmuns.AGE, age);

        database.insert(SQLiteSchema.Table.TABLE_NAME, null, values);
        myData = new MyData();
        myData.setName(values.getAsString(SQLiteSchema.Colmuns.NAME));
        myData.setAge(values.getAsInteger(SQLiteSchema.Colmuns.AGE));
       /* String mname = myData.getName();
        int mage = myData.getAge();*/
        return myData;
    }


    /**
     * Insert detailed my data.
     * 详情页添加数据
     * 描述和评价
     * @param  @param database the database
     * @return the my data
     */
    public static void insert_detailed(MyData mydata, SQLiteDatabase database){

        //String descibe, String appraise,int row,
        /*String sql = "update" + SQLiteSchema.Table.TABLE_NAME +" set"
                     + SQLiteSchema.Colmuns.DESCRIBE + "= "+ "'" + descibe + "'"
                     +" where " + SQLiteSchema.Colmuns.ID + "=" + row;*/

        ContentValues values = new ContentValues();
        values.put(SQLiteSchema.Colmuns.DESCRIBE, mydata.getDescribe());
        values.put(SQLiteSchema.Colmuns.APPRIASE, mydata.getAppraise());
        database.update(SQLiteSchema.Table.TABLE_NAME, values, "id=?",new String[]{mydata.getId()+""});


        /*ContentValues values = new ContentValues();
        values.put(SQLiteSchema.Colmuns.DESCRIBE, descibe);
        values.put(SQLiteSchema.Colmuns.APPRIASE, appraise);

        database.insert(SQLiteSchema.Table.TABLE_NAME, null, values);*/
        /*myData = new MyData();
        myData.setDescribe(values.getAsString(SQLiteSchema.Colmuns.DESCRIBE));
        myData.setAppraise(values.getAsString(SQLiteSchema.Colmuns.APPRIASE));
        String mname = myData.getName();
        int mage = myData.getAge();*/
        //return myData;
    }

    /**
     * Select list.
     * 查询数据
     *
     * @param edit_text_search the edit text search
     * @param database         the database
     * @return the list
     */
    public static List<MyData> select( String edit_text_search, SQLiteDatabase database) {


        String sql = "select*from "+SQLiteSchema.Table.TABLE_NAME + " where "+ SQLiteSchema.Colmuns.NAME + " like " + "'" +edit_text_search+"%'";
              /*  +" and ("+SQLiteSchema.Colmuns.NAME + " = "+ edit_text_search+ ")";*/

        data = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, null);
        while(cursor.moveToNext()){


            int name = cursor.getColumnIndex(SQLiteSchema.Colmuns.NAME);
            String name_text = cursor.getString(name);

            int age = cursor.getColumnIndex(SQLiteSchema.Colmuns.AGE);
            int age_text = cursor.getInt(age);
            Log.d(TAG, "select: " + name_text+age_text);
            myData = new MyData();
            myData.setName(name_text);
            myData.setAge(age_text);
            data.add(myData);
           // Log.d(TAG, "select:已经存进去的 " + myData.getName() + myData.getAge());
        }
           // Log.d(TAG, "select:已经存进去的 " + myData.getName() + myData.getAge());
            return data;
    }

    /**
     * delete db
     * 删除数据
     *
     * @param database the database
     * @param name     the name
     */
    public static void delete(SQLiteDatabase database, String name){
        /*String sql = "delete from "+ SQLiteSchema.Table.TABLE_NAME + " where " + SQLiteSchema.Colmuns.NAME + " = " +name;
        database.execSQL(sql);*/
        //database.delete()
        database.delete(SQLiteSchema.Table.TABLE_NAME, "name=?", new String[]{name});
    }

    public static void showDataList(SQLiteDatabase database, List<MyData> datas){
        String sql = "select*from "+ SQLiteSchema.Table.TABLE_NAME;
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int index_name = cursor.getColumnIndex(SQLiteSchema.Colmuns.NAME);
            int index_age = cursor.getColumnIndex(SQLiteSchema.Colmuns.AGE);
            int index_id = cursor.getColumnIndex(SQLiteSchema.Colmuns.ID);
            int index_describe = cursor.getColumnIndex(SQLiteSchema.Colmuns.DESCRIBE);

            int age = cursor.getInt(index_age);
            int id = cursor.getInt(index_id);
            String describe = cursor.getString(index_describe);
            String name = cursor.getString(index_name);

            myData = new MyData();
            myData.setId(id);
            myData.setName(name);
            myData.setAge(age);
            myData.setDescribe(describe);
            datas.add(myData);
            Log.d(TAG, "showDataList: datas" + datas);
        }
    }
}
