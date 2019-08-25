package com.example.mysubmission41.services;

import com.example.mysubmission41.response.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TvApiService {
    @GET("tv/popular")
    Call<TvShowResponse> getTvShow(@Query("api_key") String apiKey);
}
