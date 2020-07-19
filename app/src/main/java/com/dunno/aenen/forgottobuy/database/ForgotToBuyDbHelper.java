package com.dunno.aenen.forgottobuy.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yaroslav on 31.05.2020.
 */

public class ForgotToBuyDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 12;
    public static final String DATABASE_NAME = "forgotToBuy.db";

    public ForgotToBuyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ForgotToBuyContract.Product.SQL_CREATE_TABLE);
        db.execSQL(ForgotToBuyContract.Checklist.SQL_CREATE_TABLE);
        db.execSQL(ForgotToBuyContract.ChecklistItem.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ForgotToBuyContract.ChecklistItem.SQL_DELETE_TABLE);
        db.execSQL(ForgotToBuyContract.Checklist.SQL_DELETE_TABLE);
        db.execSQL(ForgotToBuyContract.Product.SQL_DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public ChecklistDTO getChecklist(long idChecklist) {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] projection = {
                ForgotToBuyContract.Checklist._ID,
                ForgotToBuyContract.Checklist.COLUMN_NAME_TITLE,
                ForgotToBuyContract.Checklist.COLUMN_NAME_CREATION_DATE
        };
        String selection = ForgotToBuyContract.Checklist._ID + " = ?";
        String[] selectionArgs = { Long.toString(idChecklist) };
        Cursor cursor = db.query(ForgotToBuyContract.Checklist.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        ChecklistDTO checklist = new ChecklistDTO();
        if(cursor.moveToNext()) {
            long idList = cursor.getLong(cursor.getColumnIndexOrThrow(ForgotToBuyContract.Checklist._ID));
            String title= cursor.getString(cursor.getColumnIndexOrThrow(ForgotToBuyContract.Checklist.COLUMN_NAME_TITLE));
            long longDate=cursor.getLong(cursor.getColumnIndexOrThrow(ForgotToBuyContract.Checklist.COLUMN_NAME_CREATION_DATE));
            Date creationDate = new Date(longDate * 1000);

            checklist = new ChecklistDTO(idList, title, creationDate);
        }
        cursor.close();

        return checklist;

    }


    public List<ChecklistDTO> getChecklists() {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] projection = {
                ForgotToBuyContract.Checklist._ID,
                ForgotToBuyContract.Checklist.COLUMN_NAME_TITLE,
                ForgotToBuyContract.Checklist.COLUMN_NAME_CREATION_DATE
        };
//        String selection = ForgotToBuyContract.List.COLUMN_NAME_NAME + " = ?";
//        String[] selectionArgs = {"My first sqlite insert♥♥"};
        String sortOrder = ForgotToBuyContract.Checklist.COLUMN_NAME_CREATION_DATE + " DESC";
        Cursor cursor = db.query(
                ForgotToBuyContract.Checklist.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List<ChecklistDTO> lists = new ArrayList<ChecklistDTO>();
        while(cursor.moveToNext()) {
            long idList = cursor.getLong(cursor.getColumnIndexOrThrow(ForgotToBuyContract.Checklist._ID));
            String title= cursor.getString(cursor.getColumnIndexOrThrow(ForgotToBuyContract.Checklist.COLUMN_NAME_TITLE));

            String stringDate=cursor.getString(cursor.getColumnIndexOrThrow(ForgotToBuyContract.Checklist.COLUMN_NAME_CREATION_DATE));
            long longDate=cursor.getLong(cursor.getColumnIndexOrThrow(ForgotToBuyContract.Checklist.COLUMN_NAME_CREATION_DATE));
            Date creationDate = new Date(longDate * 1000);
            // https://stackoverflow.com/questions/20495083/android-sqlite-store-date-types
            // https://stackoverflow.com/questions/7363112/best-way-to-work-with-dates-in-android-sqlite

            lists.add(new ChecklistDTO(idList, title, creationDate));
        }
        cursor.close();

        return  lists;
    }

    public List<ChecklistItemDTO> getChecklistItems(long idChecklist) {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] projection = {
                ForgotToBuyContract.ChecklistItem._ID,
                ForgotToBuyContract.ChecklistItem.COLUMN_NAME_NAME,
                ForgotToBuyContract.ChecklistItem.COLUMN_NAME_SEQUENCE,
                ForgotToBuyContract.ChecklistItem.COLUMN_NAME_IS_CHECKED
        };
        String selection = ForgotToBuyContract.ChecklistItem.COLUMN_NAME_CHECKLIST_ID + " = ?";
        String[] selectionArgs = { Long.toString(idChecklist) };
        String sortOrder = ForgotToBuyContract.ChecklistItem.COLUMN_NAME_SEQUENCE;
        Cursor cursor = db.query(ForgotToBuyContract.ChecklistItem.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);

        List<ChecklistItemDTO> result = new ArrayList<ChecklistItemDTO>();
        while(cursor.moveToNext()) {
            long idChecklistItem = cursor.getLong(cursor.getColumnIndexOrThrow(ForgotToBuyContract.ChecklistItem._ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(ForgotToBuyContract.ChecklistItem.COLUMN_NAME_NAME));
            int sequence = cursor.getInt(cursor.getColumnIndexOrThrow(ForgotToBuyContract.ChecklistItem.COLUMN_NAME_SEQUENCE));
            boolean isChecked = cursor.getInt(cursor.getColumnIndexOrThrow(ForgotToBuyContract.ChecklistItem.COLUMN_NAME_IS_CHECKED)) != 0;

            result.add(new ChecklistItemDTO(idChecklistItem, name, sequence, isChecked));
        }
        cursor.close();

        return result;
    }

    public void deleteChecklist(long idChecklist){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ForgotToBuyContract.ChecklistItem.TABLE_NAME, ForgotToBuyContract.ChecklistItem.COLUMN_NAME_CHECKLIST_ID + " = ?", new String[]{String.valueOf(idChecklist)});
        db.delete(ForgotToBuyContract.Checklist.TABLE_NAME, ForgotToBuyContract.Checklist._ID + " = ?", new String[]{String.valueOf(idChecklist)});
    }

    public void setChecklistItemIsChecked(long idChecklistItem, boolean isCheckedValue){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ForgotToBuyContract.ChecklistItem.COLUMN_NAME_IS_CHECKED, isCheckedValue? 1 : 0);
        db.update(ForgotToBuyContract.ChecklistItem.TABLE_NAME, cv, ForgotToBuyContract.ChecklistItem._ID + " = ?", new String[] { String.valueOf(idChecklistItem) });
    }

    public void insertChecklist(String checklistTitle, List<String> mChecklistItemNames) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues checklistValues = new ContentValues();
        if(checklistTitle == null || checklistTitle.isEmpty())
            checklistValues.putNull(ForgotToBuyContract.Checklist.COLUMN_NAME_TITLE);
        else
            checklistValues.put(ForgotToBuyContract.Checklist.COLUMN_NAME_TITLE, checklistTitle);
        long checklistId = db.insert(ForgotToBuyContract.Checklist.TABLE_NAME, null, checklistValues);

        for (int i = 0; i < mChecklistItemNames.size(); i++) {
            // inserting checklist
            ContentValues checklistItemValues = new ContentValues();
            checklistItemValues.put(ForgotToBuyContract.ChecklistItem.COLUMN_NAME_CHECKLIST_ID, checklistId);
            checklistItemValues.put(ForgotToBuyContract.ChecklistItem.COLUMN_NAME_SEQUENCE, i + 1);
            checklistItemValues.put(ForgotToBuyContract.ChecklistItem.COLUMN_NAME_NAME, mChecklistItemNames.get(i));
            db.insert(ForgotToBuyContract.ChecklistItem.TABLE_NAME, null, checklistItemValues);

            // inserting product
            String[] projection = {
                    ForgotToBuyContract.Product._ID,
                    ForgotToBuyContract.Product.COLUMN_NAME_NAME,
                    ForgotToBuyContract.Product.COLUMN_NAME_POPULARITY
            };
            String selection = ForgotToBuyContract.Product.COLUMN_NAME_NAME + " = ?";
            String[] selectionArgs = { mChecklistItemNames.get(i).toLowerCase() };
            Cursor cursor = db.query(ForgotToBuyContract.Product.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

            if(cursor.getCount() > 0){
                cursor.moveToNext();
                long idProduct = cursor.getLong(cursor.getColumnIndexOrThrow(ForgotToBuyContract.Product._ID));
                long popularity = cursor.getLong(cursor.getColumnIndexOrThrow(ForgotToBuyContract.Product.COLUMN_NAME_POPULARITY));

                ContentValues productValues = new ContentValues();
                productValues.put(ForgotToBuyContract.Product.COLUMN_NAME_POPULARITY, popularity + 1);
                db.update(ForgotToBuyContract.Product.TABLE_NAME, productValues, ForgotToBuyContract.Product._ID + " = ?", new String[] { String.valueOf(idProduct) });
            }
            else {
                ContentValues productValues = new ContentValues();
                productValues.put(ForgotToBuyContract.Product.COLUMN_NAME_NAME, mChecklistItemNames.get(i).toLowerCase());
                db.insert(ForgotToBuyContract.Product.TABLE_NAME, null, productValues);
            }
        }
    }
}