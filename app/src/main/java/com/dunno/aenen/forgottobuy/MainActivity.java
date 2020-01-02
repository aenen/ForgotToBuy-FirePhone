package com.dunno.aenen.forgottobuy;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amazon.euclid.util.TiltScrollController;
import com.amazon.euclid.widget.ZShadowReceiver;
import com.amazon.euclid.widget.ZTextView;


public class MainActivity extends Activity {

    // TiltScrollController used to control tilt scrolling.
    private TiltScrollController mTiltScrollController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        try {
            super.onCreate(savedInstanceState);

            // Remove the title bar, and make the app fullscreen.
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.activity_main);

            final ZShadowReceiver headerBackground = (ZShadowReceiver) findViewById(R.id.header_shadow_receiver);
            headerBackground.setBackgroundColor(Color.BLUE);
//        }
//        catch(Throwable ex){
//            Log.e("error", ex.getMessage());
//        }
    }
}
