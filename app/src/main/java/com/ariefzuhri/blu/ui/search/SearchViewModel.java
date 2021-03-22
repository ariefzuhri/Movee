package com.ariefzuhri.blu.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ariefzuhri.blu.data.source.CatalogRepository;
import com.ariefzuhri.blu.data.source.remote.response.GenresResponse;
import com.ariefzuhri.blu.data.source.remote.response.MovieResponse;
import com.ariefzuhri.blu.data.source.remote.response.MultiSearchResponse;
import com.ariefzuhri.blu.data.source.remote.response.TVResponse;

public class SearchViewModel extends ViewModel {

    private final CatalogRepository repository;

    private int page;

    public SearchViewModel(CatalogRepository repository){
        this.repository = repository;
    }

    public void setPage(int page){
        this.page = page;
    }

    public LiveData<MultiSearchResponse> getMultiSearch(String query){
        return repository.getMultiSearch(query, page);
    }

    public LiveData<GenresResponse> getGenres(String mediaType) {
        return repository.getGenres(mediaType);
    }

    public LiveData<MovieResponse> getMovieTrending(){
        return repository.getMovieTrending(page);
    }

    public LiveData<TVResponse> getTVTrending(){
        return repository.getTVTrending(page);
    }

    public LiveData<MovieResponse> getMovieLatestRelease(){
        return repository.getMovieLatestRelease(page);
    }

    public LiveData<TVResponse> getTVLatestRelease(){
        return repository.getTVLatestRelease(page);
    }

    public LiveData<MovieResponse> getMovieNowPlaying(){
        return repository.getMovieNowPlaying(page);
    }

    public LiveData<TVResponse> getTVOnTheAir(){
        return repository.getTVOnTheAir(page);
    }

    public LiveData<MovieResponse> getMovieUpcoming(){
        return repository.getMovieUpcoming(page);
    }

    public LiveData<MovieResponse> getMovieTopRated(){
        return repository.getMovieTopRated(page);
    }

    public LiveData<TVResponse> getTVTopRated(){
        return repository.getTVTopRated(page);
    }

    public LiveData<MovieResponse> getMoviePopular(){
        return repository.getMoviePopular(page);
    }

    public LiveData<TVResponse> getTVPopular(){
        return repository.getTVPopular(page);
    }

    public LiveData<MovieResponse> getMovieRecommendations(int movieId){
        return repository.getMovieRecommendations(movieId, page);
    }

    public LiveData<TVResponse> getTVRecommendations(int tvId){
        return repository.getTVRecommendations(tvId, page);
    }
}
