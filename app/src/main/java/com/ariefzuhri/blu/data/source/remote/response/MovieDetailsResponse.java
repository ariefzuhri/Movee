package com.ariefzuhri.blu.data.source.remote.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MovieDetailsResponse{

	@SerializedName("title")
	private String title;

	@SerializedName("backdrop_path")
	private String backdropPath;

	@SerializedName("genres")
	private List<GenreItem> genres;

	@SerializedName("popularity")
	private double popularity;

	@SerializedName("id")
	private int id;

	@SerializedName("vote_count")
	private int voteCount;

	@SerializedName("overview")
	private String overview;

	@SerializedName("runtime")
	private int runtime;

	@SerializedName("poster_path")
	private String posterPath;

	@SerializedName("production_companies")
	private List<ProductionCompanyItem> productionCompanies;

	@SerializedName("release_date")
	private String releaseDate;

	@SerializedName("vote_average")
	private double voteAverage;

	@SerializedName("status")
	private String status;

	public String getTitle(){
		return title;
	}

	public String getBackdropPath(){
		return backdropPath;
	}

	public List<GenreItem> getGenres(){
		return genres;
	}

	public double getPopularity(){
		return popularity;
	}

	public int getId(){
		return id;
	}

	public int getVoteCount(){
		return voteCount;
	}

	public String getOverview(){
		return overview;
	}

	public int getRuntime(){
		return runtime;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public List<ProductionCompanyItem> getProductionCompanies(){
		return productionCompanies;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public double getVoteAverage(){
		return voteAverage;
	}

	public String getStatus(){
		return status;
	}
}