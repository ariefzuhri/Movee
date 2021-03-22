package com.ariefzuhri.blu.data.source.remote.entity;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

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

	@NotNull
	@Override
	public String toString() {
		return "GenreEntity{" +
				"name='" + name + '\'' +
				", id=" + id +
				'}';
	}
}