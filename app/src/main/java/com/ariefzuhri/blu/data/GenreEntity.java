package com.ariefzuhri.blu.data;

import org.jetbrains.annotations.NotNull;

public class GenreEntity {

    private final int id;
    private final String name;

    public GenreEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    @NotNull
    @Override
    public String toString() {
        return "GenreEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
