package com.dunno.aenen.forgottobuy;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ScorekeeperActivity extends Activity {

    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";

    private int mScore1;
    private int mScore2;

    private TextView mScoreText1;
    private TextView mScoreText2;

    private boolean mNightMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorekeeper);

        mScoreText1 = (TextView) findViewById(R.id.score_1);
        mScoreText2 = (TextView) findViewById(R.id.score_2);

        if(savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2);

            // Set the score text views
            mScoreText1.setText(String.valueOf(mScore1));
            mScoreText2.setText(String.valueOf(mScore2));
        }
    }

    public void decreaseScore(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.decreaseTeam1:
                mScore1--;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.decreaseTeam2:
                mScore2--;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
        }
    }

    public void increaseScore(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.increaseTeam1:
                mScore1++;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.increaseTeam2:
                mScore2++;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.scorekeeper_menu, menu);

        if (mNightMode)
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        else
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.night_mode) {
            if(mNightMode) {
                setTheme(euclid.R.style.Theme_Euclid_Light);
                mNightMode = !mNightMode;
            } else {
                setTheme(euclid.R.style.Theme_Euclid_Dark);
                mNightMode = !mNightMode;
            }

            recreate();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the scores.
        outState.putInt(STATE_SCORE_1, mScore1);
        outState.putInt(STATE_SCORE_2, mScore2);
        super.onSaveInstanceState(outState);
    }
}
