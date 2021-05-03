package com.ariefzuhri.movee.ui.main.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ariefzuhri.movee.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.movee.data.CatalogRepository;
import com.ariefzuhri.movee.vo.Resource;

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

    private MutableLiveData<Resource<List<MediaEntity>>> nowPlaying;
    private MutableLiveData<Resource<List<MediaEntity>>> trending;
    private LiveData<Resource<List<GenreEntity>>> genres;

    public LiveData<Resource<List<MediaEntity>>> getNowPlaying() {
        if (nowPlaying == null) nowPlaying = repository.getMovieNowPlaying(1);
        return nowPlaying;
    }

    public LiveData<Resource<List<MediaEntity>>> getTrending(){
        if (trending == null) trending = repository.getMovieTrending(trendingPage);
        return trending;
    }

    public LiveData<Resource<List<GenreEntity>>> getGenres() {
        if (genres == null) genres = repository.getGenres();
        return genres;
    }
}
