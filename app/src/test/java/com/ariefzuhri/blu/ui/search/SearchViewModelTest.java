package com.ariefzuhri.blu.ui.search;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.ariefzuhri.blu.data.GenreEntity;
import com.ariefzuhri.blu.data.MediaEntity;
import com.ariefzuhri.blu.data.source.CatalogRepository;
import com.ariefzuhri.blu.utils.DataDummy;
import com.ariefzuhri.blu.utils.LiveDataTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.ariefzuhri.blu.utils.Constants.MEDIA_TYPE_MOVIE;
import static com.ariefzuhri.blu.utils.Constants.MEDIA_TYPE_TV;
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
    private CatalogRepository catalogRepository;

    @Mock
    private Observer<List<MediaEntity>> mediaEntitiesObserver;

    @Mock
    private Observer<List<GenreEntity>> genreEntitiesObserver;

    @Before
    public void setUp(){
        viewModel = new SearchViewModel(catalogRepository);
        viewModel.setPage(page);
    }

    @Test
    public void getMultiSearch() {
        String query = "the promised neverland";

        List<MediaEntity> dummyMultiSearch = DataDummy.generateDummyMultiSearch();

        MutableLiveData<List<MediaEntity>> multiSearch = new MutableLiveData<>();
        multiSearch.setValue(dummyMultiSearch);

        when(catalogRepository.getMultiSearch(query, page)).thenReturn(multiSearch);
        List<MediaEntity> mediaEntities = LiveDataTestUtil.getValue(viewModel.getMultiSearch(query));
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
        List<GenreEntity> movieGenreEntities = LiveDataTestUtil.getValue(viewModel.getGenres(MEDIA_TYPE_MOVIE));
        verify(catalogRepository).getGenres(MEDIA_TYPE_MOVIE);
        assertNotNull(movieGenreEntities);
        assertEquals(dummyMovieGenres.size(), movieGenreEntities.size());

        viewModel.getGenres(MEDIA_TYPE_MOVIE).observeForever(genreEntitiesObserver);
        verify(genreEntitiesObserver).onChanged(dummyMovieGenres);

        MutableLiveData<List<GenreEntity>> tvGenres = new MutableLiveData<>();
        tvGenres.setValue(dummyTVGenres);

        when(catalogRepository.getGenres(MEDIA_TYPE_TV)).thenReturn(tvGenres);
        List<GenreEntity> tvGenreEntities = LiveDataTestUtil.getValue(viewModel.getGenres(MEDIA_TYPE_TV));
        verify(catalogRepository).getGenres(MEDIA_TYPE_TV);
        assertNotNull(tvGenreEntities);
        assertEquals(dummyTVGenres.size(), tvGenreEntities.size());

        viewModel.getGenres(MEDIA_TYPE_TV).observeForever(genreEntitiesObserver);
        verify(genreEntitiesObserver).onChanged(dummyTVGenres);
    }

    @Test
    public void getMovieTrending() {
        List<MediaEntity> dummyMovieTrending = DataDummy.generateDummyMovieTrending();

        MutableLiveData<List<MediaEntity>> movieTrending = new MutableLiveData<>();
        movieTrending.setValue(dummyMovieTrending);

        when(catalogRepository.getMovieTrending(page)).thenReturn(movieTrending);
        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(viewModel.getMovieTrending());
        verify(catalogRepository).getMovieTrending(page);
        assertNotNull(movieEntities);
        assertEquals(dummyMovieTrending.size(), movieEntities.size());

        viewModel.getMovieTrending().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieTrending);
    }

    @Test
    public void getTVTrending() {
        List<MediaEntity> dummyTVTrending = DataDummy.generateDummyTVTrending();

        MutableLiveData<List<MediaEntity>> tvTrending = new MutableLiveData<>();
        tvTrending.setValue(dummyTVTrending);

        when(catalogRepository.getTVTrending(page)).thenReturn(tvTrending);
        List<MediaEntity> tvEntities = LiveDataTestUtil.getValue(viewModel.getTVTrending());
        verify(catalogRepository).getTVTrending(page);
        assertNotNull(tvEntities);
        assertEquals(dummyTVTrending.size(), tvEntities.size());

        viewModel.getTVTrending().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVTrending);
    }

    @Test
    public void getMovieLatestRelease() {
        List<MediaEntity> dummyMovieLatestRelease = DataDummy.generateDummyMovieLatestRelease();

        MutableLiveData<List<MediaEntity>> movieLatestRelease = new MutableLiveData<>();
        movieLatestRelease.setValue(dummyMovieLatestRelease);

        when(catalogRepository.getMovieLatestRelease(page)).thenReturn(movieLatestRelease);
        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(viewModel.getMovieLatestRelease());
        verify(catalogRepository).getMovieLatestRelease(page);
        assertNotNull(movieEntities);
        assertEquals(dummyMovieLatestRelease.size(), movieEntities.size());

        viewModel.getMovieLatestRelease().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieLatestRelease);
    }

    @Test
    public void getTVLatestRelease() {
        List<MediaEntity> dummyTVLatestRelease = DataDummy.generateDummyTVLatestRelease();

        MutableLiveData<List<MediaEntity>> tvLatestRelease = new MutableLiveData<>();
        tvLatestRelease.setValue(dummyTVLatestRelease);

        when(catalogRepository.getTVLatestRelease(page)).thenReturn(tvLatestRelease);
        List<MediaEntity> tvEntities = LiveDataTestUtil.getValue(viewModel.getTVLatestRelease());
        verify(catalogRepository).getTVLatestRelease(page);
        assertNotNull(tvEntities);
        assertEquals(dummyTVLatestRelease.size(), tvEntities.size());

        viewModel.getTVLatestRelease().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVLatestRelease);
    }

    @Test
    public void getMovieNowPlaying() {
        List<MediaEntity> dummyMovieNowPlaying = DataDummy.generateDummyMovieNowPlaying();

        MutableLiveData<List<MediaEntity>> movieNowPlaying = new MutableLiveData<>();
        movieNowPlaying.setValue(dummyMovieNowPlaying);

        when(catalogRepository.getMovieNowPlaying(page)).thenReturn(movieNowPlaying);
        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(viewModel.getMovieNowPlaying());
        verify(catalogRepository).getMovieNowPlaying(page);
        assertNotNull(movieEntities);
        assertEquals(dummyMovieNowPlaying.size(), movieEntities.size());

        viewModel.getMovieNowPlaying().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieNowPlaying);
    }

    @Test
    public void getTVOnTheAir() {
        List<MediaEntity> dummyTVOnTheAir = DataDummy.generateDummyTVOnTheAir();

        MutableLiveData<List<MediaEntity>> tvOnTheAir = new MutableLiveData<>();
        tvOnTheAir.setValue(dummyTVOnTheAir);

        when(catalogRepository.getTVOnTheAir(page)).thenReturn(tvOnTheAir);
        List<MediaEntity> tvEntities = LiveDataTestUtil.getValue(viewModel.getTVOnTheAir());
        verify(catalogRepository).getTVOnTheAir(page);
        assertNotNull(tvEntities);
        assertEquals(dummyTVOnTheAir.size(), tvEntities.size());

        viewModel.getTVOnTheAir().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVOnTheAir);
    }

    @Test
    public void getMovieUpcoming() {
        List<MediaEntity> dummyMovieUpcoming = DataDummy.generateDummyMovieUpcoming();

        MutableLiveData<List<MediaEntity>> movieUpcoming = new MutableLiveData<>();
        movieUpcoming.setValue(dummyMovieUpcoming);

        when(catalogRepository.getMovieUpcoming(page)).thenReturn(movieUpcoming);
        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(viewModel.getMovieUpcoming());
        verify(catalogRepository).getMovieUpcoming(page);
        assertNotNull(movieEntities);
        assertEquals(dummyMovieUpcoming.size(), movieEntities.size());

        viewModel.getMovieUpcoming().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieUpcoming);
    }

    @Test
    public void getMovieTopRated() {
        List<MediaEntity> dummyMovieTopRated = DataDummy.generateDummyMovieTopRated();

        MutableLiveData<List<MediaEntity>> movieTopRated = new MutableLiveData<>();
        movieTopRated.setValue(dummyMovieTopRated);

        when(catalogRepository.getMovieTopRated(page)).thenReturn(movieTopRated);
        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(viewModel.getMovieTopRated());
        verify(catalogRepository).getMovieTopRated(page);
        assertNotNull(movieEntities);
        assertEquals(dummyMovieTopRated.size(), movieEntities.size());

        viewModel.getMovieTopRated().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieTopRated);
    }

    @Test
    public void getTVTopRated() {
        List<MediaEntity> dummyTVTopRated = DataDummy.generateDummyTVTopRated();

        MutableLiveData<List<MediaEntity>> tvTopRated = new MutableLiveData<>();
        tvTopRated.setValue(dummyTVTopRated);

        when(catalogRepository.getTVTopRated(page)).thenReturn(tvTopRated);
        List<MediaEntity> tvEntities = LiveDataTestUtil.getValue(viewModel.getTVTopRated());
        verify(catalogRepository).getTVTopRated(page);
        assertNotNull(tvEntities);
        assertEquals(dummyTVTopRated.size(), tvEntities.size());

        viewModel.getTVTopRated().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVTopRated);
    }

    @Test
    public void getMoviePopular() {
        List<MediaEntity> dummyMoviePopular = DataDummy.generateDummyMoviePopular();

        MutableLiveData<List<MediaEntity>> moviePopular = new MutableLiveData<>();
        moviePopular.setValue(dummyMoviePopular);

        when(catalogRepository.getMoviePopular(page)).thenReturn(moviePopular);
        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(viewModel.getMoviePopular());
        verify(catalogRepository).getMoviePopular(page);
        assertNotNull(movieEntities);
        assertEquals(dummyMoviePopular.size(), movieEntities.size());

        viewModel.getMoviePopular().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMoviePopular);
    }

    @Test
    public void getTVPopular() {
        List<MediaEntity> dummyTVPopular = DataDummy.generateDummyTVPopular();

        MutableLiveData<List<MediaEntity>> tvPopular = new MutableLiveData<>();
        tvPopular.setValue(dummyTVPopular);

        when(catalogRepository.getTVPopular(page)).thenReturn(tvPopular);
        List<MediaEntity> tvEntities = LiveDataTestUtil.getValue(viewModel.getTVPopular());
        verify(catalogRepository).getTVPopular(page);
        assertNotNull(tvEntities);
        assertEquals(dummyTVPopular.size(), tvEntities.size());

        viewModel.getTVPopular().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVPopular);
    }

    @Test
    public void getMovieRecommendations() {
        List<MediaEntity> dummyMovieRecommendations = DataDummy.generateDummyMovieRecommendations();

        MutableLiveData<List<MediaEntity>> movieRecommendations = new MutableLiveData<>();
        movieRecommendations.setValue(dummyMovieRecommendations);

        when(catalogRepository.getMovieRecommendations(movieId, page)).thenReturn(movieRecommendations);
        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(viewModel.getMovieRecommendations(movieId));
        verify(catalogRepository).getMovieRecommendations(movieId, page);
        assertNotNull(movieEntities);
        assertEquals(dummyMovieRecommendations.size(), movieEntities.size());

        viewModel.getMovieRecommendations(movieId).observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieRecommendations);
    }

    @Test
    public void getTVRecommendations() {
        List<MediaEntity> dummyTVRecommendations = DataDummy.generateDummyTVRecommendations();

        MutableLiveData<List<MediaEntity>> tvRecommendations = new MutableLiveData<>();
        tvRecommendations.setValue(dummyTVRecommendations);

        when(catalogRepository.getTVRecommendations(tvId, page)).thenReturn(tvRecommendations);
        List<MediaEntity> tvEntities = LiveDataTestUtil.getValue(viewModel.getTVRecommendations(tvId));
        verify(catalogRepository).getTVRecommendations(tvId, page);
        assertNotNull(tvEntities);
        assertEquals(dummyTVRecommendations.size(), tvEntities.size());

        viewModel.getTVRecommendations(tvId).observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVRecommendations);
    }
}