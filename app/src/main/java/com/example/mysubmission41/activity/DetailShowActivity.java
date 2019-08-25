package com.example.mysubmission41.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mysubmission41.ApiConfig;
import com.example.mysubmission41.R;
import com.example.mysubmission41.pojo.TvShow;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailShowActivity extends AppCompatActivity {

    public static final String EXTRA_SHOW = "extra_show";
    TvShow tvShow;
    ImageView postershow;
    CollapsingToolbarLayout collapsingshow;
    TextView showOverview, showVote, showDate, showPop;
    FloatingActionButton btnBackDropShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_show);

        tvShow = getIntent().getParcelableExtra(EXTRA_SHOW);

        collapsingshow = findViewById(R.id.collapsing_show);
        collapsingshow.setCollapsedTitleTextColor(getResources().getColor(R.color.colorWhite));
        collapsingshow.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));
        collapsingshow.setTitle(tvShow.getName());

        postershow = findViewById(R.id.show_poster);
        String posterPathShow = tvShow.getPosterPath();
        String posterShowFix = ApiConfig.IMAGE_URL+posterPathShow;
        Glide.with(this).load(posterShowFix).into(postershow);

        showVote = findViewById(R.id.show_vote);
        showVote.setText(String.valueOf(tvShow.getVoteAverage()));

        showPop = findViewById(R.id.show_pop);
        showPop.setText(String.valueOf(tvShow.getPopularity()));

        showDate = findViewById(R.id.show_date);
        showDate.setText(tvShow.getFirstAirDate());

        showOverview = findViewById(R.id.show_overview);
        showOverview.setText(tvShow.getOverview());

        String backDropPathShow = tvShow.getBackdropPath();
        String backDPFixShow = ApiConfig.IMAGE_URL+backDropPathShow;
        btnBackDropShow = findViewById(R.id.btn_backDropShow);
        btnBackDropShow.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(backDPFixShow));
            startActivity(intent);
        });

    }
}
