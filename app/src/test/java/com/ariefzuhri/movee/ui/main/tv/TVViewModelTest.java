package com.ariefzuhri.movee.ui.main.tv;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.ariefzuhri.movee.core.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.core.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.movee.core.data.repository.CatalogRepository;
import com.ariefzuhri.movee.main.tv.TVViewModel;
import com.ariefzuhri.movee.core.utils.DataDummy;
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
public class TVViewModelTest {

    private TVViewModel viewModel;

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
        viewModel = new TVViewModel(catalogRepository);
        viewModel.setTrendingPage(page);
    }

    @Test
    public void getOnTheAir() {
        Resource<List<MediaEntity>> dummyTVOnTheAir = Resource.success(DataDummy.generateDummyTVOnTheAir());
        MutableLiveData<Resource<List<MediaEntity>>> onTheAir = new MutableLiveData<>();
        onTheAir.setValue(dummyTVOnTheAir);

        when(catalogRepository.getTVOnTheAir(page)).thenReturn(onTheAir);
        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(viewModel.getOnTheAir());
        verify(catalogRepository).getTVOnTheAir(page);

        assertNotNull(dummyTVOnTheAir.data);
        assertNotNull(tvEntities.data);
        assertEquals(dummyTVOnTheAir.data.size(), tvEntities.data.size());

        viewModel.getOnTheAir().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVOnTheAir);
    }

    @Test
    public void getTrending() {
        Resource<List<MediaEntity>> dummyTVTrending = Resource.success(DataDummy.generateDummyTVTrending());
        MutableLiveData<Resource<List<MediaEntity>>> trending = new MutableLiveData<>();
        trending.setValue(dummyTVTrending);

        when(catalogRepository.getTVTrending(page)).thenReturn(trending);
        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(viewModel.getTrending());
        verify(catalogRepository).getTVTrending(page);

        assertNotNull(dummyTVTrending.data);
        assertNotNull(tvEntities.data);
        assertEquals(dummyTVTrending.data.size(), tvEntities.data.size());

        viewModel.getTrending().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVTrending);
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