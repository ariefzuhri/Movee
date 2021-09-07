package com.ariefzuhri.movee.core.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class CrewItem {

    @SerializedName("credit_id")
    private final String creditId;

    @SerializedName("name")
    private final String name;

    @SerializedName("profile_path")
    private final Object profilePath;

    @SerializedName("id")
    private final int id;

    @SerializedName("job")
    private final String job;

    public CrewItem(int id, String name, String creditId, Object profilePath, String job) {
        this.creditId = creditId;
        this.name = name;
        this.profilePath = profilePath;
        this.id = id;
        this.job = job;
    }

    public String getCreditId() {
        return creditId;
    }

    public String getName() {
        return name;
    }

    public Object getProfilePath() {
        return profilePath;
    }

    public int getId() {
        return id;
    }

    public String getJob() {
        return job;
    }
}