package com.ariefzuhri.blu.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ariefzuhri.blu.data.GenreEntity;
import com.ariefzuhri.blu.data.MediaEntity;
import com.ariefzuhri.blu.data.source.CatalogRepository;

import java.util.List;

public class SearchViewModel extends ViewModel {

    private final CatalogRepository repository;

    private int page;

    public SearchViewModel(CatalogRepository repository){
        this.repository = repository;
    }

    public void setPage(int page){
        this.page = page;
    }

    public LiveData<List<MediaEntity>> getMultiSearch(String query){
        return repository.getMultiSearch(query, page);
    }

    public LiveData<List<GenreEntity>> getGenres(String mediaType) {
        return repository.getGenres(mediaType);
    }

    public LiveData<List<MediaEntity>> getMovieTrending(){
        return repository.getMovieTrending(page);
    }

    public LiveData<List<MediaEntity>> getTVTrending(){
        return repository.getTVTrending(page);
    }

    public LiveData<List<MediaEntity>> getMovieLatestRelease(){
        return repository.getMovieLatestRelease(page);
    }

    public LiveData<List<MediaEntity>> getTVLatestRelease(){
        return repository.getTVLatestRelease(page);
    }

    public LiveData<List<MediaEntity>> getMovieNowPlaying(){
        return repository.getMovieNowPlaying(page);
    }

    public LiveData<List<MediaEntity>> getTVOnTheAir(){
        return repository.getTVOnTheAir(page);
    }

    public LiveData<List<MediaEntity>> getMovieUpcoming(){
        return repository.getMovieUpcoming(page);
    }

    public LiveData<List<MediaEntity>> getMovieTopRated(){
        return repository.getMovieTopRated(page);
    }

    public LiveData<List<MediaEntity>> getTVTopRated(){
        return repository.getTVTopRated(page);
    }

    public LiveData<List<MediaEntity>> getMoviePopular(){
        return repository.getMoviePopular(page);
    }

    public LiveData<List<MediaEntity>> getTVPopular(){
        return repository.getTVPopular(page);
    }

    public LiveData<List<MediaEntity>> getMovieRecommendations(int movieId){
        return repository.getMovieRecommendations(movieId, page);
    }

    public LiveData<List<MediaEntity>> getTVRecommendations(int tvId){
        return repository.getTVRecommendations(tvId, page);
    }
}
