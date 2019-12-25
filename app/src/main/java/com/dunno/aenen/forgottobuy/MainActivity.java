package com.dunno.aenen.forgottobuy;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.amazon.euclid.widget.ZShadowReceiver;


public class MainActivity extends Activity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove the title bar, and make the app fullscreen.
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        final ZShadowReceiver headerBackground = (ZShadowReceiver) findViewById(R.id.header_shadow_receiver);
        headerBackground.setBackgroundColor(Color.MAGENTA);

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
