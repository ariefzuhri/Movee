package com.ariefzuhri.blu.data.source.remote.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class VideosResponse{

	@SerializedName("id")
	private int id;

	@SerializedName("results")
	private List<VideoItem> results;

	public int getId(){
		return id;
	}

	public List<VideoItem> getResults(){
		return results;
	}
}