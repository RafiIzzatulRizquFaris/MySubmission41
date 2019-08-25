package com.example.mysubmission41.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mysubmission41.R;

import java.util.Objects;

public class AboutActivity extends AppCompatActivity {

    ImageView logoToughput;

    private void setActionBarTitle(){
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.about));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setActionBarTitle();

        logoToughput = findViewById(R.id.logo_toughput);
        Glide.with(this).load(R.drawable.toughput).into(logoToughput);
    }
}
