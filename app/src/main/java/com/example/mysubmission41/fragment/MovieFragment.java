package com.example.mysubmission41.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysubmission41.R;
import com.example.mysubmission41.activity.DetailMovieActivity;
import com.example.mysubmission41.adapter.MovieAdapter;
import com.example.mysubmission41.click.ItemClickSupport;
import com.example.mysubmission41.pojo.Movie;
import com.example.mysubmission41.viewmodel.MovieViewModel;

import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MovieViewModel movieViewModel;
        recyclerView = Objects.requireNonNull(getActivity()).findViewById(R.id.rv_category_movie);
        progressBar = getActivity().findViewById(R.id.progress_bar);

        progressBar.setVisibility(View.VISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovie().observe(this, getMovie);
    }
    private Observer<List<Movie>> getMovie = new Observer<List<Movie>>() {
        @Override
        public void onChanged(List<Movie> movies) {
            MovieAdapter movieAdapter;
            movieAdapter = new MovieAdapter(getContext(), movies);
            recyclerView.setAdapter(movieAdapter);
            ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView, position, v) -> {
                Intent intent = new Intent(getActivity(), DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movies.get(position));
                startActivity(intent);
            });
            if (movies != null) {
                progressBar.setVisibility(View.GONE);
            }
        }
    };
}
