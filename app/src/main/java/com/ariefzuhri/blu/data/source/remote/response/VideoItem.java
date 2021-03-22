package com.ariefzuhri.blu.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class VideoItem {

	@SerializedName("site")
	private String site;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("type")
	private String type;

	@SerializedName("key")
	private String key;

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