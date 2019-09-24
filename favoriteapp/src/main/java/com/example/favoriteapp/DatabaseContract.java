package com.example.favoriteapp;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static final class MovieColumns implements BaseColumns {
        public static String TABEL_MOVIE = "tabelMovie";
        public static String MOVIE_ID = "movie_id";
        public static String JUDUL = "title";
        public static String OVERVIEW = "overview";
        public static String POSTER = "poster_path";
        public static String RELEASE = "release_date";
        public static String VOTE = "vote_average";

    }

    public static final class TvShowColumns implements BaseColumns {
        public static String TABEL_TV = "tabelTvshow";
        public static String TV_ID = "id";
        public static String TV_JUDUL = "name";
        public static String TV_OVERVIEW = "overview";
        public static String TV_POSTER = "poster_path";
        public static String TV_RELEASE = "release_date";
        public static String TV_VOTE = "vote_average";

    }

    public static final String AUTHORITY = "com.example.mysubmission41";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(MovieColumns.TABEL_MOVIE)
            .build();

    public static final Uri CONTENT_URI_TV = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TvShowColumns.TABEL_TV)
            .build();
    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static Double getColumnDouble(Cursor cursor, String columnName){
        return cursor.getDouble(cursor.getColumnIndex(columnName));
    }
}
