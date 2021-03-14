package com.ariefzuhri.blu.data.entity;

import com.google.gson.annotations.SerializedName;

public class GenreEntity {

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