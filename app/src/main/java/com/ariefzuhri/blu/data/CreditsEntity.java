package com.ariefzuhri.blu.data;

import java.util.List;

public class CreditsEntity {

    private final int id;
    private final List<CastEntity> cast;
    private final List<CrewEntity> crew;

    public CreditsEntity(int id, List<CastEntity> cast, List<CrewEntity> crew) {
        this.id = id;
        this.cast = cast;
        this.crew = crew;
    }

    public int getId(){
        return id;
    }

    public List<CastEntity> getCast(){
        return cast;
    }

    public List<CrewEntity> getCrew(){
        return crew;
    }
}
