package com.ariefzuhri.blu.ui.main.home.tv;

import androidx.lifecycle.LiveData;
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

    public LiveData<List<MediaEntity>> getOnTheAir() {
        return repository.getTVOnTheAir(1);
    }

    public LiveData<List<MediaEntity>> getTrending(){
        return repository.getTVTrending(page);
    }

    public LiveData<List<GenreEntity>> getGenres() {
        return repository.getGenres(MEDIA_TYPE_TV);
    }
}
