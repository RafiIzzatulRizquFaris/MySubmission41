package com.example.favoriteapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.favoriteapp.fragment.MovieFragment;
import com.example.favoriteapp.fragment.TvFragment;

public class TabFragmentAdapter extends FragmentPagerAdapter {

    private int[] title = new int[]{R.string.title_movies, R.string.title_tv_show};
    private Context cContext;

    public TabFragmentAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        cContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch(position){
            case 0:
                return new MovieFragment();
            case 1 :
                return new TvFragment();
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return cContext.getResources().getString(title[position]);
    }

    @Override
    public int getCount() {
        return title.length;
    }
}
