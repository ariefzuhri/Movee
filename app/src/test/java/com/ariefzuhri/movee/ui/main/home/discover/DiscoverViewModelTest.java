package com.ariefzuhri.movee.ui.main.home.discover;

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
    public void setUp(){
        viewModel = new DiscoverViewModel(catalogRepository);
    }

    @Test
    public void getMovieUpcoming() {
        List<MediaEntity> dummyMovieUpcoming = DataDummy.generateDummyMovieUpcoming();
        MutableLiveData<Resource<List<MediaEntity>>> movieUpcoming = new MutableLiveData<>();
        movieUpcoming.setValue(Resource.success(dummyMovieUpcoming));

        when(catalogRepository.getMovieUpcoming(page)).thenReturn(movieUpcoming);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getMovieUpcoming());
        verify(catalogRepository).getMovieUpcoming(page);
        assertNotNull(movieEntities.data);
        assertEquals(dummyMovieUpcoming.size(), movieEntities.data.size());

        viewModel.getMovieUpcoming().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success(dummyMovieUpcoming));
    }

    @Test
    public void getMoviePopular() {
        List<MediaEntity> dummyMoviePopular = DataDummy.generateDummyMoviePopular();
        MutableLiveData<Resource<List<MediaEntity>>> moviePopular = new MutableLiveData<>();
        moviePopular.setValue(Resource.success(dummyMoviePopular));

        when(catalogRepository.getMoviePopular(page)).thenReturn(moviePopular);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getMoviePopular());
        verify(catalogRepository).getMoviePopular(page);
        assertNotNull(movieEntities.data);
        assertEquals(dummyMoviePopular.size(), movieEntities.data.size());

        viewModel.getMoviePopular().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success(dummyMoviePopular));
    }

    @Test
    public void getTVPopular() {
        List<MediaEntity> dummyTVPopular = DataDummy.generateDummyTVPopular();
        MutableLiveData<Resource<List<MediaEntity>>> tvPopular = new MutableLiveData<>();
        tvPopular.setValue(Resource.success(dummyTVPopular));

        when(catalogRepository.getTVPopular(page)).thenReturn(tvPopular);
        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(viewModel.getTVPopular());
        verify(catalogRepository).getTVPopular(page);
        assertNotNull(tvEntities.data);
        assertEquals(dummyTVPopular.size(), tvEntities.data.size());

        viewModel.getTVPopular().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success(dummyTVPopular));
    }

    @Test
    public void getMovieTopRated() {
        List<MediaEntity> dummyMovieTopRated = DataDummy.generateDummyMovieTopRated();
        MutableLiveData<Resource<List<MediaEntity>>> movieTopRated = new MutableLiveData<>();
        movieTopRated.setValue(Resource.success(dummyMovieTopRated));

        when(catalogRepository.getMovieTopRated(page)).thenReturn(movieTopRated);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getMovieTopRated());
        verify(catalogRepository).getMovieTopRated(page);
        assertNotNull(movieEntities.data);
        assertEquals(dummyMovieTopRated.size(), movieEntities.data.size());

        viewModel.getMovieTopRated().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success(dummyMovieTopRated));
    }

    @Test
    public void getTVTopRated() {
        List<MediaEntity> dummyTVTopRated = DataDummy.generateDummyTVTopRated();
        MutableLiveData<Resource<List<MediaEntity>>> tvTopRated = new MutableLiveData<>();
        tvTopRated.setValue(Resource.success(dummyTVTopRated));

        when(catalogRepository.getTVTopRated(page)).thenReturn(tvTopRated);
        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(viewModel.getTVTopRated());
        verify(catalogRepository).getTVTopRated(page);
        assertNotNull(tvEntities.data);
        assertEquals(dummyTVTopRated.size(), tvEntities.data.size());

        viewModel.getTVTopRated().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success((dummyTVTopRated)));
    }

    @Test
    public void getMovieLatestRelease() {
        List<MediaEntity> dummyMovieLatestRelease = DataDummy.generateDummyMovieLatestRelease();
        MutableLiveData<Resource<List<MediaEntity>>> movieLatestRelease = new MutableLiveData<>();
        movieLatestRelease.setValue(Resource.success(dummyMovieLatestRelease));

        when(catalogRepository.getMovieLatestRelease(page)).thenReturn(movieLatestRelease);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getMovieLatestRelease());
        verify(catalogRepository).getMovieLatestRelease(page);
        assertNotNull(movieEntities.data);
        assertEquals(dummyMovieLatestRelease.size(), movieEntities.data.size());

        viewModel.getMovieLatestRelease().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success(dummyMovieLatestRelease));
    }

    @Test
    public void getTVLatestRelease() {
        List<MediaEntity> dummyTVLatestRelease = DataDummy.generateDummyTVLatestRelease();
        MutableLiveData<Resource<List<MediaEntity>>> tvLatestRelease = new MutableLiveData<>();
        tvLatestRelease.setValue(Resource.success(dummyTVLatestRelease));

        when(catalogRepository.getTVLatestRelease(page)).thenReturn(tvLatestRelease);
        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(viewModel.getTVLatestRelease());
        verify(catalogRepository).getTVLatestRelease(page);
        assertNotNull(tvEntities.data);
        assertEquals(dummyTVLatestRelease.size(), tvEntities.data.size());

        viewModel.getTVLatestRelease().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success(dummyTVLatestRelease));
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
}