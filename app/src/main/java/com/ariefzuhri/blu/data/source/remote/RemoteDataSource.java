package com.ariefzuhri.blu.data.source.remote;

import android.util.Log;

import com.ariefzuhri.blu.data.source.remote.response.CreditsResponse;
import com.ariefzuhri.blu.data.source.remote.response.GenresResponse;
import com.ariefzuhri.blu.data.source.remote.response.MovieDetailsResponse;
import com.ariefzuhri.blu.data.source.remote.response.MovieResponse;
import com.ariefzuhri.blu.data.source.remote.response.MultiSearchResponse;
import com.ariefzuhri.blu.data.source.remote.response.TVDetailsResponse;
import com.ariefzuhri.blu.data.source.remote.response.TVResponse;
import com.ariefzuhri.blu.data.source.remote.response.VideosResponse;
import com.ariefzuhri.blu.data.source.remote.rest.ApiConfig;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ariefzuhri.blu.BuildConfig.TMDB_API_KEY;
import static com.ariefzuhri.blu.utils.DateUtils.getCurrentDate;

public class RemoteDataSource {

    private static final String TAG = getInstance().getClass().getSimpleName();
    private static RemoteDataSource INSTANCE;

    private RemoteDataSource(){}

    public static RemoteDataSource getInstance(){
        if (INSTANCE == null){
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }

    public final void getMultiSearch(String query, int page, LoadMultiSearchCallback callback){
        Call<MultiSearchResponse> client = ApiConfig.getApiService().getMultiSearch(TMDB_API_KEY, query, page);
        client.enqueue(new Callback<MultiSearchResponse>() {
            @Override
            public void onResponse(@NotNull Call<MultiSearchResponse> call, @NotNull Response<MultiSearchResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onMultiSearchReceived(response.body());
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MultiSearchResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public final void getMovieDetails(int movieId, LoadMovieDetailsCallback callback){
        Call<MovieDetailsResponse> client = ApiConfig.getApiService().getMovieDetails(movieId, TMDB_API_KEY);
        client.enqueue(new Callback<MovieDetailsResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieDetailsResponse> call, @NotNull Response<MovieDetailsResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onMovieDetailsReceived(response.body());
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MovieDetailsResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public final void getTVDetails(int tvId, LoadTVDetailsCallback callback){
        Call<TVDetailsResponse> client = ApiConfig.getApiService().getTVDetails(tvId, TMDB_API_KEY);
        client.enqueue(new Callback<TVDetailsResponse>() {
            @Override
            public void onResponse(@NotNull Call<TVDetailsResponse> call, @NotNull Response<TVDetailsResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onTVDetailsReceived(response.body());
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<TVDetailsResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public final void getMovieTrending(int page, LoadMovieTrendingCallback callback){
        Call<MovieResponse> client = ApiConfig.getApiService().getMovieTrending(TMDB_API_KEY, page);
        client.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieResponse> call, @NotNull Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onMovieTrendingReceived(response.body());
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public final void getTVTrending(int page, LoadTVTrendingCallback callback){
        Call<TVResponse> client = ApiConfig.getApiService().getTVTrending(TMDB_API_KEY, page);
        client.enqueue(new Callback<TVResponse>() {
            @Override
            public void onResponse(@NotNull Call<TVResponse> call, @NotNull Response<TVResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onTVTrendingReceived(response.body());
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<TVResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public final void getMovieLatestRelease(int page, LoadMovieLatestReleaseCallback callback){
        Call<MovieResponse> client = ApiConfig.getApiService().getMovieLatestRelease(TMDB_API_KEY, getCurrentDate(),  getCurrentDate(), page);
        client.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieResponse> call, @NotNull Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onMovieLatestReleaseReceived(response.body());
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public final void getTVLatestRelease(int page, LoadTVLatestReleaseCallback callback){
        Call<TVResponse> client = ApiConfig.getApiService().getTVLatestRelease(TMDB_API_KEY,  getCurrentDate(),  getCurrentDate(), page);
        client.enqueue(new Callback<TVResponse>() {
            @Override
            public void onResponse(@NotNull Call<TVResponse> call, @NotNull Response<TVResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onTVLatestReleaseReceived(response.body());
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<TVResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public final void getMovieNowPlaying(int page, LoadMovieNowPlayingCallback callback){
        Call<MovieResponse> client = ApiConfig.getApiService().getMovieNowPlaying(TMDB_API_KEY, page);
        client.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieResponse> call, @NotNull Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onMovieNowPlayingReceived(response.body());
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public final void getTVOnTheAir(int page, LoadTVOnTheAirCallback callback){
        Call<TVResponse> client = ApiConfig.getApiService().getTVOnTheAir(TMDB_API_KEY, page);
        client.enqueue(new Callback<TVResponse>() {
            @Override
            public void onResponse(@NotNull Call<TVResponse> call, @NotNull Response<TVResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onTVOnTheAirReceived(response.body());
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<TVResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public final void getMovieUpcoming(int page, LoadMovieUpcomingCallback callback){
        Call<MovieResponse> client = ApiConfig.getApiService().getMovieUpcoming(TMDB_API_KEY, page);
        client.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieResponse> call, @NotNull Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onMovieUpcomingReceived(response.body());
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public final void getMovieTopRated(int page, LoadMovieTopRatedCallback callback){
        Call<MovieResponse> client = ApiConfig.getApiService().getMovieTopRated(TMDB_API_KEY, page);
        client.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieResponse> call, @NotNull Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onMovieTopRatedReceived(response.body());
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public final void getTVTopRated(int page, LoadTVTopRatedCallback callback){
        Call<TVResponse> client = ApiConfig.getApiService().getTVTopRated(TMDB_API_KEY, page);
        client.enqueue(new Callback<TVResponse>() {
            @Override
            public void onResponse(@NotNull Call<TVResponse> call, @NotNull Response<TVResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onTVTopRatedReceived(response.body());
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<TVResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public final void getMoviePopular(int page, LoadMoviePopularCallback callback){
        Call<MovieResponse> client = ApiConfig.getApiService().getMoviePopular(TMDB_API_KEY, page);
        client.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieResponse> call, @NotNull Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onMoviePopularReceived(response.body());
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public final void getTVPopular(int page, LoadTVPopularCallback callback){
        Call<TVResponse> client = ApiConfig.getApiService().getTVPopular(TMDB_API_KEY, page);
        client.enqueue(new Callback<TVResponse>() {
            @Override
            public void onResponse(@NotNull Call<TVResponse> call, @NotNull Response<TVResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onTVPopularReceived(response.body());
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<TVResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public final void getMovieRecommendations(int movieId, int page, LoadMovieRecommendationsCallback callback){
        Call<MovieResponse> client = ApiConfig.getApiService().getMovieRecommendations(movieId, TMDB_API_KEY, page);
        client.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieResponse> call, @NotNull Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onMovieRecommendationsReceived(response.body());
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public final void getTVRecommendations(int tvId, int page, LoadTVRecommendationsCallback callback){
        Call<TVResponse> client = ApiConfig.getApiService().getTVRecommendations(tvId, TMDB_API_KEY, page);
        client.enqueue(new Callback<TVResponse>() {
            @Override
            public void onResponse(@NotNull Call<TVResponse> call, @NotNull Response<TVResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onTVRecommendationsReceived(response.body());
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<TVResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public final void getGenres(String mediaType, LoadGenresCallback callback){
        Call<GenresResponse> client = ApiConfig.getApiService().getGenres(mediaType, TMDB_API_KEY);
        client.enqueue(new Callback<GenresResponse>() {
            @Override
            public void onResponse(@NotNull Call<GenresResponse> call, @NotNull Response<GenresResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onGenresReceived(response.body());
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<GenresResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public final void getVideos(String mediaType, int mediaId, LoadVideosCallback callback){
        Call<VideosResponse> client = ApiConfig.getApiService().getVideos(mediaType, mediaId, TMDB_API_KEY);
        client.enqueue(new Callback<VideosResponse>() {
            @Override
            public void onResponse(@NotNull Call<VideosResponse> call, @NotNull Response<VideosResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onVideosReceived(response.body());
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<VideosResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public final void getCredits(String mediaType, int mediaId, LoadCreditsCallback callback){
        Call<CreditsResponse> client = ApiConfig.getApiService().getCredits(mediaType, mediaId, TMDB_API_KEY);
        client.enqueue(new Callback<CreditsResponse>() {
            @Override
            public void onResponse(@NotNull Call<CreditsResponse> call, @NotNull Response<CreditsResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onCreditsReceived(response.body());
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<CreditsResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public interface LoadMultiSearchCallback{
        void onMultiSearchReceived(MultiSearchResponse multiSearchResponse);
    }

    public interface LoadMovieDetailsCallback{
        void onMovieDetailsReceived(MovieDetailsResponse movieDetailsResponse);
    }

    public interface LoadTVDetailsCallback{
        void onTVDetailsReceived(TVDetailsResponse tvDetailsResponse);
    }

    public interface LoadMovieTrendingCallback{
        void onMovieTrendingReceived(MovieResponse movieResponse);
    }

    public interface LoadTVTrendingCallback{
        void onTVTrendingReceived(TVResponse tvResponse);
    }

    public interface LoadMovieLatestReleaseCallback{
        void onMovieLatestReleaseReceived(MovieResponse movieResponse);
    }

    public interface LoadTVLatestReleaseCallback{
        void onTVLatestReleaseReceived(TVResponse tvResponse);
    }

    public interface LoadMovieNowPlayingCallback{
        void onMovieNowPlayingReceived(MovieResponse movieResponse);
    }

    public interface LoadTVOnTheAirCallback{
        void onTVOnTheAirReceived(TVResponse tvResponse);
    }

    public interface LoadMovieUpcomingCallback{
        void onMovieUpcomingReceived(MovieResponse movieResponse);
    }

    public interface LoadMovieTopRatedCallback{
        void onMovieTopRatedReceived(MovieResponse movieResponse);
    }

    public interface LoadTVTopRatedCallback{
        void onTVTopRatedReceived(TVResponse tvResponse);
    }

    public interface LoadMoviePopularCallback{
        void onMoviePopularReceived(MovieResponse movieResponse);
    }

    public interface LoadTVPopularCallback{
        void onTVPopularReceived(TVResponse tvResponse);
    }

    public interface LoadMovieRecommendationsCallback{
        void onMovieRecommendationsReceived(MovieResponse movieResponse);
    }

    public interface LoadTVRecommendationsCallback{
        void onTVRecommendationsReceived(TVResponse tvResponse);
    }

    public interface LoadGenresCallback{
        void onGenresReceived(GenresResponse genresResponse);
    }

    public interface LoadVideosCallback{
        void onVideosReceived(VideosResponse videosResponse);
    }

    public interface LoadCreditsCallback{
        void onCreditsReceived(CreditsResponse creditsResponse);
    }
}
