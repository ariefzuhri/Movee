package com.ariefzuhri.blu.data.entity;

import com.google.gson.annotations.SerializedName;

public class CrewEntity {

	@SerializedName("credit_id")
	private String creditId;

	@SerializedName("name")
	private String name;

	@SerializedName("profile_path")
	private Object profilePath;

	@SerializedName("id")
	private int id;

	@SerializedName("job")
	private String job;

	public String getCreditId(){
		return creditId;
	}

	public String getName(){
		return name;
	}

	public Object getProfilePath(){
		return profilePath;
	}

	public int getId(){
		return id;
	}

	public String getJob(){
		return job;
	}
}