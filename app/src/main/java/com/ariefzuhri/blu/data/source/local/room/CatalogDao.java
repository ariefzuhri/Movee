package com.ariefzuhri.blu.data.source.local.room;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Transaction;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.ariefzuhri.blu.data.source.local.entity.FavoriteEntity;
import com.ariefzuhri.blu.data.source.local.entity.FavoriteWithGenres;
import com.ariefzuhri.blu.data.source.local.entity.GenreEntity;
import com.ariefzuhri.blu.data.source.local.entity.FavoriteGenreJoin;

import java.util.List;

@Dao
public abstract class CatalogDao {

    @Transaction
    @RawQuery(observedEntities = FavoriteEntity.class)
    public abstract DataSource.Factory<Integer, FavoriteWithGenres> getAllFavoriteWithGenres(SupportSQLiteQuery query);

    @Transaction
    @Query("SELECT * FROM favoriteEntities WHERE id = :id AND type = :type")
    public abstract LiveData<FavoriteWithGenres> getFavoriteWithGenresById(Integer id, String type);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertFavorite(FavoriteEntity favorite);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertFavoriteGenreJoin(FavoriteGenreJoin favoriteGenreJoin);

    @Transaction
    public void insertFavoriteAndFavoriteGenreJoin(FavoriteEntity favorite) {
        insertFavorite(favorite);
        for (GenreEntity genre : favorite.getGenres()){
            FavoriteGenreJoin join = new FavoriteGenreJoin(
                    favorite.getId(), favorite.getType(), genre.getId());
            insertFavoriteGenreJoin(join);
        }
    }

    @Update
    abstract void updateFavorite(FavoriteEntity favorite);

    @Transaction
    public void updateFavoriteAndFavoriteGenreJoin(FavoriteEntity favorite) {
        updateFavorite(favorite);
        deleteFavoriteGenreJoin(favorite.getId(), favorite.getType());
        for (GenreEntity genre : favorite.getGenres()){
            FavoriteGenreJoin join = new FavoriteGenreJoin(
                    favorite.getId(), favorite.getType(), genre.getId());
            insertFavoriteGenreJoin(join);
        }
    }

    @Delete
    public abstract void deleteFavorite(FavoriteEntity favorite);

    @Query("DELETE FROM favoriteGenreJoins WHERE favoriteId = :favoriteId AND favoriteType = :favoriteType")
    public abstract void deleteFavoriteGenreJoin(int favoriteId, String favoriteType);

    @Query("SELECT * FROM genreEntities")
    public abstract LiveData<List<GenreEntity>> getGenres();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertGenres(List<GenreEntity> genres);
}
