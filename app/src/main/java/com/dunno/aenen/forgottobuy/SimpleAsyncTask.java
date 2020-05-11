package com.dunno.aenen.forgottobuy;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

/**
 * Created by Yaroslav on 10.05.2020.
 */
public class SimpleAsyncTask extends AsyncTask<Void, Integer, String>{
    private WeakReference<TextView> mTextView;

    SimpleAsyncTask(TextView tv) {
        mTextView = new WeakReference<TextView>(tv);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11) * 200;

        int s = 0;

        try {
            while(s < n) {
                Thread.sleep(1);
                s++;
                publishProgress(s);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mTextView.get().setText(values[0].toString());
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
}
