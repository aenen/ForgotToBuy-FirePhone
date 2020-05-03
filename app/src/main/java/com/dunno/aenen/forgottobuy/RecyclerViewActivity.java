package com.dunno.aenen.forgottobuy;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.amazon.euclid.widget.ZToolBarItem;

import java.util.LinkedList;


public class RecyclerViewActivity extends Activity implements View.OnClickListener {

    private final LinkedList<String> mWordList = new LinkedList<String>();

    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        for (int i = 0; i < 10; i++) {
            mWordList.addLast("Word " + i);
        }

        // Get a handle to the RecyclerView.
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new WordListAdapter(this, mWordList);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ZToolBarItem toolBarItem = null;

        // TabBarItem must have their onClickListeners and popupListeners set outside of the layout.
        toolBarItem = (ZToolBarItem)findViewById(R.id.addRvItem);
        toolBarItem.setOnClickListener(this);

        toolBarItem = (ZToolBarItem)findViewById(R.id.removeFirstRvItem);
        toolBarItem.setOnClickListener(this);

        toolBarItem = (ZToolBarItem)findViewById(R.id.removeLastRvItem);
        toolBarItem.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.addRvItem:
                int wordListSize = mWordList.size();
                // Add a new word to the wordList.
                mWordList.addLast("+ Word " + wordListSize);
                // Notify the adapter, that the data has changed.
                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
                // Scroll to the bottom.
                mRecyclerView.smoothScrollToPosition(wordListSize);
                break;
            case R.id.removeFirstRvItem:
                mWordList.removeFirst();
                mRecyclerView.getAdapter().notifyItemRemoved(0);
                ((LinearLayoutManager)mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(0,0);
//                new LinearLayoutManager(this).scrollToPositionWithOffset(0, 0);
//                mRecyclerView.smoothScrollToPosition(0);
                break;
            case R.id.removeLastRvItem:
                mWordList.removeLast();
                mRecyclerView.getAdapter().notifyItemRemoved(mWordList.size());
                mRecyclerView.smoothScrollToPosition(mWordList.size() - 1);
                break;
        }
    }
}
