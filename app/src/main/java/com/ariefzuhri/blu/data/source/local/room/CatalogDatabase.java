package com.ariefzuhri.blu.data.source.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.ariefzuhri.blu.data.source.local.entity.FavoriteEntity;
import com.ariefzuhri.blu.data.source.local.entity.FavoriteGenreJoin;
import com.ariefzuhri.blu.data.source.local.entity.GenreEntity;
import com.ariefzuhri.blu.utils.Converters;

@Database(entities = {FavoriteEntity.class, GenreEntity.class, FavoriteGenreJoin.class},
        version = 1,
        exportSchema = false)
@TypeConverters(Converters.class)
public abstract class CatalogDatabase extends RoomDatabase {

    public abstract CatalogDao catalogDao();

    private static volatile CatalogDatabase INSTANCE;

    public static CatalogDatabase getInstance(Context context){
        if (INSTANCE == null) {
            synchronized (CatalogDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        CatalogDatabase.class, "Catalog.db")
                        .build();
            }
        }
        return INSTANCE;
    }
}
