package com.ariefzuhri.movee.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ariefzuhri.movee.data.CatalogRepository;
import com.ariefzuhri.movee.di.Injection;
import com.ariefzuhri.movee.ui.detail.DetailMediaViewModel;
import com.ariefzuhri.movee.ui.main.home.favorite.FavoriteViewModel;
import com.ariefzuhri.movee.ui.main.home.discover.DiscoverViewModel;
import com.ariefzuhri.movee.ui.main.home.movie.MovieViewModel;
import com.ariefzuhri.movee.ui.main.home.tv.TVViewModel;
import com.ariefzuhri.movee.ui.search.SearchViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;
    private final CatalogRepository mCatalogRepository;

    private ViewModelFactory(Application application, CatalogRepository catalogRepository){
        mApplication = application;
        mCatalogRepository = catalogRepository;
    }

    public static ViewModelFactory getInstance(Application application){
        if (INSTANCE == null){
            synchronized (ViewModelFactory.class){
                INSTANCE = new ViewModelFactory(application,
                        Injection.provideRepository(application.getApplicationContext()));
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
            return (T) new SearchViewModel(mApplication, mCatalogRepository);
        } else if (modelClass.isAssignableFrom(FavoriteViewModel.class)){
            return (T) new FavoriteViewModel(mCatalogRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
