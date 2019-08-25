package com.example.mysubmission41;


import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.example.mysubmission41.DatabaseContract.CONTENT_URI;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFavFragment extends Fragment {

    private FavMovieAdapter favMovieAdapter;
    private Cursor list;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;


    public MovieFavFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_movie_fav, container, false);
        swipeRefreshLayout = view.findViewById(R.id.sr_favorite_movie);
        recyclerView = view.findViewById(R.id.rv_favorite_movie);

        new loadData().execute();
        showListData();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            new loadData().execute();
            showListData();
            swipeRefreshLayout.setRefreshing(false);
        });
        return view;
    }

    private void showListData() {
        favMovieAdapter = new FavMovieAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(favMovieAdapter);
        favMovieAdapter.setLismovies(list);
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
            favMovieAdapter.setLismovies(list);
            favMovieAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
