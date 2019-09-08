package com.example.mysubmission41.favorite.fragmentfav;


import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mysubmission41.R;
import com.example.mysubmission41.activity.DetailMovieActivity;
import com.example.mysubmission41.adapter.MovieAdapter;
import com.example.mysubmission41.click.ItemClickSupport;
import com.example.mysubmission41.pojo.Movie;

import java.util.ArrayList;
import java.util.List;

import static com.example.mysubmission41.favorite.DatabaseContract.CONTENT_URI;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFavFragment extends Fragment {

//    private MovieAdapter favMovieAdapter;
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
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new loadData().execute();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            new loadData().execute();
            swipeRefreshLayout.setRefreshing(false);
        });
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

            List<Movie> list = new ArrayList<>();
            while(cursor.moveToNext()) {
                Movie data = new Movie(cursor);
                list.add(data);
            }

            MovieAdapter favMovieAdapter = new MovieAdapter(getContext(), list);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(favMovieAdapter);
            ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView, position, v) -> {
                Intent intent = new Intent(getActivity(), DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, list.get(position));
                startActivity(intent);
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
