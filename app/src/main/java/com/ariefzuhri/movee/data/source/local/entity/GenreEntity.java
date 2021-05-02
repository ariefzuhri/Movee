package com.ariefzuhri.movee.data.source.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "genreEntities")
public class GenreEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private final Integer id;

    @ColumnInfo(name = "name")
    private final String name;

    public GenreEntity(@NotNull Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @NotNull
    public Integer getId(){
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
