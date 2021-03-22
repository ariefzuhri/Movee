package com.ariefzuhri.blu.data.source.remote.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CreditsResponse{

	@SerializedName("cast")
	private List<CastItem> cast;

	@SerializedName("id")
	private int id;

	@SerializedName("crew")
	private List<CrewItem> crew;

	public List<CastItem> getCast(){
		return cast;
	}

	public int getId(){
		return id;
	}

	public List<CrewItem> getCrew(){
		return crew;
	}
}