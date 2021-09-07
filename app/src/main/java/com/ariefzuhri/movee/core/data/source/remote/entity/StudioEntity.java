package com.ariefzuhri.movee.core.data.source.remote.entity;

public class StudioEntity {

    private final int id;
    private final String name;
    private final String logo;

    public StudioEntity(int id, String name, String logo) {
        this.id = id;
        this.name = name;
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }
}