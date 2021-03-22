package com.ariefzuhri.blu.ui.main.home;

import com.ariefzuhri.blu.data.source.remote.entity.MovieEntity;
import com.ariefzuhri.blu.data.source.remote.entity.SearchResultEntity;
import com.ariefzuhri.blu.data.source.remote.entity.TVEntity;
import com.ariefzuhri.blu.data.source.remote.response.MovieDetailsResponse;
import com.ariefzuhri.blu.data.source.remote.response.TVDetailsResponse;
import com.ariefzuhri.blu.data.AiredDate;
import com.ariefzuhri.blu.data.MediaEntity;

import java.util.ArrayList;
import java.util.List;

import static com.ariefzuhri.blu.utils.Constants.MEDIA_TYPE_MOVIE;
import static com.ariefzuhri.blu.utils.Constants.MEDIA_TYPE_TV;

public class MediaHelper {

    public static MediaEntity movieEntityToMedia(MovieEntity movie){
        return new MediaEntity(movie.getId(),
                movie.getTitle(),
                movie.getPosterPath(),
                movie.getBackdropPath(),
                movie.getVoteAverage(),
                movie.getVoteCount(),
                movie.getPopularity(),
                MEDIA_TYPE_MOVIE,
                new AiredDate(movie.getReleaseDate()),
                movie.getGenreIds(),
                movie.getOverview());
    }

    public static MediaEntity tvEntityToMedia(TVEntity tv){
        return new MediaEntity(tv.getId(),
                tv.getName(),
                tv.getPosterPath(),
                tv.getBackdropPath(),
                tv.getVoteAverage(),
                tv.getVoteCount(),
                tv.getPopularity(),
                MEDIA_TYPE_TV,
                new AiredDate(tv.getFirstAirDate(), null),
                tv.getGenreIds(),
                tv.getOverview());
    }

    public static List<MediaEntity> movieEntitiesToMediaList(List<MovieEntity> movieEntityList){
        List<MediaEntity> mediaList = new ArrayList<>();
        for (MovieEntity movie : movieEntityList){
            mediaList.add(movieEntityToMedia(movie));
        }
        return mediaList;
    }

    public static List<MediaEntity> tvEntitiesToMediaList(List<TVEntity> tvEntityList){
        List<MediaEntity> mediaList = new ArrayList<>();
        for (TVEntity tv : tvEntityList){
            mediaList.add(tvEntityToMedia(tv));
        }
        return mediaList;
    }

    public static List<MediaEntity> searchResultEntitiesToMediaList(List<SearchResultEntity> searchResultEntityList){
        List<MediaEntity> mediaList = new ArrayList<>();
        for (SearchResultEntity searchResult : searchResultEntityList){
            if (searchResult.getMediaType().equals(MEDIA_TYPE_MOVIE)){
                mediaList.add(new MediaEntity(searchResult.getId(),
                        searchResult.getTitle(),
                        searchResult.getPosterPath(),
                        searchResult.getBackdropPath(),
                        searchResult.getVoteAverage(),
                        searchResult.getVoteCount(),
                        searchResult.getPopularity(),
                        MEDIA_TYPE_MOVIE,
                        new AiredDate(searchResult.getReleaseDate()),
                        searchResult.getGenreIds(),
                        searchResult.getOverview()));
            } else if (searchResult.getMediaType().equals(MEDIA_TYPE_TV)){
                mediaList.add(new MediaEntity(searchResult.getId(),
                        searchResult.getName(),
                        searchResult.getPosterPath(),
                        searchResult.getBackdropPath(),
                        searchResult.getVoteAverage(),
                        searchResult.getVoteCount(),
                        searchResult.getPopularity(),
                        MEDIA_TYPE_TV,
                        new AiredDate(searchResult.getFirstAirDate(), null),
                        searchResult.getGenreIds(),
                        searchResult.getOverview()));
            }
        }
        return mediaList;
    }

    public static MediaEntity movieDetailsToMedia(MovieDetailsResponse movie){
        return new MediaEntity(movie.getId(),
                movie.getTitle(),
                movie.getPosterPath(),
                movie.getBackdropPath(),
                movie.getVoteAverage(),
                movie.getVoteCount(),
                movie.getPopularity(),
                MEDIA_TYPE_MOVIE,
                1,
                movie.getStatus(),
                new AiredDate(movie.getReleaseDate()),
                movie.getProductionCompanies(),
                movie.getGenres(),
                movie.getRuntime(),
                movie.getOverview(),
                null);
    }

    public static MediaEntity tvDetailsToMedia(TVDetailsResponse tv){
        return new MediaEntity(tv.getId(),
                tv.getName(),
                tv.getPosterPath(),
                tv.getBackdropPath(),
                tv.getVoteAverage(),
                tv.getVoteCount(),
                tv.getPopularity(),
                MEDIA_TYPE_TV,
                tv.getNumberOfEpisodes(),
                tv.getStatus(),
                new AiredDate(tv.getFirstAirDate(), tv.getLastAirDate()),
                tv.getProductionCompanies(),
                tv.getGenres(),
                (tv.getEpisodeRunTime().isEmpty()) ? 0 : tv.getEpisodeRunTime().get(0),
                tv.getOverview(),
                null);
    }
}
