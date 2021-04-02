package com.ariefzuhri.blu.data;

public class CrewEntity {

    private final int id;
    private final String name;
    private final String creditId;
    private final Object profile;
    private final String job;

    public CrewEntity(int id, String name, String creditId, Object profile, String job) {
        this.id = id;
        this.name = name;
        this.creditId = creditId;
        this.profile = profile;
        this.job = job;
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

    public Object getProfile() {
        return profile;
    }

    public String getJob() {
        return job;
    }
}
