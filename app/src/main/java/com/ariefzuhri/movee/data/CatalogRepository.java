package com.ariefzuhri.movee.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.ariefzuhri.movee.data.source.local.LocalDataSource;
import com.ariefzuhri.movee.data.source.local.entity.FavoriteEntity;
import com.ariefzuhri.movee.data.source.local.entity.FavoriteWithGenres;
import com.ariefzuhri.movee.data.source.remote.ApiResponse;
import com.ariefzuhri.movee.data.source.remote.RemoteDataSource;
import com.ariefzuhri.movee.data.source.remote.entity.AiredDateEntity;
import com.ariefzuhri.movee.data.source.remote.entity.CastEntity;
import com.ariefzuhri.movee.data.source.remote.entity.CreditsEntity;
import com.ariefzuhri.movee.data.source.remote.entity.CrewEntity;
import com.ariefzuhri.movee.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.movee.data.source.remote.entity.StudioEntity;
import com.ariefzuhri.movee.data.source.remote.entity.TrailerEntity;
import com.ariefzuhri.movee.data.source.remote.response.CastItem;
import com.ariefzuhri.movee.data.source.remote.response.CreditsResponse;
import com.ariefzuhri.movee.data.source.remote.response.CrewItem;
import com.ariefzuhri.movee.data.source.remote.response.GenreItem;
import com.ariefzuhri.movee.data.source.remote.response.GenresResponse;
import com.ariefzuhri.movee.data.source.remote.response.MovieDetailsResponse;
import com.ariefzuhri.movee.data.source.remote.response.MovieItem;
import com.ariefzuhri.movee.data.source.remote.response.MovieResponse;
import com.ariefzuhri.movee.data.source.remote.response.MultiSearchResponse;
import com.ariefzuhri.movee.data.source.remote.response.ProductionCompanyItem;
import com.ariefzuhri.movee.data.source.remote.response.SearchResultItem;
import com.ariefzuhri.movee.data.source.remote.response.TVDetailsResponse;
import com.ariefzuhri.movee.data.source.remote.response.TVItem;
import com.ariefzuhri.movee.data.source.remote.response.TVResponse;
import com.ariefzuhri.movee.data.source.remote.response.VideoItem;
import com.ariefzuhri.movee.data.source.remote.response.VideosResponse;
import com.ariefzuhri.movee.utils.AppExecutors;
import com.ariefzuhri.movee.utils.FilterFavorite;
import com.ariefzuhri.movee.vo.Resource;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.ariefzuhri.movee.utils.Constants.MEDIA_TYPE_MOVIE;
import static com.ariefzuhri.movee.utils.Constants.MEDIA_TYPE_TV;

public class CatalogRepository implements CatalogDataSource {

    private volatile static CatalogRepository INSTANCE = null;

    private final AppExecutors appExecutors;
    private final LocalDataSource localDataSource;
    private final RemoteDataSource remoteDataSource;

