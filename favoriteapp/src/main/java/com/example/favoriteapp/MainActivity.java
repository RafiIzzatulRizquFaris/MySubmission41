package com.example.favoriteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentPagerAdapter sectionsPagerAdapter = new TabFragmentAdapter(getSupportFragmentManager(), this);
        ViewPager viewPager = findViewById(R.id.view_pager_favapp);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tab_layout_favapp);
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorPrimaryDark),getResources().getColor(android.R.color.white));
        tabLayout.setupWithViewPager(viewPager);
    }
}
