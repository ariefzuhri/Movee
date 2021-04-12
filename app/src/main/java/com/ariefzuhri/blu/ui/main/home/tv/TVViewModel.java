package com.ariefzuhri.blu.ui.main.home.tv;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ariefzuhri.blu.data.GenreEntity;
import com.ariefzuhri.blu.data.MediaEntity;
import com.ariefzuhri.blu.data.source.CatalogRepository;

import java.util.List;

import static com.ariefzuhri.blu.utils.Constants.MEDIA_TYPE_TV;

public class TVViewModel extends ViewModel {

    private final CatalogRepository repository;

    private int page;

    public TVViewModel(CatalogRepository repository){
        this.repository = repository;
    }

    public void setPage(int page){
        this.page = page;
    }

    private MutableLiveData<List<MediaEntity>> onTheAir;
    private MutableLiveData<List<MediaEntity>> trending;
    private MutableLiveData<List<GenreEntity>> genres;

    public LiveData<List<MediaEntity>> getOnTheAir() {
        if (onTheAir == null) onTheAir = repository.getTVOnTheAir(page);
        return onTheAir;
    }

    public LiveData<List<MediaEntity>> getTrending(){
        if (trending == null) trending = repository.getMovieTrending(page);
        return trending;
    }

    public LiveData<List<GenreEntity>> getGenres() {
        if (genres == null) genres = repository.getGenres(MEDIA_TYPE_TV);
        return genres;
    }
}
