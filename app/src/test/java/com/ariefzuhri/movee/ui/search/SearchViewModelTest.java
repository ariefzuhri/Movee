package com.ariefzuhri.movee.ui.search;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.ariefzuhri.movee.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.movee.data.CatalogRepository;
import com.ariefzuhri.movee.utils.DataDummy;
import com.ariefzuhri.movee.utils.LiveDataTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.ariefzuhri.movee.utils.Constants.MEDIA_TYPE_MOVIE;
import static com.ariefzuhri.movee.utils.Constants.MEDIA_TYPE_TV;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_MOVIE_LATEST_RELEASE;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_MOVIE_NOW_PLAYING;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_MOVIE_POPULAR;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_MOVIE_RECOMMENDATIONS;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_MOVIE_TOP_RATED;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_MOVIE_TRENDING;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_MOVIE_UPCOMING;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_TV_LATEST_RELEASE;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_TV_ON_THE_AIR;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_TV_POPULAR;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_TV_RECOMMENDATIONS;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_TV_TOP_RATED;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_TV_TRENDING;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchViewModelTest {
    private SearchViewModel viewModel;

    private final MediaEntity dummyMovieDetails = DataDummy.generateDummyMovieDetails();
    private final int movieId = dummyMovieDetails.getId();
    private final MediaEntity dummyTVDetails = DataDummy.generateDummyTVDetails();
    private final int tvId = dummyTVDetails.getId();
    private final int page = 1;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private Application application;

    @Mock
    private CatalogRepository catalogRepository;

    @Mock
    private Observer<List<MediaEntity>> mediaEntitiesObserver;

    @Mock
    private Observer<List<GenreEntity>> genreEntitiesObserver;

    @Before
    public void setUp(){
        viewModel = new SearchViewModel(application, catalogRepository);
        viewModel.setPage(page);
    }

    @Test
    public void getMultiSearch() {
        String query = "the promised neverland";
        viewModel.setQuery(query);

        List<MediaEntity> dummyMultiSearch = DataDummy.generateDummyMultiSearch();

        MutableLiveData<List<MediaEntity>> multiSearch = new MutableLiveData<>();
        multiSearch.setValue(dummyMultiSearch);

        when(catalogRepository.getMultiSearch(query, page)).thenReturn(multiSearch);
        List<MediaEntity> mediaEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMultiSearch(query, page);
        assertNotNull(mediaEntities);
        assertEquals(dummyMultiSearch.size(), mediaEntities.size());
    }

    @Test
    public void getGenres() {
        List<GenreEntity> dummyMovieGenres = DataDummy.generateDummyMovieGenres();
        List<GenreEntity> dummyTVGenres = DataDummy.generateDummyTVGenres();

        MutableLiveData<List<GenreEntity>> movieGenres = new MutableLiveData<>();
        movieGenres.setValue(dummyMovieGenres);

        when(catalogRepository.getGenres(MEDIA_TYPE_MOVIE)).thenReturn(movieGenres);
        List<GenreEntity> movieGenreEntities = LiveDataTestUtil.getValue(viewModel.getMovieGenres());
        verify(catalogRepository).getGenres(MEDIA_TYPE_MOVIE);
        assertNotNull(movieGenreEntities);
        assertEquals(dummyMovieGenres.size(), movieGenreEntities.size());

        viewModel.getMovieGenres().observeForever(genreEntitiesObserver);
        verify(genreEntitiesObserver).onChanged(dummyMovieGenres);

        MutableLiveData<List<GenreEntity>> tvGenres = new MutableLiveData<>();
        tvGenres.setValue(dummyTVGenres);

        when(catalogRepository.getGenres(MEDIA_TYPE_TV)).thenReturn(tvGenres);
        List<GenreEntity> tvGenreEntities = LiveDataTestUtil.getValue(viewModel.getTVGenres());
        verify(catalogRepository).getGenres(MEDIA_TYPE_TV);
        assertNotNull(tvGenreEntities);
        assertEquals(dummyTVGenres.size(), tvGenreEntities.size());

        viewModel.getTVGenres().observeForever(genreEntitiesObserver);
        verify(genreEntitiesObserver).onChanged(dummyTVGenres);
    }

    @Test
    public void getMovieTrending() {
        viewModel.setQueryType(QUERY_TYPE_MOVIE_TRENDING);

        List<MediaEntity> dummyMovieTrending = DataDummy.generateDummyMovieTrending();

        MutableLiveData<List<MediaEntity>> movieTrending = new MutableLiveData<>();
        movieTrending.setValue(dummyMovieTrending);

        when(catalogRepository.getMovieTrending(page)).thenReturn(movieTrending);
        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMovieTrending(page);
        assertNotNull(movieEntities);
        assertEquals(dummyMovieTrending.size(), movieEntities.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieTrending);
    }

    @Test
    public void getTVTrending() {
        viewModel.setQueryType(QUERY_TYPE_TV_TRENDING);

        List<MediaEntity> dummyTVTrending = DataDummy.generateDummyTVTrending();

        MutableLiveData<List<MediaEntity>> tvTrending = new MutableLiveData<>();
        tvTrending.setValue(dummyTVTrending);

        when(catalogRepository.getTVTrending(page)).thenReturn(tvTrending);
        List<MediaEntity> tvEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getTVTrending(page);
        assertNotNull(tvEntities);
        assertEquals(dummyTVTrending.size(), tvEntities.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVTrending);
    }

    @Test
    public void getMovieLatestRelease() {
        viewModel.setQueryType(QUERY_TYPE_MOVIE_LATEST_RELEASE);

        List<MediaEntity> dummyMovieLatestRelease = DataDummy.generateDummyMovieLatestRelease();

        MutableLiveData<List<MediaEntity>> movieLatestRelease = new MutableLiveData<>();
        movieLatestRelease.setValue(dummyMovieLatestRelease);

        when(catalogRepository.getMovieLatestRelease(page)).thenReturn(movieLatestRelease);
        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMovieLatestRelease(page);
        assertNotNull(movieEntities);
        assertEquals(dummyMovieLatestRelease.size(), movieEntities.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieLatestRelease);
    }

    @Test
    public void getTVLatestRelease() {
        viewModel.setQueryType(QUERY_TYPE_TV_LATEST_RELEASE);

        List<MediaEntity> dummyTVLatestRelease = DataDummy.generateDummyTVLatestRelease();

        MutableLiveData<List<MediaEntity>> tvLatestRelease = new MutableLiveData<>();
        tvLatestRelease.setValue(dummyTVLatestRelease);

        when(catalogRepository.getTVLatestRelease(page)).thenReturn(tvLatestRelease);
        List<MediaEntity> tvEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getTVLatestRelease(page);
        assertNotNull(tvEntities);
        assertEquals(dummyTVLatestRelease.size(), tvEntities.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVLatestRelease);
    }

    @Test
    public void getMovieNowPlaying() {
        viewModel.setQueryType(QUERY_TYPE_MOVIE_NOW_PLAYING);

        List<MediaEntity> dummyMovieNowPlaying = DataDummy.generateDummyMovieNowPlaying();

        MutableLiveData<List<MediaEntity>> movieNowPlaying = new MutableLiveData<>();
        movieNowPlaying.setValue(dummyMovieNowPlaying);

        when(catalogRepository.getMovieNowPlaying(page)).thenReturn(movieNowPlaying);
        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMovieNowPlaying(page);
        assertNotNull(movieEntities);
        assertEquals(dummyMovieNowPlaying.size(), movieEntities.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieNowPlaying);
    }

    @Test
    public void getTVOnTheAir() {
        viewModel.setQueryType(QUERY_TYPE_TV_ON_THE_AIR);

        List<MediaEntity> dummyTVOnTheAir = DataDummy.generateDummyTVOnTheAir();

        MutableLiveData<List<MediaEntity>> tvOnTheAir = new MutableLiveData<>();
        tvOnTheAir.setValue(dummyTVOnTheAir);

        when(catalogRepository.getTVOnTheAir(page)).thenReturn(tvOnTheAir);
        List<MediaEntity> tvEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getTVOnTheAir(page);
        assertNotNull(tvEntities);
        assertEquals(dummyTVOnTheAir.size(), tvEntities.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVOnTheAir);
    }

    @Test
    public void getMovieUpcoming() {
        viewModel.setQueryType(QUERY_TYPE_MOVIE_UPCOMING);

        List<MediaEntity> dummyMovieUpcoming = DataDummy.generateDummyMovieUpcoming();

        MutableLiveData<List<MediaEntity>> movieUpcoming = new MutableLiveData<>();
        movieUpcoming.setValue(dummyMovieUpcoming);

        when(catalogRepository.getMovieUpcoming(page)).thenReturn(movieUpcoming);
        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMovieUpcoming(page);
        assertNotNull(movieEntities);
        assertEquals(dummyMovieUpcoming.size(), movieEntities.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieUpcoming);
    }

    @Test
    public void getMovieTopRated() {
        viewModel.setQueryType(QUERY_TYPE_MOVIE_TOP_RATED);

        List<MediaEntity> dummyMovieTopRated = DataDummy.generateDummyMovieTopRated();

        MutableLiveData<List<MediaEntity>> movieTopRated = new MutableLiveData<>();
        movieTopRated.setValue(dummyMovieTopRated);

        when(catalogRepository.getMovieTopRated(page)).thenReturn(movieTopRated);
        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMovieTopRated(page);
        assertNotNull(movieEntities);
        assertEquals(dummyMovieTopRated.size(), movieEntities.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieTopRated);
    }

    @Test
    public void getTVTopRated() {
        viewModel.setQueryType(QUERY_TYPE_TV_TOP_RATED);

        List<MediaEntity> dummyTVTopRated = DataDummy.generateDummyTVTopRated();

        MutableLiveData<List<MediaEntity>> tvTopRated = new MutableLiveData<>();
        tvTopRated.setValue(dummyTVTopRated);

        when(catalogRepository.getTVTopRated(page)).thenReturn(tvTopRated);
        List<MediaEntity> tvEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getTVTopRated(page);
        assertNotNull(tvEntities);
        assertEquals(dummyTVTopRated.size(), tvEntities.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVTopRated);
    }

    @Test
    public void getMoviePopular() {
        viewModel.setQueryType(QUERY_TYPE_MOVIE_POPULAR);

        List<MediaEntity> dummyMoviePopular = DataDummy.generateDummyMoviePopular();

        MutableLiveData<List<MediaEntity>> moviePopular = new MutableLiveData<>();
        moviePopular.setValue(dummyMoviePopular);

        when(catalogRepository.getMoviePopular(page)).thenReturn(moviePopular);
        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMoviePopular(page);
        assertNotNull(movieEntities);
        assertEquals(dummyMoviePopular.size(), movieEntities.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMoviePopular);
    }

    @Test
    public void getTVPopular() {
        viewModel.setQueryType(QUERY_TYPE_TV_POPULAR);

        List<MediaEntity> dummyTVPopular = DataDummy.generateDummyTVPopular();

        MutableLiveData<List<MediaEntity>> tvPopular = new MutableLiveData<>();
        tvPopular.setValue(dummyTVPopular);

        when(catalogRepository.getTVPopular(page)).thenReturn(tvPopular);
        List<MediaEntity> tvEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getTVPopular(page);
        assertNotNull(tvEntities);
        assertEquals(dummyTVPopular.size(), tvEntities.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVPopular);
    }

    @Test
    public void getMovieRecommendations() {
        viewModel.setQueryType(QUERY_TYPE_MOVIE_RECOMMENDATIONS);
        viewModel.setMediaId(movieId);

        List<MediaEntity> dummyMovieRecommendations = DataDummy.generateDummyMovieRecommendations();

        MutableLiveData<List<MediaEntity>> movieRecommendations = new MutableLiveData<>();
        movieRecommendations.setValue(dummyMovieRecommendations);

        when(catalogRepository.getMovieRecommendations(movieId, page)).thenReturn(movieRecommendations);
        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMovieRecommendations(movieId, page);
        assertNotNull(movieEntities);
        assertEquals(dummyMovieRecommendations.size(), movieEntities.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieRecommendations);
    }

    @Test
    public void getTVRecommendations() {
        viewModel.setQueryType(QUERY_TYPE_TV_RECOMMENDATIONS);
        viewModel.setMediaId(tvId);

        List<MediaEntity> dummyTVRecommendations = DataDummy.generateDummyTVRecommendations();

        MutableLiveData<List<MediaEntity>> tvRecommendations = new MutableLiveData<>();
        tvRecommendations.setValue(dummyTVRecommendations);

        when(catalogRepository.getTVRecommendations(tvId, page)).thenReturn(tvRecommendations);
        List<MediaEntity> tvEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getTVRecommendations(tvId, page);
        assertNotNull(tvEntities);
        assertEquals(dummyTVRecommendations.size(), tvEntities.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVRecommendations);
    }
}