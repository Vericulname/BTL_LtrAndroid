package com.example.clockapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.clockapp.activity.Alarm_active;

public class alarmReceiver  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("alarm", "tests ");
        Intent i = new Intent(context, Alarm_active.class);
        Alarm_active a = new Alarm_active();
        Context context1 = a.getApplicationContext();
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
