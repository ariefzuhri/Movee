package com.ariefzuhri.blu.data.source.local.entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class FavoriteWithGenres {

    @Embedded
    public final FavoriteEntity favorite;

    @Relation(
            parentColumn = "uid",
            entity = GenreEntity.class,
            entityColumn = "id",
            associateBy = @Junction(
                    value = FavoriteGenreJoin.class,
                    parentColumn = "favoriteUid",
                    entityColumn = "genreId"
            ))
    public final List<GenreEntity> genres;

    public FavoriteWithGenres(FavoriteEntity favorite, List<GenreEntity> genres) {
        this.favorite = favorite;
        this.genres = genres;
    }
}
