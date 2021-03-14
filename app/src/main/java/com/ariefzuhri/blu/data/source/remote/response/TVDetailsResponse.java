package com.ariefzuhri.blu.data.source.remote.response;

import java.util.List;

import com.ariefzuhri.blu.data.entity.GenreEntity;
import com.ariefzuhri.blu.data.entity.ProductionCompanyEntity;
import com.google.gson.annotations.SerializedName;

public class TVDetailsResponse{

	@SerializedName("number_of_episodes")
	private int numberOfEpisodes;

	@SerializedName("backdrop_path")
	private String backdropPath;

	@SerializedName("genres")
	private List<GenreEntity> genres;

	@SerializedName("popularity")
	private double popularity;

	@SerializedName("id")
	private int id;

	@SerializedName("number_of_seasons")
	private int numberOfSeasons;

	@SerializedName("vote_count")
	private int voteCount;

	@SerializedName("first_air_date")
	private String firstAirDate;

	@SerializedName("overview")
	private String overview;

	@SerializedName("poster_path")
	private String posterPath;

	@SerializedName("production_companies")
	private List<ProductionCompanyEntity> productionCompanies;

	@SerializedName("vote_average")
	private double voteAverage;

	@SerializedName("name")
	private String name;

	@SerializedName("episode_run_time")
	private List<Integer> episodeRunTime;

	@SerializedName("last_air_date")
	private String lastAirDate;

	@SerializedName("status")
	private String status;

	public int getNumberOfEpisodes(){
		return numberOfEpisodes;
	}

	public String getBackdropPath(){
		return backdropPath;
	}

	public List<GenreEntity> getGenres(){
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

	public List<ProductionCompanyEntity> getProductionCompanies(){
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