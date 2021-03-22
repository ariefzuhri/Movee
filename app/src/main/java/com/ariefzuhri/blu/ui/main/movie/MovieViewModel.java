package com.ariefzuhri.blu.ui.main.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ariefzuhri.blu.data.GenreEntity;
import com.ariefzuhri.blu.data.MediaEntity;
import com.ariefzuhri.blu.data.source.CatalogRepository;

import java.util.List;

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

    public LiveData<List<MediaEntity>> getNowPlaying() {
        return repository.getMovieNowPlaying(1);
    }

    public LiveData<List<MediaEntity>> getTrending(){
        return repository.getMovieTrending(page);
    }

    public LiveData<List<GenreEntity>> getGenres() {
        return repository.getGenres(MEDIA_TYPE_MOVIE);
    }
}
