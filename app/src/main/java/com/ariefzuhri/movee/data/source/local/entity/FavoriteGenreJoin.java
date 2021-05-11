package com.ariefzuhri.movee.data.source.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

@Entity(tableName = "favoriteGenreJoins",
        primaryKeys = {"favoriteUid", "genreId"},
        indices = {
                @Index(value = "favoriteUid"),
                @Index(value = "genreId")
        },
        foreignKeys = {
                @ForeignKey(entity = FavoriteEntity.class,
                        parentColumns = "uid",
                        childColumns = "favoriteUid",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = GenreEntity.class,
                        parentColumns = "id",
                        childColumns = "genreId")
        })
public class FavoriteGenreJoin {

    @NonNull
    @ColumnInfo(name = "favoriteUid")
    private final String favoriteUid;

    @NonNull
    @ColumnInfo(name = "genreId")
    private final Integer genreId;

    @NonNull
    @ColumnInfo(name = "favoriteId")
    private final Integer favoriteId;

    @NonNull
    @ColumnInfo(name = "favoriteType")
    private final String favoriteType;

    @SuppressWarnings("unused")
    public FavoriteGenreJoin(@NonNull String favoriteUid, @NonNull Integer genreId, @NonNull Integer favoriteId, @NonNull String favoriteType) {
        this.favoriteUid = favoriteUid;
        this.genreId = genreId;
        this.favoriteId = favoriteId;
        this.favoriteType = favoriteType;
    }

    @Ignore
    public FavoriteGenreJoin(@NonNull Integer favoriteId, @NonNull String favoriteType, @NonNull Integer genreId) {
        this.favoriteUid = favoriteType + favoriteId;
        this.genreId = genreId;
        this.favoriteId = favoriteId;
        this.favoriteType = favoriteType;
    }

    @NonNull
    public String getFavoriteUid() {
        return favoriteUid;
    }

    @NonNull
    public Integer getGenreId() {
        return genreId;
    }

    @NonNull
    public Integer getFavoriteId() {
        return favoriteId;
    }

    @NonNull
    public String getFavoriteType() {
        return favoriteType;
    }
}
