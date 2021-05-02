package com.ariefzuhri.movee.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class VideoItem {

	@SerializedName("site")
	private final String site;

	@SerializedName("name")
	private final String name;

	@SerializedName("id")
	private final String id;

	@SerializedName("type")
	private final String type;

	@SerializedName("key")
	private final String key;

	public VideoItem(String id, String name, String site, String type, String key) {
		this.site = site;
		this.name = name;
		this.id = id;
		this.type = type;
		this.key = key;
	}

	public String getSite(){
		return site;
	}

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}

	public String getType(){
		return type;
	}

	public String getKey(){
		return key;
	}
}