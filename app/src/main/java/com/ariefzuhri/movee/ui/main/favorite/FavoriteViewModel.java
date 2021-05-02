package com.ariefzuhri.movee.ui.main.favorite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.ariefzuhri.movee.data.CatalogRepository;
import com.ariefzuhri.movee.data.source.local.entity.FavoriteEntity;
import com.ariefzuhri.movee.data.source.local.entity.FavoriteWithGenres;
import com.ariefzuhri.movee.utils.FilterFavorite;

public class FavoriteViewModel extends ViewModel {

    private final CatalogRepository repository;

    private MutableLiveData<FilterFavorite> filter;
    private LiveData<PagedList<FavoriteWithGenres>> favorites;

    public FavoriteViewModel(CatalogRepository repository){
        this.repository = repository;
    }

    public void setFilter(FilterFavorite filter) {
        this.filter.setValue(filter);
    }

    public LiveData<FilterFavorite> getFilter(){
        if (filter == null) {
            filter = new MutableLiveData<>();
            filter.setValue(new FilterFavorite());
        }
        return filter;
    }

    public LiveData<PagedList<FavoriteWithGenres>> getFavorites(){
        if (favorites == null) favorites = Transformations.switchMap(filter, repository::getAllFavoriteWithGenres);
        return favorites;
    }

    public void deleteFavorite(FavoriteEntity favorite) {
        repository.deleteFavorite(favorite);
    }
}
