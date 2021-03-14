package com.ariefzuhri.blu.ui.movie;

import com.ariefzuhri.blu.model.Movie;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.ariefzuhri.blu.utils.Constants.TYPE_MOVIE;
import static com.ariefzuhri.blu.utils.Constants.TYPE_TV;
import static org.junit.Assert.*;

public class MovieViewModelTest {
    private MovieViewModel viewModel;

    @Before
    public void setUp(){
        viewModel = new MovieViewModel();
    }

    @Test
    public void getMovies() {
        viewModel.setSelectedMovies(TYPE_MOVIE);
        ArrayList<Movie> movieList = viewModel.getMovies();
        assertNotNull(movieList);
        assertEquals(10, movieList.size());
    }

    @Test
    public void getTVs() {
        viewModel.setSelectedMovies(TYPE_TV);
        ArrayList<Movie> movieList = viewModel.getMovies();
        assertNotNull(movieList);
        assertEquals(10, movieList.size());
    }
}