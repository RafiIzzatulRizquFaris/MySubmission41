package com.example.mysubmission41;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.mysubmission41.DatabaseContract.MovieColumns.JUDUL;
import static com.example.mysubmission41.DatabaseContract.MovieColumns.MOVIE_ID;
import static com.example.mysubmission41.DatabaseContract.MovieColumns.OVERVIEW;
import static com.example.mysubmission41.DatabaseContract.MovieColumns.POSTER;
import static com.example.mysubmission41.DatabaseContract.MovieColumns.RELEASE;
import static com.example.mysubmission41.DatabaseContract.MovieColumns.VOTE;
import static com.example.mysubmission41.DatabaseContract.MovieColumns.TABEL_MOVIE;
import static com.example.mysubmission41.DatabaseContract.TvShowColumns.TABEL_TV;
import static com.example.mysubmission41.DatabaseContract.TvShowColumns.TV_ID;
import static com.example.mysubmission41.DatabaseContract.TvShowColumns.TV_JUDUL;
import static com.example.mysubmission41.DatabaseContract.TvShowColumns.TV_OVERVIEW;
import static com.example.mysubmission41.DatabaseContract.TvShowColumns.TV_POSTER;
import static com.example.mysubmission41.DatabaseContract.TvShowColumns.TV_RELEASE;
import static com.example.mysubmission41.DatabaseContract.TvShowColumns.TV_VOTE;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "database";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABEL_MOVIE = String.format("CREATE TABLE %s"+
                    "(%s INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "%s TEXT NOT NULL,"+
                    "%s TEXT NOT NULL,"+
                    "%s TEXT NOT NULL,"+
                    "%s TEXT NOT NULL,"+
                    "%s TEXT NOT NULL,"+
                    "%s TEXT NOT NULL)",
            TABEL_MOVIE,
            _ID,
            MOVIE_ID,
            JUDUL,
            OVERVIEW,
            RELEASE,
            VOTE,
            POSTER
    );

    private static final String SQL_CREATE_TABEL_TV_SHOW = String.format("CREATE TABLE %s"+
                    "(%s INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "%s TEXT NOT NULL,"+
                    "%s TEXT NOT NULL,"+
                    "%s TEXT NOT NULL,"+
                    "%s TEXT NOT NULL,"+
                    "%s TEXT NOT NULL,"+
                    "%s TEXT NOT NULL)",
            TABEL_TV,
            _ID,
            TV_ID,
            TV_JUDUL,
            TV_OVERVIEW,
            TV_RELEASE,
            TV_VOTE,
            TV_POSTER
    );

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABEL_MOVIE);
        db.execSQL(SQL_CREATE_TABEL_TV_SHOW);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABEL_MOVIE);
        db.execSQL("DROP TABLE IF EXISTS "+ TABEL_TV);
        onCreate(db);
    }

    DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}

