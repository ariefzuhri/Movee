package com.ariefzuhri.blu.data.source.remote.response;

import java.util.List;

import com.ariefzuhri.blu.data.entity.VideoEntity;
import com.google.gson.annotations.SerializedName;

public class VideosResponse{

	@SerializedName("id")
	private int id;

	@SerializedName("results")
	private List<VideoEntity> results;

	public int getId(){
		return id;
	}

	public List<VideoEntity> getResults(){
		return results;
	}
}