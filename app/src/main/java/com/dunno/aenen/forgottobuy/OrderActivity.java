package com.dunno.aenen.forgottobuy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.amazon.euclid.widget.ZContainer;
import com.amazon.euclid.widget.ZTextView;


public class OrderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_order);

        ((ZContainer) findViewById(R.id.content_host)).setBackgroundResource(R.color.teal);

        Intent intent = getIntent();
        String message = "Order: " + intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        ZTextView zTextView = (ZTextView) findViewById(R.id.order_textview);
        zTextView.setText(message);
    }

    /**
     * OnNavigationUp callback when clicking on the header title area.
     */
    @Override
    public boolean onNavigateUp() {
        return super.onNavigateUp();
    }
}
