package com.ariefzuhri.blu.ui.detail;

import com.ariefzuhri.blu.model.Movie;
import com.ariefzuhri.blu.utils.DummyMovie;

import org.junit.Before;
import org.junit.Test;

import static com.ariefzuhri.blu.utils.Constants.TYPE_MOVIE;
import static com.ariefzuhri.blu.utils.Constants.TYPE_TV;
import static org.junit.Assert.*;

public class DetailMediaViewModelTest {
    private DetailMediaViewModel viewModel;
    private final Movie dummyMovie = DummyMovie.generateMovies(TYPE_MOVIE).get(0);
    private final int movieId = dummyMovie.getId();
    private final Movie dummyTV = DummyMovie.generateMovies(TYPE_TV).get(0);
    private final int tvId = dummyTV.getId();

    @Before
    public void setUp(){
        viewModel = new DetailMediaViewModel();
    }

    @Test
    public void getMovie() {
        viewModel.setSelectedMovie(movieId);
        Movie movie = viewModel.getMovie();
        assertNotNull(movie);
        assertEquals(dummyMovie.getId(), movie.getId());
        assertEquals(dummyMovie.getTitle(), movie.getTitle());
        assertEquals(dummyMovie.getPoster(), movie.getPoster());
        assertEquals(dummyMovie.getCover(), movie.getCover());
        assertEquals(dummyMovie.getScore(), movie.getScore());
        assertEquals(dummyMovie.getType(), movie.getType());
        assertEquals(dummyMovie.getEpisodes(), movie.getEpisodes());
        assertEquals(dummyMovie.getStatus(), movie.getStatus());
        assertEquals(dummyMovie.getAiredDate(), movie.getAiredDate());
        assertEquals(dummyMovie.getStudio(), movie.getStudio());
        assertEquals(dummyMovie.getGenres(), movie.getGenres());
        assertEquals(dummyMovie.getRuntime(), movie.getRuntime());
        assertEquals(dummyMovie.getSynopsis(), movie.getSynopsis());
        assertEquals(dummyMovie.getTrailer(), movie.getTrailer());
    }

    @Test
    public void getTV() {
        viewModel.setSelectedMovie(tvId);
        Movie tv = viewModel.getMovie();
        assertNotNull(tv);
        assertEquals(dummyTV.getId(), tv.getId());
        assertEquals(dummyTV.getTitle(), tv.getTitle());
        assertEquals(dummyTV.getPoster(), tv.getPoster());
        assertEquals(dummyTV.getCover(), tv.getCover());
        assertEquals(dummyTV.getScore(), tv.getScore());
        assertEquals(dummyTV.getType(), tv.getType());
        assertEquals(dummyTV.getEpisodes(), tv.getEpisodes());
        assertEquals(dummyTV.getStatus(), tv.getStatus());
        assertEquals(dummyTV.getAiredDate(), tv.getAiredDate());
        assertEquals(dummyTV.getStudio(), tv.getStudio());
        assertEquals(dummyTV.getGenres(), tv.getGenres());
        assertEquals(dummyTV.getRuntime(), tv.getRuntime());
        assertEquals(dummyTV.getSynopsis(), tv.getSynopsis());
        assertEquals(dummyTV.getTrailer(), tv.getTrailer());
    }
}