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
import com.ariefzuhri.movee.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_MOVIE_LATEST_RELEASE;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_MOVIE_NOW_PLAYING;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_MOVIE_POPULAR;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_MOVIE_RECOMMENDATIONS;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_MOVIE_TOP_RATED;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_MOVIE_UPCOMING;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_MULTI_SEARCH;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_TV_LATEST_RELEASE;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_TV_ON_THE_AIR;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_TV_POPULAR;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_TV_RECOMMENDATIONS;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_TV_TOP_RATED;
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
    public void setUp(){
        viewModel = new SearchViewModel(application, catalogRepository);
        viewModel.setPage(page);
    }

    @Test
    public void getMultiSearch() {
        viewModel.setQueryType(QUERY_TYPE_MULTI_SEARCH);

        String query = "the promised neverland";
        viewModel.setQuery(query);

        List<MediaEntity> dummyMultiSearch = DataDummy.generateDummyMultiSearch();
        MutableLiveData<Resource<List<MediaEntity>>> multiSearch = new MutableLiveData<>();
        multiSearch.setValue(Resource.success(dummyMultiSearch));

        when(catalogRepository.getMultiSearch(query, page)).thenReturn(multiSearch);
        Resource<List<MediaEntity>> mediaEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMultiSearch(query, page);
        assertNotNull(mediaEntities.data);
        assertEquals(dummyMultiSearch.size(), mediaEntities.data.size());
    }

    @Test
    public void getGenres() {
        List<GenreEntity> dummyGenres = DataDummy.generateDummyGenres();
        MutableLiveData<Resource<List<GenreEntity>>> movieGenres = new MutableLiveData<>();
        movieGenres.setValue(Resource.success(dummyGenres));

        when(catalogRepository.getGenres()).thenReturn(movieGenres);
        Resource<List<GenreEntity>> movieGenreEntities = LiveDataTestUtil.getValue(viewModel.getGenres());
        verify(catalogRepository).getGenres();
        assertNotNull(movieGenreEntities.data);
        assertEquals(dummyGenres.size(), movieGenreEntities.data.size());

        viewModel.getGenres().observeForever(genreEntitiesObserver);
        verify(genreEntitiesObserver).onChanged(Resource.success(dummyGenres));
    }

    @Test
    public void getMovieLatestRelease() {
        viewModel.setQueryType(QUERY_TYPE_MOVIE_LATEST_RELEASE);

        List<MediaEntity> dummyMovieLatestRelease = DataDummy.generateDummyMovieLatestRelease();
        MutableLiveData<Resource<List<MediaEntity>>> movieLatestRelease = new MutableLiveData<>();
        movieLatestRelease.setValue(Resource.success(dummyMovieLatestRelease));

        when(catalogRepository.getMovieLatestRelease(page)).thenReturn(movieLatestRelease);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMovieLatestRelease(page);
        assertNotNull(movieEntities.data);
        assertEquals(dummyMovieLatestRelease.size(), movieEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success(dummyMovieLatestRelease));
    }

    @Test
    public void getTVLatestRelease() {
        viewModel.setQueryType(QUERY_TYPE_TV_LATEST_RELEASE);

        List<MediaEntity> dummyTVLatestRelease = DataDummy.generateDummyTVLatestRelease();
        MutableLiveData<Resource<List<MediaEntity>>> tvLatestRelease = new MutableLiveData<>();
        tvLatestRelease.setValue(Resource.success(dummyTVLatestRelease));

        when(catalogRepository.getTVLatestRelease(page)).thenReturn(tvLatestRelease);
        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getTVLatestRelease(page);
        assertNotNull(tvEntities.data);
        assertEquals(dummyTVLatestRelease.size(), tvEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success(dummyTVLatestRelease));
    }

    @Test
    public void getMovieNowPlaying() {
        viewModel.setQueryType(QUERY_TYPE_MOVIE_NOW_PLAYING);

        List<MediaEntity> dummyMovieNowPlaying = DataDummy.generateDummyMovieNowPlaying();
        MutableLiveData<Resource<List<MediaEntity>>> nowPlaying = new MutableLiveData<>();
        nowPlaying.setValue(Resource.success(dummyMovieNowPlaying));

        when(catalogRepository.getMovieNowPlaying(page)).thenReturn(nowPlaying);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMovieNowPlaying(page);
        assertNotNull(movieEntities.data);

        assertEquals(dummyMovieNowPlaying.size(), movieEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success(dummyMovieNowPlaying));
    }

    @Test
    public void getTVOnTheAir() {
        viewModel.setQueryType(QUERY_TYPE_TV_ON_THE_AIR);

        List<MediaEntity> dummyTVOnTheAir = DataDummy.generateDummyTVOnTheAir();
        MutableLiveData<Resource<List<MediaEntity>>> onTheAir = new MutableLiveData<>();
        onTheAir.setValue(Resource.success(dummyTVOnTheAir));

        when(catalogRepository.getTVOnTheAir(page)).thenReturn(onTheAir);
        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getTVOnTheAir(page);
        assertNotNull(tvEntities.data);
        assertEquals(dummyTVOnTheAir.size(), tvEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success(dummyTVOnTheAir));
    }

    @Test
    public void getMovieUpcoming() {
        viewModel.setQueryType(QUERY_TYPE_MOVIE_UPCOMING);

        List<MediaEntity> dummyMovieUpcoming = DataDummy.generateDummyMovieUpcoming();
        MutableLiveData<Resource<List<MediaEntity>>> movieUpcoming = new MutableLiveData<>();
        movieUpcoming.setValue(Resource.success(dummyMovieUpcoming));

        when(catalogRepository.getMovieUpcoming(page)).thenReturn(movieUpcoming);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMovieUpcoming(page);
        assertNotNull(movieEntities.data);
        assertEquals(dummyMovieUpcoming.size(), movieEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success(dummyMovieUpcoming));
    }

    @Test
    public void getMovieTopRated() {
        viewModel.setQueryType(QUERY_TYPE_MOVIE_TOP_RATED);

        List<MediaEntity> dummyMovieTopRated = DataDummy.generateDummyMovieTopRated();
        MutableLiveData<Resource<List<MediaEntity>>> movieTopRated = new MutableLiveData<>();
        movieTopRated.setValue(Resource.success(dummyMovieTopRated));

        when(catalogRepository.getMovieTopRated(page)).thenReturn(movieTopRated);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMovieTopRated(page);
        assertNotNull(movieEntities.data);
        assertEquals(dummyMovieTopRated.size(), movieEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success(dummyMovieTopRated));
    }

    @Test
    public void getTVTopRated() {
        viewModel.setQueryType(QUERY_TYPE_TV_TOP_RATED);

        List<MediaEntity> dummyTVTopRated = DataDummy.generateDummyTVTopRated();
        MutableLiveData<Resource<List<MediaEntity>>> tvTopRated = new MutableLiveData<>();
        tvTopRated.setValue(Resource.success(dummyTVTopRated));

        when(catalogRepository.getTVTopRated(page)).thenReturn(tvTopRated);
        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getTVTopRated(page);
        assertNotNull(tvEntities.data);
        assertEquals(dummyTVTopRated.size(), tvEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success((dummyTVTopRated)));
    }

    @Test
    public void getMoviePopular() {
        viewModel.setQueryType(QUERY_TYPE_MOVIE_POPULAR);

        List<MediaEntity> dummyMoviePopular = DataDummy.generateDummyMoviePopular();
        MutableLiveData<Resource<List<MediaEntity>>> moviePopular = new MutableLiveData<>();
        moviePopular.setValue(Resource.success(dummyMoviePopular));

        when(catalogRepository.getMoviePopular(page)).thenReturn(moviePopular);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMoviePopular(page);
        assertNotNull(movieEntities.data);
        assertEquals(dummyMoviePopular.size(), movieEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success(dummyMoviePopular));
    }

    @Test
    public void getTVPopular() {
        viewModel.setQueryType(QUERY_TYPE_TV_POPULAR);

        List<MediaEntity> dummyTVPopular = DataDummy.generateDummyTVPopular();
        MutableLiveData<Resource<List<MediaEntity>>> tvPopular = new MutableLiveData<>();
        tvPopular.setValue(Resource.success(dummyTVPopular));

        when(catalogRepository.getTVPopular(page)).thenReturn(tvPopular);
        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getTVPopular(page);
        assertNotNull(tvEntities.data);
        assertEquals(dummyTVPopular.size(), tvEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success(dummyTVPopular));
    }

    @Test
    public void getMovieRecommendations() {
        viewModel.setQueryType(QUERY_TYPE_MOVIE_RECOMMENDATIONS);
        viewModel.setMediaId(movieId);

        List<MediaEntity> dummyMovieRecommendations = DataDummy.generateDummyMovieRecommendations();
        MutableLiveData<Resource<List<MediaEntity>>> movieRecommendations = new MutableLiveData<>();
        movieRecommendations.setValue(Resource.success(dummyMovieRecommendations));

        when(catalogRepository.getMovieRecommendations(movieId, page)).thenReturn(movieRecommendations);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getMovieRecommendations(movieId, page);
        assertNotNull(movieEntities.data);
        assertEquals(dummyMovieRecommendations.size(), movieEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success(dummyMovieRecommendations));
    }

    @Test
    public void getTVRecommendations() {
        viewModel.setQueryType(QUERY_TYPE_TV_RECOMMENDATIONS);
        viewModel.setMediaId(tvId);

        List<MediaEntity> dummyTVRecommendations = DataDummy.generateDummyTVRecommendations();
        MutableLiveData<Resource<List<MediaEntity>>> tvRecommendations = new MutableLiveData<>();
        tvRecommendations.setValue(Resource.success(dummyTVRecommendations));

        when(catalogRepository.getTVRecommendations(tvId, page)).thenReturn(tvRecommendations);
        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(viewModel.getSearchResult());
        verify(catalogRepository).getTVRecommendations(tvId, page);
        assertNotNull(tvEntities.data);
        assertEquals(dummyTVRecommendations.size(), tvEntities.data.size());

        viewModel.getSearchResult().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success(dummyTVRecommendations));
    }
}