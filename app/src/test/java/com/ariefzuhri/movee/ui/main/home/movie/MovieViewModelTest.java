package com.ariefzuhri.movee.ui.main.home.movie;

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
public class MovieViewModelTest {

    private MovieViewModel viewModel;

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
        viewModel = new MovieViewModel(catalogRepository);
        viewModel.setTrendingPage(page);
    }

    @Test
    public void getNowPlaying() {
        List<MediaEntity> dummyMovieNowPlaying = DataDummy.generateDummyMovieNowPlaying();
        MutableLiveData<Resource<List<MediaEntity>>> nowPlaying = new MutableLiveData<>();
        nowPlaying.setValue(Resource.success(dummyMovieNowPlaying));

        when(catalogRepository.getMovieNowPlaying(page)).thenReturn(nowPlaying);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getNowPlaying());
        verify(catalogRepository).getMovieNowPlaying(page);
        assertNotNull(movieEntities.data);

    /*Satu test case itu bisa dipisah menjadi 2 (dua) test case berbeda, pertama untuk menguji
    (assertion) nilai yang didapat dari repository saat fungsi getCourses yang ada di viewModel
    dipanggil dan yang kedua adalah pengujian untuk melakukan verifikasi jika nilai dari observer
    berbeda.*/
        assertEquals(dummyMovieNowPlaying.size(), movieEntities.data.size());

        viewModel.getNowPlaying().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success(dummyMovieNowPlaying));
    }

    @Test
    public void getTrending() {
        List<MediaEntity> dummyMovieTrending = DataDummy.generateDummyMovieTrending();
        MutableLiveData<Resource<List<MediaEntity>>> trending = new MutableLiveData<>();
        trending.setValue(Resource.success(dummyMovieTrending));

        when(catalogRepository.getMovieTrending(page)).thenReturn(trending);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getTrending());
        verify(catalogRepository).getMovieTrending(page);
        assertNotNull(movieEntities.data);
        assertEquals(dummyMovieTrending.size(), movieEntities.data.size());

        viewModel.getTrending().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(Resource.success(dummyMovieTrending));
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