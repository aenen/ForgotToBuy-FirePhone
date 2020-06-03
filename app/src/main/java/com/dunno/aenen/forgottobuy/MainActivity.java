package com.dunno.aenen.forgottobuy;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.Window;

import com.amazon.euclid.util.TiltScrollController;
import com.amazon.euclid.widget.ZContainer;
import com.amazon.euclid.widget.ZHeaderNavigationBar;

import java.util.List;

import amazon.widget.OnActionsMenuClickListener;

public class MainActivity extends Activity implements OnActionsMenuClickListener {

    private ForgotToBuyDbHelper mDbHelper;
    private RecyclerView mRecyclerView;
    private ChecklistAdapter mAdapter;
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

        List<ChecklistDTO> lists = mDbHelper.getChecklists();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new ChecklistAdapter(this, lists);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Event handler when menu item is clicked.
     */
    @Override
    public void onActionClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.action_add_checklist:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        mDbHelper.close();
        super.onDestroy();
    }
}
