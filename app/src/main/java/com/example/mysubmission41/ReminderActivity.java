package com.example.mysubmission41;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Switch;

import java.util.Objects;

public class ReminderActivity extends AppCompatActivity {

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

    }
}
