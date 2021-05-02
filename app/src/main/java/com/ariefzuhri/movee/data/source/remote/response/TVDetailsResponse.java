package com.ariefzuhri.movee.data.source.remote.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TVDetailsResponse{

	@SerializedName("number_of_episodes")
	private final int numberOfEpisodes;

	@SerializedName("backdrop_path")
	private final String backdropPath;

	@SerializedName("genres")
	private final List<GenreItem> genres;

	@SerializedName("popularity")
	private final double popularity;

	@SerializedName("id")
	private final int id;

	@SerializedName("number_of_seasons")
	private final int numberOfSeasons;

	@SerializedName("vote_count")
	private final int voteCount;

	@SerializedName("first_air_date")
	private final String firstAirDate;

	@SerializedName("overview")
	private final String overview;

	@SerializedName("poster_path")
	private final String posterPath;

	@SerializedName("production_companies")
	private final List<ProductionCompanyItem> productionCompanies;

	@SerializedName("vote_average")
	private final double voteAverage;

	@SerializedName("name")
	private final String name;

	@SerializedName("episode_run_time")
	private final List<Integer> episodeRunTime;

	@SerializedName("last_air_date")
	private final String lastAirDate;

	@SerializedName("status")
	private final String status;

	public TVDetailsResponse(int id, String name, String posterPath, String backdropPath, double voteAverage, int voteCount, double popularity, int numberOfSeasons, int numberOfEpisodes, String status, String firstAirDate, String lastAirDate, List<ProductionCompanyItem> productionCompanies, List<GenreItem> genres, List<Integer> episodeRunTime, String overview) {
		this.name = name;
		this.backdropPath = backdropPath;
		this.genres = genres;
		this.popularity = popularity;
		this.numberOfSeasons = numberOfSeasons;
		this.numberOfEpisodes = numberOfEpisodes;
		this.id = id;
		this.voteCount = voteCount;
		this.overview = overview;
		this.episodeRunTime = episodeRunTime;
		this.posterPath = posterPath;
		this.productionCompanies = productionCompanies;
		this.firstAirDate = firstAirDate;
		this.lastAirDate = lastAirDate;
		this.voteAverage = voteAverage;
		this.status = status;
	}

	public int getNumberOfEpisodes(){
		return numberOfEpisodes;
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

	public int getNumberOfSeasons(){
		return numberOfSeasons;
	}

	public int getVoteCount(){
		return voteCount;
	}

	public String getFirstAirDate(){
		return firstAirDate;
	}

	public String getOverview(){
		return overview;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public List<ProductionCompanyItem> getProductionCompanies(){
		return productionCompanies;
	}

	public double getVoteAverage(){
		return voteAverage;
	}

	public String getName(){
		return name;
	}

	public List<Integer> getEpisodeRunTime(){
		return episodeRunTime;
	}

	public String getLastAirDate(){
		return lastAirDate;
	}

	public String getStatus(){
		return status;
	}
}