package com.example.mysubmission41.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mysubmission41.ApiConfig;
import com.example.mysubmission41.R;
import com.example.mysubmission41.pojo.Movie;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.mysubmission41.DatabaseContract.CONTENT_URI;
import static com.example.mysubmission41.DatabaseContract.MovieColumns.JUDUL;
import static com.example.mysubmission41.DatabaseContract.MovieColumns.MOVIE_ID;
import static com.example.mysubmission41.DatabaseContract.MovieColumns.OVERVIEW;
import static com.example.mysubmission41.DatabaseContract.MovieColumns.POSTER;
import static com.example.mysubmission41.DatabaseContract.MovieColumns.RELEASE;
import static com.example.mysubmission41.DatabaseContract.MovieColumns.VOTE;

public class DetailMovieActivity extends AppCompatActivity{

    public static final String EXTRA_MOVIE = "extra_movie";
    Movie movie;
    ImageView posterMovie;
    CollapsingToolbarLayout collapsingMovie;
    TextView movieOverview, movieVote, movieDate, moviePop;
    FloatingActionButton btnBackDrop;
    Button btnFavoriteMovie;
    private Boolean isFavorite = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie_acitivity);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        collapsingMovie = findViewById(R.id.collapsing_movie);
        collapsingMovie.setCollapsedTitleTextColor(getResources().getColor(R.color.colorWhite));
        collapsingMovie.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));
        collapsingMovie.setTitle(movie.getTitle());

        posterMovie = findViewById(R.id.movie_poster);
        String posterPath = movie.getPosterPath();
        String posterFix = ApiConfig.IMAGE_URL+posterPath;
        Glide.with(this).load(posterFix).into(posterMovie);

        movieVote = findViewById(R.id.movie_vote);
        movieVote.setText(String.valueOf(movie.getVoteAverage()));

        moviePop = findViewById(R.id.movie_pop);
        moviePop.setText(String.valueOf(movie.getPopularity()));

        movieDate = findViewById(R.id.movie_date);
        movieDate.setText(movie.getReleaseDate());

        movieOverview = findViewById(R.id.movie_overview);
        movieOverview.setText(movie.getOverview());

        String backDropPath = movie.getBackdropPath();
        String backDPFix = ApiConfig.IMAGE_URL+backDropPath;
        btnBackDrop = findViewById(R.id.btn_backDrop);
        btnBackDrop.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(backDPFix));
            startActivity(intent);
        });

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


    private void FavoriteSave() {

        String TAG = "detailMovie";

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

}
