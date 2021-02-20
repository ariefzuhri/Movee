package com.ariefzuhri.blu.movie;

import androidx.lifecycle.ViewModel;

import com.ariefzuhri.blu.model.Movie;

import java.util.ArrayList;

import static com.ariefzuhri.blu.utils.DummyMovie.generateMovies;

public class MovieViewModel extends ViewModel {
    public ArrayList<Movie> getMovies(String type) {
        return generateMovies(type);
    }
}
