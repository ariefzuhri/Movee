package com.ariefzuhri.blu.data.source.remote.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MultiSearchResponse{

	@SerializedName("page")
	private final int page;

	@SerializedName("total_pages")
	private final int totalPages;

	@SerializedName("results")
	private final List<SearchResultItem> results;

	@SerializedName("total_results")
	private final int totalResults;

	public MultiSearchResponse(int page, int totalPages, List<SearchResultItem> results, int totalResults) {
		this.page = page;
		this.totalPages = totalPages;
		this.results = results;
		this.totalResults = totalResults;
	}

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