package com.example.britz.firebasechat.data;

import android.content.Context;
import android.content.SharedPreferences;

public class Pref {
    SharedPreferences pref;
    SharedPreferences.Editor ed;
    static Pref me = null;
    String preferenceId = "britz.Pref";

    final String GOOGLE_AD_ID = "google_ad_id";
    final String USER_NAME = "user_name";
    private Pref(Context context) {
        me = this;
        context = context.getApplicationContext();
        pref = context.getSharedPreferences(preferenceId, 0);
        ed = pref.edit();
    }

    public static Pref getInstance(Context context) {
        if(me == null)
            me = new Pref(context);
        return me;
    }
    public String getUserName() {
        return  pref.getString(USER_NAME, null);
    }

    public void setUserName(String name) {
        ed.putString(USER_NAME, name);
        ed.commit();
    }

    public String getGoogleAdId() {
        return pref.getString(GOOGLE_AD_ID, null);
    }

    public void setGoogleAdId(String adid) {
        ed.putString(GOOGLE_AD_ID, adid);
        ed.commit();
    }
}
