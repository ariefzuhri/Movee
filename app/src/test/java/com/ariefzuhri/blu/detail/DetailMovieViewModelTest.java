package com.ariefzuhri.blu.detail;

import com.ariefzuhri.blu.model.Movie;
import com.ariefzuhri.blu.utils.DummyMovie;

import org.junit.Before;
import org.junit.Test;

import static com.ariefzuhri.blu.utils.Constants.TYPE_TV;
import static org.junit.Assert.*;

public class DetailMovieViewModelTest {
    private DetailMovieViewModel viewModel;
    private final Movie dummyMovie = DummyMovie.generateMovies(TYPE_TV).get(0);
    private final int movieId = dummyMovie.getId();

    @Before
    public void setUp(){
        viewModel = new DetailMovieViewModel();
        viewModel.setSelectedMovie(movieId);
    }

    @Test
    public void getMovie() {
        viewModel.setSelectedMovie(dummyMovie.getId());
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
}