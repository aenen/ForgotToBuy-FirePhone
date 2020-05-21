package com.dunno.aenen.forgottobuy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    public CustomReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();

        if (intentAction != null) {
            String toastMessage = "unknown intent action";

            // Error:(17, 20) error: strings in switch are not supported in -source 1.6
            // (use -source 7 or higher to enable strings in switch)
            // he-he.. окeй, настав час для if-elseif-elseif-elseif-elseif-elseif-elseif-elseif-elseif-elseif-else :)

            if (intentAction==Intent.ACTION_POWER_CONNECTED)
                    toastMessage = "Power connected!";
            else if (intentAction==Intent.ACTION_POWER_DISCONNECTED)
                toastMessage = "Power disconnected!";
            else if (intentAction==ACTION_CUSTOM_BROADCAST)
                toastMessage = "Custom Broadcast Received";

            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }
}
