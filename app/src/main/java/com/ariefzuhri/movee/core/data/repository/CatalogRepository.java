package com.ariefzuhri.movee.core.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.ariefzuhri.movee.core.data.source.local.entity.FavoriteEntity;
import com.ariefzuhri.movee.core.data.source.local.entity.FavoriteWithGenres;
import com.ariefzuhri.movee.core.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.core.data.source.remote.RemoteDataSource;
import com.ariefzuhri.movee.core.data.source.local.LocalDataSource;
import com.ariefzuhri.movee.core.data.source.remote.network.ApiResponse;
import com.ariefzuhri.movee.core.data.source.remote.entity.CreditsEntity;
import com.ariefzuhri.movee.core.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.movee.core.data.source.remote.entity.TrailerEntity;
import com.ariefzuhri.movee.core.data.source.remote.response.GenresResponse;
import com.ariefzuhri.movee.core.utils.AppExecutors;
import com.ariefzuhri.movee.core.utils.FilterFavorite;

import java.util.List;

import static com.ariefzuhri.movee.core.utils.DataMapper.creditsResponseToCredit;
import static com.ariefzuhri.movee.core.utils.DataMapper.genreResponseToGenreList;
import static com.ariefzuhri.movee.core.utils.DataMapper.movieDetailsResponseToMedia;
import static com.ariefzuhri.movee.core.utils.DataMapper.movieResponseToMediaList;
import static com.ariefzuhri.movee.core.utils.DataMapper.multiSearchResponseToMediaList;
import static com.ariefzuhri.movee.core.utils.DataMapper.tvDetailsResponseToMedia;
import static com.ariefzuhri.movee.core.utils.DataMapper.tvResponsesToMediaList;
import static com.ariefzuhri.movee.core.utils.DataMapper.videosResponseToTrailerList;

public class CatalogRepository implements CatalogDataSource {

    private volatile static CatalogRepository INSTANCE = null;

    private final AppExecutors appExecutors;
    private final LocalDataSource localDataSource;
    private final RemoteDataSource remoteDataSource;

    private CatalogRepository(@NonNull RemoteDataSource remoteDataSource, LocalDataSource localDataSource, AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static CatalogRepository getInstance(RemoteDataSource remoteDataSource, LocalDataSource localDataSource, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (CatalogRepository.class) {
                INSTANCE = new CatalogRepository(remoteDataSource, localDataSource, appExecutors);
            }
        }
        return INSTANCE;
    }

    @Override
    public MutableLiveData<Resource<List<MediaEntity>>> getMultiSearch(String query, int page) {
        MutableLiveData<Resource<List<MediaEntity>>> result = new MutableLiveData<>();
        remoteDataSource.getMultiSearch(query, page, response -> {
            if (response.body != null) {
                List<MediaEntity> mediaList = multiSearchResponseToMediaList(response.body);
                result.postValue(Resource.success(mediaList));
            }
        });
        return result;
    }

    @Override
    public MutableLiveData<Resource<MediaEntity>> getMovieDetails(int movieId) {
        MutableLiveData<Resource<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getMovieDetails(movieId, response -> {
            if (response.body != null) {
                MediaEntity media = movieDetailsResponseToMedia(response.body);
                result.postValue(Resource.success(media));
            }
        });
        return result;
    }

    @Override
    public MutableLiveData<Resource<MediaEntity>> getTVDetails(int tvId) {
        MutableLiveData<Resource<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getTVDetails(tvId, response -> {
            if (response.body != null) {
                MediaEntity media = tvDetailsResponseToMedia(response.body);
                result.postValue(Resource.success(media));
            }
        });
        return result;
    }

    @Override
    public MutableLiveData<Resource<List<MediaEntity>>> getMovieTrending(int page) {
        MutableLiveData<Resource<List<MediaEntity>>> result = new MutableLiveData<>();
        remoteDataSource.getMovieTrending(page, response -> {
            if (response.body != null) {
                List<MediaEntity> mediaList = movieResponseToMediaList(response.body);
                result.postValue(Resource.success(mediaList));
            }
        });
        return result;
    }

