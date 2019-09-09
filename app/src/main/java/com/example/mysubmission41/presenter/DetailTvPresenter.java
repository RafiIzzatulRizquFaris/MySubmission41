package com.example.mysubmission41.presenter;

import com.example.mysubmission41.contract.DetailTvContract;
import com.example.mysubmission41.pojo.TvDetailItem;
import com.example.mysubmission41.viewmodel.TvDetailModel;

public class DetailTvPresenter implements DetailTvContract.Presenter, DetailTvContract.Model.OnFinishedListener {

    private DetailTvContract.View detailTvView;
    private DetailTvContract.Model detailTvModel;

    public DetailTvPresenter(DetailTvContract.View detailTvView) {
        this.detailTvView = detailTvView;
        this.detailTvModel = new TvDetailModel();
    }

    @Override
    public void onFinished(TvDetailItem tvDetailItem) {
        if (detailTvView != null) {
            detailTvView.hideProgress();
        }
        detailTvView.setDataToView(tvDetailItem);
    }

    @Override
    public void onFailure(Throwable throwable) {
        if (detailTvView != null) {
            detailTvView.hideProgress();
        }
    }

    @Override
    public void onDestroy() {
        this.detailTvView = null;
    }

    @Override
    public void requestTvData(int tvId) {
        if (detailTvView != null) {
            detailTvView.showProgress();
        }
        detailTvModel.getDetail(this, tvId);
    }
}

