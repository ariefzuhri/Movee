package com.ariefzuhri.blu.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ariefzuhri.blu.data.source.local.entity.FavoriteEntity;
import com.ariefzuhri.blu.data.source.local.entity.FavoriteWithGenres;
import com.ariefzuhri.blu.data.source.remote.entity.CreditsEntity;
import com.ariefzuhri.blu.data.source.local.entity.GenreEntity;
import com.ariefzuhri.blu.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.blu.data.source.remote.entity.TrailerEntity;
import com.ariefzuhri.blu.data.CatalogRepository;
import com.ariefzuhri.blu.vo.Resource;

import java.util.List;

import static com.ariefzuhri.blu.utils.Constants.MEDIA_TYPE_MOVIE;
import static com.ariefzuhri.blu.utils.Constants.MEDIA_TYPE_TV;

public class DetailMediaViewModel extends ViewModel {

    private final CatalogRepository repository;

    private String mediaType;
    private int mediaId;

    public DetailMediaViewModel(CatalogRepository repository){
        this.repository = repository;
    }

    public void setMedia(String mediaType, int mediaId) {
        this.mediaType = mediaType;
        this.mediaId = mediaId;
    }

    private MutableLiveData<MediaEntity> mediaDetails;
    private MutableLiveData<List<TrailerEntity>> trailers;
    private MutableLiveData<CreditsEntity> credits;
    private MutableLiveData<List<MediaEntity>> recommendations;
    private LiveData<Resource<List<GenreEntity>>> genres;
    private LiveData<FavoriteWithGenres> favoriteWithGenres;

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
                recommendations = repository.getMovieRecommendations(mediaId, 1);
            } else if (mediaType.equals(MEDIA_TYPE_TV)){
                recommendations = repository.getTVRecommendations(mediaId, 1);
            }
        }
        return recommendations;
    }

    public LiveData<Resource<List<GenreEntity>>> getGenres() {
        if (genres == null) genres = repository.getGenres();
        return genres;
    }

    public void insertFavorite(FavoriteEntity favorite){
        repository.insertFavorite(favorite);
    }

    public void updateFavorite(FavoriteEntity favorite){
        repository.updateFavorite(favorite);
    }

    public void deleteFavorite(FavoriteEntity favorite) {
        repository.deleteFavorite(favorite);
    }

    public LiveData<FavoriteWithGenres> getFavoriteWithGenres(){
        if (favoriteWithGenres == null) favoriteWithGenres =
                repository.getFavoriteWithGenresById(mediaId, mediaType);
        return favoriteWithGenres;
    }
}
