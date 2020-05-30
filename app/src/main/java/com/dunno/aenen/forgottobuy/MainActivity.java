package com.dunno.aenen.forgottobuy;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amazon.euclid.util.TiltScrollController;
import com.amazon.euclid.widget.ZContainer;
import com.amazon.euclid.widget.ZHeaderNavigationBar;
import com.amazon.euclid.widget.ZLinearLayout;
import com.amazon.euclid.widget.ZShadowReceiver;
import com.amazon.euclid.widget.ZTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import amazon.widget.OnActionsMenuClickListener;

import static com.dunno.aenen.forgottobuy.ForgotToBuyContract.*;


public class MainActivity extends Activity implements OnActionsMenuClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ((ZContainer) findViewById(R.id.content_host)).setBackgroundResource(R.color.teal);

        final ZHeaderNavigationBar headerNavBar = (ZHeaderNavigationBar) findViewById(R.id.zheadernavigationbar);
        headerNavBar.setOnHeaderActionsClickListener(this);

        registerForContextMenu(headerNavBar);

        //db testing
        ForgotToBuyDbHelper dbHelper = new ForgotToBuyDbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //insert
        ContentValues values = new ContentValues();
        values.put(Product.COLUMN_NAME_DESCRIPTION, "My first sqlite insert♥♥♥");
        long newRowId = db.insert(Product.TABLE_NAME, null, values);

        //select
        String[] projection = {
                BaseColumns._ID,
                Product.COLUMN_NAME_DESCRIPTION,
                Product.COLUMN_NAME_POPULARITY
        };
        String selection = Product.COLUMN_NAME_DESCRIPTION + " = ?";
        String[] selectionArgs = {"My first sqlite insert♥♥♥"};
        String sortOrder = Product.COLUMN_NAME_DESCRIPTION + " DESC";
        Cursor cursor = db.query(
                Product.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        List itemIds = new ArrayList();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(Product._ID));
            String desc = cursor.getString(
                    cursor.getColumnIndexOrThrow(Product.COLUMN_NAME_DESCRIPTION));
            itemIds.add(itemId);
        }
        cursor.close();
    }

    /**
     * Event handler when menu item is clicked.
     */
    @Override
    public void onActionClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.action_order:
                break;
        }
    }
}
