package com.ariefzuhri.movee.utils;

public interface Constants {

    String BASE_URL_YOUTUBE = "https://youtu.be/";
    String BASE_URL_TMDB = "https://api.themoviedb.org/3/";
    String BASE_URL_TMDB_IMAGE = "https://image.tmdb.org/t/p/";

    String EXTRA_BOOKMARK_FILTER = "extra_favorite_filter";
    String EXTRA_MEDIA_TYPE = "extra_media_type";
    String EXTRA_MEDIA_ID = "extra_movie_id";
    String EXTRA_QUERY = "extra_query";
    String EXTRA_QUERY_TYPE = "extra_query_type";

    String IMAGE_SIZE_NORMAL = "w500";
    String IMAGE_SIZE_HIGH = "original";

    String MEDIA_TYPE_MOVIE = "movie";
    String MEDIA_TYPE_TV = "tv";

    String MOVIE_STATUS_PLANNED = "Planned";
    String MOVIE_STATUS_PRE_PRODUCTION = "Pre Production";
    String MOVIE_STATUS_IN_PRODUCTION = "In Production";
    String MOVIE_STATUS_POST_PRODUCTION = "Post Production";
    String MOVIE_STATUS_RELEASED = "Released";

    int ORIENTATION_TYPE_VERTICAL = 0;
    int ORIENTATION_TYPE_HORIZONTAL = 1;

    int QUERY_TYPE_MULTI_SEARCH = 100;
    //int QUERY_TYPE_MOVIE_TRENDING = 201;
    int QUERY_TYPE_MOVIE_LATEST_RELEASE = 202;
    int QUERY_TYPE_MOVIE_NOW_PLAYING = 203;
    int QUERY_TYPE_MOVIE_UPCOMING = 204;
    int QUERY_TYPE_MOVIE_TOP_RATED = 205;
    int QUERY_TYPE_MOVIE_POPULAR = 206;
    int QUERY_TYPE_MOVIE_RECOMMENDATIONS = 207;
    //int QUERY_TYPE_TV_TRENDING = 301;
    int QUERY_TYPE_TV_LATEST_RELEASE = 302;
    int QUERY_TYPE_TV_ON_THE_AIR = 303;
    int QUERY_TYPE_TV_TOP_RATED = 305;
    int QUERY_TYPE_TV_POPULAR = 306;
    int QUERY_TYPE_TV_RECOMMENDATIONS = 307;

    String TV_STATUS_RETURNING_SERIES = "Returning Series";
    String TV_STATUS_ENDED = "Ended";

    String VIDEO_SITE_YOUTUBE = "YouTube";
    String VIDEO_TYPE_TRAILER = "Trailer";
    String VIDEO_TYPE_TEASER = "Teaser";
}
