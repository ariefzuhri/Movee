package com.ariefzuhri.movee.data.source.remote.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MovieDetailsResponse{

	@SerializedName("title")
	private final String title;

	@SerializedName("backdrop_path")
	private final String backdropPath;

	@SerializedName("genres")
	private final List<GenreItem> genres;

	@SerializedName("popularity")
	private final double popularity;

	@SerializedName("id")
	private final int id;

	@SerializedName("vote_count")
	private final int voteCount;

	@SerializedName("overview")
	private final String overview;

	@SerializedName("runtime")
	private final int runtime;

	@SerializedName("poster_path")
	private final String posterPath;

	@SerializedName("production_companies")
	private final List<ProductionCompanyItem> productionCompanies;

	@SerializedName("release_date")
	private final String releaseDate;

	@SerializedName("vote_average")
	private final double voteAverage;

	@SerializedName("status")
	private final String status;

	public MovieDetailsResponse(int id, String title, String posterPath, String backdropPath, double voteAverage, int voteCount, double popularity, String status, String releaseDate, List<ProductionCompanyItem> productionCompanies, List<GenreItem> genres, int runtime, String overview) {
		this.title = title;
		this.backdropPath = backdropPath;
		this.genres = genres;
		this.popularity = popularity;
		this.id = id;
		this.voteCount = voteCount;
		this.overview = overview;
		this.runtime = runtime;
		this.posterPath = posterPath;
		this.productionCompanies = productionCompanies;
		this.releaseDate = releaseDate;
		this.voteAverage = voteAverage;
		this.status = status;
	}

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