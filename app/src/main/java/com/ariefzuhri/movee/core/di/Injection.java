package com.ariefzuhri.movee.core.di;

import android.content.Context;

import com.ariefzuhri.movee.core.data.repository.CatalogRepository;
import com.ariefzuhri.movee.core.data.source.local.LocalDataSource;
import com.ariefzuhri.movee.core.data.source.local.persistence.CatalogDatabase;
import com.ariefzuhri.movee.core.data.source.remote.RemoteDataSource;
import com.ariefzuhri.movee.core.utils.AppExecutors;

public class Injection {

    public static CatalogRepository provideRepository(Context context) {
        CatalogDatabase database = CatalogDatabase.getInstance(context);
        LocalDataSource localDataSource = LocalDataSource.getInstance(database.catalogDao());
        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance();
        AppExecutors appExecutors = new AppExecutors();
        return CatalogRepository.getInstance(remoteDataSource, localDataSource, appExecutors);
    }
}
