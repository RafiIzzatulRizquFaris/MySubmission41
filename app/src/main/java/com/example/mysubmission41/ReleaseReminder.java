package com.example.mysubmission41;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.mysubmission41.pojo.MovieDetailItem;
import com.example.mysubmission41.response.MovieListResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mysubmission41.ApiConfig.API_KEY;

public class ReleaseReminder extends BroadcastReceiver {

    public static final int pageNo = 1;
    private final String TAG = "Release Reminder: ";
    public static final int NOTIFICATION_ID = 11;
    public static String CHANNEL_ID = "channel_02";
    public static CharSequence CHANNEL_NAME = "movie channel";


    @Override
    public void onReceive(Context context, Intent intent) {
        getMovies(context);
    }

    private void getMovies(Context context) {
        final ApiInterface apiInterface = ApiConfig.getClient().create(ApiInterface.class);
        Call<MovieListResponse> call = apiInterface.getMovieDiscover(API_KEY, pageNo);
        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                List<MovieDetailItem> movieItems = response.body().getResults();

                String title = movieItems.get(0).getTitle();
                String getMessage = context.getResources().getString(R.string.message_release_today);
                String message = title + " " + getMessage;
                String releaseDate = movieItems.get(0).getReleaseDate();
                int id  = movieItems.get(0).getId();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date date = new Date();
                final String getTime = dateFormat.format(date);
                Log.d(TAG, "TIME: " + releaseDate);

                if (releaseDate.equals(getTime)) {
                    notifRelease(context, title, message, id);
                }

            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {

            }
        });
    }

    private void notifRelease(Context context, String title, String message, int id) {
    }
}
