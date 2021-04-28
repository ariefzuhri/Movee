package com.ariefzuhri.blu.data.source.remote.entity;

public class CastEntity {

    private final int id;
    private final String name;
    private final String creditId;
    private final String profile;
    private final String knownForDepartment;
    private final String character;

    public CastEntity(int id, String name, String creditId, String profile, String knownForDepartment, String character) {
        this.id = id;
        this.name = name;
        this.creditId = creditId;
        this.profile = profile;
        this.knownForDepartment = knownForDepartment;
        this.character = character;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreditId() {
        return creditId;
    }

    public String getProfile() {
        return profile;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public String getCharacter() {
        return character;
    }
}
