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
import com.example.mysubmission41.contract.DetailTvContract;
import com.example.mysubmission41.presenter.DetailTvPresenter;
import com.example.mysubmission41.R;
import com.example.mysubmission41.favorite.TvHelper;
import com.example.mysubmission41.pojo.TvDetailItem;
import com.example.mysubmission41.pojo.TvShow;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.mysubmission41.favorite.DatabaseContract.CONTENT_URI_TV;
import static com.example.mysubmission41.favorite.DatabaseContract.TvShowColumns.TV_ID;
import static com.example.mysubmission41.favorite.DatabaseContract.TvShowColumns.TV_JUDUL;
import static com.example.mysubmission41.favorite.DatabaseContract.TvShowColumns.TV_OVERVIEW;
import static com.example.mysubmission41.favorite.DatabaseContract.TvShowColumns.TV_POSTER;
import static com.example.mysubmission41.favorite.DatabaseContract.TvShowColumns.TV_RELEASE;
import static com.example.mysubmission41.favorite.DatabaseContract.TvShowColumns.TV_VOTE;

public class DetailShowActivity extends AppCompatActivity implements DetailTvContract.View {

    public static final String EXTRA_SHOW = "extra_show";
    private TvShow tvShow;
    ImageView postershow;
    CollapsingToolbarLayout collapsingshow;
    TextView showOverview, showVote, showDate, showPop;
    FloatingActionButton btnBackDropShow;
    ProgressBar pgDetail;
    Button btnFavorite;
    private Boolean isFavorite = false;
    private TvHelper tvHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_show);

        tvShow = getIntent().getParcelableExtra(EXTRA_SHOW);


        collapsingshow = findViewById(R.id.collapsing_show);
        postershow = findViewById(R.id.show_poster);
        showVote = findViewById(R.id.show_vote);
        showPop = findViewById(R.id.show_pop);
        showDate = findViewById(R.id.show_date);
        showOverview = findViewById(R.id.show_overview);
        pgDetail = findViewById(R.id.pg_tv_detail);
        btnBackDropShow = findViewById(R.id.btn_backDropShow);


        DetailTvPresenter detailTvPresenter = new DetailTvPresenter(this);
        detailTvPresenter.requestTvData(tvShow.getId());

        btnFavorite = findViewById(R.id.btn_tvshow_fav);
        btnFavorite.setOnClickListener(view -> {
            if (isFavorite) FavoriteRemove();
            else FavoriteSave();

            isFavorite = !isFavorite;
            setFavorite();
        });

    }

    private void FavoriteSave() {
        String TAG = "detailTvShow";

        tvHelper = new TvHelper(this);
        tvHelper.open();

        Log.d(TAG, " Detail Movie: " + tvShow.getId());

        ContentValues contentValues = new ContentValues();
        contentValues.put(TV_ID, tvShow.getId());
        contentValues.put(TV_JUDUL, tvShow.getName());
        contentValues.put(TV_OVERVIEW, tvShow.getOverview());
        contentValues.put(TV_RELEASE, tvShow.getFirstAirDate());
        contentValues.put(TV_POSTER, tvShow.getPosterPath());
        contentValues.put(TV_VOTE, tvShow.getVoteAverage());

        getContentResolver().insert(CONTENT_URI_TV, contentValues);
        Log.d(TAG, "Content Values: " + contentValues);
        Toast.makeText(this, "Save Success", Toast.LENGTH_SHORT).show();
    }

    private void FavoriteRemove() {
        getContentResolver().delete(
                Uri.parse(CONTENT_URI_TV + "/" + tvShow.getId()),
                null,
                null
        );
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
    }

    private void setFavorite() {
        if (isFavorite) btnFavorite.setText(R.string.favorited);
        else btnFavorite.setText(R.string.favourite);
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
    public void setDataToView(TvDetailItem tvDetailItem) {
        loadDataSQL();
        int size = 0;
        String genres = "";
        size = tvDetailItem.getGenres().size();
        for (int i = 0; i < size; i++) {
            genres += tvDetailItem.getGenres().get(i).getName() + (i + 1 < size ? ", " : "");
        }

        collapsingshow.setTitle(tvShow.getName());
        collapsingshow.setCollapsedTitleTextColor(getResources().getColor(R.color.colorWhite));
        collapsingshow.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));

        String posterPathShow = tvShow.getPosterPath();
        String posterShowFix = ApiConfig.IMAGE_URL+posterPathShow;
        Glide.with(this).load(posterShowFix).into(postershow);

        showVote.setText(String.valueOf(tvShow.getVoteAverage()));
        showPop.setText(String.valueOf(tvShow.getPopularity()));
        showDate.setText(tvShow.getFirstAirDate());
        showOverview.setText(tvShow.getOverview());

        String backDropPathShow = tvShow.getBackdropPath();
        String backDPFixShow = ApiConfig.IMAGE_URL+backDropPathShow;
        btnBackDropShow.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(backDPFixShow));
            startActivity(intent);
        });
    }

    private void loadDataSQL() {
        tvHelper = new TvHelper(this);
        tvHelper.open();

        Cursor cursor = getContentResolver().query(
                Uri.parse(CONTENT_URI_TV + "/"),
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()){
            Log.e("Tv Show", cursor.getString(2));
        }

        if (cursor != null) {
            if (cursor.moveToFirst()) isFavorite = true;

            cursor.close();
        }
        setFavorite();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e("DETAIL TV ACTIVITY :", String.valueOf(throwable));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String TAG = "Details: ";
        Log.d(TAG, "Movie Helper: " + tvHelper);
        if (tvHelper != null) {
            tvHelper.close();
        }
    }
}
