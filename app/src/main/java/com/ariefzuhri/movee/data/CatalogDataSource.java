package com.ariefzuhri.movee.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import com.ariefzuhri.movee.data.source.local.entity.FavoriteEntity;
import com.ariefzuhri.movee.data.source.local.entity.FavoriteWithGenres;
import com.ariefzuhri.movee.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.data.source.remote.entity.CreditsEntity;
import com.ariefzuhri.movee.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.movee.data.source.remote.entity.TrailerEntity;
import com.ariefzuhri.movee.utils.FilterFavorite;
import com.ariefzuhri.movee.vo.Resource;

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

    MutableLiveData<List<TrailerEntity>> getVideos(String mediaType, int mediaId);

    MutableLiveData<CreditsEntity> getCredits(String mediaType, int mediaId);

    LiveData<PagedList<FavoriteWithGenres>> getAllFavoriteWithGenres(FilterFavorite filter);

    LiveData<FavoriteWithGenres> getFavoriteWithGenresById(int id, String type);

    void insertFavorite(FavoriteEntity favorite);

    void updateFavorite(FavoriteEntity favorite);

    void deleteFavorite(FavoriteEntity favorite);

    LiveData<Resource<List<GenreEntity>>> getGenres();
}
