package com.dunno.aenen.forgottobuy;

import amazon.widget.TabBar.OnTabChangeListener;
import amazon.widget.TabBarItem;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import com.amazon.euclid.widget.ZContainer;
import com.amazon.euclid.widget.ZTabBar;
import com.amazon.euclid.widget.ZTabBarItem;


public class TabBarActivity extends Activity implements OnTabChangeListener, OnClickListener {

    private static final String TAG = "ZTabBarActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tab_bar);

        ((ZContainer) findViewById(R.id.content_host)).setBackgroundResource(R.color.teal);

        final ZTabBar tabBar = (ZTabBar)findViewById(R.id.ztabbar);
        tabBar.setOnTabChangedListener(this);

        ZTabBarItem tabBarItem = null;

        // TabBarItems must have their onClickListeners set outside of the layout.
        tabBarItem = (ZTabBarItem)findViewById(R.id.ztabbaritem1);
        tabBarItem.setOnClickListener(this);

        tabBarItem = (ZTabBarItem)findViewById(R.id.ztabbaritem2);
        tabBarItem.setOnClickListener(this);

        tabBarItem = (ZTabBarItem)findViewById(R.id.ztabbaritem3);
        tabBarItem.setOnClickListener(this);
    }

    /**
     * Called before the selection changes.
     */
    @Override
    public void onTabChangeBegin() {
        Log.v(TAG, "onTabChangeBegin");
    }

    /**
     * Called after each tab change.
     */
    @Override
    public void onTabChangeEnd() {
        Log.v(TAG, "onTabChangeEnd");
    }

    /**
     * Called when an already selected tab is selected again.
     */
    @Override
    public void onTabReselected(TabBarItem tabBarItem) {
        Log.v(TAG, "onTabReselected: " + tabBarItem.getText());
    }

    /**
     * Called when the user selects a new tab.
     */
    @Override
    public void onTabSelected(TabBarItem tabBarItem) {
        Log.v(TAG, "onTabSelected: " + tabBarItem.getText());
        getFragmentManager().beginTransaction().replace(R.id.tabContent, new TabFragment1()).commit();
    }

    /**
     * Called when a tab is no longer selected.
     */
    @Override
    public void onTabUnselected(TabBarItem tabBarItem) {
        Log.v(TAG, "onTabUnselected: " + tabBarItem.getText());
    }

    /**
     * Called when a given view is clicked.
     */
    @Override
    public void onClick(View view) {
        final ZTabBarItem tabBarItem = (ZTabBarItem)view;
        Log.v(TAG, "onClick " + tabBarItem.getText());
    }
}
