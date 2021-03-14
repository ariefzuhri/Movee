package com.ariefzuhri.blu.data.entity;

import com.google.gson.annotations.SerializedName;

public class CastEntity {

	@SerializedName("character")
	private String character;

	@SerializedName("credit_id")
	private String creditId;

	@SerializedName("known_for_department")
	private String knownForDepartment;

	@SerializedName("name")
	private String name;

	@SerializedName("profile_path")
	private String profilePath;

	@SerializedName("id")
	private int id;

	public String getCharacter(){
		return character;
	}

	public String getCreditId(){
		return creditId;
	}

	public String getKnownForDepartment(){
		return knownForDepartment;
	}

	public String getName(){
		return name;
	}

	public String getProfilePath(){
		return profilePath;
	}

	public int getId(){
		return id;
	}
}