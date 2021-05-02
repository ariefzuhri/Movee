package com.ariefzuhri.movee.data.source.remote.entity;

public class TrailerEntity {

    private final String id;
    private final String name;
    private final String site;
    private final String type;
    private final String key;

    public TrailerEntity(String id, String name, String site, String type, String key) {
        this.id = id;
        this.name = name;
        this.site = site;
        this.type = type;
        this.key = key;
    }

    public String getSite(){
        return site;
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }

    public String getType(){
        return type;
    }

    public String getKey(){
        return key;
    }
}
