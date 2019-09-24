package com.example.mysubmission41;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TSearchActivity extends AppCompatActivity {

    public static final String EXTRA_QUERY_TV = "search_tv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tsearch);

        String query = getIntent().getStringExtra(EXTRA_QUERY_TV);
    }
}
