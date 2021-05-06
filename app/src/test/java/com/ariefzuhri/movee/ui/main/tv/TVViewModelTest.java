package com.ariefzuhri.movee.ui.main.tv;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.ariefzuhri.movee.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.movee.data.CatalogRepository;
import com.ariefzuhri.movee.ui.main.home.tv.TVViewModel;
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
    public void setUp(){
        viewModel = new TVViewModel(catalogRepository);
        viewModel.setTrendingPage(page);
    }

    @Test
    public void getOnTheAir() {
        List<MediaEntity> dummyTVOnTheAir = DataDummy.generateDummyTVOnTheAir();
        MutableLiveData<Resource<List<MediaEntity>>> onTheAir = new MutableLiveData<>();
        onTheAir.setValue(Resource.success(dummyTVOnTheAir));

        when(catalogRepository.getTVOnTheAir(page)).thenReturn(onTheAir);
        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(viewModel.getOnTheAir());
        verify(catalogRepository).getTVOnTheAir(page);
        assertNotNull(tvEntities.data);
        assertEquals(dummyTVOnTheAir.size(), tvEntities.data.size());

        viewModel.getOnTheAir().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success(dummyTVOnTheAir));
    }

    @Test
    public void getTrending() {
        List<MediaEntity> dummyTVTrending = DataDummy.generateDummyTVTrending();
        MutableLiveData<Resource<List<MediaEntity>>> trending = new MutableLiveData<>();
        trending.setValue(Resource.success(dummyTVTrending));

        when(catalogRepository.getTVTrending(page)).thenReturn(trending);
        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(viewModel.getTrending());
        verify(catalogRepository).getTVTrending(page);
        assertNotNull(tvEntities.data);
        assertEquals(dummyTVTrending.size(), tvEntities.data.size());

        viewModel.getTrending().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success(dummyTVTrending));
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