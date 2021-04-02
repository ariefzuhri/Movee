package com.ariefzuhri.blu.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class ProductionCompanyItem {

	@SerializedName("logo_path")
	private final String logoPath;

	@SerializedName("name")
	private final String name;

	@SerializedName("id")
	private final int id;

	public ProductionCompanyItem(int id, String name, String logoPath) {
		this.logoPath = logoPath;
		this.name = name;
		this.id = id;
	}

	public String getLogoPath(){
		return logoPath;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}
}