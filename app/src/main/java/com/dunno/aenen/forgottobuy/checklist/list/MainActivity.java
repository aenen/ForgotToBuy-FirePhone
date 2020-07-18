package com.dunno.aenen.forgottobuy.checklist.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.Window;

import com.amazon.euclid.util.TiltScrollController;
import com.amazon.euclid.widget.ZContainer;
import com.amazon.euclid.widget.ZHeaderNavigationBar;
import com.dunno.aenen.forgottobuy.R;
import com.dunno.aenen.forgottobuy.checklist.insertion.ChecklistInsertionActivity;
import com.dunno.aenen.forgottobuy.database.ChecklistDTO;
import com.dunno.aenen.forgottobuy.database.ForgotToBuyDbHelper;

import java.util.Collections;
import java.util.List;

import amazon.widget.OnActionsMenuClickListener;

public class MainActivity extends Activity implements OnActionsMenuClickListener {

    public static final String EXTRA_IDCHECKLIST = "com.dunno.aenen.forgottobuy.extra.IDCHECKLIST";
    public static final int ADD_CHECKLIST_REQUEST = 1;
    private ForgotToBuyDbHelper mDbHelper;
    private RecyclerView mRecyclerView;
    private ChecklistAdapter mAdapter;
    private TiltScrollController mTiltScrollController;
    private List<ChecklistDTO> mChecklists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ((ZContainer) findViewById(R.id.content_host)).setBackgroundResource(R.color.teal);

        final ZHeaderNavigationBar headerNavBar = (ZHeaderNavigationBar) findViewById(R.id.zheadernavigationbar);
        headerNavBar.setOnHeaderActionsClickListener(this);

        mDbHelper = new ForgotToBuyDbHelper(getApplicationContext());

        mChecklists = mDbHelper.getChecklists();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new ChecklistAdapter(this, mChecklists);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                Long idChecklist = mChecklists.get(viewHolder.getAdapterPosition()).IdList;

                mDbHelper.deleteChecklist(idChecklist);
                mChecklists.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(mRecyclerView);
    }

    /**
     * Event handler when menu item is clicked.
     */
    @Override
    public void onActionClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.action_add_checklist:
                this.startActivityForResult(new Intent(this, ChecklistInsertionActivity.class), ADD_CHECKLIST_REQUEST);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        mDbHelper.close();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_CHECKLIST_REQUEST) {
            if (resultCode == RESULT_OK) {
                mChecklists.clear();
                mChecklists.addAll(mDbHelper.getChecklists());
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
