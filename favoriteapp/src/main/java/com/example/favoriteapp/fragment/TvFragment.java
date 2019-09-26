package com.example.favoriteapp.fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.favoriteapp.R;
import com.example.favoriteapp.adapter.FavTvAdapt;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FavTvAdapt favTvAdapt;
    private Cursor list;

    public TvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv, container, false);
        recyclerView = view.findViewById(R.id.rv_tv_favapp);
        swipeRefreshLayout = view.findViewById(R.id.srl_tv_favapp);
        return view;
    }

}
