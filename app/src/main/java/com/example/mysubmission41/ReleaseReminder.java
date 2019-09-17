package com.example.mysubmission41;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.mysubmission41.pojo.MovieDetailItem;
import com.example.mysubmission41.response.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mysubmission41.ApiConfig.API_KEY;

public class ReleaseReminder extends BroadcastReceiver {

    public static final int pageNo = 1;


    @Override
    public void onReceive(Context context, Intent intent) {
        getMovies(context);
    }

    private void getMovies(Context context) {
        final ApiInterface apiInterface = ApiConfig.getClient().create(ApiInterface.class);
        Call<MovieDetailItem> call = apiInterface.getMovieDiscover(API_KEY, pageNo);
        call.enqueue(new Callback<MovieDetailItem>() {
            @Override
            public void onResponse(Call<MovieDetailItem> call, Response<MovieDetailItem> response) {

            }

            @Override
            public void onFailure(Call<MovieDetailItem> call, Throwable t) {

            }
        });
    }
}
