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
        public static final String TABLE_NAME = "products_tb";
        public static final String COLUMN_NAME_DESCRIPTION = "productDesc";
        public static final String COLUMN_NAME_POPULARITY = "popularity";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + Product.TABLE_NAME + " (" +
                        Product._ID + " INTEGER PRIMARY KEY," +
                        Product.COLUMN_NAME_DESCRIPTION + " TEXT NOT NULL," +
                        Product.COLUMN_NAME_POPULARITY + " INTEGER DEFAULT 0)";

        public static final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + Product.TABLE_NAME;

    }

    public static class List implements BaseColumns {
        public static final String TABLE_NAME = "lists_tb";
        public static final String COLUMN_NAME_DESCRIPTION = "listDesc";
        public static final String COLUMN_NAME_CREATION_DATE = "creationDate";
    }

    public static class ListItem implements BaseColumns {
        public static final String TABLE_NAME = "listItems_tb";
        public static final String COLUMN_NAME_LIST_ID = List._ID;
        public static final String COLUMN_NAME_PRODUCT_ID = Product._ID;
        public static final String COLUMN_NAME_IS_CHECKED = "isChecked";
    }
}
