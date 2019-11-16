package com.example.mysubmission41.services;

import com.example.mysubmission41.response.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {
    @GET("movie/popular")
    Call<MovieResponse> getMovie(
            @Query("api_key") String apiKey
    );
}
