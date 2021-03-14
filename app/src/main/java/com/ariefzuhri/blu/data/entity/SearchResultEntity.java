package com.ariefzuhri.blu.data.entity;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SearchResultEntity {

	@SerializedName("overview")
	private String overview;

	@SerializedName("title")
	private String title;

	@SerializedName("genre_ids")
	private List<Integer> genreIds;

	@SerializedName("poster_path")
	private String posterPath;

	@SerializedName("backdrop_path")
	private String backdropPath;

	@SerializedName("media_type")
	private String mediaType;

	@SerializedName("release_date")
	private String releaseDate;

	@SerializedName("popularity")
	private double popularity;

	@SerializedName("vote_average")
	private double voteAverage;

	@SerializedName("id")
	private int id;

	@SerializedName("vote_count")
	private int voteCount;

	@SerializedName("first_air_date")
	private String firstAirDate;

	private String originalName;

	@SerializedName("name")
	private String name;

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

	public String getMediaType(){
		return mediaType;
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

	public String getFirstAirDate(){
		return firstAirDate;
	}

	public String getOriginalName(){
		return originalName;
	}

	public String getName(){
		return name;
	}
}