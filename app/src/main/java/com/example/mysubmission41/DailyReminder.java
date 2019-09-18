package com.example.mysubmission41;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DailyReminder extends BroadcastReceiver {

    public static final int NOTIFICATION_ID = 10;
    public static String CHANNEL_ID = "channel_01";
    public static CharSequence CHANNEL_NAME = "daily channel";

    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
