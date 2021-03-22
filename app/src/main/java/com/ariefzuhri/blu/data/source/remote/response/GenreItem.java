package com.ariefzuhri.blu.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class GenreItem {

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}
}