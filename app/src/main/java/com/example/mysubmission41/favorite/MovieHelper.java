package com.example.mysubmission41.favorite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static android.provider.BaseColumns._ID;
import static com.example.mysubmission41.favorite.DatabaseContract.MovieColumns.MOVIE_ID;
import static com.example.mysubmission41.favorite.DatabaseContract.MovieColumns.TABEL_MOVIE;

public class MovieHelper {
    private static String DATABASE_TABLE = TABEL_MOVIE;

    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public MovieHelper(Context context) {
        this.context = context;
    }

    public MovieHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
        if (database.isOpen()) databaseHelper.close();
    }

    public Cursor queryProvider() {
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " DESC"
        );
    }

    public Cursor queryByIdProvider(String id) {
        return database.query(
                DATABASE_TABLE,
                null,
                MOVIE_ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null
        );
    }

    public long insertProvider(ContentValues values) {
        return database.insert(
                DATABASE_TABLE,
                null,
                values
        );
    }

    public int updateProvider(String id, ContentValues values) {
        return database.update(
                DATABASE_TABLE,
                values,
                MOVIE_ID + " = ?",
                new String[]{id}
        );
    }

    public int deleteProvider(String id) {
        return database.delete(
                DATABASE_TABLE,
                MOVIE_ID + " = ?",
                new String[]{id}
        );
    }
}
