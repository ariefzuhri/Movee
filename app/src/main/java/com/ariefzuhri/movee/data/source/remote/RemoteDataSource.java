package com.ariefzuhri.movee.data.source.remote;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.ariefzuhri.movee.data.source.remote.response.CreditsResponse;
import com.ariefzuhri.movee.data.source.remote.response.GenresResponse;
import com.ariefzuhri.movee.data.source.remote.response.MovieDetailsResponse;
import com.ariefzuhri.movee.data.source.remote.response.MovieResponse;
import com.ariefzuhri.movee.data.source.remote.response.MultiSearchResponse;
import com.ariefzuhri.movee.data.source.remote.response.TVDetailsResponse;
import com.ariefzuhri.movee.data.source.remote.response.TVResponse;
import com.ariefzuhri.movee.data.source.remote.response.VideosResponse;
import com.ariefzuhri.movee.data.source.remote.network.ApiConfig;
import com.ariefzuhri.movee.utils.EspressoIdlingResource;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ariefzuhri.movee.BuildConfig.TMDB_API_KEY;
import static com.ariefzuhri.movee.utils.Constants.MEDIA_TYPE_MOVIE;
import static com.ariefzuhri.movee.utils.Constants.MEDIA_TYPE_TV;
import static com.ariefzuhri.movee.utils.DateHelper.getCurrentDate;

public class RemoteDataSource {

    private final String TAG = getClass().getSimpleName();
    private static RemoteDataSource INSTANCE;

