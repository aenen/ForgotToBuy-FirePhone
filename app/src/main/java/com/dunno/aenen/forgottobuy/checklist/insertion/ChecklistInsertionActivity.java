package com.dunno.aenen.forgottobuy.checklist.insertion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        EditText product = (EditText)findViewById(R.id.checklist_insertion_product);

        product.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Toast.makeText(ChecklistInsertionActivity.this, v.getText(), Toast.LENGTH_SHORT).show();
                v.setText(null);
                return true;
            }
        });
    }

    @Override
    public void onActionClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.action_add_checklist:
                this.startActivity(new Intent(this, ChecklistInsertionActivity.class));
                break;
        }
    }

    public void onProductInsertionClick(View view) {

        EditText product = (EditText)findViewById(R.id.checklist_insertion_product);
        product.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

//        product.setSelection(3, product.length());
        product.setText(null);

//        product.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
    }
}
