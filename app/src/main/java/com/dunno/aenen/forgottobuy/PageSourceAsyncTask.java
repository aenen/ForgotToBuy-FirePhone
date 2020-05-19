package com.dunno.aenen.forgottobuy;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.net.Uri;

import org.apache.http.HttpHost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Yaroslav on 19.05.2020.
 */
public class PageSourceAsyncTask extends AsyncTaskLoader<String> {

    private String mUrl;
    private WebProtocol mWebProtocol;

    public PageSourceAsyncTask(Context context, WebProtocol webProtocol, String url) {
        super(context);
        mWebProtocol = webProtocol;
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        forceLoad();
    }

    @Override
    public String loadInBackground() {
        String result = null;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            URL requestUrl = new URL(Uri.parse(mWebProtocol.name() + "://" + mUrl).toString());

            switch (mWebProtocol) {
                case HTTP:
                    urlConnection = (HttpURLConnection) requestUrl.openConnection();
                    break;
                case HTTPS:
                    urlConnection = (HttpsURLConnection) requestUrl.openConnection();
                    break;
            }

            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

            result = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return result;
    }


}
