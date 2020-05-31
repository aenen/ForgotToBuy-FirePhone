package com.dunno.aenen.forgottobuy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.security.ProtectionDomain;

/**
 * Created by Yaroslav on 30.05.2020.
 */
public final class ForgotToBuyContract {

    private ForgotToBuyContract() {}

    public static class Product implements BaseColumns {
        public static final String _ID = "idProduct";
        public static final String TABLE_NAME = "products_tb";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_POPULARITY = "popularity";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_NAME + " TEXT NOT NULL UNIQUE," +
                        COLUMN_NAME_POPULARITY + " INTEGER DEFAULT 0)";

        public static final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

    public static class List implements BaseColumns {
        public static final String _ID = "idList";
        public static final String TABLE_NAME = "lists_tb";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CREATION_DATE = "creationDate";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_TITLE + " TEXT NULL," +
                        COLUMN_NAME_CREATION_DATE + " DATE DEFAULT (datetime('now','localtime')))";

        public static final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class ListItem implements BaseColumns {
        public static final String _ID = "idListItem";
        public static final String TABLE_NAME = "listItems_tb";
        public static final String COLUMN_NAME_LIST_ID = List._ID;
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_IS_CHECKED = "isChecked";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_LIST_ID + " TEXT NOT NULL," +
                        COLUMN_NAME_NAME + " TEXT NOT NULL," +
                        COLUMN_NAME_IS_CHECKED + " INTEGER DEFAULT 0," +
                        "FOREIGN KEY (" + COLUMN_NAME_LIST_ID + ")" +
                        "REFERENCES " + List.TABLE_NAME + " (" + List._ID + ")" +
                        "ON DELETE CASCADE)";

        public static final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
