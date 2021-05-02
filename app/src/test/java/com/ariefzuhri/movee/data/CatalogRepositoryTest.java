package com.ariefzuhri.movee.data;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

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
import com.ariefzuhri.movee.utils.DataDummy;
import com.ariefzuhri.movee.utils.LiveDataTestUtil;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.ariefzuhri.movee.utils.Constants.MEDIA_TYPE_MOVIE;
import static com.ariefzuhri.movee.utils.Constants.MEDIA_TYPE_TV;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

public class CatalogRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private final RemoteDataSource remote = Mockito.mock(RemoteDataSource.class);
    private final FakeCatalogRepository catalogRepository = new FakeCatalogRepository(remote);

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
                    .onMultiSearchReceived(multiSearchResponse);
            return null;
        }).when(remote).getMultiSearch(eq(query), eq(page), any(RemoteDataSource.LoadMultiSearchCallback.class));

        List<MediaEntity> mediaEntities = LiveDataTestUtil.getValue(catalogRepository.getMultiSearch(query, page));

        verify(remote).getMultiSearch(eq(query), eq(page), any(RemoteDataSource.LoadMultiSearchCallback.class));

        assertNotNull(mediaEntities);
        assertEquals(multiSearchResponse.getResults().size(), mediaEntities.size());
    }

    @Test
    public void getMovieDetails() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMovieDetailsCallback) invocation.getArguments()[1])
                    .onMovieDetailsReceived(movieDetailsResponse);
            return null;
        }).when(remote).getMovieDetails(eq(movieId), any(RemoteDataSource.LoadMovieDetailsCallback.class));

        MediaEntity movieDetailsEntity = LiveDataTestUtil.getValue(catalogRepository.getMovieDetails(movieId));

        verify(remote).getMovieDetails(eq(movieId), any(RemoteDataSource.LoadMovieDetailsCallback.class));

        assertNotNull(movieDetailsEntity);
        assertEquals(movieDetailsResponse.getId(), movieDetailsEntity.getId());
        assertEquals(movieDetailsResponse.getId(), movieDetailsEntity.getId());
        assertEquals(movieDetailsResponse.getTitle(), movieDetailsEntity.getTitle());
        assertEquals(movieDetailsResponse.getPosterPath(), movieDetailsEntity.getPoster());
        assertEquals(movieDetailsResponse.getBackdropPath(), movieDetailsEntity.getCover());
        assertEquals(movieDetailsResponse.getVoteAverage(), movieDetailsEntity.getScoreAverage(), 0);
        assertEquals(movieDetailsResponse.getVoteCount(), movieDetailsEntity.getScoreCount());
        assertEquals(movieDetailsResponse.getPopularity(), movieDetailsEntity.getPopularity(), 0);
        assertEquals(movieDetailsResponse.getStatus(), movieDetailsEntity.getStatus());
        assertEquals(movieDetailsResponse.getReleaseDate(), movieDetailsEntity.getAiredDate().getStartDate());
        assertEquals(movieDetailsResponse.getProductionCompanies().size(), movieDetailsEntity.getStudios().size());
        assertEquals(movieDetailsResponse.getGenres().size(), movieDetailsEntity.getGenres().size());
        assertEquals(movieDetailsResponse.getRuntime(), movieDetailsEntity.getRuntime());
        assertEquals(movieDetailsResponse.getOverview(), movieDetailsEntity.getSynopsis());
    }

    @Test
    public void getTVDetails() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadTVDetailsCallback) invocation.getArguments()[1])
                    .onTVDetailsReceived(tvDetailsResponse);
            return null;
        }).when(remote).getTVDetails(eq(tvId), any(RemoteDataSource.LoadTVDetailsCallback.class));

        MediaEntity tvDetailsEntity = LiveDataTestUtil.getValue(catalogRepository.getTVDetails(tvId));

        verify(remote).getTVDetails(eq(tvId), any(RemoteDataSource.LoadTVDetailsCallback.class));

        assertNotNull(tvDetailsEntity);
        assertEquals(tvDetailsResponse.getId(), tvDetailsEntity.getId());
        assertEquals(tvDetailsResponse.getName(), tvDetailsEntity.getTitle());
        assertEquals(tvDetailsResponse.getPosterPath(), tvDetailsEntity.getPoster());
        assertEquals(tvDetailsResponse.getBackdropPath(), tvDetailsEntity.getCover());
        assertEquals(tvDetailsResponse.getVoteAverage(), tvDetailsEntity.getScoreAverage(), 0);
        assertEquals(tvDetailsResponse.getVoteCount(), tvDetailsEntity.getScoreCount());
        assertEquals(tvDetailsResponse.getPopularity(), tvDetailsEntity.getPopularity(), 0);
        assertEquals(tvDetailsResponse.getNumberOfEpisodes(), tvDetailsEntity.getEpisodes());
        assertEquals(tvDetailsResponse.getStatus(), tvDetailsEntity.getStatus());
        assertEquals(tvDetailsResponse.getFirstAirDate(), tvDetailsEntity.getAiredDate().getStartDate());
        assertEquals(tvDetailsResponse.getLastAirDate(), tvDetailsEntity.getAiredDate().getEndDate());
        assertEquals(tvDetailsResponse.getProductionCompanies().size(), tvDetailsEntity.getStudios().size());
        assertEquals(tvDetailsResponse.getGenres().size(), tvDetailsEntity.getGenres().size());
        assertEquals(tvDetailsResponse.getEpisodeRunTime().get(0).intValue(), tvDetailsEntity.getRuntime());
        assertEquals(tvDetailsResponse.getOverview(), tvDetailsEntity.getSynopsis());
    }

    @Test
    public void getMovieTrending() {
        MovieResponse movieResponse = DataDummy.generateRemoteDummyMovieTrending();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMovieTrendingCallback) invocation.getArguments()[1])
                    .onMovieTrendingReceived(movieResponse);
            return null;
        }).when(remote).getMovieTrending(eq(page), any(RemoteDataSource.LoadMovieTrendingCallback.class));

        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(catalogRepository.getMovieTrending(page));

        verify(remote).getMovieTrending(eq(page), any(RemoteDataSource.LoadMovieTrendingCallback.class));

        assertNotNull(movieEntities);
        assertEquals(movieResponse.getResults().size(), movieEntities.size());
    }

    @Test
    public void getTVTrending() {
        TVResponse tvResponse = DataDummy.generateRemoteDummyTVTrending();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadTVTrendingCallback) invocation.getArguments()[1])
                    .onTVTrendingReceived(tvResponse);
            return null;
        }).when(remote).getTVTrending(eq(page), any(RemoteDataSource.LoadTVTrendingCallback.class));

        List<MediaEntity> tvEntities = LiveDataTestUtil.getValue(catalogRepository.getTVTrending(page));

        verify(remote).getTVTrending(eq(page), any(RemoteDataSource.LoadTVTrendingCallback.class));

        assertNotNull(tvEntities);
        assertEquals(tvResponse.getResults().size(), tvEntities.size());
    }

    @Test
    public void getMovieLatestRelease() {
        MovieResponse movieResponse = DataDummy.generateRemoteDummyMovieLatestRelease();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMovieLatestReleaseCallback) invocation.getArguments()[1])
                    .onMovieLatestReleaseReceived(movieResponse);
            return null;
        }).when(remote).getMovieLatestRelease(eq(page), any(RemoteDataSource.LoadMovieLatestReleaseCallback.class));

        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(catalogRepository.getMovieLatestRelease(page));

        verify(remote).getMovieLatestRelease(eq(page), any(RemoteDataSource.LoadMovieLatestReleaseCallback.class));

        assertNotNull(movieEntities);
        assertEquals(movieResponse.getResults().size(), movieEntities.size());
    }

    @Test
    public void getTVLatestRelease() {
        TVResponse tvResponse = DataDummy.generateRemoteDummyTVLatestRelease();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadTVLatestReleaseCallback) invocation.getArguments()[1])
                    .onTVLatestReleaseReceived(tvResponse);
            return null;
        }).when(remote).getTVLatestRelease(eq(page), any(RemoteDataSource.LoadTVLatestReleaseCallback.class));

        List<MediaEntity> tvEntities = LiveDataTestUtil.getValue(catalogRepository.getTVLatestRelease(page));

        verify(remote).getTVLatestRelease(eq(page), any(RemoteDataSource.LoadTVLatestReleaseCallback.class));

        assertNotNull(tvEntities);
        assertEquals(tvResponse.getResults().size(), tvEntities.size());
    }

    @Test
    public void getMovieNowPlaying() {
        MovieResponse movieResponse = DataDummy.generateRemoteDummyMovieNowPlaying();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMovieNowPlayingCallback) invocation.getArguments()[1])
                    .onMovieNowPlayingReceived(movieResponse);
            return null;
        }).when(remote).getMovieNowPlaying(eq(page), any(RemoteDataSource.LoadMovieNowPlayingCallback.class));

        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(catalogRepository.getMovieNowPlaying(page));

        verify(remote).getMovieNowPlaying(eq(page), any(RemoteDataSource.LoadMovieNowPlayingCallback.class));

        assertNotNull(movieEntities);
        assertEquals(movieResponse.getResults().size(), movieEntities.size());
    }

    @Test
    public void getTVOnTheAir() {
        TVResponse tvResponse = DataDummy.generateRemoteDummyTVOnTheAir();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadTVOnTheAirCallback) invocation.getArguments()[1])
                    .onTVOnTheAirReceived(tvResponse);
            return null;
        }).when(remote).getTVOnTheAir(eq(page), any(RemoteDataSource.LoadTVOnTheAirCallback.class));

        List<MediaEntity> tvEntities = LiveDataTestUtil.getValue(catalogRepository.getTVOnTheAir(page));

        verify(remote).getTVOnTheAir(eq(page), any(RemoteDataSource.LoadTVOnTheAirCallback.class));

        assertNotNull(tvEntities);
        assertEquals(tvResponse.getResults().size(), tvEntities.size());
    }

    @Test
    public void getMovieUpcoming() {
        MovieResponse movieResponse = DataDummy.generateRemoteDummyMovieUpcoming();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMovieUpcomingCallback) invocation.getArguments()[1])
                    .onMovieUpcomingReceived(movieResponse);
            return null;
        }).when(remote).getMovieUpcoming(eq(page), any(RemoteDataSource.LoadMovieUpcomingCallback.class));

        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(catalogRepository.getMovieUpcoming(page));

        verify(remote).getMovieUpcoming(eq(page), any(RemoteDataSource.LoadMovieUpcomingCallback.class));

        assertNotNull(movieEntities);
        assertEquals(movieResponse.getResults().size(), movieEntities.size());
    }

    @Test
    public void getMovieTopRated() {
        MovieResponse movieResponse = DataDummy.generateRemoteDummyMovieTopRated();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMovieTopRatedCallback) invocation.getArguments()[1])
                    .onMovieTopRatedReceived(movieResponse);
            return null;
        }).when(remote).getMovieTopRated(eq(page), any(RemoteDataSource.LoadMovieTopRatedCallback.class));

        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(catalogRepository.getMovieTopRated(page));

        verify(remote).getMovieTopRated(eq(page), any(RemoteDataSource.LoadMovieTopRatedCallback.class));

        assertNotNull(movieEntities);
        assertEquals(movieResponse.getResults().size(), movieEntities.size());
    }

    @Test
    public void getTVTopRated() {
        TVResponse tvResponse = DataDummy.generateRemoteDummyTVTopRated();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadTVTopRatedCallback) invocation.getArguments()[1])
                    .onTVTopRatedReceived(tvResponse);
            return null;
        }).when(remote).getTVTopRated(eq(page), any(RemoteDataSource.LoadTVTopRatedCallback.class));

        List<MediaEntity> tvEntities = LiveDataTestUtil.getValue(catalogRepository.getTVTopRated(page));

        verify(remote).getTVTopRated(eq(page), any(RemoteDataSource.LoadTVTopRatedCallback.class));

        assertNotNull(tvEntities);
        assertEquals(tvResponse.getResults().size(), tvEntities.size());
    }

    @Test
    public void getMoviePopular() {
        MovieResponse movieResponse = DataDummy.generateRemoteDummyMoviePopular();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMoviePopularCallback) invocation.getArguments()[1])
                    .onMoviePopularReceived(movieResponse);
            return null;
        }).when(remote).getMoviePopular(eq(page), any(RemoteDataSource.LoadMoviePopularCallback.class));

        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(catalogRepository.getMoviePopular(page));

        verify(remote).getMoviePopular(eq(page), any(RemoteDataSource.LoadMoviePopularCallback.class));

        assertNotNull(movieEntities);
        assertEquals(movieResponse.getResults().size(), movieEntities.size());
    }

    @Test
    public void getTVPopular() {
        TVResponse tvResponse = DataDummy.generateRemoteDummyTVPopular();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadTVPopularCallback) invocation.getArguments()[1])
                    .onTVPopularReceived(tvResponse);
            return null;
        }).when(remote).getTVPopular(eq(page), any(RemoteDataSource.LoadTVPopularCallback.class));

        List<MediaEntity> tvEntities = LiveDataTestUtil.getValue(catalogRepository.getTVPopular(page));

        verify(remote).getTVPopular(eq(page), any(RemoteDataSource.LoadTVPopularCallback.class));

        assertNotNull(tvEntities);
        assertEquals(tvResponse.getResults().size(), tvEntities.size());
    }

    @Test
    public void getMovieRecommendations() {
        MovieResponse movieResponse = DataDummy.generateRemoteDummyMovieRecommendations();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMovieRecommendationsCallback) invocation.getArguments()[2])
                    .onMovieRecommendationsReceived(movieResponse);
            return null;
        }).when(remote).getMovieRecommendations(eq(movieId), eq(page), any(RemoteDataSource.LoadMovieRecommendationsCallback.class));

        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(catalogRepository.getMovieRecommendations(movieId, page));

        verify(remote).getMovieRecommendations(eq(movieId), eq(page), any(RemoteDataSource.LoadMovieRecommendationsCallback.class));

        assertNotNull(movieEntities);
        assertEquals(movieResponse.getResults().size(), movieEntities.size());
    }

    @Test
    public void getTVRecommendations() {
        TVResponse tvResponse = DataDummy.generateRemoteDummyTVRecommendations();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadTVRecommendationsCallback) invocation.getArguments()[2])
                    .onTVRecommendationsReceived(tvResponse);
            return null;
        }).when(remote).getTVRecommendations(eq(tvId), eq(page), any(RemoteDataSource.LoadTVRecommendationsCallback.class));

        List<MediaEntity> tvEntities = LiveDataTestUtil.getValue(catalogRepository.getTVRecommendations(tvId, page));

        verify(remote).getTVRecommendations(eq(tvId), eq(page), any(RemoteDataSource.LoadTVRecommendationsCallback.class));

        assertNotNull(tvEntities);
        assertEquals(tvResponse.getResults().size(), tvEntities.size());
    }

    @Test
    public void getGenres() {
        GenresResponse movieGenresResponse = DataDummy.generateRemoteDummyMovieGenres();
        GenresResponse tvGenresResponse = DataDummy.generateRemoteDummyTVGenres();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadGenresCallback) invocation.getArguments()[1])
                    .onGenresReceived(movieGenresResponse);
            return null;
        }).when(remote).getGenres(eq(MEDIA_TYPE_MOVIE), any(RemoteDataSource.LoadGenresCallback.class));

        List<GenreEntity> movieGenreEntities = LiveDataTestUtil.getValue(catalogRepository.getGenres(MEDIA_TYPE_MOVIE));

        verify(remote).getGenres(eq(MEDIA_TYPE_MOVIE), any(RemoteDataSource.LoadGenresCallback.class));

        assertNotNull(movieGenreEntities);
        assertEquals(movieGenresResponse.getGenres().size(), movieGenreEntities.size());

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadGenresCallback) invocation.getArguments()[1])
                    .onGenresReceived(tvGenresResponse);
            return null;
        }).when(remote).getGenres(eq(MEDIA_TYPE_TV), any(RemoteDataSource.LoadGenresCallback.class));

        List<GenreEntity> tvGenreEntities = LiveDataTestUtil.getValue(catalogRepository.getGenres(MEDIA_TYPE_TV));

        verify(remote).getGenres(eq(MEDIA_TYPE_TV), any(RemoteDataSource.LoadGenresCallback.class));

        assertNotNull(tvGenreEntities);
        assertEquals(tvGenresResponse.getGenres().size(), tvGenreEntities.size());
    }

    @Test
    public void getVideos() {
        VideosResponse movieVideosResponse = DataDummy.generateRemoteDummyMovieVideos();
        VideosResponse tvVideosResponse = DataDummy.generateRemoteDummyTVVideos();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadVideosCallback) invocation.getArguments()[2])
                    .onVideosReceived(movieVideosResponse);
            return null;
        }).when(remote).getVideos(eq(MEDIA_TYPE_MOVIE), eq(movieId), any(RemoteDataSource.LoadVideosCallback.class));

        List<TrailerEntity> movieTrailerEntities = LiveDataTestUtil.getValue(catalogRepository.getVideos(MEDIA_TYPE_MOVIE, movieId));

        verify(remote).getVideos(eq(MEDIA_TYPE_MOVIE), eq(movieId), any(RemoteDataSource.LoadVideosCallback.class));

        assertNotNull(movieTrailerEntities);
        assertEquals(movieVideosResponse.getResults().size(), movieTrailerEntities.size());

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadVideosCallback) invocation.getArguments()[2])
                    .onVideosReceived(tvVideosResponse);
            return null;
        }).when(remote).getVideos(eq(MEDIA_TYPE_TV), eq(tvId), any(RemoteDataSource.LoadVideosCallback.class));

        List<TrailerEntity> tvTrailerEntities = LiveDataTestUtil.getValue(catalogRepository.getVideos(MEDIA_TYPE_TV, tvId));

        verify(remote).getVideos(eq(MEDIA_TYPE_TV), eq(tvId), any(RemoteDataSource.LoadVideosCallback.class));

        assertNotNull(tvTrailerEntities);
        assertEquals(tvVideosResponse.getResults().size(), tvTrailerEntities.size());
    }

    @Test
    public void getCredits() {
        CreditsResponse movieCreditsResponse = DataDummy.generateRemoteDummyMovieCredits();
        CreditsResponse tvCreditsResponse = DataDummy.generateRemoteDummyTVCredits();

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadCreditsCallback) invocation.getArguments()[2])
                    .onCreditsReceived(movieCreditsResponse);
            return null;
        }).when(remote).getCredits(eq(MEDIA_TYPE_MOVIE), eq(movieId), any(RemoteDataSource.LoadCreditsCallback.class));

        CreditsEntity movieCreditsEntity = LiveDataTestUtil.getValue(catalogRepository.getCredits(MEDIA_TYPE_MOVIE, movieId));

        verify(remote).getCredits(eq(MEDIA_TYPE_MOVIE), eq(movieId), any(RemoteDataSource.LoadCreditsCallback.class));

        assertNotNull(movieCreditsEntity);
        assertEquals(movieCreditsResponse.getCast().size(), movieCreditsEntity.getCast().size());
        assertEquals(movieCreditsResponse.getCrew().size(), movieCreditsEntity.getCrew().size());

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadCreditsCallback) invocation.getArguments()[2])
                    .onCreditsReceived(tvCreditsResponse);
            return null;
        }).when(remote).getCredits(eq(MEDIA_TYPE_TV), eq(tvId), any(RemoteDataSource.LoadCreditsCallback.class));

        CreditsEntity tvCreditsEntity = LiveDataTestUtil.getValue(catalogRepository.getCredits(MEDIA_TYPE_TV, tvId));

        verify(remote).getCredits(eq(MEDIA_TYPE_TV), eq(tvId), any(RemoteDataSource.LoadCreditsCallback.class));

        assertNotNull(tvCreditsEntity);
        assertEquals(tvCreditsResponse.getCast().size(), tvCreditsEntity.getCast().size());
        assertEquals(tvCreditsResponse.getCrew().size(), tvCreditsEntity.getCrew().size());
    }
}