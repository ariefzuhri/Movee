package com.ariefzuhri.blu.ui.main.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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

    private MutableLiveData<List<MediaEntity>> nowPlaying;
    private MutableLiveData<List<MediaEntity>> trending;
    private MutableLiveData<List<GenreEntity>> genres;

    public LiveData<List<MediaEntity>> getNowPlaying() {
        if (nowPlaying == null) nowPlaying = repository.getMovieNowPlaying(page);
        return nowPlaying;
    }

    public LiveData<List<MediaEntity>> getTrending(){
        if (trending == null) trending = repository.getMovieTrending(page);
        return trending;
    }

    public LiveData<List<GenreEntity>> getGenres() {
        if (genres == null) genres = repository.getGenres(MEDIA_TYPE_MOVIE);
        return genres;
    }
}
