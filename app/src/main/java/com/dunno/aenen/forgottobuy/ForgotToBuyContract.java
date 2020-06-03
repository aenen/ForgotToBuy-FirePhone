package com.dunno.aenen.forgottobuy;

import android.provider.BaseColumns;

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

    public static class Checklist implements BaseColumns {
        public static final String _ID = "idChecklist";
        public static final String TABLE_NAME = "checklists_tb";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CREATION_DATE = "creationDate";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_TITLE + " TEXT NULL," +
                        COLUMN_NAME_CREATION_DATE + " DATETIME DEFAULT (strftime('%s','now')))";

        public static final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class ChecklistItem implements BaseColumns {
        public static final String _ID = "idChecklistItem";
        public static final String TABLE_NAME = "checklistItems_tb";
        public static final String COLUMN_NAME_CHECKLIST_ID = Checklist._ID;
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_SEQUENCE = "sequence";
        public static final String COLUMN_NAME_IS_CHECKED = "isChecked";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_CHECKLIST_ID + " TEXT NOT NULL," +
                        COLUMN_NAME_NAME + " TEXT NOT NULL," +
                        COLUMN_NAME_SEQUENCE + " INTEGER NOT NULL," +
                        COLUMN_NAME_IS_CHECKED + " INTEGER DEFAULT 0," +
                        "FOREIGN KEY (" + COLUMN_NAME_CHECKLIST_ID + ")" +
                        "REFERENCES " + Checklist.TABLE_NAME + " (" + Checklist._ID + ")" +
                        "ON DELETE CASCADE)";

        public static final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
