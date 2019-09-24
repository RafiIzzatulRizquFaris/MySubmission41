package com.example.mysubmission41;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.mysubmission41.adapter.MovieAdapter;
import com.example.mysubmission41.pojo.Movie;

import java.util.ArrayList;
import java.util.Objects;

public class MSearchActivity extends AppCompatActivity {

    public static final String EXTRA_QUERY = "search_movie";
    private static final String TAG = "MSearchActivity";

    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    ArrayList<Movie> movieArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msearch);
        String query = getIntent().getStringExtra(EXTRA_QUERY);
        Log.d(TAG, "search: " + query);
        setActionBarTitle(query);

        recyclerView = findViewById(R.id.rv_search_movie);
    }

    private void setActionBarTitle(String query){
        Objects.requireNonNull(getSupportActionBar()).setTitle(query);
    }
}
