package com.example.mysubmission41.searching;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysubmission41.ApiConfig;
import com.example.mysubmission41.ApiInterface;
import com.example.mysubmission41.R;
import com.example.mysubmission41.activity.DetailMovieActivity;
import com.example.mysubmission41.adapter.TvShowAdapter;
import com.example.mysubmission41.click.ItemClickSupport;
import com.example.mysubmission41.pojo.SearchTvModel;
import com.example.mysubmission41.pojo.TvShow;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mysubmission41.ApiConfig.API_KEY;
import static com.example.mysubmission41.activity.DetailShowActivity.EXTRA_SHOW;

public class TSearchActivity extends AppCompatActivity {

    public static final String EXTRA_QUERY_TV = "search_tv";
    private static final String TAG = "MSearchActivity";

    RecyclerView recyclerView;
    TvShowAdapter tvShowAdapter;
    ProgressBar pgbar;
    ArrayList<TvShow> tvShowArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tsearch);
        String query = getIntent().getStringExtra(EXTRA_QUERY_TV);
        Log.d(TAG, "search: " + query);
        setActionBarTitle(query);
        recyclerView = findViewById(R.id.rv_search_tv);
        pgbar = findViewById(R.id.pg_tv_search);
        pgbar.setVisibility(View.VISIBLE);
        setup();
        loadUp(query);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView, position, v) -> {
            Intent intent = new Intent(this, DetailMovieActivity.class);
            intent.putExtra(EXTRA_SHOW, tvShowArrayList.get(position));
            startActivity(intent);
        });
    }

    private void loadUp(String query) {
        final ApiInterface apiInterface = ApiConfig.getClient().create(ApiInterface.class);
        Call<SearchTvModel> call = apiInterface.getSearchTv(API_KEY, query);
        call.enqueue(new Callback<SearchTvModel>() {
            @Override
            public void onResponse(Call<SearchTvModel> call, Response<SearchTvModel> response) {
                tvShowArrayList.addAll(response.body().getTvShow());
                tvShowAdapter.notifyDataSetChanged();
                pgbar.setVisibility(View.GONE);
                Toast.makeText(TSearchActivity.this, query+" Loaded", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<SearchTvModel> call, Throwable t) {
                Log.e(TAG, "Error in :", t);
                Toast.makeText(TSearchActivity.this, "Load Failed, Check your connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setup() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        tvShowAdapter = new TvShowAdapter(this, tvShowArrayList);
        recyclerView.setAdapter(tvShowAdapter);
    }

    private void setActionBarTitle(String query) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(query);
    }
}
