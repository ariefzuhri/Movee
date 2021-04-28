package com.ariefzuhri.blu.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.sqlite.db.SimpleSQLiteQuery;

import com.ariefzuhri.blu.data.source.local.entity.FavoriteEntity;
import com.ariefzuhri.blu.data.source.local.entity.FavoriteWithGenres;
import com.ariefzuhri.blu.data.source.local.entity.GenreEntity;
import com.ariefzuhri.blu.data.source.local.room.CatalogDao;
import com.ariefzuhri.blu.utils.FilterFavorite;
import com.ariefzuhri.blu.utils.FilterUtils;

import java.util.List;

public class LocalDataSource {

    private static LocalDataSource INSTANCE;
    private final CatalogDao catalogDao;

    private LocalDataSource(CatalogDao catalogDao) {
        this.catalogDao = catalogDao;
    }

    public static LocalDataSource getInstance(CatalogDao catalogDao){
        if (INSTANCE == null){
            INSTANCE = new LocalDataSource(catalogDao);
        }
        return INSTANCE;
    }
    public DataSource.Factory<Integer, FavoriteWithGenres> getAllFavoriteWithGenres(FilterFavorite filter) {
        SimpleSQLiteQuery query = FilterUtils.getFilteredFavoriteQuery(filter);
        return catalogDao.getAllFavoriteWithGenres(query);
    }

    public LiveData<FavoriteWithGenres> getFavoriteWithGenresById(int id, String type) {
        return catalogDao.getFavoriteWithGenresById(id, type);
    }

    public void insertFavorite(FavoriteEntity favorite) {
        catalogDao.insertFavoriteAndFavoriteGenreJoin(favorite);
    }

    public void updateFavorite(FavoriteEntity favorite) {
        catalogDao.updateFavoriteAndFavoriteGenreJoin(favorite);
    }

    public void deleteFavorite(FavoriteEntity favorite) {
        catalogDao.deleteFavorite(favorite);
    }

    public LiveData<List<GenreEntity>> getGenres() {
        return catalogDao.getGenres();
    }

    public void insertGenres(List<GenreEntity> genres) {
        catalogDao.insertGenres(genres);
    }
}
