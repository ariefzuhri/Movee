package com.ariefzuhri.blu.data.source.remote;

import com.ariefzuhri.blu.data.source.remote.response.CreditsResponse;
import com.ariefzuhri.blu.data.source.remote.response.GenresResponse;
import com.ariefzuhri.blu.data.source.remote.response.MovieDetailsResponse;
import com.ariefzuhri.blu.data.source.remote.response.MovieResponse;
import com.ariefzuhri.blu.data.source.remote.response.MultiSearchResponse;
import com.ariefzuhri.blu.data.source.remote.response.TVDetailsResponse;
import com.ariefzuhri.blu.data.source.remote.response.TVResponse;
import com.ariefzuhri.blu.data.source.remote.response.VideosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    /* Pencarian */
    @GET("search/multi")
    Call<MultiSearchResponse> getMultiSearch(
            @Query("api_key") String apiKey,
            @Query("query") String query,
            @Query("page") String page
    );

    /* Detail */
    @GET("movie/{movie_id}")
    Call<MovieDetailsResponse> getMovieDetails(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey
    );

    @GET("tv/{tv_id}")
    Call<TVDetailsResponse> getTVDetails(
            @Path("tv_id") int tvId,
            @Query("api_key") String apiKey
    );

    /* Trending hari ini */
    @GET("trending/movie/day")
    Call<MovieResponse> getMovieTrending(
            @Query("api_key") String apiKey,
            @Query("page") String page
    );

    @GET("trending/tv/day")
    Call<TVResponse> getTVTrending(
            @Query("api_key") String apiKey,
            @Query("page") String page
    );

    /* Rilis hari ini */
    @GET("discover/movie")
    Call<MovieResponse> getMovieLatestRelease(
            @Query("api_key") String apiKey,
            @Query("primary_release_date.gte") String todaysDateGTE,
            @Query("primary_release_date.lte") String todaysDateLTE,
            @Query("page") String page
    );

    @GET("discover/tv")
    Call<TVResponse> getTVLatestRelease(
            @Query("api_key") String apiKey,
            @Query("primary_release_date.gte") String todaysDateGTE,
            @Query("primary_release_date.lte") String todaysDateLTE,
            @Query("page") String page
    );

    /* Sedang tayang */
    @GET("movie/now_playing")
    Call<MovieResponse> getMovieNowPlaying(
            @Path("media_type") String mediaType,
            @Query("api_key") String apiKey,
            @Query("primary_release_date.gte") String todaysDateGTE,
            @Query("primary_release_date.lte") String todaysDateLTE,
            @Query("page") String page
    );

    @GET("tv/on_the_air")
    Call<TVResponse> getTVOnTheAir(
            @Query("api_key") String apiKey,
            @Query("page") String page
    );

    /* Mendatang */
    @GET("movie/upcoming")
    Call<MovieResponse> getMovieUpcoming(
            @Query("api_key") String apiKey,
            @Query("page") String page
    );

    /* Teratas */
    @GET("movie/top_rated")
    Call<MovieResponse> getMovieTopRated(
            @Query("api_key") String apiKey,
            @Query("page") String page
    );

    @GET("tv/top_rated")
    Call<TVResponse> getTVTopRated(
            @Query("api_key") String apiKey,
            @Query("page") String page
    );

    /* Populer */
    @GET("movie/popular")
    Call<MovieResponse> getMoviePopular(
            @Query("api_key") String apiKey,
            @Query("page") String page
    );

    @GET("tv/popular")
    Call<TVResponse> getTVPopular(
            @Query("api_key") String apiKey,
            @Query("page") String page
    );

    /* Rekomendasi */
    @GET("movie/{movie_id}/recommendations")
    Call<MovieResponse> getMovieRecommendations(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey,
            @Query("page") String page
    );

    @GET("tv/{tv_id}/recommendations")
    Call<TVResponse> getTVRecommendations(
            @Path("tv_id") String tvId,
            @Query("api_key") String apiKey,
            @Query("page") String page
    );

    /* Genre */
    @GET("genre/{media_type}/list")
    Call<GenresResponse> getGenres(
            @Path("media_type") String mediaType,
            @Query("api_key") String apiKey
    );

    /* VideoEntity */
    @GET("{media_type}/{id}/videos")
    Call<VideosResponse> getVideos(
            @Path("media_type") String mediaType,
            @Path("tv_id") String id,
            @Query("api_key") String apiKey
    );

    /* Kredit */
    @GET("{media_type}/{id}/credits")
    Call<CreditsResponse> getCredits(
            @Path("media_type") String mediaType,
            @Path("id") String id,
            @Query("api_key") String apiKey
    );
}
