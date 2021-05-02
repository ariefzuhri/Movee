package com.ariefzuhri.movee.data.source.remote.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class VideosResponse{

	@SerializedName("id")
	private final int id;

	@SerializedName("results")
	private final List<VideoItem> results;

	public VideosResponse(int id, List<VideoItem> results) {
		this.id = id;
		this.results = results;
	}

	public int getId(){
		return id;
	}

	public List<VideoItem> getResults(){
		return results;
	}
}