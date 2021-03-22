package com.ariefzuhri.blu.ui.main.discover;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ariefzuhri.blu.data.source.CatalogRepository;
import com.ariefzuhri.blu.data.source.remote.response.GenresResponse;
import com.ariefzuhri.blu.data.source.remote.response.MovieResponse;
import com.ariefzuhri.blu.data.source.remote.response.TVResponse;

public class DiscoverViewModel extends ViewModel {

    private final CatalogRepository repository;

    public DiscoverViewModel(CatalogRepository repository){
        this.repository = repository;
    }

    public LiveData<MovieResponse> getMovieUpcoming(){
        return repository.getMovieUpcoming(1);
    }

    public LiveData<MovieResponse> getMoviePopular(){
        return repository.getMoviePopular(1);
    }

    public LiveData<TVResponse> getTVPopular(){
        return repository.getTVPopular(1);
    }

    public LiveData<MovieResponse> getMovieTopRated(){
        return repository.getMovieTopRated(1);
    }

    public LiveData<TVResponse> getTVTopRated(){
        return repository.getTVTopRated(1);
    }

    public LiveData<MovieResponse> getMovieLatestRelease(){
        return repository.getMovieLatestRelease(1);
    }

    public LiveData<TVResponse> getTVLatestRelease(){
        return repository.getTVLatestRelease(1);
    }

    public LiveData<GenresResponse> getGenres(String mediaType) {
        return repository.getGenres(mediaType);
    }
}
