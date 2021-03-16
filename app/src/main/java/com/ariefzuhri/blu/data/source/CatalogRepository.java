package com.ariefzuhri.blu.data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ariefzuhri.blu.data.source.remote.RemoteDataSource;
import com.ariefzuhri.blu.data.source.remote.response.CreditsResponse;
import com.ariefzuhri.blu.data.source.remote.response.GenresResponse;
import com.ariefzuhri.blu.data.source.remote.response.MovieDetailsResponse;
import com.ariefzuhri.blu.data.source.remote.response.MovieResponse;
import com.ariefzuhri.blu.data.source.remote.response.MultiSearchResponse;
import com.ariefzuhri.blu.data.source.remote.response.TVDetailsResponse;
import com.ariefzuhri.blu.data.source.remote.response.TVResponse;
import com.ariefzuhri.blu.data.source.remote.response.VideosResponse;

public class CatalogRepository implements CatalogDataSource {

    private volatile static CatalogRepository INSTANCE = null;

    private final RemoteDataSource remoteDataSource;

    private CatalogRepository (@NonNull RemoteDataSource remoteDataSource){
        this.remoteDataSource = remoteDataSource;
    }

    public static CatalogRepository getInstance(RemoteDataSource remoteDataSource){
        if (INSTANCE == null){
            synchronized (CatalogRepository.class){
                if (INSTANCE == null){
                    INSTANCE = new CatalogRepository(remoteDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<MultiSearchResponse> getMultiSearch(String query, int page) {
        MutableLiveData<MultiSearchResponse> result = new MutableLiveData<>();
        remoteDataSource.getMultiSearch(query, page, result::postValue);
        return result;
    }

    @Override
    public LiveData<MovieDetailsResponse> getMovieDetails(int movieId) {
        MutableLiveData<MovieDetailsResponse> result = new MutableLiveData<>();
        remoteDataSource.getMovieDetails(movieId, result::postValue);
        return result;
    }

    @Override
    public LiveData<TVDetailsResponse> getTVDetails(int tvId) {
        MutableLiveData<TVDetailsResponse> result = new MutableLiveData<>();
        remoteDataSource.getTVDetails(tvId, result::postValue);
        return result;
    }

    @Override
    public LiveData<MovieResponse> getMovieTrending(int page) {
        MutableLiveData<MovieResponse> result = new MutableLiveData<>();
        remoteDataSource.getMovieTrending(page, result::postValue);
        return result;
    }

    @Override
    public LiveData<TVResponse> getTVTrending(int page) {
        MutableLiveData<TVResponse> result = new MutableLiveData<>();
        remoteDataSource.getTVTrending(page, result::postValue);
        return result;
    }

    @Override
    public LiveData<MovieResponse> getMovieLatestRelease(int page) {
        MutableLiveData<MovieResponse> result = new MutableLiveData<>();
        remoteDataSource.getMovieLatestRelease(page, result::postValue);
        return result;
    }

    @Override
    public LiveData<TVResponse> getTVLatestRelease(int page) {
        MutableLiveData<TVResponse> result = new MutableLiveData<>();
        remoteDataSource.getTVLatestRelease(page, result::postValue);
        return result;
    }

    @Override
    public LiveData<MovieResponse> getMovieNowPlaying(int page) {
        MutableLiveData<MovieResponse> result = new MutableLiveData<>();
        remoteDataSource.getMovieNowPlaying(page, result::postValue);
        return result;
    }

    @Override
    public LiveData<TVResponse> getTVOnTheAir(int page) {
        MutableLiveData<TVResponse> result = new MutableLiveData<>();
        remoteDataSource.getTVOnTheAir(page, result::postValue);
        return result;
    }

    @Override
    public LiveData<MovieResponse> getMovieUpcoming(int page) {
        MutableLiveData<MovieResponse> result = new MutableLiveData<>();
        remoteDataSource.getMovieUpcoming(page, result::postValue);
        return result;
    }

    @Override
    public LiveData<MovieResponse> getMovieTopRated(int page) {
        MutableLiveData<MovieResponse> result = new MutableLiveData<>();
        remoteDataSource.getMovieTopRated(page, result::postValue);
        return result;
    }

    @Override
    public LiveData<TVResponse> getTVTopRated(int page) {
        MutableLiveData<TVResponse> result = new MutableLiveData<>();
        remoteDataSource.getTVTopRated(page, result::postValue);
        return result;
    }

    @Override
    public LiveData<MovieResponse> getMoviePopular(int page) {
        MutableLiveData<MovieResponse> result = new MutableLiveData<>();
        remoteDataSource.getMoviePopular(page, result::postValue);
        return result;
    }

    @Override
    public LiveData<TVResponse> getTVPopular(int page) {
        MutableLiveData<TVResponse> result = new MutableLiveData<>();
        remoteDataSource.getTVPopular(page, result::postValue);
        return result;
    }

    @Override
    public LiveData<MovieResponse> getMovieRecommendations(int movieId, int page) {
        MutableLiveData<MovieResponse> result = new MutableLiveData<>();
        remoteDataSource.getMovieRecommendations(movieId, page, result::postValue);
        return result;
    }

    @Override
    public LiveData<TVResponse> getTVRecommendations(int tvId, int page) {
        MutableLiveData<TVResponse> result = new MutableLiveData<>();
        remoteDataSource.getTVRecommendations(tvId, page, result::postValue);
        return result;
    }

    @Override
    public LiveData<GenresResponse> getGenres(String mediaType) {
        MutableLiveData<GenresResponse> result = new MutableLiveData<>();
        remoteDataSource.getGenres(mediaType, result::postValue);
        return result;
    }

    @Override
    public LiveData<VideosResponse> getVideos(String mediaType, int mediaId) {
        MutableLiveData<VideosResponse> result = new MutableLiveData<>();
        remoteDataSource.getVideos(mediaType, mediaId, result::postValue);
        return result;
    }

    @Override
    public LiveData<CreditsResponse> getCredits(String mediaType, int mediaId) {
        MutableLiveData<CreditsResponse> result = new MutableLiveData<>();
        remoteDataSource.getCredits(mediaType, mediaId, result::postValue);
        return result;
    }
}
