package com.example.mysubmission41;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysubmission41.adapter.TvShowAdapter;
import com.example.mysubmission41.pojo.TvShow;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mysubmission41.ApiConfig.API_KEY;

public class TSearchActivity extends AppCompatActivity {

    public static final String EXTRA_QUERY_TV = "search_tv";
    private static final String TAG = "MSearchActivity";

    RecyclerView recyclerView;
    TvShowAdapter tvShowAdapter;
    ArrayList<TvShow> tvShowArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tsearch);
        String query = getIntent().getStringExtra(EXTRA_QUERY_TV);
        Log.d(TAG, "search: " + query);
        setActionBarTitle(query);
        recyclerView = findViewById(R.id.rv_search_tv);
        setup();
        loadUp(query);
    }

    private void loadUp(String query) {
        final ApiInterface apiInterface = ApiConfig.getClient().create(ApiInterface.class);
        Call<SearchTvModel> call = apiInterface.getSearchTv(API_KEY, query);
        call.enqueue(new Callback<SearchTvModel>() {
            @Override
            public void onResponse(Call<SearchTvModel> call, Response<SearchTvModel> response) {
                tvShowArrayList.addAll(response.body().getTvShow());
                tvShowAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SearchTvModel> call, Throwable t) {
                Log.e(TAG, "Error in :", t);
                Toast.makeText(TSearchActivity.this, "Load Failed", Toast.LENGTH_LONG).show();
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
