package com.dunno.aenen.forgottobuy.checklist.insertion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
import com.dunno.aenen.forgottobuy.database.ForgotToBuyDbHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import amazon.widget.OnActionsMenuClickListener;

public class ChecklistInsertionActivity extends Activity implements OnActionsMenuClickListener {

    private RecyclerView mRecyclerView;
    private ForgotToBuyDbHelper mDbHelper;
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

        mDbHelper = new ForgotToBuyDbHelper(getApplicationContext());

        EditText product = (EditText) findViewById(R.id.checklist_insertion_product);

        product.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                addProduct();
                return true;
            }
        });

        final ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                ItemTouchHelper.DOWN | ItemTouchHelper.UP, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(mChecklistItemNames, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                mChecklistItemNames.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        helper.attachToRecyclerView(mRecyclerView);

        product.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

//                if (s.length() != 0 ){
//                    EditText product = (EditText)findViewById(R.id.checklist_insertion_product);
//
//                    if (!predictedProduct.equals(s.toString()) && !currentPredictedSumething.equals(s.toString())) {
//
//                        int shouldIPredictItMmmhhh = predictedProductName.indexOf(s.toString());
//                        if(shouldIPredictItMmmhhh == 0) {
//                            predictedProduct = s + predictedProductName.substring(s.length());
//                            currentPredictedSumething = s.toString();
//                            product.setText(predictedProduct);
//                            product.setSelection(s.length(), product.length());
//                        }
//                    }
//                }
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
        switch (menuItem.getItemId()) {
            case R.id.action_checklist_insertion_save:
                mDbHelper.insertChecklist(((EditText) findViewById(R.id.checklist_insertion_title)).getText().toString(), mChecklistItemNames);
                setResult(RESULT_OK);
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        mDbHelper.close();
        super.onDestroy();
    }

    public void onProductInsertionClick(View view) {
        addProduct();
    }

    private void addProduct() {
        EditText product = (EditText) findViewById(R.id.checklist_insertion_product);
        String productName = product.getText().toString();

        if (productName.isEmpty())
            return;

        mChecklistItemNames.add(0, productName);
        mRecyclerView.getAdapter().notifyItemInserted(0);
        mRecyclerView.scrollToPosition(0);

        product.setText(null);
    }
}
