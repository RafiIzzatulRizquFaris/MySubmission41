package com.example.mysubmission41;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {
    private String PREFS = "moviecatalogue";
    private String DAILY_REMINDER = "dailyreminder";
    private String RELEASE_REMINDER = "releasereminder";
    private String MSG_DAILY = "messagedaily";
    private String MSG_RELEASE = "messagerelease";

    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;

    public Preference(Context context) {
        preferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setTimeDaily(String time) {
        editor.putString(DAILY_REMINDER, time);
        editor.commit();
    }

    public void setMsgDaily(String message) {
        editor.putString(MSG_DAILY, message);
    }

    public void setReleaseTime(String time) {
        editor.putString(RELEASE_REMINDER, time);
        editor.commit();
    }

    public void setMsgRelease(String message) {
        editor.putString(MSG_RELEASE, message);
    }
}