    public static RemoteDataSource getInstance(){
        if (INSTANCE == null){
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }

    public void getMultiSearch(String query, int page, LoadMultiSearchCallback callback){
        EspressoIdlingResource.increment();
        Call<MultiSearchResponse> client = ApiConfig.getApiService().getMultiSearch(TMDB_API_KEY, query, page);
        client.enqueue(new Callback<MultiSearchResponse>() {
            @Override
            public void onResponse(@NotNull Call<MultiSearchResponse> call, @NotNull Response<MultiSearchResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onMultiSearchReceived(ApiResponse.success(response.body()));
                        EspressoIdlingResource.decrement();
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MultiSearchResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getMovieDetails(int movieId, LoadMovieDetailsCallback callback){
        EspressoIdlingResource.increment();
        Call<MovieDetailsResponse> client = ApiConfig.getApiService().getMovieDetails(movieId, TMDB_API_KEY);
        client.enqueue(new Callback<MovieDetailsResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieDetailsResponse> call, @NotNull Response<MovieDetailsResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onMovieDetailsReceived(ApiResponse.success(response.body()));
                        EspressoIdlingResource.decrement();
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MovieDetailsResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getTVDetails(int tvId, LoadTVDetailsCallback callback){
        EspressoIdlingResource.increment();
        Call<TVDetailsResponse> client = ApiConfig.getApiService().getTVDetails(tvId, TMDB_API_KEY);
        client.enqueue(new Callback<TVDetailsResponse>() {
            @Override
            public void onResponse(@NotNull Call<TVDetailsResponse> call, @NotNull Response<TVDetailsResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onTVDetailsReceived(ApiResponse.success(response.body()));
                        EspressoIdlingResource.decrement();
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<TVDetailsResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getMovieTrending(int page, LoadMovieTrendingCallback callback){
        EspressoIdlingResource.increment();
        Call<MovieResponse> client = ApiConfig.getApiService().getMovieTrending(TMDB_API_KEY, page);
        client.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieResponse> call, @NotNull Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onMovieTrendingReceived(ApiResponse.success(response.body()));
                        EspressoIdlingResource.decrement();
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getTVTrending(int page, LoadTVTrendingCallback callback){
        EspressoIdlingResource.increment();
        Call<TVResponse> client = ApiConfig.getApiService().getTVTrending(TMDB_API_KEY, page);
        client.enqueue(new Callback<TVResponse>() {
            @Override
            public void onResponse(@NotNull Call<TVResponse> call, @NotNull Response<TVResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onTVTrendingReceived(ApiResponse.success(response.body()));
                        EspressoIdlingResource.decrement();
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<TVResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getMovieLatestRelease(int page, LoadMovieLatestReleaseCallback callback){
        EspressoIdlingResource.increment();
        Call<MovieResponse> client = ApiConfig.getApiService().getMovieLatestRelease(TMDB_API_KEY, getCurrentDate(),  getCurrentDate(), page);
        client.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieResponse> call, @NotNull Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onMovieLatestReleaseReceived(ApiResponse.success(response.body()));
                        EspressoIdlingResource.decrement();
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getTVLatestRelease(int page, LoadTVLatestReleaseCallback callback){
        EspressoIdlingResource.increment();
        Call<TVResponse> client = ApiConfig.getApiService().getTVLatestRelease(TMDB_API_KEY,  getCurrentDate(),  getCurrentDate(), page);
        client.enqueue(new Callback<TVResponse>() {
            @Override
            public void onResponse(@NotNull Call<TVResponse> call, @NotNull Response<TVResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onTVLatestReleaseReceived(ApiResponse.success(response.body()));
                        EspressoIdlingResource.decrement();
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<TVResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getMovieNowPlaying(int page, LoadMovieNowPlayingCallback callback){
        EspressoIdlingResource.increment();
        Call<MovieResponse> client = ApiConfig.getApiService().getMovieNowPlaying(TMDB_API_KEY, page);
        client.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieResponse> call, @NotNull Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onMovieNowPlayingReceived(ApiResponse.success(response.body()));
                        EspressoIdlingResource.decrement();
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getTVOnTheAir(int page, LoadTVOnTheAirCallback callback){
        EspressoIdlingResource.increment();
        Call<TVResponse> client = ApiConfig.getApiService().getTVOnTheAir(TMDB_API_KEY, page);
        client.enqueue(new Callback<TVResponse>() {
            @Override
            public void onResponse(@NotNull Call<TVResponse> call, @NotNull Response<TVResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onTVOnTheAirReceived(ApiResponse.success(response.body()));
                        EspressoIdlingResource.decrement();
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<TVResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getMovieUpcoming(int page, LoadMovieUpcomingCallback callback){
        EspressoIdlingResource.increment();
        Call<MovieResponse> client = ApiConfig.getApiService().getMovieUpcoming(TMDB_API_KEY, page);
        client.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieResponse> call, @NotNull Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onMovieUpcomingReceived(ApiResponse.success(response.body()));
                        EspressoIdlingResource.decrement();
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getMovieTopRated(int page, LoadMovieTopRatedCallback callback){
        EspressoIdlingResource.increment();
        Call<MovieResponse> client = ApiConfig.getApiService().getMovieTopRated(TMDB_API_KEY, page);
        client.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieResponse> call, @NotNull Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onMovieTopRatedReceived(ApiResponse.success(response.body()));
                        EspressoIdlingResource.decrement();
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getTVTopRated(int page, LoadTVTopRatedCallback callback){
        EspressoIdlingResource.increment();
        Call<TVResponse> client = ApiConfig.getApiService().getTVTopRated(TMDB_API_KEY, page);
        client.enqueue(new Callback<TVResponse>() {
            @Override
            public void onResponse(@NotNull Call<TVResponse> call, @NotNull Response<TVResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onTVTopRatedReceived(ApiResponse.success(response.body()));
                        EspressoIdlingResource.decrement();
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<TVResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getMoviePopular(int page, LoadMoviePopularCallback callback){
        EspressoIdlingResource.increment();
        Call<MovieResponse> client = ApiConfig.getApiService().getMoviePopular(TMDB_API_KEY, page);
        client.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieResponse> call, @NotNull Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onMoviePopularReceived(ApiResponse.success(response.body()));
                        EspressoIdlingResource.decrement();
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getTVPopular(int page, LoadTVPopularCallback callback){
        EspressoIdlingResource.increment();
        Call<TVResponse> client = ApiConfig.getApiService().getTVPopular(TMDB_API_KEY, page);
        client.enqueue(new Callback<TVResponse>() {
            @Override
            public void onResponse(@NotNull Call<TVResponse> call, @NotNull Response<TVResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onTVPopularReceived(ApiResponse.success(response.body()));
                        EspressoIdlingResource.decrement();
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<TVResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getMovieRecommendations(int movieId, int page, LoadMovieRecommendationsCallback callback){
        EspressoIdlingResource.increment();
        Call<MovieResponse> client = ApiConfig.getApiService().getMovieRecommendations(movieId, TMDB_API_KEY, page);
        client.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieResponse> call, @NotNull Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onMovieRecommendationsReceived(ApiResponse.success(response.body()));
                        EspressoIdlingResource.decrement();
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getTVRecommendations(int tvId, int page, LoadTVRecommendationsCallback callback){
        EspressoIdlingResource.increment();
        Call<TVResponse> client = ApiConfig.getApiService().getTVRecommendations(tvId, TMDB_API_KEY, page);
        client.enqueue(new Callback<TVResponse>() {
            @Override
            public void onResponse(@NotNull Call<TVResponse> call, @NotNull Response<TVResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onTVRecommendationsReceived(ApiResponse.success(response.body()));
                        EspressoIdlingResource.decrement();
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<TVResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public LiveData<ApiResponse<GenresResponse>> getGenres(){
        MediatorLiveData<ApiResponse<GenresResponse>> resultMerger = new MediatorLiveData<>();

        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<GenresResponse>> resultMovie = new MutableLiveData<>();
        Call<GenresResponse> clientMovie = ApiConfig.getApiService().getGenres(MEDIA_TYPE_MOVIE, TMDB_API_KEY);
        clientMovie.enqueue(new Callback<GenresResponse>() {
            @Override
            public void onResponse(@NotNull Call<GenresResponse> call, @NotNull Response<GenresResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        resultMovie.setValue(ApiResponse.success(response.body()));
                        resultMerger.addSource(resultMovie, resultMerger::setValue);
                        EspressoIdlingResource.decrement();
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<GenresResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<GenresResponse>> resultTV = new MutableLiveData<>();
        Call<GenresResponse> clientTV = ApiConfig.getApiService().getGenres(MEDIA_TYPE_TV, TMDB_API_KEY);
        clientTV.enqueue(new Callback<GenresResponse>() {
            @Override
            public void onResponse(@NotNull Call<GenresResponse> call, @NotNull Response<GenresResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        resultTV.setValue(ApiResponse.success(response.body()));
                        resultMerger.addSource(resultTV, resultMerger::setValue);
                        EspressoIdlingResource.decrement();
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<GenresResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return resultMerger;
    }

    public void getVideos(String mediaType, int mediaId, LoadVideosCallback callback){
        EspressoIdlingResource.increment();
        Call<VideosResponse> client = ApiConfig.getApiService().getVideos(mediaType, mediaId, TMDB_API_KEY);
        client.enqueue(new Callback<VideosResponse>() {
            @Override
            public void onResponse(@NotNull Call<VideosResponse> call, @NotNull Response<VideosResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onVideosReceived(ApiResponse.success(response.body()));
                        EspressoIdlingResource.decrement();
                    }
                } else Log.e(TAG, "onFailure: " + response.message());
            }

            @Override
            public void onFailure(@NotNull Call<VideosResponse> call, @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getCredits(String mediaType, int mediaId, LoadCreditsCallback callback){
        EspressoIdlingResource.increment();
        Call<CreditsResponse> client = ApiConfig.getApiService().getCredits(mediaType, mediaId, TMDB_API_KEY);
        client.enqueue(new Callback<CreditsResponse>() {
            @Override
            public void onResponse(@NotNull Call<CreditsResponse> call, @NotNull Response<CreditsResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.onCreditsReceived(ApiResponse.success(response.body()));
                        EspressoIdlingResource.decrement();
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
        void onMultiSearchReceived(ApiResponse<MultiSearchResponse> multiSearchResponse);
    }

    public interface LoadMovieDetailsCallback{
        void onMovieDetailsReceived(ApiResponse<MovieDetailsResponse> movieDetailsResponse);
    }

    public interface LoadTVDetailsCallback{
        void onTVDetailsReceived(ApiResponse<TVDetailsResponse> tvDetailsResponse);
    }

    public interface LoadMovieTrendingCallback{
        void onMovieTrendingReceived(ApiResponse<MovieResponse> movieResponse);
    }

    public interface LoadTVTrendingCallback{
        void onTVTrendingReceived(ApiResponse<TVResponse> tvResponse);
    }

    public interface LoadMovieLatestReleaseCallback{
        void onMovieLatestReleaseReceived(ApiResponse<MovieResponse> movieResponse);
    }

    public interface LoadTVLatestReleaseCallback{
        void onTVLatestReleaseReceived(ApiResponse<TVResponse> tvResponse);
    }

    public interface LoadMovieNowPlayingCallback{
        void onMovieNowPlayingReceived(ApiResponse<MovieResponse> movieResponse);
    }

    public interface LoadTVOnTheAirCallback{
        void onTVOnTheAirReceived(ApiResponse<TVResponse> tvResponse);
    }

    public interface LoadMovieUpcomingCallback{
        void onMovieUpcomingReceived(ApiResponse<MovieResponse> movieResponse);
    }

    public interface LoadMovieTopRatedCallback{
        void onMovieTopRatedReceived(ApiResponse<MovieResponse> movieResponse);
    }

    public interface LoadTVTopRatedCallback{
        void onTVTopRatedReceived(ApiResponse<TVResponse> tvResponse);
    }

    public interface LoadMoviePopularCallback{
        void onMoviePopularReceived(ApiResponse<MovieResponse> movieResponse);
    }

    public interface LoadTVPopularCallback{
        void onTVPopularReceived(ApiResponse<TVResponse> tvResponse);
    }

    public interface LoadMovieRecommendationsCallback{
        void onMovieRecommendationsReceived(ApiResponse<MovieResponse> movieResponse);
    }

    public interface LoadTVRecommendationsCallback{
        void onTVRecommendationsReceived(ApiResponse<TVResponse> tvResponse);
    }

    public interface LoadVideosCallback{
        void onVideosReceived(ApiResponse<VideosResponse> videosResponse);
    }

    public interface LoadCreditsCallback{
        void onCreditsReceived(ApiResponse<CreditsResponse> creditsResponse);
    }
}
