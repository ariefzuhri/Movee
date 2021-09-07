package com.ariefzuhri.movee.core.data.source.remote.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TVResponse {

    @SerializedName("page")
    private final int page;

    @SerializedName("total_pages")
    private final int totalPages;

    @SerializedName("results")
    private final List<TVItem> results;

    @SerializedName("total_results")
    private final int totalResults;

    public TVResponse(int page, int totalPages, List<TVItem> results, int totalResults) {
        this.page = page;
        this.totalPages = totalPages;
        this.results = results;
        this.totalResults = totalResults;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<TVItem> getResults() {
        return results;
    }

    public int getTotalResults() {
        return totalResults;
    }
}