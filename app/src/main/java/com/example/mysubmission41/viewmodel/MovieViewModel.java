package com.example.mysubmission41.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mysubmission41.ApiConfig;
import com.example.mysubmission41.services.MovieApiService;
import com.example.mysubmission41.pojo.Movie;
import com.example.mysubmission41.response.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<List<Movie>> listMutableLiveDataMovie;

    public LiveData<List<Movie>> getMovie(){
        if (listMutableLiveDataMovie == null){
            listMutableLiveDataMovie = new MutableLiveData<>();
            loadMovie();
        }
        return listMutableLiveDataMovie;
    }

    private void loadMovie() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConfig.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieApiService movieApiService = retrofit.create(MovieApiService.class);

        Call<MovieResponse> call = movieApiService.getMovie(ApiConfig.API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                listMutableLiveDataMovie.setValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e("Kesalahan Movie", t.getMessage());
            }
        });
    }
}
