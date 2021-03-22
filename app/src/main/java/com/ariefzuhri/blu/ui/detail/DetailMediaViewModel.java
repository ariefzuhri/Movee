package com.ariefzuhri.blu.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ariefzuhri.blu.data.source.CatalogRepository;
import com.ariefzuhri.blu.data.source.remote.response.CreditsResponse;
import com.ariefzuhri.blu.data.source.remote.response.GenresResponse;
import com.ariefzuhri.blu.data.source.remote.response.MovieDetailsResponse;
import com.ariefzuhri.blu.data.source.remote.response.MovieResponse;
import com.ariefzuhri.blu.data.source.remote.response.TVDetailsResponse;
import com.ariefzuhri.blu.data.source.remote.response.TVResponse;
import com.ariefzuhri.blu.data.source.remote.response.VideosResponse;

public class DetailMediaViewModel extends ViewModel {
    private final CatalogRepository repository;

    private String mediaType;
    private int mediaId;

    public DetailMediaViewModel(CatalogRepository repository){
        this.repository = repository;
    }

    public void setSelectedMedia(String mediaType, int mediaId){
        this.mediaType = mediaType;
        this.mediaId = mediaId;
    }

    public LiveData<MovieDetailsResponse> getMovieDetails(){
        return repository.getMovieDetails(mediaId);
    }

    public LiveData<TVDetailsResponse> getTVDetails(){
        return repository.getTVDetails(mediaId);
    }

    public LiveData<VideosResponse> getVideos(){
        return repository.getVideos(mediaType, mediaId);
    }

    public LiveData<CreditsResponse> getCredits(){
        return repository.getCredits(mediaType, mediaId);
    }

    public LiveData<MovieResponse> getMovieRecommendations(){
        return repository.getMovieRecommendations(mediaId, 1);
    }

    public LiveData<TVResponse> getTVRecommendations(){
        return repository.getTVRecommendations(mediaId, 1);
    }

    public LiveData<GenresResponse> getGenres() {
        return repository.getGenres(mediaType);
    }
}
