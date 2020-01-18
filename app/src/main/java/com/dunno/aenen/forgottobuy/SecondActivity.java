package com.dunno.aenen.forgottobuy;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SecondActivity extends Activity {
    public static final String SHOPPING_ITEM_REPLY =
            "com.dunno.aenen.forgottobuy.extra.REPLY";
    private static final String LOG_TAG = SecondActivity.class.getSimpleName();
    private final List<String> buttonNames = Arrays.asList("Яблуко", "Багет", "Куряче філе", "Молоко", "Помідор", "Огірок", "Морква", "Греча", "Рис", "Кіндер сюрприз", "Кола", "Булочка", "Часник", "Лимон");
private LinearLayout productContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        productContainer = (LinearLayout) findViewById(R.id.product_container);

        for (String product : buttonNames) {
            Button btn = new Button(this);
            btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btn.setText(product);
            btn.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    Intent replyIntent = new Intent();
                    replyIntent.putExtra(SHOPPING_ITEM_REPLY, ((Button)v).getText());
                    setResult(RESULT_OK, replyIntent);
                    finish();
                }
            });

            productContainer.addView(btn);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
