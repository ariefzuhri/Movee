package com.ariefzuhri.blu.data.source.remote.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MovieItem {

	@SerializedName("overview")
	private final String overview;

	@SerializedName("title")
	private final String title;

	@SerializedName("genre_ids")
	private final List<Integer> genreIds;

	@SerializedName("poster_path")
	private final String posterPath;

	@SerializedName("backdrop_path")
	private final String backdropPath;

	@SerializedName("release_date")
	private final String releaseDate;

	@SerializedName("popularity")
	private final double popularity;

	@SerializedName("vote_average")
	private final double voteAverage;

	@SerializedName("id")
	private final int id;

	@SerializedName("vote_count")
	private final int voteCount;

	public MovieItem(int id, String title, String posterPath, String backdropPath, double voteAverage, int voteCount, double popularity, String releaseDate, List<Integer> genreIds, String overview) {
		this.overview = overview;
		this.title = title;
		this.genreIds = genreIds;
		this.posterPath = posterPath;
		this.backdropPath = backdropPath;
		this.releaseDate = releaseDate;
		this.popularity = popularity;
		this.voteAverage = voteAverage;
		this.id = id;
		this.voteCount = voteCount;
	}

	public String getOverview(){
		return overview;
	}

	public String getTitle(){
		return title;
	}

	public List<Integer> getGenreIds(){
		return genreIds;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public String getBackdropPath(){
		return backdropPath;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public double getPopularity(){
		return popularity;
	}

	public double getVoteAverage(){
		return voteAverage;
	}

	public int getId(){
		return id;
	}

	public int getVoteCount(){
		return voteCount;
	}
}