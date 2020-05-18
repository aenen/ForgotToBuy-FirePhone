package com.dunno.aenen.forgottobuy;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by Yaroslav on 18.05.2020.
 */
public class BookLoader extends AsyncTaskLoader<String> {

    private String mQueryString;

    public BookLoader(@NonNull Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        forceLoad();
    }

    @Override
    public String loadInBackground() {
        return NetworkUtils.getBookInfo(mQueryString);
    }
}
