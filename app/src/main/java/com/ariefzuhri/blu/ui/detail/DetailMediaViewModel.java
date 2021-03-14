package com.ariefzuhri.blu.ui.detail;

import androidx.lifecycle.ViewModel;

import com.ariefzuhri.blu.model.Movie;

import java.util.ArrayList;

import static com.ariefzuhri.blu.utils.Constants.TYPE_MOVIE;
import static com.ariefzuhri.blu.utils.Constants.TYPE_TV;
import static com.ariefzuhri.blu.utils.DummyMovie.generateMovies;

public class DetailMediaViewModel extends ViewModel {
    private int movieId;

    public void setSelectedMovie(int movieId){
        this.movieId = movieId;
    }

    public Movie getMovie(){
        Movie result = new Movie();
        ArrayList<Movie> movieList = new ArrayList<>();
        movieList.addAll(generateMovies(TYPE_MOVIE));
        movieList.addAll(generateMovies(TYPE_TV));
        for (Movie movie : movieList){
            if (movie.getId() == movieId){
                result = movie;
                break;
            }
        }
        return result;
    }
}
