package com.example.mysubmission41.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchTvModel {
    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<TvShow> getTvShow() {
        return tvShow;
    }

    public void setTvShow(List<TvShow> tvShow) {
        this.tvShow = tvShow;
    }

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("results")
    private List<TvShow> tvShow;
}
