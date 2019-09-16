package com.example.mysubmission41;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Switch;

public class ReminderActivity extends AppCompatActivity {

    Switch aSwitchDaily, aSwitchRelease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        aSwitchDaily = findViewById(R.id.sw_daily_reminder);
        aSwitchRelease = findViewById(R.id.sw_release_reminder);

    }
}
