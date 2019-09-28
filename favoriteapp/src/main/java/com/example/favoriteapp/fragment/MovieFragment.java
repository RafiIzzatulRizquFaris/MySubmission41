package com.example.favoriteapp.fragment;


import android.content.Intent;
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

import com.example.favoriteapp.ItemClickSupport;
import com.example.favoriteapp.R;
import com.example.favoriteapp.adapter.FavMovieAdapt;

import static com.example.favoriteapp.DatabaseContract.CONTENT_URI;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FavMovieAdapt favMovieAdapt;
    private Cursor list;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        recyclerView = view.findViewById(R.id.rv_movie_favapp);
        swipeRefreshLayout = view.findViewById(R.id.srl_movie_favapp);

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
        favMovieAdapt = new FavMovieAdapt(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(favMovieAdapt);
        favMovieAdapt.setListMovie(list);
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
                    null
            );
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            list = cursor;
            favMovieAdapt.setListMovie(list);
            favMovieAdapt.notifyDataSetChanged();
            ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra(EXTRA_MOVIE, list.getInt(position));
                    startActivity(intent);
                }
            });
        }
    }
}
