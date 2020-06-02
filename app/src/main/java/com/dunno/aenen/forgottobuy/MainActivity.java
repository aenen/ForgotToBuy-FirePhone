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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amazon.euclid.util.TiltScrollController;
import com.amazon.euclid.util.TiltScrollable;
import com.amazon.euclid.util.triggers.TouchEventTrigger;
import com.amazon.euclid.widget.ZContainer;
import com.amazon.euclid.widget.ZHeaderNavigationBar;
import com.amazon.euclid.widget.ZLinearLayout;
import com.amazon.euclid.widget.ZShadowReceiver;
import com.amazon.euclid.widget.ZTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import amazon.widget.OnActionsMenuClickListener;

public class MainActivity extends Activity implements OnActionsMenuClickListener {

    private ForgotToBuyDbHelper mDbHelper;
    private RecyclerView mRecyclerView;
    private ListAdapter mAdapter;
    private TiltScrollController mTiltScrollController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ((ZContainer) findViewById(R.id.content_host)).setBackgroundResource(R.color.teal);

        final ZHeaderNavigationBar headerNavBar = (ZHeaderNavigationBar) findViewById(R.id.zheadernavigationbar);
        headerNavBar.setOnHeaderActionsClickListener(this);

        registerForContextMenu(headerNavBar);

        mDbHelper = new ForgotToBuyDbHelper(getApplicationContext());

        //db testing
//        mDbHelper.insertTestData();
//        mDbHelper.insertTestData();
//        mDbHelper.insertTestData();
        List<ListDTO> lists = mDbHelper.getLists();

        // Get a handle to the RecyclerView.
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new ListAdapter(this, lists);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Create an instance of TiltScrollController.
        mTiltScrollController = new TiltScrollController(this);

        // Attach the ViewGroup and ListView to the TiltScrollController.
        ViewGroup containerView = (ViewGroup) findViewById(R.id.list_container);
        mTiltScrollController.attach(containerView, new RecyclerViewTiltScrollable(), mRecyclerView);

        // Add a TouchEventTrigger to the TiltScrollController.
        // This trigger disables tilt scrolling when the view is in a touch event.
        mTiltScrollController.addScrollStateTrigger(new TouchEventTrigger(mRecyclerView));
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

    /**
     * Called every time the activity is launched.
     * Registers for tilt event listeners.
     */
    @Override
    public void onResume() {
        super.onResume();
        mTiltScrollController.registerEventObservers();
    }

    /**
     * Called upon application pause or shutdown.
     * Release the TiltScrollController's event listeners.
     */
    @Override
    public void onPause() {
        mTiltScrollController.unregisterEventObservers();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mDbHelper.close();
        super.onDestroy();
    }
}
