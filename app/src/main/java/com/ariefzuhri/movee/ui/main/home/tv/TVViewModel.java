package com.ariefzuhri.movee.ui.main.home.tv;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ariefzuhri.movee.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.movee.data.CatalogRepository;
import com.ariefzuhri.movee.vo.Resource;

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

    private MutableLiveData<Resource<List<MediaEntity>>> onTheAir;
    private MutableLiveData<Resource<List<MediaEntity>>> trending;
    private LiveData<Resource<List<GenreEntity>>> genres;

    public LiveData<Resource<List<MediaEntity>>> getOnTheAir() {
        if (onTheAir == null) onTheAir = repository.getTVOnTheAir(1);
        return onTheAir;
    }

    public LiveData<Resource<List<MediaEntity>>> getTrending(){
        if (trending == null) trending = repository.getTVTrending(trendingPage);
        return trending;
    }

    public LiveData<Resource<List<GenreEntity>>> getGenres() {
        if (genres == null) genres = repository.getGenres();
        return genres;
    }
}
