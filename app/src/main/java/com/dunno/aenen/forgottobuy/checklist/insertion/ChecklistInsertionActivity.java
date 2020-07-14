package com.dunno.aenen.forgottobuy.checklist.insertion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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
import com.dunno.aenen.forgottobuy.checklist.list.ChecklistAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import amazon.widget.OnActionsMenuClickListener;

public class ChecklistInsertionActivity extends Activity implements OnActionsMenuClickListener {

    private RecyclerView mRecyclerView;
    private ChecklistInsertionItemAdapter mAdapter;
    private String predictedProduct = "Я";
    private String currentPredictedSumething = "";
    private String predictedProductName = "Яблуко";
    private LinkedList<String> mChecklistItemNames = new LinkedList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_checklist_insertion);

        ((ZContainer) findViewById(R.id.checklist_insertion_navigation_bar_container)).setBackgroundResource(R.color.teal);

        final ZHeaderNavigationBar headerNavBar = (ZHeaderNavigationBar) findViewById(R.id.checklist_insertion_navigation_bar);
        headerNavBar.setOnHeaderActionsClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.checklist_insertion_items_rv);
        mAdapter = new ChecklistInsertionItemAdapter(this, mChecklistItemNames);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        EditText product = (EditText)findViewById(R.id.checklist_insertion_product);

        product.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                Toast.makeText(ChecklistInsertionActivity.this, v.getText(), Toast.LENGTH_SHORT).show();

                mChecklistItemNames.add(0, v.getText().toString());
                mRecyclerView.getAdapter().notifyItemInserted(0);

                v.setText(null);
                return true;
            }
        });


        product.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() != 0 ){
                    EditText product = (EditText)findViewById(R.id.checklist_insertion_product);
//
                    if (!predictedProduct.equals(s.toString()) && !currentPredictedSumething.equals(s.toString())) {

                        int shouldIPredictItMmmhhh = predictedProductName.indexOf(s.toString());
                        if(shouldIPredictItMmmhhh == 0) {
                            predictedProduct = s + predictedProductName.substring(s.length());
                            currentPredictedSumething = s.toString();
                            product.setText(predictedProduct);
                            product.setSelection(s.length(), product.length());
                        }
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {



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
//        product.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

        int mChecklistItemNamesSize = mChecklistItemNames.size();
        mChecklistItemNames.add(0, product.getText().toString());
        mRecyclerView.getAdapter().notifyItemInserted(0);

        product.setText(null);
//        product.setSelection(1, product.length());

//        product.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
    }
}
