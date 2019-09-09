package com.example.mysubmission41.viewmodel;

import com.example.mysubmission41.ApiConfig;
import com.example.mysubmission41.ApiInterface;
import com.example.mysubmission41.contract.DetailTvContract;
import com.example.mysubmission41.pojo.TvDetailItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mysubmission41.ApiConfig.API_KEY;

public class TvDetailModel implements DetailTvContract.Model {

    @Override
    public void getDetail(final OnFinishedListener onFinishedListener, int tvId) {

        final ApiInterface apiService = ApiConfig.getClient().create(ApiInterface.class);

        Call<TvDetailItem> call = apiService.getTVDetails(tvId, API_KEY);
        call.enqueue(new Callback<TvDetailItem>() {
            @Override
            public void onResponse(Call<TvDetailItem> call, Response<TvDetailItem> response) {
                TvDetailItem tvDetailtem = response.body();
                onFinishedListener.onFinished(tvDetailtem);
            }

            @Override
            public void onFailure(Call<TvDetailItem> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}

