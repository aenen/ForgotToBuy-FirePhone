package com.dunno.aenen.forgottobuy;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;


public class StandUpActivity extends Activity {

    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 1;
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand_up);

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        final Intent notifyIntent = new Intent(this, AlarmReceiver.class);

        boolean alarmUp = (PendingIntent.getBroadcast(StandUpActivity.this, NOTIFICATION_ID, notifyIntent,
                PendingIntent.FLAG_NO_CREATE) != null);
        ToggleButton alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);
        alarmToggle.setChecked(alarmUp);

        alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String toastMessage;

                final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                        (StandUpActivity.this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                if (isChecked) {
                    long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
                    long triggerTime = SystemClock.elapsedRealtime()
                            + repeatInterval;

                    //If the Toggle is turned on, set the repeating alarm with a 15 minute interval
                    if (alarmManager != null) {
                        alarmManager.setInexactRepeating
                                (AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                        triggerTime, repeatInterval, notifyPendingIntent);
                    }

                    toastMessage = getString(R.string.alarm_on_desc);
                }
                else {
                    if (alarmManager != null) {
                        alarmManager.cancel(notifyPendingIntent);
                        notifyPendingIntent.cancel();
                    }
                    mNotificationManager.cancelAll();
                    toastMessage = getString(R.string.alarm_off_desc);
                }

                Toast.makeText(StandUpActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
