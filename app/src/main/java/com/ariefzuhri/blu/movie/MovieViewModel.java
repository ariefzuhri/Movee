package com.ariefzuhri.blu.movie;

import androidx.lifecycle.ViewModel;

import com.ariefzuhri.blu.model.Movie;

import java.util.ArrayList;

import static com.ariefzuhri.blu.utils.DummyMovie.generateMovies;

public class MovieViewModel extends ViewModel {
    private String movieType;

    public void setSelectedMovies(String movieType){
        this.movieType = movieType;
    }

    public ArrayList<Movie> getMovies() {
        return generateMovies(movieType);
    }
}
