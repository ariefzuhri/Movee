package com.ariefzuhri.blu.ui.main.home.tv;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ariefzuhri.blu.data.source.local.entity.GenreEntity;
import com.ariefzuhri.blu.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.blu.data.CatalogRepository;
import com.ariefzuhri.blu.vo.Resource;

import java.util.List;

public class TVViewModel extends ViewModel {

    private final CatalogRepository repository;

    private int trendingPage;

    public TVViewModel(CatalogRepository repository){
        this.repository = repository;
    }

    public void setTrendingPage(int trendingPage){
        this.trendingPage = trendingPage;
    }

    private MutableLiveData<List<MediaEntity>> onTheAir;
    private MutableLiveData<List<MediaEntity>> trending;
    private LiveData<Resource<List<GenreEntity>>> genres;

    public LiveData<List<MediaEntity>> getOnTheAir() {
        if (onTheAir == null) onTheAir = repository.getTVOnTheAir(1);
        return onTheAir;
    }

    public LiveData<List<MediaEntity>> getTrending(){
        if (trending == null) trending = repository.getTVTrending(trendingPage);
        return trending;
    }

    public LiveData<Resource<List<GenreEntity>>> getGenres() {
        if (genres == null) genres = repository.getGenres();
        return genres;
    }
}
