package com.ariefzuhri.blu.data.source.remote.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GenresResponse{

	@SerializedName("genres")
	private List<GenreItem> genres;

	public List<GenreItem> getGenres(){
		return genres;
	}
}