package com.example.mysubmission41;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mysubmission41.adapter.MovieAdapter;
import com.example.mysubmission41.pojo.Movie;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mysubmission41.ApiConfig.API_KEY;

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
        setup();
        loadUp(query);
    }

    private void loadUp(String query) {
        final ApiInterface apiInterface = ApiConfig.getClient().create(ApiInterface.class);
        Call<SearchMovieModel> call = apiInterface.getSearchMovie(API_KEY, query);
        call.enqueue(new Callback<SearchMovieModel>() {
            @Override
            public void onResponse(Call<SearchMovieModel> call, Response<SearchMovieModel> response) {
                movieArrayList.addAll(response.body().getMovie());
                movieAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SearchMovieModel> call, Throwable t) {
                Log.e(TAG, "Error in :", t);
                Toast.makeText(MSearchActivity.this, "Load Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setup() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        movieAdapter = new MovieAdapter(this, movieArrayList);
        recyclerView.setAdapter(movieAdapter);
    }

    private void setActionBarTitle(String query){
        Objects.requireNonNull(getSupportActionBar()).setTitle(query);
    }
}
