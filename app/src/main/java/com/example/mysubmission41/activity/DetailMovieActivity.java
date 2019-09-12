package com.example.mysubmission41.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mysubmission41.ApiConfig;
import com.example.mysubmission41.contract.DetailMovieContract;
import com.example.mysubmission41.presenter.DetailMoviePresenter;
import com.example.mysubmission41.favorite.MovieHelper;
import com.example.mysubmission41.R;
import com.example.mysubmission41.pojo.Movie;
import com.example.mysubmission41.pojo.MovieDetailItem;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.mysubmission41.favorite.DatabaseContract.CONTENT_URI;
import static com.example.mysubmission41.favorite.DatabaseContract.MovieColumns.JUDUL;
import static com.example.mysubmission41.favorite.DatabaseContract.MovieColumns.MOVIE_ID;
import static com.example.mysubmission41.favorite.DatabaseContract.MovieColumns.OVERVIEW;
import static com.example.mysubmission41.favorite.DatabaseContract.MovieColumns.POSTER;
import static com.example.mysubmission41.favorite.DatabaseContract.MovieColumns.RELEASE;
import static com.example.mysubmission41.favorite.DatabaseContract.MovieColumns.VOTE;

public class DetailMovieActivity extends AppCompatActivity implements DetailMovieContract.View {

    public static final String EXTRA_MOVIE = "extra_movie";
    Movie movie;
    ImageView posterMovie;
    CollapsingToolbarLayout collapsingMovie;
    TextView movieOverview, movieVote, movieDate, moviePop;
    FloatingActionButton btnBackDrop;
    Button btnFavoriteMovie;
    ProgressBar pgDetail;
    private Boolean isFavorite = false;
    private MovieHelper mMovieHelper;
    private final String TAG = "Details: ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie_acitivity);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        collapsingMovie = findViewById(R.id.collapsing_movie);
        collapsingMovie.setCollapsedTitleTextColor(getResources().getColor(R.color.colorWhite));
        collapsingMovie.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));

        posterMovie = findViewById(R.id.movie_poster);

        movieVote = findViewById(R.id.movie_vote);

        moviePop = findViewById(R.id.movie_pop);

        movieDate = findViewById(R.id.movie_date);

        movieOverview = findViewById(R.id.movie_overview);

        pgDetail = findViewById(R.id.pg_detail);

        btnBackDrop = findViewById(R.id.btn_backDrop);


        DetailMoviePresenter detailPresenter = new DetailMoviePresenter(this);
        detailPresenter.requestMovieData(movie.getId());

        btnFavoriteMovie = findViewById(R.id.btn_movie_fav);
        btnFavoriteMovie.setOnClickListener(view -> {
            if (isFavorite) FavoriteRemove();
            else FavoriteSave();

            isFavorite = !isFavorite;
            setFavorite();
        });
    }

    private void setFavorite() {
        if (isFavorite) btnFavoriteMovie.setText(R.string.favorited);
        else btnFavoriteMovie.setText(R.string.favourite);
    }


    @Override
    public void showProgress() {
        pgDetail.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pgDetail.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToView(MovieDetailItem movieDetailItem) {
        loadDataSQL();

        int size = 0;
        String genres = "";
        size = movieDetailItem.getGenres().size();
        for (int i = 0; i < size; i++) {
            genres += movieDetailItem.getGenres().get(i).getName() + (i + 1 < size ? ", " : "");
        }

        collapsingMovie.setTitle(movie.getTitle());

        String posterPath = movie.getPosterPath();
        String posterFix = ApiConfig.IMAGE_URL+posterPath;
        Glide.with(this).load(posterFix).into(posterMovie);

        movieVote.setText(String.valueOf(movie.getVoteAverage()));
        moviePop.setText(String.valueOf(movie.getPopularity()));
        movieDate.setText(movie.getReleaseDate());
        movieOverview.setText(movie.getOverview());

        String backDropPath = movie.getBackdropPath();
        String backDPFix = ApiConfig.IMAGE_URL+backDropPath;
        btnBackDrop.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(backDPFix));
            startActivity(intent);
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Movie Helper: " + mMovieHelper);
        if (mMovieHelper != null) {
            mMovieHelper.close();
        }
    }


    private void loadDataSQL() {
        mMovieHelper = new MovieHelper(this);
        mMovieHelper.open();

        Cursor cursor2 = getContentResolver().query(
                Uri.parse(CONTENT_URI + "/"),
                null,
                null,
                null,
                null
        );

        while (cursor2.moveToNext()) {
            Log.e("Movie", cursor2.getString(2));
        }

        Cursor cursor = getContentResolver().query(
                Uri.parse(CONTENT_URI + "/" + movie.getId()),
                null,
                null,
                null,
                null
        );

        Log.d(TAG, "Cursor: " + cursor);

        if (cursor != null) {
            if (cursor.moveToFirst()) isFavorite = true;

            cursor.close();
        }
        setFavorite();
    }


    private void FavoriteSave() {

        String TAG = "detailMovie";

        mMovieHelper = new MovieHelper(this);
        mMovieHelper.open();

        Log.d(TAG, " Detail Movie: " + movie.getId());

        ContentValues contentValues = new ContentValues();
        contentValues.put(MOVIE_ID, movie.getId());
        contentValues.put(JUDUL, movie.getTitle());
        contentValues.put(OVERVIEW, movie.getOverview());
        contentValues.put(RELEASE, movie.getReleaseDate());
        contentValues.put(POSTER, movie.getPosterPath());
        contentValues.put(VOTE, movie.getVoteAverage());

        getContentResolver().insert(CONTENT_URI, contentValues);
        Log.d(TAG, "Content Values: " + contentValues);
        Toast.makeText(this, "Save Success", Toast.LENGTH_SHORT).show();
    }

    private void FavoriteRemove() {
        getContentResolver().delete(
                Uri.parse(CONTENT_URI + "/" + movie.getId()),
                null,
                null
        );
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.d("DETAIL MOVIE ACTIVITY :", String.valueOf(throwable));
    }
}
