package com.ariefzuhri.blu.ui.main.discover;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ariefzuhri.blu.data.GenreEntity;
import com.ariefzuhri.blu.data.MediaEntity;
import com.ariefzuhri.blu.data.source.CatalogRepository;

import java.util.List;

public class DiscoverViewModel extends ViewModel {

    private final CatalogRepository repository;

    public DiscoverViewModel(CatalogRepository repository){
        this.repository = repository;
    }

    public LiveData<List<MediaEntity>> getMovieUpcoming(){
        return repository.getMovieUpcoming(1);
    }

    public LiveData<List<MediaEntity>> getMoviePopular(){
        return repository.getMoviePopular(1);
    }

    public LiveData<List<MediaEntity>> getTVPopular(){
        return repository.getTVPopular(1);
    }

    public LiveData<List<MediaEntity>> getMovieTopRated(){
        return repository.getMovieTopRated(1);
    }

    public LiveData<List<MediaEntity>> getTVTopRated(){
        return repository.getTVTopRated(1);
    }

    public LiveData<List<MediaEntity>> getMovieLatestRelease(){
        return repository.getMovieLatestRelease(1);
    }

    public LiveData<List<MediaEntity>> getTVLatestRelease(){
        return repository.getTVLatestRelease(1);
    }

    public LiveData<List<GenreEntity>> getGenres(String mediaType) {
        return repository.getGenres(mediaType);
    }
}
