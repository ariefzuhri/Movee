package com.ariefzuhri.blu.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ariefzuhri.blu.data.CreditsEntity;
import com.ariefzuhri.blu.data.GenreEntity;
import com.ariefzuhri.blu.data.MediaEntity;
import com.ariefzuhri.blu.data.TrailerEntity;
import com.ariefzuhri.blu.data.source.CatalogRepository;

import java.util.List;

import static com.ariefzuhri.blu.utils.Constants.MEDIA_TYPE_MOVIE;
import static com.ariefzuhri.blu.utils.Constants.MEDIA_TYPE_TV;

public class DetailMediaViewModel extends ViewModel {

    private final CatalogRepository repository;

    private int page;
    private String mediaType;
    private int mediaId;

    public DetailMediaViewModel(CatalogRepository repository){
        this.repository = repository;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    private MutableLiveData<MediaEntity> mediaDetails;
    private MutableLiveData<List<TrailerEntity>> trailers;
    private MutableLiveData<CreditsEntity> credits;
    private MutableLiveData<List<MediaEntity>> recommendations;
    private MutableLiveData<List<GenreEntity>> genres;

    public LiveData<MediaEntity> getMediaDetails(){
        if (mediaDetails == null) {
            if (mediaType.equals(MEDIA_TYPE_MOVIE)){
                mediaDetails = repository.getMovieDetails(mediaId);
            } else if (mediaType.equals(MEDIA_TYPE_TV)){
                mediaDetails = repository.getTVDetails(mediaId);
            }
        }
        return mediaDetails;
    }

    public LiveData<List<TrailerEntity>> getTrailers(){
        if (trailers == null) trailers = repository.getVideos(mediaType, mediaId);
        return trailers;
    }

    public LiveData<CreditsEntity> getCredits(){
        if (credits == null) credits = repository.getCredits(mediaType, mediaId);
        return credits;
    }

    public LiveData<List<MediaEntity>> getRecommendations(){
        if (recommendations == null) {
            if (mediaType.equals(MEDIA_TYPE_MOVIE)){
                recommendations = repository.getMovieRecommendations(mediaId, page);
            } else if (mediaType.equals(MEDIA_TYPE_TV)){
                recommendations = repository.getTVRecommendations(mediaId, page);
            }
        }
        return recommendations;
    }

    public LiveData<List<GenreEntity>> getGenres() {
        if (genres == null) genres = repository.getGenres(mediaType);
        return genres;
    }
}
