package com.ariefzuhri.blu.ui.main.discover;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ariefzuhri.blu.data.source.local.entity.GenreEntity;
import com.ariefzuhri.blu.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.blu.data.CatalogRepository;
import com.ariefzuhri.blu.vo.Resource;

import java.util.List;

public class DiscoverViewModel extends ViewModel {

    private final CatalogRepository repository;

    public DiscoverViewModel(CatalogRepository repository){
        this.repository = repository;
    }

    private MutableLiveData<List<MediaEntity>> moviePopular;
    private MutableLiveData<List<MediaEntity>> tvPopular;
    private MutableLiveData<List<MediaEntity>> movieUpcoming;
    private MutableLiveData<List<MediaEntity>> movieLatestRelease;
    private MutableLiveData<List<MediaEntity>> tvLatestRelease;
    private MutableLiveData<List<MediaEntity>> movieTopRated;
    private MutableLiveData<List<MediaEntity>> tvTopRated;
    private LiveData<Resource<List<GenreEntity>>> genres;

    public LiveData<List<MediaEntity>> getMoviePopular(){
        if (moviePopular == null) moviePopular = repository.getMoviePopular(1);
        return moviePopular;
    }

    public LiveData<List<MediaEntity>> getTVPopular(){
        if (tvPopular == null) tvPopular = repository.getTVPopular(1);
        return tvPopular;
    }

    public LiveData<List<MediaEntity>> getMovieUpcoming(){
        if (movieUpcoming == null) movieUpcoming = repository.getMovieUpcoming(1);
        return movieUpcoming;
    }

    public LiveData<List<MediaEntity>> getMovieLatestRelease(){
        if (movieLatestRelease == null) movieLatestRelease = repository.getMovieLatestRelease(1);
        return movieLatestRelease;
    }

    public LiveData<List<MediaEntity>> getTVLatestRelease(){
        if (tvLatestRelease == null) tvLatestRelease = repository.getTVLatestRelease(1);
        return tvLatestRelease;
    }

    public LiveData<List<MediaEntity>> getMovieTopRated(){
        if (movieTopRated == null) movieTopRated = repository.getMovieTopRated(1);
        return movieTopRated;
    }

    public LiveData<List<MediaEntity>> getTVTopRated(){
        if (tvTopRated == null) tvTopRated = repository.getTVTopRated(1);
        return tvTopRated;
    }

    public LiveData<Resource<List<GenreEntity>>> getGenres() {
        if (genres == null) genres = repository.getGenres();
        return genres;
    }
}
