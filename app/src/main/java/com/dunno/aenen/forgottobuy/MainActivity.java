package com.dunno.aenen.forgottobuy;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "whi are you");

        try {
            throw new Exception("I'm sorry.....");
        }
        catch (Throwable ex)
        {
            Log.e(LOG_TAG, ex.getMessage());
        }
    }
}
