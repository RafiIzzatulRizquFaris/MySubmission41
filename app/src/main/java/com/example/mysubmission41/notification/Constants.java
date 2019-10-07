package com.example.mysubmission41.notification;

import android.content.Context;

import static com.example.mysubmission41.ApiConfig.API_KEY;

public class Constants {
    public static final String URL_TYPE_NEW_RELEASE = "release";
    public static final String URL_MOVIES = "movie";


    public static String getUrl (String type, String category, String keyword, Context context){
        switch (type){
            case URL_TYPE_NEW_RELEASE:
                return "https://api.themoviedb.org/3/discover/movie"
                        + "?api_key=" + API_KEY
                        + "&primary_release_date.gte=" + keyword
                        + "&primary_release_date.lte=" + keyword;
            default:
                return "https://api.themoviedb.org/3/discover/movie"
                        + "?api_key=" + API_KEY
                        + "&primary_release_date.gte=" + keyword
                        + "&primary_release_date.lte=" + keyword;
        }
    }
}
