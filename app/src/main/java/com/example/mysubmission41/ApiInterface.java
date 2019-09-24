package com.example.mysubmission41;

import com.example.mysubmission41.pojo.MovieDetailItem;
import com.example.mysubmission41.pojo.SearchMovieModel;
import com.example.mysubmission41.pojo.SearchTvModel;
import com.example.mysubmission41.pojo.TvDetailItem;
import com.example.mysubmission41.response.MovieListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("discover/movie")
    Call<MovieListResponse> getMovieDiscover(@Query("api_key") String apiKey, @Query("page") int PageNo);

//    @GET("discover/tv")
//    Call<TvListResponse> getTvDiscover(@Query("api_key") String apiKey, @Query("page") int PageNo);

    @GET("movie/{movie_id}")
    Call<MovieDetailItem> getMovieDetails(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("tv/{tv_id}")
    Call<TvDetailItem> getTVDetails(@Path("tv_id") int tvId, @Query("api_key") String apiKey);

    @GET("search/movie")
    Call<SearchMovieModel> getSearchMovie(@Query("api_key") String apiKey, @Query("query") String query);

    @GET("search/tv")
    Call<SearchTvModel> getSearchTv(@Query("api_key") String apiKey, @Query("query") String query);
}

