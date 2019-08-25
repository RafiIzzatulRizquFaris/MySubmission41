package com.example.mysubmission41;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.mysubmission41.adapter.TabFavoriteAdapter;
import com.google.android.material.tabs.TabLayout;

public class FavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        FragmentPagerAdapter sectionsPagerAdapter = new TabFavoriteAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.favorite_view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.favorite_tab_layout);
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorPrimaryDark),getResources().getColor(android.R.color.white));
        tabLayout.setupWithViewPager(viewPager);
    }
}
