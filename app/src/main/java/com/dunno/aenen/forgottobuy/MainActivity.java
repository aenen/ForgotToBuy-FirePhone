package com.dunno.aenen.forgottobuy;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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


public class MainActivity extends Activity implements OnActionsMenuClickListener {

    public static final String EXTRA_MESSAGE = "com.dunno.aenen.forgottobuy.extra.MESSAGE";
    private String mOrderMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ((ZContainer) findViewById(R.id.content_host)).setBackgroundResource(R.color.teal);

        final ZHeaderNavigationBar headerNavBar = (ZHeaderNavigationBar) findViewById(R.id.zheadernavigationbar);
        headerNavBar.setOnHeaderActionsClickListener(this);
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Shows a message that the donut image was clicked.
     */
    public void showDonutOrder(View view) {
        mOrderMessage = getString(R.string.donut_order_message);
        displayToast(mOrderMessage);
    }

    /**
     * Shows a message that the ice cream sandwich image was clicked.
     */
    public void showIceCreamOrder(View view) {
        mOrderMessage = getString(R.string.ice_cream_order_message);
        displayToast(mOrderMessage);
    }

    /**
     * Shows a message that the froyo image was clicked.
     */
    public void showFroyoOrder(View view) {
        mOrderMessage = getString(R.string.froyo_order_message);
        displayToast(mOrderMessage);
    }

    /**
     * Event handler when menu item is clicked.
     */
    @Override
    public void onActionClick(MenuItem menuItem) {
        Intent intent = new Intent(MainActivity.this, OrderActivity.class);
        intent.putExtra(EXTRA_MESSAGE, mOrderMessage);
        startActivity(intent);
    }
}
