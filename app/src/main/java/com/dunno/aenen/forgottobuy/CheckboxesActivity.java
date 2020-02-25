package com.dunno.aenen.forgottobuy;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.amazon.euclid.widget.ZCheckBox;
import com.amazon.euclid.widget.ZContainer;
import com.amazon.euclid.widget.ZLinearLayout;


public class CheckboxesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_checkboxes);
        ((ZContainer) findViewById(R.id.content_host)).setBackgroundResource(R.color.teal);
    }

    public void onCheckBoxClick(View view) {
        displayToast(((ZCheckBox)view).getText().toString());
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void onShowToppingsClick(View view) {
        ZLinearLayout container = (ZLinearLayout) findViewById(R.id.topping_container);
        String toppings = "Toppings: ";

        for (int i = 0; i < container.getChildCount(); i++) {
            if (container.getChildAt(i).getClass() == ZCheckBox.class){
                ZCheckBox checkBox = (ZCheckBox)container.getChildAt(i);

                if(checkBox.isChecked())
                    toppings += " " + checkBox.getText().toString();
            }
        }

        displayToast(toppings);
    }
}
