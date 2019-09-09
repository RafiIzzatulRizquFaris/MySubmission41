package com.example.mysubmission41.contract;

import com.example.mysubmission41.pojo.TvDetailItem;

public interface DetailTvContract {

    interface Model {
        interface OnFinishedListener {
            void onFinished(TvDetailItem tvDetailItem);

            void onFailure(Throwable throwable);
        }

        void getDetail(OnFinishedListener onFinishedListener, int tvId);
    }

    interface View {

        void showProgress();

        void hideProgress();

        void setDataToView(TvDetailItem tvDetailItem);

        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {

        void onDestroy();

        void requestTvData(int tvId);
    }
}

