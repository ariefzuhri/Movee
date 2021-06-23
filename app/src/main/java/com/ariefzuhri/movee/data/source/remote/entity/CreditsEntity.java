package com.ariefzuhri.movee.data.source.remote.entity;

import java.util.List;

public class CreditsEntity {

    private final int id;
    private final List<CastEntity> casts;
    private final List<CrewEntity> crews;

    public CreditsEntity(int id, List<CastEntity> casts, List<CrewEntity> crews) {
        this.id = id;
        this.casts = casts;
        this.crews = crews;
    }

    public int getId(){
        return id;
    }

    public List<CastEntity> getCasts(){
        return casts;
    }

    public List<CrewEntity> getCrews(){
        return crews;
    }
}
