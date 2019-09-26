package com.example.favoriteapp.fragment;


import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.favoriteapp.R;
import com.example.favoriteapp.adapter.FavTvAdapt;

import static com.example.favoriteapp.DatabaseContract.CONTENT_URI;


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
        new loadData().execute();
        showListData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new loadData().execute();
                showListData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    private void showListData() {
        favTvAdapt = new FavTvAdapt(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(favTvAdapt);
        favTvAdapt.setListTv(list);
        recyclerView.setHasFixedSize(true);
    }

    private class loadData extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected Cursor doInBackground(Void... voids) {
            return getContext().getContentResolver().query(
                    CONTENT_URI,
                    null,
                    null,
                    null,
                    null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            list = cursor;
            favTvAdapt.setListTv(list);
            favTvAdapt.notifyDataSetChanged();
        }
    }
}
