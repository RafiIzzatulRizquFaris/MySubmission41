package com.example.mysubmission41.viewmodel;

import android.util.Log;

import com.example.mysubmission41.ApiConfig;
import com.example.mysubmission41.ApiInterface;
import com.example.mysubmission41.contract.DetailMovieContract;
import com.example.mysubmission41.pojo.MovieDetailItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mysubmission41.ApiConfig.API_KEY;

public class MovieDetailModel implements DetailMovieContract.Model {

    @Override
    public void getDetail(final OnFinishedListener onFinishedListener, int movieId) {

        final ApiInterface apiService = ApiConfig.getClient().create(ApiInterface.class);

        Call<MovieDetailItem> call = apiService.getMovieDetails(movieId, API_KEY);
        String TAG = "Detail Model";
        Log.d(TAG, "link: " + call);
        call.enqueue(new Callback<MovieDetailItem>() {
            @Override
            public void onResponse(Call<MovieDetailItem> call, Response<MovieDetailItem> response) {
                MovieDetailItem movieDetailItem = response.body();
                onFinishedListener.onFinished(movieDetailItem);
            }

            @Override
            public void onFailure(Call<MovieDetailItem> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}

