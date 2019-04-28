package com.jeq.myapplication.data;

/**
 * The type Sq lite schema.数据库结构
 */
public class SQLiteSchema {


    public static final String DB_NAME = "test";
    public static final int DB_VERSION = 1;

    /**
     * The type Table.表
     */
    public static class Table{
        /**
         * The constant TABLE_NAME.表名
         */
        public static final String TABLE_NAME = "test";
    }

    /**
     * The type Colmuns.列
     */
    public static class Colmuns{
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String AGE = "age";
        public static final String DESCRIBE = "describe";
        public static final String APPRIASE = "appriase";

    }
}
