package com.example.mysubmission41;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {
    public final static String API_KEY = "ac80477002d21dc4400901f64c0506a7";
    public final static String URL = "https://api.themoviedb.org/3/";
    public final static String IMAGE_URL = "https://image.tmdb.org/t/p/w500/";
    private static Retrofit retrofit = null;


    static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
