package com.ariefzuhri.blu.data.source.remote.response;

import java.util.List;

import com.ariefzuhri.blu.data.source.remote.entity.CastEntity;
import com.ariefzuhri.blu.data.source.remote.entity.CrewEntity;
import com.google.gson.annotations.SerializedName;

public class CreditsResponse{

	@SerializedName("cast")
	private List<CastEntity> cast;

	@SerializedName("id")
	private int id;

	@SerializedName("crew")
	private List<CrewEntity> crew;

	public List<CastEntity> getCast(){
		return cast;
	}

	public int getId(){
		return id;
	}

	public List<CrewEntity> getCrew(){
		return crew;
	}
}