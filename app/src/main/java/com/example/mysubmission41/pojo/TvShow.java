package com.example.mysubmission41.pojo;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import static com.example.mysubmission41.favorite.DatabaseContract.TvShowColumns.TV_ID;
import static com.example.mysubmission41.favorite.DatabaseContract.TvShowColumns.TV_JUDUL;
import static com.example.mysubmission41.favorite.DatabaseContract.TvShowColumns.TV_OVERVIEW;
import static com.example.mysubmission41.favorite.DatabaseContract.TvShowColumns.TV_POSTER;
import static com.example.mysubmission41.favorite.DatabaseContract.TvShowColumns.TV_RELEASE;
import static com.example.mysubmission41.favorite.DatabaseContract.TvShowColumns.TV_VOTE;
import static com.example.mysubmission41.favorite.DatabaseContract.getColumnDouble;
import static com.example.mysubmission41.favorite.DatabaseContract.getColumnInt;
import static com.example.mysubmission41.favorite.DatabaseContract.getColumnString;

public class TvShow implements Parcelable {
    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("overview")
    private String overview;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("origin_country")
    private List<String> originCountry;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("original_name")
    private String originalName;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("vote_count")
    private int voteCount;

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public TvShow(String firstAirDate, String overview, String originalLanguage, List<Integer> genreIds, String posterPath, List<String> originCountry, String backdropPath, String originalName, double popularity, double voteAverage, String name, int id, int voteCount) {
        this.firstAirDate = firstAirDate;
        this.overview = overview;
        this.originalLanguage = originalLanguage;
        this.genreIds = genreIds;
        this.posterPath = posterPath;
        this.originCountry = originCountry;
        this.backdropPath = backdropPath;
        this.originalName = originalName;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
        this.name = name;
        this.id = id;
        this.voteCount = voteCount;
    }

    public TvShow (Cursor cursor){
        this.id = getColumnInt(cursor, TV_ID);
        this.name = getColumnString(cursor, TV_JUDUL);
        this.overview = getColumnString(cursor, TV_OVERVIEW);
        this.voteAverage = getColumnDouble(cursor, TV_VOTE);
        this.posterPath = getColumnString(cursor, TV_POSTER);
        this.firstAirDate = getColumnString(cursor, TV_RELEASE);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstAirDate);
        dest.writeString(this.overview);
        dest.writeString(this.originalLanguage);
        dest.writeList(this.genreIds);
        dest.writeString(this.posterPath);
        dest.writeStringList(this.originCountry);
        dest.writeString(this.backdropPath);
        dest.writeString(this.originalName);
        dest.writeDouble(this.popularity);
        dest.writeDouble(this.voteAverage);
        dest.writeString(this.name);
        dest.writeInt(this.id);
        dest.writeInt(this.voteCount);
    }

    protected TvShow(Parcel in) {
        this.firstAirDate = in.readString();
        this.overview = in.readString();
        this.originalLanguage = in.readString();
        this.genreIds = new ArrayList<>();
        in.readList(this.genreIds, Integer.class.getClassLoader());
        this.posterPath = in.readString();
        this.originCountry = in.createStringArrayList();
        this.backdropPath = in.readString();
        this.originalName = in.readString();
        this.popularity = in.readDouble();
        this.voteAverage = in.readDouble();
        this.name = in.readString();
        this.id = in.readInt();
        this.voteCount = in.readInt();
    }

    public static final Parcelable.Creator<TvShow> CREATOR = new Parcelable.Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel source) {
            return new TvShow(source);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };
}

