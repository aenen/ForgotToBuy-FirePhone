package com.dunno.aenen.forgottobuy;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.amazon.euclid.widget.ZShadowReceiver;
import com.amazon.euclid.widget.ZTextView;


public class MainActivity extends Activity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private int mCount = 0;
    private ZTextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove the title bar, and make the app fullscreen.
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        mShowCount = (ZTextView) findViewById(R.id.show_count);

        final ZShadowReceiver headerBackground = (ZShadowReceiver) findViewById(R.id.header_shadow_receiver);
        headerBackground.setBackgroundColor(Color.GRAY);

        Log.d("MainActivity", "whi are you");

        try {
            throw new Exception("I'm sorry.....");
        }
        catch (Throwable ex)
        {
            Log.e(LOG_TAG, ex.getMessage());
        }
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this,R.string.toast_message,Toast.LENGTH_SHORT);
        toast.show();
    }

    public void countUp(View view) {
        ++mCount;
        if(mShowCount != null)
            mShowCount.setText(Integer.toString((mCount)));
    }
}
