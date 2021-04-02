package com.ariefzuhri.blu.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ariefzuhri.blu.data.CreditsEntity;
import com.ariefzuhri.blu.data.GenreEntity;
import com.ariefzuhri.blu.data.MediaEntity;
import com.ariefzuhri.blu.data.TrailerEntity;
import com.ariefzuhri.blu.data.source.CatalogRepository;

import java.util.List;

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

    public LiveData<MediaEntity> getMovieDetails(){
        return repository.getMovieDetails(mediaId);
    }

    public LiveData<MediaEntity> getTVDetails(){
        return repository.getTVDetails(mediaId);
    }

    public LiveData<List<TrailerEntity>> getVideos(){
        return repository.getVideos(mediaType, mediaId);
    }

    public LiveData<CreditsEntity> getCredits(){
        return repository.getCredits(mediaType, mediaId);
    }

    public LiveData<List<MediaEntity>> getMovieRecommendations(){
        return repository.getMovieRecommendations(mediaId, 1);
    }

    public LiveData<List<MediaEntity>> getTVRecommendations(){
        return repository.getTVRecommendations(mediaId, 1);
    }

    public LiveData<List<GenreEntity>> getGenres() {
        return repository.getGenres(mediaType);
    }
}
