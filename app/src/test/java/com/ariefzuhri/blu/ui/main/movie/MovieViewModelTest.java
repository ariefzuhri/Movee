package com.ariefzuhri.blu.ui.main.movie;

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
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieViewModelTest {
    private MovieViewModel viewModel;

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
        viewModel = new MovieViewModel(catalogRepository);
        viewModel.setPage(page);
    }

    @Test
    public void getNowPlaying() {
        List<MediaEntity> dummyMovieNowPlaying = DataDummy.generateDummyMovieNowPlaying();

        MutableLiveData<List<MediaEntity>> nowPlaying = new MutableLiveData<>();
        nowPlaying.setValue(dummyMovieNowPlaying);

        when(catalogRepository.getMovieNowPlaying(page)).thenReturn(nowPlaying);
        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(viewModel.getNowPlaying());
        verify(catalogRepository).getMovieNowPlaying(page);
        assertNotNull(movieEntities);
        assertEquals(dummyMovieNowPlaying.size(), movieEntities.size());

        viewModel.getNowPlaying().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieNowPlaying);
    }

    @Test
    public void getTrending() {
        List<MediaEntity> dummyMovieTrending = DataDummy.generateDummyMovieTrending();

        MutableLiveData<List<MediaEntity>> trending = new MutableLiveData<>();
        trending.setValue(dummyMovieTrending);

        when(catalogRepository.getMovieTrending(page)).thenReturn(trending);
        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(viewModel.getTrending());
        verify(catalogRepository).getMovieTrending(page);
        assertNotNull(movieEntities);
        assertEquals(dummyMovieTrending.size(), movieEntities.size());

        viewModel.getTrending().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieTrending);
    }

    @Test
    public void getGenres() {
        List<GenreEntity> dummyGenres = DataDummy.generateDummyMovieGenres();

        MutableLiveData<List<GenreEntity>> genres = new MutableLiveData<>();
        genres.setValue(dummyGenres);

        when(catalogRepository.getGenres(MEDIA_TYPE_MOVIE)).thenReturn(genres);
        List<GenreEntity> movieGenreEntities = LiveDataTestUtil.getValue(viewModel.getGenres());
        verify(catalogRepository).getGenres(MEDIA_TYPE_MOVIE);
        assertNotNull(movieGenreEntities);
        assertEquals(dummyGenres.size(), movieGenreEntities.size());

        viewModel.getGenres().observeForever(genresObserver);
        verify(genresObserver).onChanged(dummyGenres);
    }
}