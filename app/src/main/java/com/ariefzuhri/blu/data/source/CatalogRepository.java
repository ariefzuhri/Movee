package com.ariefzuhri.blu.data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ariefzuhri.blu.data.CreditEntity;
import com.ariefzuhri.blu.data.GenreEntity;
import com.ariefzuhri.blu.data.MediaEntity;
import com.ariefzuhri.blu.data.TrailerEntity;
import com.ariefzuhri.blu.data.source.remote.RemoteDataSource;

import java.util.List;

import static com.ariefzuhri.blu.data.source.CatalogRepositoryHelper.creditsResponseToCredit;
import static com.ariefzuhri.blu.data.source.CatalogRepositoryHelper.genreResponseToGenreList;
import static com.ariefzuhri.blu.data.source.CatalogRepositoryHelper.movieDetailsResponseToMedia;
import static com.ariefzuhri.blu.data.source.CatalogRepositoryHelper.movieResponseToMediaList;
import static com.ariefzuhri.blu.data.source.CatalogRepositoryHelper.multiSearchResponseToMediaList;
import static com.ariefzuhri.blu.data.source.CatalogRepositoryHelper.tvDetailsResponseToMedia;
import static com.ariefzuhri.blu.data.source.CatalogRepositoryHelper.tvResponsesToMediaList;
import static com.ariefzuhri.blu.data.source.CatalogRepositoryHelper.videosResponseToTrailerList;

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
    public LiveData<List<MediaEntity>> getMultiSearch(String query, int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getMultiSearch(query, page, response -> {
            List<MediaEntity> mediaList = multiSearchResponseToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public LiveData<MediaEntity> getMovieDetails(int movieId) {
        MutableLiveData<MediaEntity> result = new MutableLiveData<>();
        remoteDataSource.getMovieDetails(movieId, response -> {
            MediaEntity media = movieDetailsResponseToMedia(response);
            result.postValue(media);
        });
        return result;
    }

    @Override
    public LiveData<MediaEntity> getTVDetails(int tvId) {
        MutableLiveData<MediaEntity> result = new MutableLiveData<>();
        remoteDataSource.getTVDetails(tvId, response -> {
            MediaEntity media = tvDetailsResponseToMedia(response);
            result.postValue(media);
        });
        return result;
    }

    @Override
    public LiveData<List<MediaEntity>> getMovieTrending(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getMovieTrending(page, response -> {
            List<MediaEntity> mediaList = movieResponseToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public LiveData<List<MediaEntity>> getTVTrending(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getTVTrending(page, response -> {
            List<MediaEntity> mediaList = tvResponsesToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public LiveData<List<MediaEntity>> getMovieLatestRelease(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getMovieLatestRelease(page, response -> {
            List<MediaEntity> mediaList = movieResponseToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public LiveData<List<MediaEntity>> getTVLatestRelease(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getTVLatestRelease(page, response -> {
            List<MediaEntity> mediaList = tvResponsesToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public LiveData<List<MediaEntity>> getMovieNowPlaying(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getMovieNowPlaying(page, response -> {
            List<MediaEntity> mediaList = movieResponseToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public LiveData<List<MediaEntity>> getTVOnTheAir(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getTVOnTheAir(page, response -> {
            List<MediaEntity> mediaList = tvResponsesToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public LiveData<List<MediaEntity>> getMovieUpcoming(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getMovieUpcoming(page, response -> {
            List<MediaEntity> mediaList = movieResponseToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public LiveData<List<MediaEntity>> getMovieTopRated(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getMovieTopRated(page, response -> {
            List<MediaEntity> mediaList = movieResponseToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public LiveData<List<MediaEntity>> getTVTopRated(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getTVTopRated(page, response -> {
            List<MediaEntity> mediaList = tvResponsesToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public LiveData<List<MediaEntity>> getMoviePopular(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getMoviePopular(page, response -> {
            List<MediaEntity> mediaList = movieResponseToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public LiveData<List<MediaEntity>> getTVPopular(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getTVPopular(page, response -> {
            List<MediaEntity> mediaList = tvResponsesToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public LiveData<List<MediaEntity>> getMovieRecommendations(int movieId, int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getMovieRecommendations(movieId, page, response -> {
            List<MediaEntity> mediaList = movieResponseToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public LiveData<List<MediaEntity>> getTVRecommendations(int tvId, int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getTVRecommendations(tvId, page, response -> {
            List<MediaEntity> mediaList = tvResponsesToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public LiveData<List<GenreEntity>> getGenres(String mediaType) {
        MutableLiveData<List<GenreEntity>> result = new MutableLiveData<>();
        remoteDataSource.getGenres(mediaType, response -> {
            List<GenreEntity> genreList = genreResponseToGenreList(response);
            result.postValue(genreList);
        });
        return result;
    }

    @Override
    public LiveData<List<TrailerEntity>> getVideos(String mediaType, int mediaId) {
        MutableLiveData<List<TrailerEntity>> result = new MutableLiveData<>();
        remoteDataSource.getVideos(mediaType, mediaId, response -> {
            List<TrailerEntity> trailerList = videosResponseToTrailerList(response);
            result.postValue(trailerList);
        });
        return result;
    }

    @Override
    public LiveData<CreditEntity> getCredits(String mediaType, int mediaId) {
        MutableLiveData<CreditEntity> result = new MutableLiveData<>();
        remoteDataSource.getCredits(mediaType, mediaId, response -> {
            CreditEntity credit = creditsResponseToCredit(response);
            result.postValue(credit);
        });
        return result;
    }
}