    @Override
    public MutableLiveData<Resource<List<MediaEntity>>> getTVTrending(int page) {
        MutableLiveData<Resource<List<MediaEntity>>> result = new MutableLiveData<>();
        remoteDataSource.getTVTrending(page, response -> {
            if (response.body != null) {
                List<MediaEntity> mediaList = tvResponsesToMediaList(response.body);
                result.postValue(Resource.success(mediaList));
            }
        });
        return result;
    }

    @Override
    public MutableLiveData<Resource<List<MediaEntity>>> getMovieLatestRelease(int page) {
        MutableLiveData<Resource<List<MediaEntity>>> result = new MutableLiveData<>();
        remoteDataSource.getMovieLatestRelease(page, response -> {
            if (response.body != null) {
                List<MediaEntity> mediaList = movieResponseToMediaList(response.body);
                result.postValue(Resource.success(mediaList));
            }
        });
        return result;
    }

    @Override
    public MutableLiveData<Resource<List<MediaEntity>>> getTVLatestRelease(int page) {
        MutableLiveData<Resource<List<MediaEntity>>> result = new MutableLiveData<>();
        remoteDataSource.getTVLatestRelease(page, response -> {
            if (response.body != null) {
                List<MediaEntity> mediaList = tvResponsesToMediaList(response.body);
                result.postValue(Resource.success(mediaList));
            }
        });
        return result;
    }

    @Override
    public MutableLiveData<Resource<List<MediaEntity>>> getMovieNowPlaying(int page) {
        MutableLiveData<Resource<List<MediaEntity>>> result = new MutableLiveData<>();
        remoteDataSource.getMovieNowPlaying(page, response -> {
            if (response.body != null) {
                List<MediaEntity> mediaList = movieResponseToMediaList(response.body);
                result.postValue(Resource.success(mediaList));
            }
        });
        return result;
    }

    @Override
    public MutableLiveData<Resource<List<MediaEntity>>> getTVOnTheAir(int page) {
        MutableLiveData<Resource<List<MediaEntity>>> result = new MutableLiveData<>();
        remoteDataSource.getTVOnTheAir(page, response -> {
            if (response.body != null) {
                List<MediaEntity> mediaList = tvResponsesToMediaList(response.body);
                result.postValue(Resource.success(mediaList));
            }
        });
        return result;
    }

    @Override
    public MutableLiveData<Resource<List<MediaEntity>>> getMovieUpcoming(int page) {
        MutableLiveData<Resource<List<MediaEntity>>> result = new MutableLiveData<>();
        remoteDataSource.getMovieUpcoming(page, response -> {
            if (response.body != null) {
                List<MediaEntity> mediaList = movieResponseToMediaList(response.body);
                result.postValue(Resource.success(mediaList));
            }
        });
        return result;
    }

    @Override
    public MutableLiveData<Resource<List<MediaEntity>>> getMovieTopRated(int page) {
        MutableLiveData<Resource<List<MediaEntity>>> result = new MutableLiveData<>();
        remoteDataSource.getMovieTopRated(page, response -> {
            if (response.body != null) {
                List<MediaEntity> mediaList = movieResponseToMediaList(response.body);
                result.postValue(Resource.success(mediaList));
            }
        });
        return result;
    }

    @Override
    public MutableLiveData<Resource<List<MediaEntity>>> getTVTopRated(int page) {
        MutableLiveData<Resource<List<MediaEntity>>> result = new MutableLiveData<>();
        remoteDataSource.getTVTopRated(page, response -> {
            if (response.body != null) {
                List<MediaEntity> mediaList = tvResponsesToMediaList(response.body);
                result.postValue(Resource.success(mediaList));
            }
        });
        return result;
    }

    @Override
    public MutableLiveData<Resource<List<MediaEntity>>> getMoviePopular(int page) {
        MutableLiveData<Resource<List<MediaEntity>>> result = new MutableLiveData<>();
        remoteDataSource.getMoviePopular(page, response -> {
            if (response.body != null) {
                List<MediaEntity> mediaList = movieResponseToMediaList(response.body);
                result.postValue(Resource.success(mediaList));
            }
        });
        return result;
    }

