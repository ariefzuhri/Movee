package com.ariefzuhri.movee.di;

import android.content.Context;

import com.ariefzuhri.movee.data.CatalogRepository;
import com.ariefzuhri.movee.data.source.local.LocalDataSource;
import com.ariefzuhri.movee.data.source.local.room.CatalogDatabase;
import com.ariefzuhri.movee.data.source.remote.RemoteDataSource;
import com.ariefzuhri.movee.utils.AppExecutors;

public class Injection {

    public static CatalogRepository provideRepository(Context context){
        CatalogDatabase database = CatalogDatabase.getInstance(context);
        LocalDataSource localDataSource = LocalDataSource.getInstance(database.catalogDao());
        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance();
        AppExecutors appExecutors = new AppExecutors();
        return CatalogRepository.getInstance(remoteDataSource, localDataSource, appExecutors);
    }
}
