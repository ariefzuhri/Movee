package com.ariefzuhri.movee.ui.search;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.ariefzuhri.movee.core.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.core.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.movee.core.data.repository.CatalogRepository;
import com.ariefzuhri.movee.core.utils.DataDummy;
import com.ariefzuhri.movee.search.SearchViewModel;
import com.ariefzuhri.movee.utils.LiveDataTestUtil;
import com.ariefzuhri.movee.core.data.repository.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.ariefzuhri.movee.core.utils.Constants.QUERY_TYPE_MOVIE_LATEST_RELEASE;
import static com.ariefzuhri.movee.core.utils.Constants.QUERY_TYPE_MOVIE_NOW_PLAYING;
import static com.ariefzuhri.movee.core.utils.Constants.QUERY_TYPE_MOVIE_POPULAR;
import static com.ariefzuhri.movee.core.utils.Constants.QUERY_TYPE_MOVIE_RECOMMENDATIONS;
import static com.ariefzuhri.movee.core.utils.Constants.QUERY_TYPE_MOVIE_TOP_RATED;
import static com.ariefzuhri.movee.core.utils.Constants.QUERY_TYPE_MOVIE_UPCOMING;
import static com.ariefzuhri.movee.core.utils.Constants.QUERY_TYPE_MULTI_SEARCH;
import static com.ariefzuhri.movee.core.utils.Constants.QUERY_TYPE_TV_LATEST_RELEASE;
import static com.ariefzuhri.movee.core.utils.Constants.QUERY_TYPE_TV_ON_THE_AIR;
import static com.ariefzuhri.movee.core.utils.Constants.QUERY_TYPE_TV_POPULAR;
import static com.ariefzuhri.movee.core.utils.Constants.QUERY_TYPE_TV_RECOMMENDATIONS;
import static com.ariefzuhri.movee.core.utils.Constants.QUERY_TYPE_TV_TOP_RATED;
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
    private Observer<Resource<List<MediaEntity>>> mediaEntitiesObserver;

    @Mock
    private Observer<Resource<List<GenreEntity>>> genreEntitiesObserver;

    @Before
    public void setUp() {
        viewModel = new SearchViewModel(application, catalogRepository);
        viewModel.setPage(page);
    }

    @Test
    public void getMultiSearch() {
        viewModel.setQueryType(QUERY_TYPE_MULTI_SEARCH);

        String query = "the promised neverland";
        viewModel.setQuery(query);

        Resource<List<MediaEntity>> dummyMultiSearch = Resource.success(DataDummy.generateDummyMultiSearch());
        MutableLiveData<Resource<List<MediaEntity>>> multiSearch = new MutableLiveData<>();
        multiSearch.setValue(dummyMultiSearch);

        when(catalogRepository.getMultiSearch(query, page)).thenReturn(multiSearch);
        Resource<List<MediaEntity>> mediaEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMultiSearch(query, page);

        assertNotNull(dummyMultiSearch.data);
        assertNotNull(mediaEntities.data);
        assertEquals(dummyMultiSearch.data.size(), mediaEntities.data.size());
    }

    @Test
    public void getGenres() {
        Resource<List<GenreEntity>> dummyGenres = Resource.success(DataDummy.generateDummyGenres());
        MutableLiveData<Resource<List<GenreEntity>>> movieGenres = new MutableLiveData<>();
        movieGenres.setValue(dummyGenres);

        when(catalogRepository.getGenres()).thenReturn(movieGenres);
        Resource<List<GenreEntity>> movieGenreEntities = LiveDataTestUtil.getValue(viewModel.getGenres());
        verify(catalogRepository).getGenres();

        assertNotNull(dummyGenres.data);
        assertNotNull(movieGenreEntities.data);
        assertEquals(dummyGenres.data.size(), movieGenreEntities.data.size());

        viewModel.getGenres().observeForever(genreEntitiesObserver);
        verify(genreEntitiesObserver).onChanged(dummyGenres);
    }

    @Test
    public void getMovieLatestRelease() {
        viewModel.setQueryType(QUERY_TYPE_MOVIE_LATEST_RELEASE);

        Resource<List<MediaEntity>> dummyMovieLatestRelease = Resource.success(DataDummy.generateDummyMovieLatestRelease());
        MutableLiveData<Resource<List<MediaEntity>>> movieLatestRelease = new MutableLiveData<>();
        movieLatestRelease.setValue(dummyMovieLatestRelease);

        when(catalogRepository.getMovieLatestRelease(page)).thenReturn(movieLatestRelease);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMovieLatestRelease(page);

        assertNotNull(dummyMovieLatestRelease.data);
        assertNotNull(movieEntities.data);
        assertEquals(dummyMovieLatestRelease.data.size(), movieEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieLatestRelease);
    }

    @Test
    public void getTVLatestRelease() {
        viewModel.setQueryType(QUERY_TYPE_TV_LATEST_RELEASE);

        Resource<List<MediaEntity>> dummyTVLatestRelease = Resource.success(DataDummy.generateDummyTVLatestRelease());
        MutableLiveData<Resource<List<MediaEntity>>> tvLatestRelease = new MutableLiveData<>();
        tvLatestRelease.setValue(dummyTVLatestRelease);

        when(catalogRepository.getTVLatestRelease(page)).thenReturn(tvLatestRelease);
        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getTVLatestRelease(page);

        assertNotNull(dummyTVLatestRelease.data);
        assertNotNull(tvEntities.data);
        assertEquals(dummyTVLatestRelease.data.size(), tvEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVLatestRelease);
    }

    @Test
    public void getMovieNowPlaying() {
        viewModel.setQueryType(QUERY_TYPE_MOVIE_NOW_PLAYING);

        Resource<List<MediaEntity>> dummyMovieNowPlaying = Resource.success(DataDummy.generateDummyMovieNowPlaying());
        MutableLiveData<Resource<List<MediaEntity>>> nowPlaying = new MutableLiveData<>();
        nowPlaying.setValue(dummyMovieNowPlaying);

        when(catalogRepository.getMovieNowPlaying(page)).thenReturn(nowPlaying);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMovieNowPlaying(page);

        assertNotNull(dummyMovieNowPlaying.data);
        assertNotNull(movieEntities.data);
        assertEquals(dummyMovieNowPlaying.data.size(), movieEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieNowPlaying);
    }

    @Test
    public void getTVOnTheAir() {
        viewModel.setQueryType(QUERY_TYPE_TV_ON_THE_AIR);

        Resource<List<MediaEntity>> dummyTVOnTheAir = Resource.success(DataDummy.generateDummyTVOnTheAir());
        MutableLiveData<Resource<List<MediaEntity>>> onTheAir = new MutableLiveData<>();
        onTheAir.setValue(dummyTVOnTheAir);

        when(catalogRepository.getTVOnTheAir(page)).thenReturn(onTheAir);
        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getTVOnTheAir(page);

        assertNotNull(dummyTVOnTheAir.data);
        assertNotNull(tvEntities.data);
        assertEquals(dummyTVOnTheAir.data.size(), tvEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVOnTheAir);
    }

    @Test
    public void getMovieUpcoming() {
        viewModel.setQueryType(QUERY_TYPE_MOVIE_UPCOMING);

        Resource<List<MediaEntity>> dummyMovieUpcoming = Resource.success(DataDummy.generateDummyMovieUpcoming());
        MutableLiveData<Resource<List<MediaEntity>>> movieUpcoming = new MutableLiveData<>();
        movieUpcoming.setValue(dummyMovieUpcoming);

        when(catalogRepository.getMovieUpcoming(page)).thenReturn(movieUpcoming);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMovieUpcoming(page);

        assertNotNull(dummyMovieUpcoming.data);
        assertNotNull(movieEntities.data);
        assertEquals(dummyMovieUpcoming.data.size(), movieEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieUpcoming);
    }

    @Test
    public void getMovieTopRated() {
        viewModel.setQueryType(QUERY_TYPE_MOVIE_TOP_RATED);

        Resource<List<MediaEntity>> dummyMovieTopRated = Resource.success(DataDummy.generateDummyMovieTopRated());
        MutableLiveData<Resource<List<MediaEntity>>> movieTopRated = new MutableLiveData<>();
        movieTopRated.setValue(dummyMovieTopRated);

        when(catalogRepository.getMovieTopRated(page)).thenReturn(movieTopRated);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMovieTopRated(page);

        assertNotNull(dummyMovieTopRated.data);
        assertNotNull(movieEntities.data);
        assertEquals(dummyMovieTopRated.data.size(), movieEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieTopRated);
    }

    @Test
    public void getTVTopRated() {
        viewModel.setQueryType(QUERY_TYPE_TV_TOP_RATED);

        Resource<List<MediaEntity>> dummyTVTopRated = Resource.success(DataDummy.generateDummyTVTopRated());
        MutableLiveData<Resource<List<MediaEntity>>> tvTopRated = new MutableLiveData<>();
        tvTopRated.setValue(dummyTVTopRated);

        when(catalogRepository.getTVTopRated(page)).thenReturn(tvTopRated);
        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getTVTopRated(page);

        assertNotNull(dummyTVTopRated.data);
        assertNotNull(tvEntities.data);
        assertEquals(dummyTVTopRated.data.size(), tvEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVTopRated);
    }

    @Test
    public void getMoviePopular() {
        viewModel.setQueryType(QUERY_TYPE_MOVIE_POPULAR);

        Resource<List<MediaEntity>> dummyMoviePopular = Resource.success(DataDummy.generateDummyMoviePopular());
        MutableLiveData<Resource<List<MediaEntity>>> moviePopular = new MutableLiveData<>();
        moviePopular.setValue(dummyMoviePopular);

        when(catalogRepository.getMoviePopular(page)).thenReturn(moviePopular);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMoviePopular(page);

        assertNotNull(dummyMoviePopular.data);
        assertNotNull(movieEntities.data);
        assertEquals(dummyMoviePopular.data.size(), movieEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMoviePopular);
    }

    @Test
    public void getTVPopular() {
        viewModel.setQueryType(QUERY_TYPE_TV_POPULAR);

        Resource<List<MediaEntity>> dummyTVPopular = Resource.success(DataDummy.generateDummyTVPopular());
        MutableLiveData<Resource<List<MediaEntity>>> tvPopular = new MutableLiveData<>();
        tvPopular.setValue(dummyTVPopular);

        when(catalogRepository.getTVPopular(page)).thenReturn(tvPopular);
        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getTVPopular(page);

        assertNotNull(dummyTVPopular.data);
        assertNotNull(tvEntities.data);
        assertEquals(dummyTVPopular.data.size(), tvEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVPopular);
    }

    @Test
    public void getMovieRecommendations() {
        viewModel.setQueryType(QUERY_TYPE_MOVIE_RECOMMENDATIONS);
        viewModel.setMediaId(movieId);

        Resource<List<MediaEntity>> dummyMovieRecommendations = Resource.success(DataDummy.generateDummyMovieRecommendations());
        MutableLiveData<Resource<List<MediaEntity>>> movieRecommendations = new MutableLiveData<>();
        movieRecommendations.setValue(dummyMovieRecommendations);

        when(catalogRepository.getMovieRecommendations(movieId, page)).thenReturn(movieRecommendations);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMovieRecommendations(movieId, page);

        assertNotNull(dummyMovieRecommendations.data);
        assertNotNull(movieEntities.data);
        assertEquals(dummyMovieRecommendations.data.size(), movieEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieRecommendations);
    }

    @Test
    public void getTVRecommendations() {
        viewModel.setQueryType(QUERY_TYPE_TV_RECOMMENDATIONS);
        viewModel.setMediaId(tvId);

        Resource<List<MediaEntity>> dummyTVRecommendations = Resource.success(DataDummy.generateDummyTVRecommendations());
        MutableLiveData<Resource<List<MediaEntity>>> tvRecommendations = new MutableLiveData<>();
        tvRecommendations.setValue(dummyTVRecommendations);

        when(catalogRepository.getTVRecommendations(tvId, page)).thenReturn(tvRecommendations);
        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getTVRecommendations(tvId, page);

        assertNotNull(tvEntities.data);
        assertNotNull(dummyTVRecommendations.data);
        assertEquals(dummyTVRecommendations.data.size(), tvEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVRecommendations);
    }
}