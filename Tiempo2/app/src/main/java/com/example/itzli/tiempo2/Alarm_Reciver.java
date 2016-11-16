package com.example.itzli.tiempo2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Itzli on 16/11/2016.
 */

public class Alarm_Reciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("We are in te receiver","Yey");
    }
}
