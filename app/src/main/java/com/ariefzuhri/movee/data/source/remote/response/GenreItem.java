package com.ariefzuhri.movee.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class GenreItem {

	@SerializedName("name")
	private final String name;

	@SerializedName("id")
	private final int id;

	public GenreItem(int id, String name) {
		this.name = name;
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}
}