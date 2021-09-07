package com.ariefzuhri.movee.main.favorite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.ariefzuhri.movee.core.data.repository.CatalogRepository;
import com.ariefzuhri.movee.core.data.source.local.entity.FavoriteEntity;
import com.ariefzuhri.movee.core.data.source.local.entity.FavoriteWithGenres;
import com.ariefzuhri.movee.core.utils.FilterFavorite;

public class FavoriteViewModel extends ViewModel {

    private final CatalogRepository repository;

    private final MutableLiveData<FilterFavorite> filter = new MutableLiveData<>();
    private LiveData<PagedList<FavoriteWithGenres>> favorites;

    public FavoriteViewModel(CatalogRepository repository) {
        this.repository = repository;
        setFilter(new FilterFavorite());
    }

    public void setFilter(FilterFavorite filter) {
        this.filter.setValue(filter);
    }

    public LiveData<FilterFavorite> getFilter() {
        return filter;
    }

    public LiveData<PagedList<FavoriteWithGenres>> getFavorites() {
        if (favorites == null)
            favorites = Transformations.switchMap(filter, repository::getFavorites);
        return favorites;
    }

    public void deleteFavorite(FavoriteEntity favorite) {
        repository.setFavorite(favorite, false);
    }
}
