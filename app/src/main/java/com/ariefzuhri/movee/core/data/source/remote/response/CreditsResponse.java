package com.ariefzuhri.movee.core.data.source.remote.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CreditsResponse {

    @SerializedName("cast")
    private final List<CastItem> cast;

    @SerializedName("id")
    private final int id;

    @SerializedName("crew")
    private final List<CrewItem> crew;

    public CreditsResponse(int id, List<CastItem> cast, List<CrewItem> crew) {
        this.cast = cast;
        this.id = id;
        this.crew = crew;
    }

    public List<CastItem> getCast() {
        return cast;
    }

    public int getId() {
        return id;
    }

    public List<CrewItem> getCrew() {
        return crew;
    }
}