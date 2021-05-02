package com.ariefzuhri.movee.data.source.remote.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GenresResponse{

	@SerializedName("genres")
	private final List<GenreItem> genres;

	public GenresResponse(List<GenreItem> genres) {
		this.genres = genres;
	}

	public List<GenreItem> getGenres(){
		return genres;
	}
}