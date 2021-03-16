package com.ariefzuhri.blu.data.source;

import androidx.lifecycle.LiveData;

import com.ariefzuhri.blu.data.source.remote.response.CreditsResponse;
import com.ariefzuhri.blu.data.source.remote.response.GenresResponse;
import com.ariefzuhri.blu.data.source.remote.response.MovieDetailsResponse;
import com.ariefzuhri.blu.data.source.remote.response.MovieResponse;
import com.ariefzuhri.blu.data.source.remote.response.MultiSearchResponse;
import com.ariefzuhri.blu.data.source.remote.response.TVDetailsResponse;
import com.ariefzuhri.blu.data.source.remote.response.TVResponse;
import com.ariefzuhri.blu.data.source.remote.response.VideosResponse;

public interface CatalogDataSource {
    LiveData<MultiSearchResponse> getMultiSearch(String query, int page);

    LiveData<MovieDetailsResponse> getMovieDetails(int movieId);

    LiveData<TVDetailsResponse> getTVDetails(int tvId);

    LiveData<MovieResponse> getMovieTrending(int page);

    LiveData<TVResponse> getTVTrending(int page);

    LiveData<MovieResponse> getMovieLatestRelease(int page);

    LiveData<TVResponse> getTVLatestRelease(int page);

    LiveData<MovieResponse> getMovieNowPlaying(int page);

    LiveData<TVResponse> getTVOnTheAir(int page);

    LiveData<MovieResponse> getMovieUpcoming(int page);

    LiveData<MovieResponse> getMovieTopRated(int page);

    LiveData<TVResponse> getTVTopRated(int page);

    LiveData<MovieResponse> getMoviePopular(int page);

    LiveData<TVResponse> getTVPopular(int page);

    LiveData<MovieResponse> getMovieRecommendations(int movieId, int page);

    LiveData<TVResponse> getTVRecommendations(int tvId, int page);

    LiveData<GenresResponse> getGenres(String mediaType);

    LiveData<VideosResponse> getVideos(String mediaType, int mediaId);

    LiveData<CreditsResponse> getCredits(String mediaType, int mediaId);
}
