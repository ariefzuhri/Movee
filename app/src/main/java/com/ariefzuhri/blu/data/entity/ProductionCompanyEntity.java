package com.ariefzuhri.blu.data.entity;

import com.google.gson.annotations.SerializedName;

public class ProductionCompanyEntity {

	@SerializedName("logo_path")
	private String logoPath;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("origin_country")
	private String originCountry;

	public String getLogoPath(){
		return logoPath;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public String getOriginCountry(){
		return originCountry;
	}
}