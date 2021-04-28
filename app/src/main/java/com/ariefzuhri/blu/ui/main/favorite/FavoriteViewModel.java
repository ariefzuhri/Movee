package com.ariefzuhri.blu.ui.main.favorite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.ariefzuhri.blu.data.CatalogRepository;
import com.ariefzuhri.blu.data.source.local.entity.FavoriteEntity;
import com.ariefzuhri.blu.data.source.local.entity.FavoriteWithGenres;
import com.ariefzuhri.blu.utils.FilterFavorite;

public class FavoriteViewModel extends ViewModel {

    private final CatalogRepository repository;

    private MutableLiveData<FilterFavorite> filter;
    private LiveData<PagedList<FavoriteWithGenres>> allFavoriteWithGenres;

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

    public LiveData<PagedList<FavoriteWithGenres>> getAllFavoriteWithGenres(){
        if (allFavoriteWithGenres == null) allFavoriteWithGenres = Transformations.switchMap(filter, repository::getAllFavoriteWithGenres);
        return allFavoriteWithGenres;
    }

    public void deleteFavorite(FavoriteEntity favorite) {
        repository.deleteFavorite(favorite);
    }
}
