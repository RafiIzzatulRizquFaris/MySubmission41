package com.example.mysubmission41.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetailItem implements Parcelable {

    private int id;

    private String title;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private float rating;

    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("overview")
    private String overview;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("runtime")
    private String runTime;

    @SerializedName("tagline")
    private String tagline;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("genres")
    private List<Genres> genres;

    protected MovieDetailItem(Parcel in) {
        id = in.readInt();
        title = in.readString();
        releaseDate = in.readString();
        rating = in.readFloat();
        poster_path = in.readString();
        overview = in.readString();
        backdropPath = in.readString();
        runTime = in.readString();
        tagline = in.readString();
        homepage = in.readString();
    }

    public static final Creator<MovieDetailItem> CREATOR = new Creator<MovieDetailItem>() {
        @Override
        public MovieDetailItem createFromParcel(Parcel in) {
            return new MovieDetailItem(in);
        }

        @Override
        public MovieDetailItem[] newArray(int size) {
            return new MovieDetailItem[size];
        }
    };

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public MovieDetailItem(int id, String title, String releaseDate, float rating, String poster_path, String overview, String backdropPath, String runTime, String tagline, String homepage, List<Genres> genres) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.poster_path = poster_path;
        this.overview = overview;
        this.backdropPath = backdropPath;
        this.runTime = runTime;
        this.tagline = tagline;
        this.homepage = homepage;
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", rating=" + rating +
                ", poster_path='" + poster_path + '\'' +
                ", overview='" + overview + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", runTime='" + runTime + '\'' +
                ", tagline='" + tagline + '\'' +
                ", homepage='" + homepage + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(releaseDate);
        dest.writeFloat(rating);
        dest.writeString(poster_path);
        dest.writeString(overview);
        dest.writeString(backdropPath);
        dest.writeString(runTime);
        dest.writeString(tagline);
        dest.writeString(homepage);
    }
}

