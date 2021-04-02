package com.ariefzuhri.blu.ui.main.discover;

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
public class DiscoverViewModelTest {
    private DiscoverViewModel viewModel;

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
        viewModel = new DiscoverViewModel(catalogRepository);
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
}