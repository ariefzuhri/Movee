package com.ariefzuhri.movee.ui.main.discover;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.ariefzuhri.movee.core.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.core.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.movee.core.data.repository.CatalogRepository;
import com.ariefzuhri.movee.core.utils.DataDummy;
import com.ariefzuhri.movee.main.discover.DiscoverViewModel;
import com.ariefzuhri.movee.utils.LiveDataTestUtil;
import com.ariefzuhri.movee.core.data.repository.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

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
    private Observer<Resource<List<MediaEntity>>> mediaEntitiesObserver;

    @Mock
    private Observer<Resource<List<GenreEntity>>> genreEntitiesObserver;

    @Before
    public void setUp() {
        viewModel = new DiscoverViewModel(catalogRepository);
    }

    @Test
    public void getMovieUpcoming() {
        Resource<List<MediaEntity>> dummyMovieUpcoming = Resource.success(DataDummy.generateDummyMovieUpcoming());
        MutableLiveData<Resource<List<MediaEntity>>> movieUpcoming = new MutableLiveData<>();
        movieUpcoming.setValue(dummyMovieUpcoming);

        when(catalogRepository.getMovieUpcoming(page)).thenReturn(movieUpcoming);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getMovieUpcoming());
        verify(catalogRepository).getMovieUpcoming(page);

        assertNotNull(dummyMovieUpcoming.data);
        assertNotNull(movieEntities.data);
        assertEquals(dummyMovieUpcoming.data.size(), movieEntities.data.size());

        viewModel.getMovieUpcoming().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieUpcoming);
    }

    @Test
    public void getMoviePopular() {
        Resource<List<MediaEntity>> dummyMoviePopular = Resource.success(DataDummy.generateDummyMoviePopular());
        MutableLiveData<Resource<List<MediaEntity>>> moviePopular = new MutableLiveData<>();
        moviePopular.setValue(dummyMoviePopular);

        when(catalogRepository.getMoviePopular(page)).thenReturn(moviePopular);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getMoviePopular());
        verify(catalogRepository).getMoviePopular(page);

        assertNotNull(dummyMoviePopular.data);
        assertNotNull(movieEntities.data);
        assertEquals(dummyMoviePopular.data.size(), movieEntities.data.size());

        viewModel.getMoviePopular().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMoviePopular);
    }

    @Test
    public void getTVPopular() {
        Resource<List<MediaEntity>> dummyTVPopular = Resource.success(DataDummy.generateDummyTVPopular());
        MutableLiveData<Resource<List<MediaEntity>>> tvPopular = new MutableLiveData<>();
        tvPopular.setValue(dummyTVPopular);

        when(catalogRepository.getTVPopular(page)).thenReturn(tvPopular);
        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(viewModel.getTVPopular());
        verify(catalogRepository).getTVPopular(page);

        assertNotNull(dummyTVPopular.data);
        assertNotNull(tvEntities.data);
        assertEquals(dummyTVPopular.data.size(), tvEntities.data.size());

        viewModel.getTVPopular().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVPopular);
    }

    @Test
    public void getMovieTopRated() {
        Resource<List<MediaEntity>> dummyMovieTopRated = Resource.success(DataDummy.generateDummyMovieTopRated());
        MutableLiveData<Resource<List<MediaEntity>>> movieTopRated = new MutableLiveData<>();
        movieTopRated.setValue(dummyMovieTopRated);

        when(catalogRepository.getMovieTopRated(page)).thenReturn(movieTopRated);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getMovieTopRated());
        verify(catalogRepository).getMovieTopRated(page);

        assertNotNull(dummyMovieTopRated.data);
        assertNotNull(movieEntities.data);
        assertEquals(dummyMovieTopRated.data.size(), movieEntities.data.size());

        viewModel.getMovieTopRated().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieTopRated);
    }

    @Test
    public void getTVTopRated() {
        Resource<List<MediaEntity>> dummyTVTopRated = Resource.success(DataDummy.generateDummyTVTopRated());
        MutableLiveData<Resource<List<MediaEntity>>> tvTopRated = new MutableLiveData<>();
        tvTopRated.setValue(dummyTVTopRated);

        when(catalogRepository.getTVTopRated(page)).thenReturn(tvTopRated);
        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(viewModel.getTVTopRated());
        verify(catalogRepository).getTVTopRated(page);

        assertNotNull(dummyTVTopRated.data);
        assertNotNull(tvEntities.data);
        assertEquals(dummyTVTopRated.data.size(), tvEntities.data.size());

        viewModel.getTVTopRated().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVTopRated);
    }

    @Test
    public void getMovieLatestRelease() {
        Resource<List<MediaEntity>> dummyMovieLatestRelease = Resource.success(DataDummy.generateDummyMovieLatestRelease());
        MutableLiveData<Resource<List<MediaEntity>>> movieLatestRelease = new MutableLiveData<>();
        movieLatestRelease.setValue(dummyMovieLatestRelease);

        when(catalogRepository.getMovieLatestRelease(page)).thenReturn(movieLatestRelease);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getMovieLatestRelease());
        verify(catalogRepository).getMovieLatestRelease(page);

        assertNotNull(dummyMovieLatestRelease.data);
        assertNotNull(movieEntities.data);
        assertEquals(dummyMovieLatestRelease.data.size(), movieEntities.data.size());

        viewModel.getMovieLatestRelease().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieLatestRelease);
    }

    @Test
    public void getTVLatestRelease() {
        Resource<List<MediaEntity>> dummyTVLatestRelease = Resource.success(DataDummy.generateDummyTVLatestRelease());
        MutableLiveData<Resource<List<MediaEntity>>> tvLatestRelease = new MutableLiveData<>();
        tvLatestRelease.setValue(dummyTVLatestRelease);

        when(catalogRepository.getTVLatestRelease(page)).thenReturn(tvLatestRelease);
        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(viewModel.getTVLatestRelease());
        verify(catalogRepository).getTVLatestRelease(page);

        assertNotNull(dummyTVLatestRelease.data);
        assertNotNull(tvEntities.data);
        assertEquals(dummyTVLatestRelease.data.size(), tvEntities.data.size());

        viewModel.getTVLatestRelease().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVLatestRelease);
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
}