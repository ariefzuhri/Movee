package com.ariefzuhri.movee.core.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class CastItem {

    @SerializedName("character")
    private final String character;

    @SerializedName("credit_id")
    private final String creditId;

    @SerializedName("known_for_department")
    private final String knownForDepartment;

    @SerializedName("name")
    private final String name;

    @SerializedName("profile_path")
    private final String profilePath;

    @SerializedName("id")
    private final int id;

    public CastItem(int id, String name, String creditId, String profilePath, String knownForDepartment, String character) {
        this.character = character;
        this.creditId = creditId;
        this.knownForDepartment = knownForDepartment;
        this.name = name;
        this.profilePath = profilePath;
        this.id = id;
    }

    public String getCharacter() {
        return character;
    }

    public String getCreditId() {
        return creditId;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public String getName() {
        return name;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public int getId() {
        return id;
    }
}