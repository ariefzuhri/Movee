package com.ariefzuhri.movee.detail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ariefzuhri.movee.core.data.source.local.entity.FavoriteEntity;
import com.ariefzuhri.movee.core.data.source.local.entity.FavoriteWithGenres;
import com.ariefzuhri.movee.core.data.source.remote.entity.CreditsEntity;
import com.ariefzuhri.movee.core.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.core.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.movee.core.data.source.remote.entity.TrailerEntity;
import com.ariefzuhri.movee.core.data.repository.CatalogRepository;
import com.ariefzuhri.movee.core.data.repository.Resource;

import java.util.List;

import static com.ariefzuhri.movee.core.utils.AppUtils.equalsFavoriteObjects;
import static com.ariefzuhri.movee.core.utils.Constants.MEDIA_TYPE_MOVIE;
import static com.ariefzuhri.movee.core.utils.Constants.MEDIA_TYPE_TV;

public class DetailMediaViewModel extends ViewModel {

    private final String TAG = getClass().getSimpleName();

    private final CatalogRepository repository;

    private String mediaType;
    private int mediaId;

    public DetailMediaViewModel(CatalogRepository repository) {
        this.repository = repository;
    }

    public void setMedia(String mediaType, int mediaId) {
        this.mediaType = mediaType;
        this.mediaId = mediaId;
    }

    private MutableLiveData<Resource<MediaEntity>> mediaDetails;
    private MutableLiveData<Resource<List<TrailerEntity>>> trailers;
    private MutableLiveData<Resource<CreditsEntity>> credits;
    private MutableLiveData<Resource<List<MediaEntity>>> recommendations;
    private LiveData<Resource<List<GenreEntity>>> genres;
    private LiveData<FavoriteWithGenres> favorite;

    public LiveData<Resource<MediaEntity>> getMediaDetails() {
        if (mediaDetails == null) {
            if (mediaType.equals(MEDIA_TYPE_MOVIE)) {
                mediaDetails = repository.getMovieDetails(mediaId);
            } else if (mediaType.equals(MEDIA_TYPE_TV)) {
                mediaDetails = repository.getTVDetails(mediaId);
            }
        }
        return mediaDetails;
    }

    public LiveData<Resource<List<TrailerEntity>>> getTrailers() {
        if (trailers == null) trailers = repository.getVideos(mediaType, mediaId);
        return trailers;
    }

    public LiveData<Resource<CreditsEntity>> getCredits() {
        if (credits == null) credits = repository.getCredits(mediaType, mediaId);
        return credits;
    }

    public LiveData<Resource<List<MediaEntity>>> getRecommendations() {
        if (recommendations == null) {
            if (mediaType.equals(MEDIA_TYPE_MOVIE)) {
                recommendations = repository.getMovieRecommendations(mediaId, 1);
            } else if (mediaType.equals(MEDIA_TYPE_TV)) {
                recommendations = repository.getTVRecommendations(mediaId, 1);
            }
        }
        return recommendations;
    }

    public LiveData<Resource<List<GenreEntity>>> getGenres() {
        if (genres == null) genres = repository.getGenres();
        return genres;
    }

    public void updateFavorite(FavoriteWithGenres favoriteWithGenresInDb, final FavoriteEntity updatedFavorite) {
        boolean state = favoriteWithGenresInDb != null;
        if (state) {
            FavoriteEntity favoriteInDb = favoriteWithGenresInDb.favorite;
            favoriteInDb.setGenres(favoriteWithGenresInDb.genres);

            // Update favorit di database jika ada satu nilai atribut yang tidak sama
            boolean equalsObjects = equalsFavoriteObjects(favoriteInDb, updatedFavorite);
            Log.d(TAG, "equalsFavoriteObjects: " + equalsObjects);
            if (!equalsObjects) repository.updateFavorite(updatedFavorite);
            else Log.d(TAG, "favorite in db not need updated");
        }
    }

    public void setFavorite(FavoriteEntity favorite, boolean currentState) {
        boolean newSate = !currentState;
        repository.setFavorite(favorite, newSate);
    }

    public void setFavoriteLiveData(LiveData<FavoriteWithGenres> favorite) {
        this.favorite = favorite;
    }

    public LiveData<FavoriteWithGenres> getFavorite() {
        if (favorite == null) favorite =
                repository.getFavorite(mediaId, mediaType);
        return favorite;
    }
}
