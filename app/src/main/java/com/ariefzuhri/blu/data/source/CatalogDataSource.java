package com.ariefzuhri.blu.data.source;

import androidx.lifecycle.MutableLiveData;

import com.ariefzuhri.blu.data.CreditsEntity;
import com.ariefzuhri.blu.data.GenreEntity;
import com.ariefzuhri.blu.data.MediaEntity;
import com.ariefzuhri.blu.data.TrailerEntity;

import java.util.List;

public interface CatalogDataSource {
    MutableLiveData<List<MediaEntity>> getMultiSearch(String query, int page);

    MutableLiveData<MediaEntity> getMovieDetails(int movieId);

    MutableLiveData<MediaEntity> getTVDetails(int tvId);

    MutableLiveData<List<MediaEntity>> getMovieTrending(int page);

    MutableLiveData<List<MediaEntity>> getTVTrending(int page);

    MutableLiveData<List<MediaEntity>> getMovieLatestRelease(int page);

    MutableLiveData<List<MediaEntity>> getTVLatestRelease(int page);

    MutableLiveData<List<MediaEntity>> getMovieNowPlaying(int page);

    MutableLiveData<List<MediaEntity>> getTVOnTheAir(int page);

    MutableLiveData<List<MediaEntity>> getMovieUpcoming(int page);

    MutableLiveData<List<MediaEntity>> getMovieTopRated(int page);

    MutableLiveData<List<MediaEntity>> getTVTopRated(int page);

    MutableLiveData<List<MediaEntity>> getMoviePopular(int page);

    MutableLiveData<List<MediaEntity>> getTVPopular(int page);

    MutableLiveData<List<MediaEntity>> getMovieRecommendations(int movieId, int page);

    MutableLiveData<List<MediaEntity>> getTVRecommendations(int tvId, int page);

    MutableLiveData<List<GenreEntity>> getGenres(String mediaType);

    MutableLiveData<List<TrailerEntity>> getVideos(String mediaType, int mediaId);

    MutableLiveData<CreditsEntity> getCredits(String mediaType, int mediaId);
}
