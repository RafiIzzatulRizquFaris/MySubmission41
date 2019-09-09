package com.example.mysubmission41.contract;

import com.example.mysubmission41.pojo.MovieDetailItem;

public interface DetailMovieContract {

    interface Model {
        interface OnFinishedListener {
            void onFinished(MovieDetailItem movieDetailItem);

            void onFailure(Throwable throwable);
        }

        void getDetail(OnFinishedListener onFinishedListener, int movieId);
    }

    interface View {

        void showProgress();

        void hideProgress();

        void setDataToView(MovieDetailItem movieDetailItem);

        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {

        void onDestroy();

        void requestMovieData(int movieId);
    }


}

