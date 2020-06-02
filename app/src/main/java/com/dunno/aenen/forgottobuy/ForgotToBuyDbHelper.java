package com.dunno.aenen.forgottobuy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Yaroslav on 31.05.2020.
 */

public class ForgotToBuyDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 10;
    public static final String DATABASE_NAME = "forgotToBuy.db";

    public ForgotToBuyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ForgotToBuyContract.Product.SQL_CREATE_TABLE);
        db.execSQL(ForgotToBuyContract.List.SQL_CREATE_TABLE);
        db.execSQL(ForgotToBuyContract.ListItem.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ForgotToBuyContract.ListItem.SQL_DELETE_TABLE);
        db.execSQL(ForgotToBuyContract.List.SQL_DELETE_TABLE);
        db.execSQL(ForgotToBuyContract.Product.SQL_DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public List<ListDTO> getLists() {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] projection = {
                ForgotToBuyContract.List._ID,
                ForgotToBuyContract.List.COLUMN_NAME_TITLE,
                ForgotToBuyContract.List.COLUMN_NAME_CREATION_DATE
        };
//        String selection = ForgotToBuyContract.List.COLUMN_NAME_NAME + " = ?";
//        String[] selectionArgs = {"My first sqlite insert♥♥"};
        String sortOrder = ForgotToBuyContract.List.COLUMN_NAME_TITLE + " DESC";
        Cursor cursor = db.query(
                ForgotToBuyContract.List.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List<ListDTO> lists = new ArrayList<ListDTO>();
        while(cursor.moveToNext()) {
            long idList = cursor.getLong(cursor.getColumnIndexOrThrow(ForgotToBuyContract.List._ID));
            String title= cursor.getString(cursor.getColumnIndexOrThrow(ForgotToBuyContract.List.COLUMN_NAME_TITLE));

            String stringDate=cursor.getString(cursor.getColumnIndexOrThrow(ForgotToBuyContract.List.COLUMN_NAME_CREATION_DATE));
            long longDate=cursor.getLong(cursor.getColumnIndexOrThrow(ForgotToBuyContract.List.COLUMN_NAME_CREATION_DATE));
            Date creationDate = new Date(longDate * 1000);
            // https://stackoverflow.com/questions/20495083/android-sqlite-store-date-types
            // https://stackoverflow.com/questions/7363112/best-way-to-work-with-dates-in-android-sqlite

            lists.add(new ListDTO(idList, title, creationDate));
        }
        cursor.close();

        return  lists;
    }

    public void insertTestData() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues list1values = new ContentValues();
        list1values.put(ForgotToBuyContract.List.COLUMN_NAME_TITLE, "Сходи за продуктами");
        long list1Id = db.insert(ForgotToBuyContract.List.TABLE_NAME, null, list1values);

        ContentValues list2values = new ContentValues();
        list2values.putNull(ForgotToBuyContract.List.COLUMN_NAME_TITLE);
        long list2Id = db.insert(ForgotToBuyContract.List.TABLE_NAME, null, list2values);

        for (int i = 1; i < 4; i++) {
            ContentValues listItem1values = new ContentValues();
            listItem1values.put(ForgotToBuyContract.ListItem.COLUMN_NAME_LIST_ID, list1Id);
            listItem1values.put(ForgotToBuyContract.ListItem.COLUMN_NAME_SEQUENCE, i);
            listItem1values.put(ForgotToBuyContract.ListItem.COLUMN_NAME_NAME, "List item " + i);
            db.insert(ForgotToBuyContract.ListItem.TABLE_NAME, null, listItem1values);
        }

        for (int i = 1; i < 10; i++) {
            ContentValues listItem2values = new ContentValues();
            listItem2values.put(ForgotToBuyContract.ListItem.COLUMN_NAME_LIST_ID, list2Id);
            listItem2values.put(ForgotToBuyContract.ListItem.COLUMN_NAME_SEQUENCE, i);
            listItem2values.put(ForgotToBuyContract.ListItem.COLUMN_NAME_NAME, "List item " + i);
            db.insert(ForgotToBuyContract.ListItem.TABLE_NAME, null, listItem2values);
        }
    }
}