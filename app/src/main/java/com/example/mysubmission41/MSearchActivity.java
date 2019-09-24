package com.example.mysubmission41;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MSearchActivity extends AppCompatActivity {

    public static final String EXTRA_QUERY = "search_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msearch);

        String query = getIntent().getStringExtra(EXTRA_QUERY);
    }
}