    @Override
    public MutableLiveData<Resource<List<MediaEntity>>> getTVPopular(int page) {
        MutableLiveData<Resource<List<MediaEntity>>> result = new MutableLiveData<>();
        remoteDataSource.getTVPopular(page, response -> {
            if (response.body != null) {
                List<MediaEntity> mediaList = tvResponsesToMediaList(response.body);
                result.postValue(Resource.success(mediaList));
            }
        });
        return result;
    }

    @Override
    public MutableLiveData<Resource<List<MediaEntity>>> getMovieRecommendations(int movieId, int page) {
        MutableLiveData<Resource<List<MediaEntity>>> result = new MutableLiveData<>();
        remoteDataSource.getMovieRecommendations(movieId, page, response -> {
            if (response.body != null) {
                List<MediaEntity> mediaList = movieResponseToMediaList(response.body);
                result.postValue(Resource.success(mediaList));
            }
        });
        return result;
    }

    @Override
    public MutableLiveData<Resource<List<MediaEntity>>> getTVRecommendations(int tvId, int page) {
        MutableLiveData<Resource<List<MediaEntity>>> result = new MutableLiveData<>();
        remoteDataSource.getTVRecommendations(tvId, page, response -> {
            if (response.body != null) {
                List<MediaEntity> mediaList = tvResponsesToMediaList(response.body);
                result.postValue(Resource.success(mediaList));
            }
        });
        return result;
    }

    @Override
    public MutableLiveData<Resource<List<TrailerEntity>>> getVideos(String mediaType, int mediaId) {
        MutableLiveData<Resource<List<TrailerEntity>>> result = new MutableLiveData<>();
        remoteDataSource.getVideos(mediaType, mediaId, response -> {
            if (response.body != null) {
                List<TrailerEntity> trailerList = videosResponseToTrailerList(response.body);
                result.postValue(Resource.success(trailerList));
            }
        });
        return result;
    }

    @Override
    public MutableLiveData<Resource<CreditsEntity>> getCredits(String mediaType, int mediaId) {
        MutableLiveData<Resource<CreditsEntity>> result = new MutableLiveData<>();
        remoteDataSource.getCredits(mediaType, mediaId, response -> {
            if (response.body != null) {
                CreditsEntity credit = creditsResponseToCredit(response.body);
                result.postValue(Resource.success(credit));
            }
        });
        return result;
    }

    @Override
    public LiveData<PagedList<FavoriteWithGenres>> getFavorites(FilterFavorite filter) {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build();
        return new LivePagedListBuilder<>(localDataSource.getAllFavoriteWithGenres(filter), config).build();
    }

    @Override
    public LiveData<FavoriteWithGenres> getFavorite(int id, String type) {
        return localDataSource.getFavoriteWithGenresById(id, type);
    }

    @Override
    public void setFavorite(FavoriteEntity favorite, boolean state) {
        appExecutors.diskIO().execute(() -> localDataSource.setFavorite(favorite, state));
    }

    @Override
    public void updateFavorite(FavoriteEntity favorite) {
        appExecutors.diskIO().execute(() -> localDataSource.updateFavorite(favorite));
    }

    @Override
    public LiveData<Resource<List<GenreEntity>>> getGenres() {
        return new NetworkBoundResource<List<GenreEntity>, GenresResponse>(appExecutors) {
            @Override
            protected LiveData<List<GenreEntity>> loadFromDB() {
                return localDataSource.getGenres();
            }

            @Override
            protected Boolean shouldFetch(List<GenreEntity> data) {
                return (data == null) || (data.size() < 27);
            }

            @Override
            protected LiveData<ApiResponse<GenresResponse>> createCall() {
                return remoteDataSource.getGenres();
            }

            @Override
            protected void saveCallResult(GenresResponse data) {
                List<GenreEntity> genreList = genreResponseToGenreList(data);
                localDataSource.insertGenres(genreList);
            }
        }.asLiveData();
    }
}
