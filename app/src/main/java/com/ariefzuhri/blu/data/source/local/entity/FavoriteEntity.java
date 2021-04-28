package com.ariefzuhri.blu.data.source.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "favoriteEntities")
public class FavoriteEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uid")
    private final String uid;

    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

    @NonNull
    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "poster")
    private final String poster;

    @ColumnInfo(name = "scoreAverage")
    private final double scoreAverage;

    @ColumnInfo(name = "startDate")
    private final String startDate;

    @Ignore
    private List<GenreEntity> genres;

    public FavoriteEntity(@NonNull String uid, @NonNull Integer id, @NonNull String type, String title, String poster, double scoreAverage, String startDate) {
        this.uid = uid;
        this.id = id;
        this.type = type;
        this.title = title;
        this.poster = poster;
        this.scoreAverage = scoreAverage;
        this.startDate = startDate;
    }

    @Ignore
    public FavoriteEntity(@NonNull Integer id, @NonNull String type, String title, String poster, double scoreAverage, String startDate, List<GenreEntity> genres) {
        this.uid = type + id;
        this.id = id;
        this.type = type;
        this.title = title;
        this.poster = poster;
        this.scoreAverage = scoreAverage;
        this.startDate = startDate;
        this.genres = genres;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public double getScoreAverage() {
        return scoreAverage;
    }

    public String getStartDate() {
        return startDate;
    }

    public List<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreEntity> genres) {
        this.genres = genres;
    }
}
