package com.ariefzuhri.blu.utils;

public interface Constants {
    String BASE_URL_YOUTUBE = "https://youtu.be/";

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

    int QUERY_TYPE_MOVIE_TRENDING = 100;
    int QUERY_TYPE_MOVIE_LATEST_RELEASE = 101;
    int QUERY_TYPE_MOVIE_NOW_PLAYING = 102;
    int QUERY_TYPE_MOVIE_UPCOMING = 103;
    int QUERY_TYPE_MOVIE_TOP_RATED = 104;
    int QUERY_TYPE_MOVIE_POPULAR = 105;
    int QUERY_TYPE_MOVIE_RECOMMENDATIONS = 106;
    int QUERY_TYPE_TV_TRENDING = 200;
    int QUERY_TYPE_TV_LATEST_RELEASE = 201;
    int QUERY_TYPE_TV_ON_THE_AIR = 202;
    int QUERY_TYPE_TV_TOP_RATED = 204;
    int QUERY_TYPE_TV_POPULAR = 205;
    int QUERY_TYPE_TV_RECOMMENDATIONS = 206;

    String TV_STATUS_RETURNING_SERIES = "Returning Series";
    String TV_STATUS_ENDED = "Ended";

    String VIDEO_SITE_YOUTUBE = "YouTube";
    String VIDEO_TYPE_TRAILER = "Trailer";
    String VIDEO_TYPE_TEASER = "Teaser";
}
