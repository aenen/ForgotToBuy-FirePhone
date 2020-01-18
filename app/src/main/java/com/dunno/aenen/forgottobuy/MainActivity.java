package com.dunno.aenen.forgottobuy;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import com.amazon.euclid.widget.ZLinearLayout;
import com.amazon.euclid.widget.ZShadowReceiver;
import com.amazon.euclid.widget.ZTextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final int SHOPPING_ITEM_REQUEST = 1;
    ZLinearLayout shoppingListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

        // Remove the title bar, and make the app fullscreen.
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        shoppingListContainer = (ZLinearLayout) findViewById(R.id.shopping_list_container);

        // Restore the state.
        // See onSaveInstanceState() for what gets saved.
        Log.d(LOG_TAG, "savedInstanceState != null: " + (savedInstanceState != null));
        if (savedInstanceState != null) {
            ArrayList<String> shoppingItems = savedInstanceState.getStringArrayList("shoppingItems");

            if(shoppingItems != null) {
                for (String shoppingItem : shoppingItems) {
                    ZTextView textView = new ZTextView(this);
                    textView.setText(shoppingItem);
                    textView.setLayoutParams(new ZLinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                    shoppingListContainer.addView(textView);
                }
            }
        }

        final ZContainer headerBackground = (ZContainer) findViewById(R.id.header_shadow_receiver);
        headerBackground.setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(LOG_TAG, "onSaveInstanceState");

        ArrayList<String> shoppingItemNames = new ArrayList<String>();

        for (int i = 0; i < shoppingListContainer.getChildCount(); i++) {
            View view = shoppingListContainer.getChildAt(i);

            if (view instanceof ZTextView)
                shoppingItemNames.add(((ZTextView)view).getText().toString());
        }

        outState.putStringArrayList("shoppingItems", shoppingItemNames);
    }

    public void onAddShoppingItemClick(View view) {
        startActivityForResult(new Intent(this, SecondActivity.class), SHOPPING_ITEM_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SHOPPING_ITEM_REQUEST) {
            if (resultCode == RESULT_OK) {
                String shoppingItem = data.getStringExtra(SecondActivity.SHOPPING_ITEM_REPLY);

                ZTextView textView = new ZTextView(this);
                textView.setText(shoppingItem);
                textView.setLayoutParams(new ZLinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                shoppingListContainer.addView(textView);
            }
        }
    }



    @Override
    public void onStart(){
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }
}
