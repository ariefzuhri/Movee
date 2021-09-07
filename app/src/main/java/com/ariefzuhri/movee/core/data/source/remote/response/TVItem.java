package com.ariefzuhri.movee.core.data.source.remote.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TVItem {

    @SerializedName("first_air_date")
    private final String firstAirDate;

    @SerializedName("overview")
    private final String overview;

    @SerializedName("genre_ids")
    private final List<Integer> genreIds;

    @SerializedName("poster_path")
    private final String posterPath;

    @SerializedName("backdrop_path")
    private final String backdropPath;

    @SerializedName("popularity")
    private final double popularity;

    @SerializedName("vote_average")
    private final double voteAverage;

    @SerializedName("name")
    private final String name;

    @SerializedName("id")
    private final int id;

    @SerializedName("vote_count")
    private final int voteCount;

    public TVItem(int id, String name, String posterPath, String backdropPath, double voteAverage, int voteCount, double popularity, String firstAirDate, List<Integer> genreIds, String overview) {
        this.name = name;
        this.backdropPath = backdropPath;
        this.genreIds = genreIds;
        this.popularity = popularity;
        this.id = id;
        this.voteCount = voteCount;
        this.overview = overview;
        this.posterPath = posterPath;
        this.firstAirDate = firstAirDate;
        this.voteAverage = voteAverage;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public String getOverview() {
        return overview;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getVoteCount() {
        return voteCount;
    }
}