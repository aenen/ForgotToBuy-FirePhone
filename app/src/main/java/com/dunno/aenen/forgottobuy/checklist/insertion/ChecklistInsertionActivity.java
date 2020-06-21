package com.dunno.aenen.forgottobuy.checklist.insertion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.amazon.euclid.widget.ZContainer;
import com.amazon.euclid.widget.ZHeaderNavigationBar;
import com.dunno.aenen.forgottobuy.R;

import amazon.widget.OnActionsMenuClickListener;

public class ChecklistInsertionActivity extends Activity implements OnActionsMenuClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_checklist_insertion);

        ((ZContainer) findViewById(R.id.checklist_insertion_navigation_bar_container)).setBackgroundResource(R.color.teal);

        final ZHeaderNavigationBar headerNavBar = (ZHeaderNavigationBar) findViewById(R.id.checklist_insertion_navigation_bar);
        headerNavBar.setOnHeaderActionsClickListener(this);
    }

    @Override
    public void onActionClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.action_add_checklist:
                this.startActivity(new Intent(this, ChecklistInsertionActivity.class));
                break;
        }
    }
}
