package com.ariefzuhri.movee.utils;

import android.content.Context;

import com.ariefzuhri.movee.data.source.remote.response.CreditsResponse;
import com.ariefzuhri.movee.data.source.remote.response.GenresResponse;
import com.ariefzuhri.movee.data.source.remote.response.MovieDetailsResponse;
import com.ariefzuhri.movee.data.source.remote.response.MovieResponse;
import com.ariefzuhri.movee.data.source.remote.response.MultiSearchResponse;
import com.ariefzuhri.movee.data.source.remote.response.TVDetailsResponse;
import com.ariefzuhri.movee.data.source.remote.response.TVResponse;
import com.ariefzuhri.movee.data.source.remote.response.VideosResponse;
import com.google.gson.Gson;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;

public class JsonHelper {
    private final Context context;

    public JsonHelper(Context context){
        this.context = context;
    }

    @Nullable
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private String parsingFileToString(String fileName){
        try {
            InputStream is = context.getAssets().open(fileName);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new String(buffer);
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public GenresResponse loadGenres(){
        String fileName = "dummy_genres.json";
        return new Gson().fromJson(parsingFileToString(fileName), GenresResponse.class);
    }

    public CreditsResponse loadMovieCredits(){
        String fileName = "dummy_movie_credits.json";
        return new Gson().fromJson(parsingFileToString(fileName), CreditsResponse.class);
    }

    public MovieDetailsResponse loadMovieDetails(){
        String fileName = "dummy_movie_details.json";
        return new Gson().fromJson(parsingFileToString(fileName), MovieDetailsResponse.class);
    }

    public MovieResponse loadMovieLatestRelease(){
        String fileName = "dummy_movie_latest_release.json";
        return new Gson().fromJson(parsingFileToString(fileName), MovieResponse.class);
    }

    public MovieResponse loadMovieNowPlaying(){
        String fileName = "dummy_movie_now_playing.json";
        return new Gson().fromJson(parsingFileToString(fileName), MovieResponse.class);
    }

    public MovieResponse loadMoviePopular(){
        String fileName = "dummy_movie_popular.json";
        return new Gson().fromJson(parsingFileToString(fileName), MovieResponse.class);
    }

    public MovieResponse loadMovieRecommendations(){
        String fileName = "dummy_movie_recommendations.json";
        return new Gson().fromJson(parsingFileToString(fileName), MovieResponse.class);
    }

    public MovieResponse loadMovieTopRated(){
        String fileName = "dummy_movie_top_rated.json";
        return new Gson().fromJson(parsingFileToString(fileName), MovieResponse.class);
    }

    public MovieResponse loadMovieTrending(){
        String fileName = "dummy_movie_trending.json";
        return new Gson().fromJson(parsingFileToString(fileName), MovieResponse.class);
    }

    public MovieResponse loadMovieUpcoming(){
        String fileName = "dummy_movie_upcoming.json";
        return new Gson().fromJson(parsingFileToString(fileName), MovieResponse.class);
    }

    public VideosResponse loadMovieVideos(){
        String fileName = "dummy_movie_videos.json";
        return new Gson().fromJson(parsingFileToString(fileName), VideosResponse.class);
    }

    public MultiSearchResponse loadMultiSearch(){
        String fileName = "dummy_multi_search.json";
        return new Gson().fromJson(parsingFileToString(fileName), MultiSearchResponse.class);
    }

    public CreditsResponse loadTVCredits(){
        String fileName = "dummy_tv_credits.json";
        return new Gson().fromJson(parsingFileToString(fileName), CreditsResponse.class);
    }

    public TVDetailsResponse loadTVDetails(){
        String fileName = "dummy_tv_details.json";
        return new Gson().fromJson(parsingFileToString(fileName), TVDetailsResponse.class);
    }

    public TVResponse loadTVLatestRelease(){
        String fileName = "dummy_tv_latest_release.json";
        return new Gson().fromJson(parsingFileToString(fileName), TVResponse.class);
    }

    public TVResponse loadTVNowPlaying(){
        String fileName = "dummy_tv_now_playing.json";
        return new Gson().fromJson(parsingFileToString(fileName), TVResponse.class);
    }

    public TVResponse loadTVPopular(){
        String fileName = "dummy_tv_popular.json";
        return new Gson().fromJson(parsingFileToString(fileName), TVResponse.class);
    }

    public TVResponse loadTVRecommendations(){
        String fileName = "dummy_tv_recommendations.json";
        return new Gson().fromJson(parsingFileToString(fileName), TVResponse.class);
    }

    public TVResponse loadTVTopRated(){
        String fileName = "dummy_tv_top_rated.json";
        return new Gson().fromJson(parsingFileToString(fileName), TVResponse.class);
    }

    public TVResponse loadTVTrending(){
        String fileName = "dummy_tv_trending.json";
        return new Gson().fromJson(parsingFileToString(fileName), TVResponse.class);
    }

    public TVResponse loadTVUpcoming(){
        String fileName = "dummy_tv_upcoming.json";
        return new Gson().fromJson(parsingFileToString(fileName), TVResponse.class);
    }

    public VideosResponse loadTVVideos(){
        String fileName = "dummy_tv_videos.json";
        return new Gson().fromJson(parsingFileToString(fileName), VideosResponse.class);
    }
}
