package com.example.mysubmission41.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mysubmission41.ApiConfig;
import com.example.mysubmission41.services.TvApiService;
import com.example.mysubmission41.pojo.TvShow;
import com.example.mysubmission41.response.TvShowResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvShowViewModel extends ViewModel {
    private MutableLiveData<List<TvShow>> listMutableLiveData;

    public LiveData<List<TvShow>> getTvShow(){
        if (listMutableLiveData == null){
            listMutableLiveData = new MutableLiveData<>();
            loadTvShow();
        }
        return listMutableLiveData;
    }

    private void loadTvShow(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConfig.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TvApiService tvApiService = retrofit.create(TvApiService.class);

        Call<TvShowResponse> call = tvApiService.getTvShow(ApiConfig.API_KEY);
        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                listMutableLiveData.setValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                Log.e("Kesalahan Tv Show", t.getMessage());
            }
        });
    }
}
