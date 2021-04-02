package com.ariefzuhri.blu.data.source;

import androidx.lifecycle.LiveData;

import com.ariefzuhri.blu.data.CreditsEntity;
import com.ariefzuhri.blu.data.GenreEntity;
import com.ariefzuhri.blu.data.MediaEntity;
import com.ariefzuhri.blu.data.TrailerEntity;

import java.util.List;

public interface CatalogDataSource {
    LiveData<List<MediaEntity>> getMultiSearch(String query, int page);

    LiveData<MediaEntity> getMovieDetails(int movieId);

    LiveData<MediaEntity> getTVDetails(int tvId);

    LiveData<List<MediaEntity>> getMovieTrending(int page);

    LiveData<List<MediaEntity>> getTVTrending(int page);

    LiveData<List<MediaEntity>> getMovieLatestRelease(int page);

    LiveData<List<MediaEntity>> getTVLatestRelease(int page);

    LiveData<List<MediaEntity>> getMovieNowPlaying(int page);

    LiveData<List<MediaEntity>> getTVOnTheAir(int page);

    LiveData<List<MediaEntity>> getMovieUpcoming(int page);

    LiveData<List<MediaEntity>> getMovieTopRated(int page);

    LiveData<List<MediaEntity>> getTVTopRated(int page);

    LiveData<List<MediaEntity>> getMoviePopular(int page);

    LiveData<List<MediaEntity>> getTVPopular(int page);

    LiveData<List<MediaEntity>> getMovieRecommendations(int movieId, int page);

    LiveData<List<MediaEntity>> getTVRecommendations(int tvId, int page);

    LiveData<List<GenreEntity>> getGenres(String mediaType);

    LiveData<List<TrailerEntity>> getVideos(String mediaType, int mediaId);

    LiveData<CreditsEntity> getCredits(String mediaType, int mediaId);
}
