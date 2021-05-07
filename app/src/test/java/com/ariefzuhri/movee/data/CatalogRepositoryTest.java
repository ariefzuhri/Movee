package com.ariefzuhri.movee.data;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.ariefzuhri.movee.data.source.local.LocalDataSource;
import com.ariefzuhri.movee.data.source.local.entity.FavoriteEntity;
import com.ariefzuhri.movee.data.source.local.entity.FavoriteWithGenres;
import com.ariefzuhri.movee.data.source.remote.ApiResponse;
import com.ariefzuhri.movee.data.source.remote.RemoteDataSource;
import com.ariefzuhri.movee.data.source.remote.entity.CreditsEntity;
import com.ariefzuhri.movee.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.movee.data.source.remote.entity.TrailerEntity;
import com.ariefzuhri.movee.data.source.remote.response.CreditsResponse;
import com.ariefzuhri.movee.data.source.remote.response.GenresResponse;
import com.ariefzuhri.movee.data.source.remote.response.MovieDetailsResponse;
import com.ariefzuhri.movee.data.source.remote.response.MovieResponse;
import com.ariefzuhri.movee.data.source.remote.response.MultiSearchResponse;
import com.ariefzuhri.movee.data.source.remote.response.TVDetailsResponse;
import com.ariefzuhri.movee.data.source.remote.response.TVResponse;
import com.ariefzuhri.movee.data.source.remote.response.VideosResponse;
import com.ariefzuhri.movee.utils.AppExecutors;
import com.ariefzuhri.movee.utils.DataDummy;
import com.ariefzuhri.movee.utils.FilterFavorite;
import com.ariefzuhri.movee.utils.LiveDataTestUtil;
import com.ariefzuhri.movee.utils.PagedListUtil;
import com.ariefzuhri.movee.utils.TestExecutor;
import com.ariefzuhri.movee.vo.Resource;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static com.ariefzuhri.movee.utils.Constants.MEDIA_TYPE_MOVIE;
import static com.ariefzuhri.movee.utils.Constants.MEDIA_TYPE_TV;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CatalogRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private final RemoteDataSource remote = mock(RemoteDataSource.class);
    private final LocalDataSource local = mock(LocalDataSource.class);
    private final AppExecutors appExecutors = mock(AppExecutors.class);

    private final FakeCatalogRepository catalogRepository = new FakeCatalogRepository(remote, local, appExecutors);
    private final AppExecutors testExecutors = new AppExecutors(new TestExecutor(), new TestExecutor(), new TestExecutor());

    private final MovieDetailsResponse movieDetailsResponse = DataDummy.generateRemoteDummyMovieDetails();
    private final int movieId = movieDetailsResponse.getId();
    private final TVDetailsResponse tvDetailsResponse = DataDummy.generateRemoteDummyTVDetails();
    private final int tvId = tvDetailsResponse.getId();

    private final int page = 1;

    @Test
    public void getMultiSearch() {
        MultiSearchResponse multiSearchResponse = DataDummy.generateRemoteDummyMultiSearch();
        String query = "the promised neverland";

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMultiSearchCallback) invocation.getArguments()[2])
                    .onMultiSearchReceived(ApiResponse.success(multiSearchResponse));
            return null;
        }).when(remote).getMultiSearch(eq(query), eq(page), any(RemoteDataSource.LoadMultiSearchCallback.class));

        Resource<List<MediaEntity>> mediaEntities = LiveDataTestUtil.getValue(catalogRepository.getMultiSearch(query, page));
        verify(remote).getMultiSearch(eq(query), eq(page), any(RemoteDataSource.LoadMultiSearchCallback.class));

        assertNotNull(mediaEntities.data);
        assertEquals(multiSearchResponse.getResults().size(), mediaEntities.data.size());
    }

    @Test
    public void getMovieDetails() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMovieDetailsCallback) invocation.getArguments()[1])
                    .onMovieDetailsReceived(ApiResponse.success(movieDetailsResponse));
            return null;
        }).when(remote).getMovieDetails(eq(movieId), any(RemoteDataSource.LoadMovieDetailsCallback.class));

        Resource<MediaEntity> movieDetailsEntity = LiveDataTestUtil.getValue(catalogRepository.getMovieDetails(movieId));
        verify(remote).getMovieDetails(eq(movieId), any(RemoteDataSource.LoadMovieDetailsCallback.class));

        assertNotNull(movieDetailsEntity.data);
        assertEquals(movieDetailsResponse.getId(), movieDetailsEntity.data.getId());
        assertEquals(movieDetailsResponse.getId(), movieDetailsEntity.data.getId());
        assertEquals(movieDetailsResponse.getTitle(), movieDetailsEntity.data.getTitle());
        assertEquals(movieDetailsResponse.getPosterPath(), movieDetailsEntity.data.getPoster());
        assertEquals(movieDetailsResponse.getBackdropPath(), movieDetailsEntity.data.getCover());
        assertEquals(movieDetailsResponse.getVoteAverage(), movieDetailsEntity.data.getScoreAverage(), 0);
        assertEquals(movieDetailsResponse.getVoteCount(), movieDetailsEntity.data.getScoreCount());
        assertEquals(movieDetailsResponse.getPopularity(), movieDetailsEntity.data.getPopularity(), 0);
        assertEquals(movieDetailsResponse.getStatus(), movieDetailsEntity.data.getStatus());
        assertEquals(movieDetailsResponse.getReleaseDate(), movieDetailsEntity.data.getAiredDate().getStartDate());
        assertEquals(movieDetailsResponse.getProductionCompanies().size(), movieDetailsEntity.data.getStudios().size());
        assertEquals(movieDetailsResponse.getGenres().size(), movieDetailsEntity.data.getGenres().size());
        assertEquals(movieDetailsResponse.getRuntime(), movieDetailsEntity.data.getRuntime());
        assertEquals(movieDetailsResponse.getOverview(), movieDetailsEntity.data.getSynopsis());
    }

    @Test
    public void getTVDetails() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadTVDetailsCallback) invocation.getArguments()[1])
                    .onTVDetailsReceived(ApiResponse.success(tvDetailsResponse));
            return null;
        }).when(remote).getTVDetails(eq(tvId), any(RemoteDataSource.LoadTVDetailsCallback.class));

        Resource<MediaEntity> tvDetailsEntity = LiveDataTestUtil.getValue(catalogRepository.getTVDetails(tvId));
        verify(remote).getTVDetails(eq(tvId), any(RemoteDataSource.LoadTVDetailsCallback.class));

        assertNotNull(tvDetailsEntity.data);
        assertEquals(tvDetailsResponse.getId(), tvDetailsEntity.data.getId());
        assertEquals(tvDetailsResponse.getName(), tvDetailsEntity.data.getTitle());
        assertEquals(tvDetailsResponse.getPosterPath(), tvDetailsEntity.data.getPoster());
        assertEquals(tvDetailsResponse.getBackdropPath(), tvDetailsEntity.data.getCover());
        assertEquals(tvDetailsResponse.getVoteAverage(), tvDetailsEntity.data.getScoreAverage(), 0);
        assertEquals(tvDetailsResponse.getVoteCount(), tvDetailsEntity.data.getScoreCount());
        assertEquals(tvDetailsResponse.getPopularity(), tvDetailsEntity.data.getPopularity(), 0);
        assertEquals(tvDetailsResponse.getNumberOfEpisodes(), tvDetailsEntity.data.getEpisodes());
        assertEquals(tvDetailsResponse.getStatus(), tvDetailsEntity.data.getStatus());
        assertEquals(tvDetailsResponse.getFirstAirDate(), tvDetailsEntity.data.getAiredDate().getStartDate());
        assertEquals(tvDetailsResponse.getLastAirDate(), tvDetailsEntity.data.getAiredDate().getEndDate());
        assertEquals(tvDetailsResponse.getProductionCompanies().size(), tvDetailsEntity.data.getStudios().size());
        assertEquals(tvDetailsResponse.getGenres().size(), tvDetailsEntity.data.getGenres().size());
        assertEquals(tvDetailsResponse.getEpisodeRunTime().get(0).intValue(), tvDetailsEntity.data.getRuntime());
        assertEquals(tvDetailsResponse.getOverview(), tvDetailsEntity.data.getSynopsis());
    }

    @Test
    public void getMovieTrending() {
        MovieResponse movieResponse = DataDummy.generateRemoteDummyMovieTrending();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMovieTrendingCallback) invocation.getArguments()[1])
                    .onMovieTrendingReceived(ApiResponse.success(movieResponse));
            return null;
        }).when(remote).getMovieTrending(eq(page), any(RemoteDataSource.LoadMovieTrendingCallback.class));

        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(catalogRepository.getMovieTrending(page));
        verify(remote).getMovieTrending(eq(page), any(RemoteDataSource.LoadMovieTrendingCallback.class));

        assertNotNull(movieEntities.data);
        assertEquals(movieResponse.getResults().size(), movieEntities.data.size());
    }

    @Test
    public void getTVTrending() {
        TVResponse tvResponse = DataDummy.generateRemoteDummyTVTrending();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadTVTrendingCallback) invocation.getArguments()[1])
                    .onTVTrendingReceived(ApiResponse.success(tvResponse));
            return null;
        }).when(remote).getTVTrending(eq(page), any(RemoteDataSource.LoadTVTrendingCallback.class));

        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(catalogRepository.getTVTrending(page));
        verify(remote).getTVTrending(eq(page), any(RemoteDataSource.LoadTVTrendingCallback.class));

        assertNotNull(tvEntities.data);
        assertEquals(tvResponse.getResults().size(), tvEntities.data.size());
    }

    @Test
    public void getMovieLatestRelease() {
        MovieResponse movieResponse = DataDummy.generateRemoteDummyMovieLatestRelease();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMovieLatestReleaseCallback) invocation.getArguments()[1])
                    .onMovieLatestReleaseReceived(ApiResponse.success(movieResponse));
            return null;
        }).when(remote).getMovieLatestRelease(eq(page), any(RemoteDataSource.LoadMovieLatestReleaseCallback.class));

        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(catalogRepository.getMovieLatestRelease(page));
        verify(remote).getMovieLatestRelease(eq(page), any(RemoteDataSource.LoadMovieLatestReleaseCallback.class));

        assertNotNull(movieEntities.data);
        assertEquals(movieResponse.getResults().size(), movieEntities.data.size());
    }

    @Test
    public void getTVLatestRelease() {
        TVResponse tvResponse = DataDummy.generateRemoteDummyTVLatestRelease();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadTVLatestReleaseCallback) invocation.getArguments()[1])
                    .onTVLatestReleaseReceived(ApiResponse.success(tvResponse));
            return null;
        }).when(remote).getTVLatestRelease(eq(page), any(RemoteDataSource.LoadTVLatestReleaseCallback.class));

        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(catalogRepository.getTVLatestRelease(page));
        verify(remote).getTVLatestRelease(eq(page), any(RemoteDataSource.LoadTVLatestReleaseCallback.class));

        assertNotNull(tvEntities.data);
        assertEquals(tvResponse.getResults().size(), tvEntities.data.size());
    }

    @Test
    public void getMovieNowPlaying() {
        MovieResponse movieResponse = DataDummy.generateRemoteDummyMovieNowPlaying();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMovieNowPlayingCallback) invocation.getArguments()[1])
                    .onMovieNowPlayingReceived(ApiResponse.success(movieResponse));
            return null;
        }).when(remote).getMovieNowPlaying(eq(page), any(RemoteDataSource.LoadMovieNowPlayingCallback.class));

        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(catalogRepository.getMovieNowPlaying(page));
        verify(remote).getMovieNowPlaying(eq(page), any(RemoteDataSource.LoadMovieNowPlayingCallback.class));

        assertNotNull(movieEntities.data);
        assertEquals(movieResponse.getResults().size(), movieEntities.data.size());
    }

    @Test
    public void getTVOnTheAir() {
        TVResponse tvResponse = DataDummy.generateRemoteDummyTVOnTheAir();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadTVOnTheAirCallback) invocation.getArguments()[1])
                    .onTVOnTheAirReceived(ApiResponse.success(tvResponse));
            return null;
        }).when(remote).getTVOnTheAir(eq(page), any(RemoteDataSource.LoadTVOnTheAirCallback.class));

        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(catalogRepository.getTVOnTheAir(page));
        verify(remote).getTVOnTheAir(eq(page), any(RemoteDataSource.LoadTVOnTheAirCallback.class));

        assertNotNull(tvEntities.data);
        assertEquals(tvResponse.getResults().size(), tvEntities.data.size());
    }

    @Test
    public void getMovieUpcoming() {
        MovieResponse movieResponse = DataDummy.generateRemoteDummyMovieUpcoming();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMovieUpcomingCallback) invocation.getArguments()[1])
                    .onMovieUpcomingReceived(ApiResponse.success(movieResponse));
            return null;
        }).when(remote).getMovieUpcoming(eq(page), any(RemoteDataSource.LoadMovieUpcomingCallback.class));

        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(catalogRepository.getMovieUpcoming(page));
        verify(remote).getMovieUpcoming(eq(page), any(RemoteDataSource.LoadMovieUpcomingCallback.class));

        assertNotNull(movieEntities.data);
        assertEquals(movieResponse.getResults().size(), movieEntities.data.size());
    }

    @Test
    public void getMovieTopRated() {
        MovieResponse movieResponse = DataDummy.generateRemoteDummyMovieTopRated();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMovieTopRatedCallback) invocation.getArguments()[1])
                    .onMovieTopRatedReceived(ApiResponse.success(movieResponse));
            return null;
        }).when(remote).getMovieTopRated(eq(page), any(RemoteDataSource.LoadMovieTopRatedCallback.class));

        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(catalogRepository.getMovieTopRated(page));
        verify(remote).getMovieTopRated(eq(page), any(RemoteDataSource.LoadMovieTopRatedCallback.class));

        assertNotNull(movieEntities.data);
        assertEquals(movieResponse.getResults().size(), movieEntities.data.size());
    }

    @Test
    public void getTVTopRated() {
        TVResponse tvResponse = DataDummy.generateRemoteDummyTVTopRated();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadTVTopRatedCallback) invocation.getArguments()[1])
                    .onTVTopRatedReceived(ApiResponse.success(tvResponse));
            return null;
        }).when(remote).getTVTopRated(eq(page), any(RemoteDataSource.LoadTVTopRatedCallback.class));

        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(catalogRepository.getTVTopRated(page));
        verify(remote).getTVTopRated(eq(page), any(RemoteDataSource.LoadTVTopRatedCallback.class));

        assertNotNull(tvEntities.data);
        assertEquals(tvResponse.getResults().size(), tvEntities.data.size());
    }

    @Test
    public void getMoviePopular() {
        MovieResponse movieResponse = DataDummy.generateRemoteDummyMoviePopular();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMoviePopularCallback) invocation.getArguments()[1])
                    .onMoviePopularReceived(ApiResponse.success(movieResponse));
            return null;
        }).when(remote).getMoviePopular(eq(page), any(RemoteDataSource.LoadMoviePopularCallback.class));

        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(catalogRepository.getMoviePopular(page));
        verify(remote).getMoviePopular(eq(page), any(RemoteDataSource.LoadMoviePopularCallback.class));

        assertNotNull(movieEntities.data);
        assertEquals(movieResponse.getResults().size(), movieEntities.data.size());
    }

    @Test
    public void getTVPopular() {
        TVResponse tvResponse = DataDummy.generateRemoteDummyTVPopular();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadTVPopularCallback) invocation.getArguments()[1])
                    .onTVPopularReceived(ApiResponse.success(tvResponse));
            return null;
        }).when(remote).getTVPopular(eq(page), any(RemoteDataSource.LoadTVPopularCallback.class));

        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(catalogRepository.getTVPopular(page));
        verify(remote).getTVPopular(eq(page), any(RemoteDataSource.LoadTVPopularCallback.class));

        assertNotNull(tvEntities.data);
        assertEquals(tvResponse.getResults().size(), tvEntities.data.size());
    }

    @Test
    public void getMovieRecommendations() {
        MovieResponse movieResponse = DataDummy.generateRemoteDummyMovieRecommendations();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMovieRecommendationsCallback) invocation.getArguments()[2])
                    .onMovieRecommendationsReceived(ApiResponse.success(movieResponse));
            return null;
        }).when(remote).getMovieRecommendations(eq(movieId), eq(page), any(RemoteDataSource.LoadMovieRecommendationsCallback.class));

        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(catalogRepository.getMovieRecommendations(movieId, page));
        verify(remote).getMovieRecommendations(eq(movieId), eq(page), any(RemoteDataSource.LoadMovieRecommendationsCallback.class));

        assertNotNull(movieEntities.data);
        assertEquals(movieResponse.getResults().size(), movieEntities.data.size());
    }

    @Test
    public void getTVRecommendations() {
        TVResponse tvResponse = DataDummy.generateRemoteDummyTVRecommendations();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadTVRecommendationsCallback) invocation.getArguments()[2])
                    .onTVRecommendationsReceived(ApiResponse.success(tvResponse));
            return null;
        }).when(remote).getTVRecommendations(eq(tvId), eq(page), any(RemoteDataSource.LoadTVRecommendationsCallback.class));

        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(catalogRepository.getTVRecommendations(tvId, page));
        verify(remote).getTVRecommendations(eq(tvId), eq(page), any(RemoteDataSource.LoadTVRecommendationsCallback.class));

        assertNotNull(tvEntities.data);
        assertEquals(tvResponse.getResults().size(), tvEntities.data.size());
    }

    @Test
    public void getGenres() {
        GenresResponse genresResponse = DataDummy.generateRemoteDummyGenres();

        MutableLiveData<List<GenreEntity>> dummyGenres = new MutableLiveData<>();
        dummyGenres.setValue(DataDummy.generateDummyGenres());
        when(local.getGenres()).thenReturn(dummyGenres);

        Resource<List<GenreEntity>> genreEntities = LiveDataTestUtil.getValue(catalogRepository.getGenres());
        verify(local).getGenres();

        assertNotNull(genreEntities.data);
        assertEquals(genresResponse.getGenres().size(), genreEntities.data.size());
    }

    @Test
    public void getVideos() {
        VideosResponse movieVideosResponse = DataDummy.generateRemoteDummyMovieVideos();
        VideosResponse tvVideosResponse = DataDummy.generateRemoteDummyTVVideos();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadVideosCallback) invocation.getArguments()[2])
                    .onVideosReceived(ApiResponse.success(movieVideosResponse));
            return null;
        }).when(remote).getVideos(eq(MEDIA_TYPE_MOVIE), eq(movieId), any(RemoteDataSource.LoadVideosCallback.class));

        Resource<List<TrailerEntity>> movieTrailerEntities = LiveDataTestUtil.getValue(catalogRepository.getVideos(MEDIA_TYPE_MOVIE, movieId));
        verify(remote).getVideos(eq(MEDIA_TYPE_MOVIE), eq(movieId), any(RemoteDataSource.LoadVideosCallback.class));

        assertNotNull(movieTrailerEntities.data);
        assertEquals(movieVideosResponse.getResults().size(), movieTrailerEntities.data.size());

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadVideosCallback) invocation.getArguments()[2])
                    .onVideosReceived(ApiResponse.success(tvVideosResponse));
            return null;
        }).when(remote).getVideos(eq(MEDIA_TYPE_TV), eq(tvId), any(RemoteDataSource.LoadVideosCallback.class));

        Resource<List<TrailerEntity>> tvTrailerEntities = LiveDataTestUtil.getValue(catalogRepository.getVideos(MEDIA_TYPE_TV, tvId));
        verify(remote).getVideos(eq(MEDIA_TYPE_TV), eq(tvId), any(RemoteDataSource.LoadVideosCallback.class));

        assertNotNull(tvTrailerEntities.data);
        assertEquals(tvVideosResponse.getResults().size(), tvTrailerEntities.data.size());
    }

    @Test
    public void getCredits() {
        CreditsResponse movieCreditsResponse = DataDummy.generateRemoteDummyMovieCredits();
        CreditsResponse tvCreditsResponse = DataDummy.generateRemoteDummyTVCredits();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadCreditsCallback) invocation.getArguments()[2])
                    .onCreditsReceived(ApiResponse.success(movieCreditsResponse));
            return null;
        }).when(remote).getCredits(eq(MEDIA_TYPE_MOVIE), eq(movieId), any(RemoteDataSource.LoadCreditsCallback.class));

        Resource<CreditsEntity> movieCreditsEntity = LiveDataTestUtil.getValue(catalogRepository.getCredits(MEDIA_TYPE_MOVIE, movieId));
        verify(remote).getCredits(eq(MEDIA_TYPE_MOVIE), eq(movieId), any(RemoteDataSource.LoadCreditsCallback.class));

        assertNotNull(movieCreditsEntity.data);
        assertEquals(movieCreditsResponse.getCast().size(), movieCreditsEntity.data.getCast().size());
        assertEquals(movieCreditsResponse.getCrew().size(), movieCreditsEntity.data.getCrew().size());

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadCreditsCallback) invocation.getArguments()[2])
                    .onCreditsReceived(ApiResponse.success(tvCreditsResponse));
            return null;
        }).when(remote).getCredits(eq(MEDIA_TYPE_TV), eq(tvId), any(RemoteDataSource.LoadCreditsCallback.class));

        Resource<CreditsEntity> tvCreditsEntity = LiveDataTestUtil.getValue(catalogRepository.getCredits(MEDIA_TYPE_TV, tvId));
        verify(remote).getCredits(eq(MEDIA_TYPE_TV), eq(tvId), any(RemoteDataSource.LoadCreditsCallback.class));

        assertNotNull(tvCreditsEntity.data);
        assertEquals(tvCreditsResponse.getCast().size(), tvCreditsEntity.data.getCast().size());
        assertEquals(tvCreditsResponse.getCrew().size(), tvCreditsEntity.data.getCrew().size());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getFavorites() {
        TVResponse tvResponse = DataDummy.generateRemoteDummyTVTopRated();
        FilterFavorite filter = new FilterFavorite();

        DataSource.Factory<Integer, FavoriteWithGenres> dataSourceFactory = mock(DataSource.Factory.class);
        when(local.getAllFavoriteWithGenres(filter)).thenReturn(dataSourceFactory);
        catalogRepository.getFavorites(filter);

        Resource<PagedList<FavoriteWithGenres>> favoriteEntities = Resource.success(PagedListUtil
                .mockPagedList(DataDummy.generateDummyFavorites(DataDummy.generateDummyTVTopRated())));
        verify(local).getAllFavoriteWithGenres(filter);

        assertNotNull(favoriteEntities.data);
        assertEquals(tvResponse.getResults().size(), favoriteEntities.data.size());
    }

    @Test
    public void getFavorite() {
        MutableLiveData<FavoriteWithGenres> dummyFavorite = new MutableLiveData<>();
        dummyFavorite.setValue(DataDummy.generateDummyFavorite(DataDummy.generateDummyTVDetails()));
        when(local.getFavoriteWithGenresById(tvId, MEDIA_TYPE_TV)).thenReturn(dummyFavorite);

        FavoriteWithGenres favoriteEntity = LiveDataTestUtil.getValue(catalogRepository.getFavorite(tvId, MEDIA_TYPE_TV));
        verify(local).getFavoriteWithGenresById(tvId, MEDIA_TYPE_TV);

        assertNotNull(favoriteEntity);
        assertEquals(Integer.valueOf(tvDetailsResponse.getId()), favoriteEntity.favorite.getId());
        assertEquals(tvDetailsResponse.getName(), favoriteEntity.favorite.getTitle());
        assertEquals(tvDetailsResponse.getPosterPath(), favoriteEntity.favorite.getPoster());
        assertEquals(tvDetailsResponse.getVoteAverage(), favoriteEntity.favorite.getScoreAverage(), 0);
        assertEquals(tvDetailsResponse.getFirstAirDate(), favoriteEntity.favorite.getStartDate());
        assertEquals(tvDetailsResponse.getGenres().size(), favoriteEntity.genres.size());
    }

    @Test
    public void insertFavorite(){
        FavoriteWithGenres dummyFavorite = DataDummy.generateDummyFavorite(DataDummy.generateDummyTVDetails());
        FavoriteEntity favorite = dummyFavorite.favorite;
        favorite.setGenres(dummyFavorite.genres);

        when(appExecutors.diskIO()).thenReturn(testExecutors.diskIO());
        doNothing().when(local).insertFavorite(favorite);

        catalogRepository.insertFavorite(favorite);
        verify(local, times(1)).insertFavorite(favorite);
    }

    @Test
    public void updateFavorite(){
        FavoriteWithGenres dummyFavorite = DataDummy.generateDummyFavorite(DataDummy.generateDummyTVDetails());
        FavoriteEntity favorite = dummyFavorite.favorite;
        favorite.setGenres(dummyFavorite.genres);

        when(appExecutors.diskIO()).thenReturn(testExecutors.diskIO());
        doNothing().when(local).updateFavorite(favorite);

        catalogRepository.updateFavorite(favorite);
        verify(local, times(1)).updateFavorite(favorite);
    }

    @Test
    public void deleteFavorite() {
        FavoriteWithGenres dummyFavorite = DataDummy.generateDummyFavorite(DataDummy.generateDummyTVDetails());
        FavoriteEntity favorite = dummyFavorite.favorite;
        favorite.setGenres(dummyFavorite.genres);

        when(appExecutors.diskIO()).thenReturn(testExecutors.diskIO());
        doNothing().when(local).deleteFavorite(favorite);

        catalogRepository.deleteFavorite(favorite);
        verify(local, times(1)).deleteFavorite(favorite);
    }
}