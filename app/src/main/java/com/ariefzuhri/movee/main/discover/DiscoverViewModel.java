package com.ariefzuhri.movee.main.discover;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ariefzuhri.movee.core.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.core.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.movee.core.data.repository.CatalogRepository;
import com.ariefzuhri.movee.core.data.repository.Resource;

import java.util.List;

public class DiscoverViewModel extends ViewModel {

    private final CatalogRepository repository;

    public DiscoverViewModel(CatalogRepository repository) {
        this.repository = repository;
    }

    private MutableLiveData<Resource<List<MediaEntity>>> moviePopular;
    private MutableLiveData<Resource<List<MediaEntity>>> tvPopular;
    private MutableLiveData<Resource<List<MediaEntity>>> movieUpcoming;
    private MutableLiveData<Resource<List<MediaEntity>>> movieLatestRelease;
    private MutableLiveData<Resource<List<MediaEntity>>> tvLatestRelease;
    private MutableLiveData<Resource<List<MediaEntity>>> movieTopRated;
    private MutableLiveData<Resource<List<MediaEntity>>> tvTopRated;
    private LiveData<Resource<List<GenreEntity>>> genres;

    public LiveData<Resource<List<MediaEntity>>> getMoviePopular() {
        if (moviePopular == null) moviePopular = repository.getMoviePopular(1);
        return moviePopular;
    }

    public LiveData<Resource<List<MediaEntity>>> getTVPopular() {
        if (tvPopular == null) tvPopular = repository.getTVPopular(1);
        return tvPopular;
    }

    public LiveData<Resource<List<MediaEntity>>> getMovieUpcoming() {
        if (movieUpcoming == null) movieUpcoming = repository.getMovieUpcoming(1);
        return movieUpcoming;
    }

    public LiveData<Resource<List<MediaEntity>>> getMovieLatestRelease() {
        if (movieLatestRelease == null) movieLatestRelease = repository.getMovieLatestRelease(1);
        return movieLatestRelease;
    }

    public LiveData<Resource<List<MediaEntity>>> getTVLatestRelease() {
        if (tvLatestRelease == null) tvLatestRelease = repository.getTVLatestRelease(1);
        return tvLatestRelease;
    }

    public LiveData<Resource<List<MediaEntity>>> getMovieTopRated() {
        if (movieTopRated == null) movieTopRated = repository.getMovieTopRated(1);
        return movieTopRated;
    }

    public LiveData<Resource<List<MediaEntity>>> getTVTopRated() {
        if (tvTopRated == null) tvTopRated = repository.getTVTopRated(1);
        return tvTopRated;
    }

    public LiveData<Resource<List<GenreEntity>>> getGenres() {
        if (genres == null) genres = repository.getGenres();
        return genres;
    }
}
