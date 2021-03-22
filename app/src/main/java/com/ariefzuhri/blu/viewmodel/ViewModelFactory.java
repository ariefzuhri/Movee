package com.ariefzuhri.blu.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ariefzuhri.blu.data.source.CatalogRepository;
import com.ariefzuhri.blu.di.Injection;
import com.ariefzuhri.blu.ui.detail.DetailMediaViewModel;
import com.ariefzuhri.blu.ui.main.discover.DiscoverViewModel;
import com.ariefzuhri.blu.ui.main.movie.MovieViewModel;
import com.ariefzuhri.blu.ui.main.home.tv.TVViewModel;
import com.ariefzuhri.blu.ui.search.SearchViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final CatalogRepository mCatalogRepository;

    private ViewModelFactory(CatalogRepository catalogRepository){
        mCatalogRepository = catalogRepository;
    }

    public static ViewModelFactory getInstance(){
        if (INSTANCE == null){
            synchronized (ViewModelFactory.class){
                if (INSTANCE == null){
                    INSTANCE = new ViewModelFactory(Injection.provideRepository());
                }
            }
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)){
            return (T) new MovieViewModel(mCatalogRepository);
        } else if (modelClass.isAssignableFrom(TVViewModel.class)){
            return (T) new TVViewModel(mCatalogRepository);
        } else if (modelClass.isAssignableFrom(DiscoverViewModel.class)){
            return (T) new DiscoverViewModel(mCatalogRepository);
        } else if (modelClass.isAssignableFrom(DetailMediaViewModel.class)){
            return (T) new DetailMediaViewModel(mCatalogRepository);
        } else if (modelClass.isAssignableFrom(SearchViewModel.class)){
            return (T) new SearchViewModel(mCatalogRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}