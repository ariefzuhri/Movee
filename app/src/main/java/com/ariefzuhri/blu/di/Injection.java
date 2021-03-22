package com.ariefzuhri.blu.di;

import com.ariefzuhri.blu.data.source.CatalogRepository;
import com.ariefzuhri.blu.data.source.remote.RemoteDataSource;

public class Injection {
    public static CatalogRepository provideRepository(){
        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance();
        return CatalogRepository.getInstance(remoteDataSource);
    }
}
