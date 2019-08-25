package com.example.mysubmission41.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mysubmission41.MovieFavFragment;
import com.example.mysubmission41.R;
import com.example.mysubmission41.TvShowFavFragment;

public class TabFavoriteAdapter extends FragmentPagerAdapter {

    private int[] title = new int[]{R.string.title_movies, R.string.title_tv_show};
    private Context cContext;

    public TabFavoriteAdapter(Context context, FragmentManager fm) {
        super(fm);
        cContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch(position){
            case 0:
                return new MovieFavFragment();
            case 1 :
                return new TvShowFavFragment();
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
