package com.example.mysubmission41.notification;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mysubmission41.R;

import java.util.Objects;

public class ReminderActivity extends AppCompatActivity {

    private String DAILY_REMINDER = "daily";
    private String RELEASE_REMINDER = "release";
    private String KEY_DAILY_REMINDER = "dailyreminder";
    private String KEY_RELEASE_REMINDER = "releasereminder";

    public DailyReminder dailyReminder;
    public ReleaseReminder releaseReminder;
    public SharedPreferences dailyPref,releasePref;
    public SharedPreferences.Editor dailyEdt, releaseEdt;
    public Preference preference;

    String timeDaily = "07:00";
    String timeRelease = "08:00";

    Switch aSwitchDaily, aSwitchRelease;

    private void setActionBarTitle(){
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.set_reminder));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        setActionBarTitle();

        aSwitchDaily = findViewById(R.id.sw_daily_reminder);
        aSwitchRelease = findViewById(R.id.sw_release_reminder);

        dailyReminder = new DailyReminder();
        releaseReminder = new ReleaseReminder();
        preference = new Preference(this);
        setPreference();

        aSwitchDaily.setOnCheckedChangeListener((buttonView, isChecked) -> setDaily(isChecked));

        aSwitchRelease.setOnCheckedChangeListener((buttonView, isChecked) -> setRelease(isChecked));
    }

    private void dailyOn() {
        String message = getResources().getString(R.string.message_daily_remainder);
        preference.setTimeDaily(timeDaily);
        preference.setMsgDaily(message);
        String TAG = "Notification: ";
        Log.d(TAG, timeDaily);
        dailyReminder.setAlarm(ReminderActivity.this, DAILY_REMINDER, timeDaily, message);
    }

    private void dailyOff() {
        dailyReminder.cancelNotification(ReminderActivity.this);
    }

    private void releaseOn() {
        String message = getResources().getString(R.string.message_release_today);
        preference.setReleaseTime(timeRelease);
        preference.setMsgRelease(message);
        releaseReminder.setAlarmRelease(ReminderActivity.this, RELEASE_REMINDER, timeRelease, message);
    }

    private void releaseOff() {
        releaseReminder.cancelNotificationRelease(ReminderActivity.this);
    }

    private void setRelease(boolean isChecked) {
        releaseEdt = releasePref.edit();
        if (isChecked) {
            releaseEdt.putBoolean(KEY_RELEASE_REMINDER, true);
            releaseEdt.apply();
            releaseOn();
        } else {
            releaseEdt.putBoolean(KEY_RELEASE_REMINDER, false);
            releaseEdt.apply();
            releaseOff();
        }
    }

    private void setDaily(boolean isChecked) {
        dailyEdt = dailyPref.edit();
        if (isChecked) {
            dailyEdt.putBoolean(KEY_DAILY_REMINDER, true);
            dailyEdt.apply();
            dailyOn();
        } else {
            dailyEdt.putBoolean(KEY_DAILY_REMINDER, false);
            dailyEdt.apply();
            dailyOff();
        }
    }

    private void setPreference() {
        dailyPref = getSharedPreferences(DAILY_REMINDER, MODE_PRIVATE);
        boolean unCheckedDaily = dailyPref.getBoolean(KEY_DAILY_REMINDER, false);
        aSwitchDaily.setChecked(unCheckedDaily);
        releasePref = getSharedPreferences(RELEASE_REMINDER, MODE_PRIVATE);
        boolean unCheckedRelease = releasePref.getBoolean(KEY_RELEASE_REMINDER, false);
        aSwitchRelease.setChecked(unCheckedRelease);

    }
}
