package com.dunno.aenen.forgottobuy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yaroslav on 31.05.2020.
 */

public class ForgotToBuyDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 4;
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
}