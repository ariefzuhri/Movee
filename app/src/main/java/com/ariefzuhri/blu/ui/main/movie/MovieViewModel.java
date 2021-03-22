package com.ariefzuhri.blu.ui.main.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ariefzuhri.blu.data.source.CatalogRepository;
import com.ariefzuhri.blu.data.source.remote.response.GenresResponse;
import com.ariefzuhri.blu.data.source.remote.response.MovieResponse;

import static com.ariefzuhri.blu.utils.Constants.MEDIA_TYPE_MOVIE;

public class MovieViewModel extends ViewModel {
    private final CatalogRepository repository;

    private int page;

    public MovieViewModel(CatalogRepository repository){
        this.repository = repository;
    }

    public void setPage(int page){
        this.page = page;
    }

    public LiveData<MovieResponse> getNowPlaying() {
        return repository.getMovieNowPlaying(1);
    }

    public LiveData<MovieResponse> getTrending(){
        return repository.getMovieTrending(page);
    }

    public LiveData<GenresResponse> getGenres() {
        return repository.getGenres(MEDIA_TYPE_MOVIE);
    }
}
