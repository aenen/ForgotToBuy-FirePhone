package com.dunno.aenen.forgottobuy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.amazon.euclid.widget.ZContainer;
import com.amazon.euclid.widget.ZHeaderNavigationBar;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;


public class ChecklistDetailActivity extends Activity {

    private ForgotToBuyDbHelper mDbHelper;
    private RecyclerView mRecyclerView;
    private ChecklistItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_detail);

        ((ZContainer) findViewById(R.id.content_host)).setBackgroundResource(R.color.teal);
        final ZHeaderNavigationBar headerNavBar = (ZHeaderNavigationBar) findViewById(R.id.zheadernavigationbar);

        Intent intent = getIntent();
        long idChecklist = intent.getLongExtra(MainActivity.EXTRA_IDCHECKLIST, 0);

        mDbHelper = new ForgotToBuyDbHelper(getApplicationContext());

        if(idChecklist != 0) {
            ChecklistDTO checklist = mDbHelper.getChecklist(idChecklist);
            Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String checklistCreationDate = formatter.format(checklist.CreationDate);
            headerNavBar.setSubtitle(checklistCreationDate);
            headerNavBar.setMainTitle(checklist.Title == null || checklist.Title.isEmpty()? "Details" : checklist.Title );
        }

        mDbHelper = new ForgotToBuyDbHelper(getApplicationContext());

        List<ChecklistItemDTO> lists = mDbHelper.getChecklistItems(idChecklist);
        mRecyclerView = (RecyclerView) findViewById(R.id.checklist_detail_items_rv);
        mAdapter = new ChecklistItemAdapter(this, lists, mDbHelper);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        mDbHelper.close();
        super.onDestroy();
    }
}
