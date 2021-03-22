package com.ariefzuhri.blu.data.source.remote.response;

import java.util.List;

import com.ariefzuhri.blu.data.source.remote.entity.GenreEntity;
import com.google.gson.annotations.SerializedName;

public class GenresResponse{

	@SerializedName("genres")
	private List<GenreEntity> genres;

	public List<GenreEntity> getGenres(){
		return genres;
	}
}