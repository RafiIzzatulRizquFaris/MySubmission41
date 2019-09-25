package com.example.favoriteapp.pojo;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import static com.example.favoriteapp.DatabaseContract.MovieColumns.JUDUL;
import static com.example.favoriteapp.DatabaseContract.MovieColumns.MOVIE_ID;
import static com.example.favoriteapp.DatabaseContract.MovieColumns.OVERVIEW;
import static com.example.favoriteapp.DatabaseContract.MovieColumns.POSTER;
import static com.example.favoriteapp.DatabaseContract.MovieColumns.RELEASE;
import static com.example.favoriteapp.DatabaseContract.MovieColumns.VOTE;
import static com.example.favoriteapp.DatabaseContract.getColumnDouble;
import static com.example.favoriteapp.DatabaseContract.getColumnInt;
import static com.example.favoriteapp.DatabaseContract.getColumnString;

public class Movie implements Parcelable {
    @SerializedName("vote_count")

    private Integer voteCount;
    @SerializedName("id")

    private Integer id;
    @SerializedName("video")

    private Boolean video;
    @SerializedName("vote_average")

    private Double voteAverage;
    @SerializedName("title")

    private String title;
    @SerializedName("popularity")

    private Double popularity;
    @SerializedName("poster_path")

    private String posterPath;
    @SerializedName("original_language")

    private String originalLanguage;
    @SerializedName("original_title")

    private String originalTitle;
    @SerializedName("genre_ids")

    private List<Integer> genreIds = null;
    @SerializedName("backdrop_path")

    private String backdropPath;
    @SerializedName("adult")

    private Boolean adult;
    @SerializedName("overview")

    private String overview;
    @SerializedName("release_date")

    private String releaseDate;

    public Movie(String title) {
        this.title = title;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.voteCount);
        dest.writeValue(this.id);
        dest.writeValue(this.video);
        dest.writeValue(this.voteAverage);
        dest.writeString(this.title);
        dest.writeValue(this.popularity);
        dest.writeString(this.posterPath);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.originalTitle);
        dest.writeList(this.genreIds);
        dest.writeString(this.backdropPath);
        dest.writeValue(this.adult);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
    }

    public Movie(Cursor cursor){
        this.id = getColumnInt(cursor, MOVIE_ID);
        this.title = getColumnString(cursor, JUDUL);
        this.overview = getColumnString(cursor, OVERVIEW);
        this.voteAverage = getColumnDouble(cursor, VOTE);
        this.posterPath = getColumnString(cursor, POSTER);
        this.releaseDate = getColumnString(cursor, RELEASE);
    }

    protected Movie(Parcel in) {
        this.voteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.video = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.title = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.posterPath = in.readString();
        this.originalLanguage = in.readString();
        this.originalTitle = in.readString();
        this.genreIds = new ArrayList<>();
        in.readList(this.genreIds, Integer.class.getClassLoader());
        this.backdropPath = in.readString();
        this.adult = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.overview = in.readString();
        this.releaseDate = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
