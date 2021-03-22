package com.ariefzuhri.blu.data.source.remote.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MultiSearchResponse{

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<SearchResultItem> results;

	@SerializedName("total_results")
	private int totalResults;

	public int getPage(){
		return page;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public List<SearchResultItem> getResults(){
		return results;
	}

	public int getTotalResults(){
		return totalResults;
	}
}