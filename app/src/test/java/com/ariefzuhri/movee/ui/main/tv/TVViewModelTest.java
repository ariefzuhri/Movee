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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.ariefzuhri.movee.utils.Constants.MEDIA_TYPE_TV;
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
    private Observer<List<MediaEntity>> mediaEntitiesObserver;

    @Mock
    private Observer<List<GenreEntity>> genresObserver;

    @Before
    public void setUp(){
        viewModel = new TVViewModel(catalogRepository);
        viewModel.setPage(page);
    }

    @Test
    public void getOnTheAir() {
        List<MediaEntity> dummyTVOnTheAir = DataDummy.generateDummyTVOnTheAir();

        MutableLiveData<List<MediaEntity>> onTheAir = new MutableLiveData<>();
        onTheAir.setValue(dummyTVOnTheAir);

        when(catalogRepository.getTVOnTheAir(page)).thenReturn(onTheAir);
        List<MediaEntity> tvEntities = LiveDataTestUtil.getValue(viewModel.getOnTheAir());
        verify(catalogRepository).getTVOnTheAir(page);
        assertNotNull(tvEntities);
        assertEquals(dummyTVOnTheAir.size(), tvEntities.size());

        viewModel.getOnTheAir().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVOnTheAir);
    }

    @Test
    public void getTrending() {
        List<MediaEntity> dummyTVTrending = DataDummy.generateDummyTVTrending();

        MutableLiveData<List<MediaEntity>> trending = new MutableLiveData<>();
        trending.setValue(dummyTVTrending);

        when(catalogRepository.getTVTrending(page)).thenReturn(trending);
        List<MediaEntity> tvEntities = LiveDataTestUtil.getValue(viewModel.getTrending());
        verify(catalogRepository).getTVTrending(page);
        assertNotNull(tvEntities);
        assertEquals(dummyTVTrending.size(), tvEntities.size());

        viewModel.getTrending().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVTrending);
    }

    @Test
    public void getGenres() {
        List<GenreEntity> dummyGenres = DataDummy.generateDummyTVGenres();

        MutableLiveData<List<GenreEntity>> genres = new MutableLiveData<>();
        genres.setValue(dummyGenres);

        when(catalogRepository.getGenres(MEDIA_TYPE_TV)).thenReturn(genres);
        List<GenreEntity> tvGenreEntities = LiveDataTestUtil.getValue(viewModel.getGenres());
        verify(catalogRepository).getGenres(MEDIA_TYPE_TV);
        assertNotNull(tvGenreEntities);
        assertEquals(dummyGenres.size(), tvGenreEntities.size());

        viewModel.getGenres().observeForever(genresObserver);
        verify(genresObserver).onChanged(dummyGenres);
    }
}