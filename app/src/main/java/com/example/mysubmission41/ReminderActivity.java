package com.example.mysubmission41;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;

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

    private final String TAG = "Notification: ";

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
    }
}
