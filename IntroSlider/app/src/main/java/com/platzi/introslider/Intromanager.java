package com.platzi.introslider;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by itzli on 24/10/2016.
 */

public class Intromanager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "itlzi-welcome";
    private static final  String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public Intromanager(Context context)
    {
        this.context=context;
        pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch (boolean isFirtsTime)
    {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH,isFirtsTime);
        editor.commit();
    }
    public boolean isFirstTimeLaunch()
    {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH,true);
    }
}
