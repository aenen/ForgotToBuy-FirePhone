package com.dunno.aenen.forgottobuy;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class WebPageActivity extends Activity implements LoaderManager.LoaderCallbacks<String>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page);
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

    public void getPageSource(View view) {
        String webProtocol = ((Spinner)findViewById(R.id.url_protocol)).getSelectedItem().toString();
        String url = ((EditText)findViewById(R.id.url)).getText().toString();

        Bundle queryBundle = new Bundle();
        queryBundle.putString("url", url);
        queryBundle.putString("webProtocol", webProtocol);
        getLoaderManager().restartLoader(0, queryBundle, this);

    }

    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {

        WebProtocol webProtocol = null;
        String url = "";

        if (bundle != null) {
            webProtocol = bundle.getString("webProtocol").equals("http://")
                    ? WebProtocol.HTTP      // Filthy poo poo code? yeah : (
                    : WebProtocol.HTTPS;    // Wanna go to bed and sleep
            url = bundle.getString("url");
        }

        return new PageSourceAsyncTask(this, webProtocol, url);
    }

    @Override
    public void onLoadFinished(Loader<String> stringLoader, String s) {
        ((TextView)findViewById(R.id.page_source)).setText(s);
    }

    @Override
    public void onLoaderReset(Loader<String> stringLoader) {

    }
}