    private CatalogRepository (@NonNull RemoteDataSource remoteDataSource, LocalDataSource localDataSource, AppExecutors appExecutors){
        this.appExecutors = appExecutors;
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static CatalogRepository getInstance(RemoteDataSource remoteDataSource, LocalDataSource localDataSource, AppExecutors appExecutors){
        if (INSTANCE == null){
            synchronized (CatalogRepository.class){
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

    /* Untuk konversi */
    @NotNull
    @Contract("_ -> new")
    private MediaEntity movieItemToMedia(@NotNull MovieItem item){
        return new MediaEntity(item.getId(),
                item.getTitle(),
                item.getPosterPath(),
                item.getBackdropPath(),
                item.getVoteAverage(),
                item.getVoteCount(),
                item.getPopularity(),
                MEDIA_TYPE_MOVIE,
                new AiredDateEntity(item.getReleaseDate()),
                item.getGenreIds(),
                item.getOverview());
    }

    @NotNull
    @Contract("_ -> new")
    private MediaEntity tvItemToMedia(@NotNull TVItem item){
        return new MediaEntity(item.getId(),
                item.getName(),
                item.getPosterPath(),
                item.getBackdropPath(),
                item.getVoteAverage(),
                item.getVoteCount(),
                item.getPopularity(),
                MEDIA_TYPE_TV,
                new AiredDateEntity(item.getFirstAirDate(), null),
                item.getGenreIds(),
                item.getOverview());
    }

    @NotNull
    private List<MediaEntity> movieResponseToMediaList(@NotNull MovieResponse response){
        List<MediaEntity> mediaList = new ArrayList<>();
        for (MovieItem movie : response.getResults()){
            mediaList.add(movieItemToMedia(movie));
        }
        return mediaList;
    }

    @NotNull
    private List<MediaEntity> tvResponsesToMediaList(@NotNull TVResponse response){
        List<MediaEntity> mediaList = new ArrayList<>();
        for (TVItem tv : response.getResults()){
            mediaList.add(tvItemToMedia(tv));
        }
        return mediaList;
    }

    @NotNull
    private List<MediaEntity> multiSearchResponseToMediaList(@NotNull MultiSearchResponse response){
        List<MediaEntity> mediaList = new ArrayList<>();
        for (SearchResultItem searchResult : response.getResults()){
            if (searchResult.getMediaType().equals(MEDIA_TYPE_MOVIE)){
                mediaList.add(new MediaEntity(searchResult.getId(),
                        searchResult.getTitle(),
                        searchResult.getPosterPath(),
                        searchResult.getBackdropPath(),
                        searchResult.getVoteAverage(),
                        searchResult.getVoteCount(),
                        searchResult.getPopularity(),
                        MEDIA_TYPE_MOVIE,
                        new AiredDateEntity(searchResult.getReleaseDate()),
                        searchResult.getGenreIds(),
                        searchResult.getOverview()));
            } else if (searchResult.getMediaType().equals(MEDIA_TYPE_TV)){
                mediaList.add(new MediaEntity(searchResult.getId(),
                        searchResult.getName(),
                        searchResult.getPosterPath(),
                        searchResult.getBackdropPath(),
                        searchResult.getVoteAverage(),
                        searchResult.getVoteCount(),
                        searchResult.getPopularity(),
                        MEDIA_TYPE_TV,
                        new AiredDateEntity(searchResult.getFirstAirDate(), null),
                        searchResult.getGenreIds(),
                        searchResult.getOverview()));
            }
        }
        return mediaList;
    }

    @NotNull
    private MediaEntity movieDetailsResponseToMedia(@NotNull MovieDetailsResponse response){
        List<GenreEntity> genreList = genresItemToGenreList(response.getGenres());
        List<StudioEntity> studioList = productionCompaniesItemToStudioList(
                response.getProductionCompanies()
        );

        return new MediaEntity(response.getId(),
                response.getTitle(),
                response.getPosterPath(),
                response.getBackdropPath(),
                response.getVoteAverage(),
                response.getVoteCount(),
                response.getPopularity(),
                MEDIA_TYPE_MOVIE,
                1,
                response.getStatus(),
                new AiredDateEntity(response.getReleaseDate()),
                studioList,
                genreList,
                response.getRuntime(),
                response.getOverview(),
                null);
    }

    @NotNull
    private MediaEntity tvDetailsResponseToMedia(@NotNull TVDetailsResponse response){
        List<GenreEntity> genreList = genresItemToGenreList(response.getGenres());
        List<StudioEntity> studioList = productionCompaniesItemToStudioList(
                response.getProductionCompanies()
        );

        return new MediaEntity(response.getId(),
                response.getName(),
                response.getPosterPath(),
                response.getBackdropPath(),
                response.getVoteAverage(),
                response.getVoteCount(),
                response.getPopularity(),
                MEDIA_TYPE_TV,
                response.getNumberOfEpisodes(),
                response.getStatus(),
                new AiredDateEntity(response.getFirstAirDate(), response.getLastAirDate()),
                studioList,
                genreList,
                (response.getEpisodeRunTime().isEmpty()) ? 0 : response.getEpisodeRunTime().get(0),
                response.getOverview(),
                null);
    }

    @NotNull
    private List<StudioEntity> productionCompaniesItemToStudioList(@NotNull List<ProductionCompanyItem> items){
        List<StudioEntity> studioList = new ArrayList<>();
        for (ProductionCompanyItem studio : items){
            studioList.add(new StudioEntity(studio.getId(), studio.getName(), studio.getLogoPath()));
        }
        return studioList;
    }

    @NotNull
    private List<GenreEntity> genresItemToGenreList(@NotNull List<GenreItem> items){
        List<GenreEntity> genreList = new ArrayList<>();
        for (GenreItem genre : items){
            genreList.add(new GenreEntity(genre.getId(), genre.getName()));
        }
        return genreList;
    }

    @NotNull
    private List<GenreEntity> genreResponseToGenreList(@NotNull GenresResponse response){
        List<GenreEntity> genreList = new ArrayList<>();
        for (GenreItem genre : response.getGenres()) {
            genreList.add(new GenreEntity(genre.getId(), genre.getName()));
        }
        return genreList;
    }

    @NotNull
    private List<TrailerEntity> videosResponseToTrailerList(@NotNull VideosResponse response){
        List<TrailerEntity> trailerList = new ArrayList<>();
        for (VideoItem video : response.getResults()) {
            trailerList.add(new TrailerEntity(video.getId(),
                    video.getName(),
                    video.getSite(),
                    video.getType(),
                    video.getKey())
            );
        }
        return trailerList;
    }

    @NotNull
    @Contract("_ -> new")
    private CreditsEntity creditsResponseToCredit(@NotNull CreditsResponse response){
        List<CastEntity> castList = new ArrayList<>();
        for (CastItem cast : response.getCast()) {
            castList.add(new CastEntity(cast.getId(),
                    cast.getName(),
                    cast.getCreditId(),
                    cast.getProfilePath(),
                    cast.getKnownForDepartment(),
                    cast.getCharacter())
            );
        }

        List<CrewEntity> crewList = new ArrayList<>();
        for (CrewItem crew : response.getCrew()) {
            crewList.add(new CrewEntity(crew.getId(),
                    crew.getName(),
                    crew.getCreditId(),
                    crew.getProfilePath(),
                    crew.getJob())
            );
        }

        return new CreditsEntity(response.getId(), castList, crewList);
    }
}
