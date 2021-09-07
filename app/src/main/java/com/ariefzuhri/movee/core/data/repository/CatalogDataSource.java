package com.ariefzuhri.movee.core.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import com.ariefzuhri.movee.core.data.source.local.entity.FavoriteEntity;
import com.ariefzuhri.movee.core.data.source.local.entity.FavoriteWithGenres;
import com.ariefzuhri.movee.core.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.core.data.source.remote.entity.CreditsEntity;
import com.ariefzuhri.movee.core.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.movee.core.data.source.remote.entity.TrailerEntity;
import com.ariefzuhri.movee.core.utils.FilterFavorite;

import java.util.List;

public interface CatalogDataSource {

    MutableLiveData<Resource<List<MediaEntity>>> getMultiSearch(String query, int page);

    MutableLiveData<Resource<MediaEntity>> getMovieDetails(int movieId);

    MutableLiveData<Resource<MediaEntity>> getTVDetails(int tvId);

    MutableLiveData<Resource<List<MediaEntity>>> getMovieTrending(int page);

    MutableLiveData<Resource<List<MediaEntity>>> getTVTrending(int page);

    MutableLiveData<Resource<List<MediaEntity>>> getMovieLatestRelease(int page);

    MutableLiveData<Resource<List<MediaEntity>>> getTVLatestRelease(int page);

    MutableLiveData<Resource<List<MediaEntity>>> getMovieNowPlaying(int page);

    MutableLiveData<Resource<List<MediaEntity>>> getTVOnTheAir(int page);

    MutableLiveData<Resource<List<MediaEntity>>> getMovieUpcoming(int page);

    MutableLiveData<Resource<List<MediaEntity>>> getMovieTopRated(int page);

    MutableLiveData<Resource<List<MediaEntity>>> getTVTopRated(int page);

    MutableLiveData<Resource<List<MediaEntity>>> getMoviePopular(int page);

    MutableLiveData<Resource<List<MediaEntity>>> getTVPopular(int page);

    MutableLiveData<Resource<List<MediaEntity>>> getMovieRecommendations(int movieId, int page);

    MutableLiveData<Resource<List<MediaEntity>>> getTVRecommendations(int tvId, int page);

    MutableLiveData<Resource<List<TrailerEntity>>> getVideos(String mediaType, int mediaId);

    MutableLiveData<Resource<CreditsEntity>> getCredits(String mediaType, int mediaId);

    LiveData<PagedList<FavoriteWithGenres>> getFavorites(FilterFavorite filter);

    LiveData<FavoriteWithGenres> getFavorite(int id, String type);

    void setFavorite(FavoriteEntity favorite, boolean state);

    void updateFavorite(FavoriteEntity favorite);

    LiveData<Resource<List<GenreEntity>>> getGenres();
}
