package com.ariefzuhri.movee.utils;

import androidx.sqlite.db.SimpleSQLiteQuery;

import static com.ariefzuhri.movee.utils.Constants.MEDIA_TYPE_MOVIE;
import static com.ariefzuhri.movee.utils.Constants.MEDIA_TYPE_TV;

public class FilterUtils {

    public static SimpleSQLiteQuery getFilteredFavoriteQuery(FilterFavorite filter) {
        StringBuilder simpleQuery = new StringBuilder().append("SELECT * FROM favoriteEntities");

        if (!filter.isShowAllMediaType()) {
            simpleQuery.append(" WHERE type = ");
            if (filter.isShowMovieOnly()) simpleQuery.append("'" + MEDIA_TYPE_MOVIE + "'");
            else if (filter.isShowTVOnly()) simpleQuery.append("'" + MEDIA_TYPE_TV + "'");
        }

        simpleQuery.append(" ORDER BY ");
        if (filter.isSortedByTitle()) simpleQuery.append("title ASC");
        else if (filter.isSortedByRating()) simpleQuery.append("scoreAverage DESC");
        else if (filter.isSortedByReleaseDate()) simpleQuery.append("startDate DESC");

        return new SimpleSQLiteQuery(simpleQuery.toString());
    }
}
