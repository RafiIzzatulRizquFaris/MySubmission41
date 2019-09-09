package com.example.mysubmission41.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvDetailItem {


    private int id;

    private String name;

    @SerializedName("original_name")
    private String originalName;

    @SerializedName("overview")
    private String overview;

    @SerializedName("vote_average")
    private float rating;

    @SerializedName("poster_path")
    private String thumbPath;

    @SerializedName("number_of_episodes")
    private int numberOfEpisode;

    @SerializedName("number_of_seasons")
    private int seasons;

    @SerializedName("last_air_date")
    private String lastAirDate;

    @SerializedName("genres")
    private List<Genres> genres;

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }



    @SerializedName("first_air_date")
    private String firstAirDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    public int getNumberOfEpisode() {
        return numberOfEpisode;
    }

    public void setNumberOfEpisode(int numberOfEpisode) {
        this.numberOfEpisode = numberOfEpisode;
    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public TvDetailItem(int id, String name, String originalName, String overview, float rating, String thumbPath, int numberOfEpisode, int seasons, String lastAirDate, String firstAirDate, List<Genres> genres) {
        this.id = id;
        this.name = name;
        this.originalName = originalName;
        this.overview = overview;
        this.rating = rating;
        this.thumbPath = thumbPath;
        this.numberOfEpisode = numberOfEpisode;
        this.seasons = seasons;
        this.lastAirDate = lastAirDate;
        this.firstAirDate = firstAirDate;
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + name + '\'' +
                ", originalName='" + originalName + '\'' +
                ", rating=" + rating +
                ", thumbPath='" + thumbPath + '\'' +
                ", overview='" + overview + '\'' +
                ", numberOfEpisode='" + numberOfEpisode + '\'' +
                ", seasons='" + seasons + '\'' +
                ", lastAirDate='" + lastAirDate + '\'' +
                ", firstAirDate='" + firstAirDate + '\'' +
                '}';
    }
}

