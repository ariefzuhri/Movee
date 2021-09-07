package com.ariefzuhri.movee.core.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.sqlite.db.SimpleSQLiteQuery;

import com.ariefzuhri.movee.core.data.source.local.entity.FavoriteEntity;
import com.ariefzuhri.movee.core.data.source.local.entity.FavoriteWithGenres;
import com.ariefzuhri.movee.core.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.core.data.source.local.persistence.CatalogDao;
import com.ariefzuhri.movee.core.utils.FilterFavorite;
import com.ariefzuhri.movee.core.utils.FilterUtils;

import java.util.List;

public class LocalDataSource {

    private static LocalDataSource INSTANCE;
    private final CatalogDao catalogDao;

    private LocalDataSource(CatalogDao catalogDao) {
        this.catalogDao = catalogDao;
    }

    public static LocalDataSource getInstance(CatalogDao catalogDao) {
        if (INSTANCE == null) {
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

    public void setFavorite(FavoriteEntity favorite, boolean state) {
        if (state) catalogDao.insertFavoriteAndFavoriteGenreJoin(favorite);
        else catalogDao.deleteFavorite(favorite);
    }

    public void updateFavorite(FavoriteEntity favorite) {
        catalogDao.updateFavoriteAndFavoriteGenreJoin(favorite);
    }

    public LiveData<List<GenreEntity>> getGenres() {
        return catalogDao.getGenres();
    }

    public void insertGenres(List<GenreEntity> genres) {
        catalogDao.insertGenres(genres);
    }
}
