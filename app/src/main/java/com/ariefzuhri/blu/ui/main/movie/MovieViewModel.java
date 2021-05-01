package com.ariefzuhri.blu.ui.main.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ariefzuhri.blu.data.source.local.entity.GenreEntity;
import com.ariefzuhri.blu.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.blu.data.CatalogRepository;
import com.ariefzuhri.blu.vo.Resource;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private final CatalogRepository repository;

    private int trendingPage;

    public MovieViewModel(CatalogRepository repository){
        this.repository = repository;
    }

    public void setTrendingPage(int trendingPage){
        this.trendingPage = trendingPage;
    }

    private MutableLiveData<List<MediaEntity>> nowPlaying;
    private MutableLiveData<List<MediaEntity>> trending;
    private LiveData<Resource<List<GenreEntity>>> genres;

    public LiveData<List<MediaEntity>> getNowPlaying() {
        if (nowPlaying == null) nowPlaying = repository.getMovieNowPlaying(1);
        return nowPlaying;
    }

    public LiveData<List<MediaEntity>> getTrending(){
        if (trending == null) trending = repository.getMovieTrending(trendingPage);
        return trending;
    }

    public LiveData<Resource<List<GenreEntity>>> getGenres() {
        if (genres == null) genres = repository.getGenres();
        return genres;
    }
}
