package com.ariefzuhri.movee.core.utils;

import com.ariefzuhri.movee.core.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.core.data.source.remote.entity.AiredDateEntity;
import com.ariefzuhri.movee.core.data.source.remote.entity.CastEntity;
import com.ariefzuhri.movee.core.data.source.remote.entity.CreditsEntity;
import com.ariefzuhri.movee.core.data.source.remote.entity.CrewEntity;
import com.ariefzuhri.movee.core.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.movee.core.data.source.remote.entity.StudioEntity;
import com.ariefzuhri.movee.core.data.source.remote.entity.TrailerEntity;
import com.ariefzuhri.movee.core.data.source.remote.response.CastItem;
import com.ariefzuhri.movee.core.data.source.remote.response.CreditsResponse;
import com.ariefzuhri.movee.core.data.source.remote.response.CrewItem;
import com.ariefzuhri.movee.core.data.source.remote.response.GenreItem;
import com.ariefzuhri.movee.core.data.source.remote.response.GenresResponse;
import com.ariefzuhri.movee.core.data.source.remote.response.MovieDetailsResponse;
import com.ariefzuhri.movee.core.data.source.remote.response.MovieItem;
import com.ariefzuhri.movee.core.data.source.remote.response.MovieResponse;
import com.ariefzuhri.movee.core.data.source.remote.response.MultiSearchResponse;
import com.ariefzuhri.movee.core.data.source.remote.response.ProductionCompanyItem;
import com.ariefzuhri.movee.core.data.source.remote.response.SearchResultItem;
import com.ariefzuhri.movee.core.data.source.remote.response.TVDetailsResponse;
import com.ariefzuhri.movee.core.data.source.remote.response.TVItem;
import com.ariefzuhri.movee.core.data.source.remote.response.TVResponse;
import com.ariefzuhri.movee.core.data.source.remote.response.VideoItem;
import com.ariefzuhri.movee.core.data.source.remote.response.VideosResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.ariefzuhri.movee.core.utils.Constants.MEDIA_TYPE_MOVIE;
import static com.ariefzuhri.movee.core.utils.Constants.MEDIA_TYPE_TV;

public class DataMapper {

    @NotNull
    public static MediaEntity movieItemToMedia(@NotNull MovieItem item) {
        return new MediaEntity(item.getId(),
                item.getTitle(),
                item.getPosterPath(),
                item.getBackdropPath(),
                item.getVoteAverage(),
                item.getVoteCount(),
                item.getPopularity(),
                MEDIA_TYPE_MOVIE,
                new AiredDateEntity(item.getReleaseDate()),
                item.getGenreIds(),
                item.getOverview());
    }

    @NotNull
    public static MediaEntity tvItemToMedia(@NotNull TVItem item) {
        return new MediaEntity(item.getId(),
                item.getName(),
                item.getPosterPath(),
                item.getBackdropPath(),
                item.getVoteAverage(),
                item.getVoteCount(),
                item.getPopularity(),
                MEDIA_TYPE_TV,
                new AiredDateEntity(item.getFirstAirDate(), null),
                item.getGenreIds(),
                item.getOverview());
    }

    @NotNull
    public static List<MediaEntity> movieResponseToMediaList(@NotNull MovieResponse response) {
        List<MediaEntity> mediaList = new ArrayList<>();
        for (MovieItem movie : response.getResults()) {
            mediaList.add(movieItemToMedia(movie));
        }
        return mediaList;
    }

    @NotNull
    public static List<MediaEntity> tvResponsesToMediaList(@NotNull TVResponse response) {
        List<MediaEntity> mediaList = new ArrayList<>();
        for (TVItem tv : response.getResults()) {
            mediaList.add(tvItemToMedia(tv));
        }
        return mediaList;
    }

    @NotNull
    public static List<MediaEntity> multiSearchResponseToMediaList(@NotNull MultiSearchResponse response) {
        List<MediaEntity> mediaList = new ArrayList<>();
        for (SearchResultItem searchResult : response.getResults()) {
            if (searchResult.getMediaType().equals(MEDIA_TYPE_MOVIE)) {
                mediaList.add(new MediaEntity(searchResult.getId(),
                        searchResult.getTitle(),
                        searchResult.getPosterPath(),
                        searchResult.getBackdropPath(),
                        searchResult.getVoteAverage(),
                        searchResult.getVoteCount(),
                        searchResult.getPopularity(),
                        MEDIA_TYPE_MOVIE,
                        new AiredDateEntity(searchResult.getReleaseDate()),
                        searchResult.getGenreIds(),
                        searchResult.getOverview()));
            } else if (searchResult.getMediaType().equals(MEDIA_TYPE_TV)) {
                mediaList.add(new MediaEntity(searchResult.getId(),
                        searchResult.getName(),
                        searchResult.getPosterPath(),
                        searchResult.getBackdropPath(),
                        searchResult.getVoteAverage(),
                        searchResult.getVoteCount(),
                        searchResult.getPopularity(),
                        MEDIA_TYPE_TV,
                        new AiredDateEntity(searchResult.getFirstAirDate(), null),
                        searchResult.getGenreIds(),
                        searchResult.getOverview()));
            }
        }
        return mediaList;
    }

    @NotNull
    public static MediaEntity movieDetailsResponseToMedia(@NotNull MovieDetailsResponse response) {
        List<GenreEntity> genreList = genresItemToGenreList(response.getGenres());
        List<StudioEntity> studioList = productionCompaniesItemToStudioList(
                response.getProductionCompanies()
        );

        return new MediaEntity(response.getId(),
                response.getTitle(),
                response.getPosterPath(),
                response.getBackdropPath(),
                response.getVoteAverage(),
                response.getVoteCount(),
                response.getPopularity(),
                MEDIA_TYPE_MOVIE,
                1,
                response.getStatus(),
                new AiredDateEntity(response.getReleaseDate()),
                studioList,
                genreList,
                response.getRuntime(),
                response.getOverview(),
                null);
    }

    @NotNull
    public static MediaEntity tvDetailsResponseToMedia(@NotNull TVDetailsResponse response) {
        List<GenreEntity> genreList = genresItemToGenreList(response.getGenres());
        List<StudioEntity> studioList = productionCompaniesItemToStudioList(
                response.getProductionCompanies()
        );

        return new MediaEntity(response.getId(),
                response.getName(),
                response.getPosterPath(),
                response.getBackdropPath(),
                response.getVoteAverage(),
                response.getVoteCount(),
                response.getPopularity(),
                MEDIA_TYPE_TV,
                response.getNumberOfEpisodes(),
                response.getStatus(),
                new AiredDateEntity(response.getFirstAirDate(), response.getLastAirDate()),
                studioList,
                genreList,
                (response.getEpisodeRunTime().isEmpty()) ? 0 : response.getEpisodeRunTime().get(0),
                response.getOverview(),
                null);
    }

    @NotNull
    public static List<StudioEntity> productionCompaniesItemToStudioList(@NotNull List<ProductionCompanyItem> items) {
        List<StudioEntity> studioList = new ArrayList<>();
        for (ProductionCompanyItem studio : items) {
            studioList.add(new StudioEntity(studio.getId(), studio.getName(), studio.getLogoPath()));
        }
        return studioList;
    }

    @NotNull
    public static List<GenreEntity> genresItemToGenreList(@NotNull List<GenreItem> items) {
        List<GenreEntity> genreList = new ArrayList<>();
        for (GenreItem genre : items) {
            genreList.add(new GenreEntity(genre.getId(), genre.getName()));
        }
        return genreList;
    }

    @NotNull
    public static List<GenreEntity> genreResponseToGenreList(@NotNull GenresResponse response) {
        List<GenreEntity> genreList = new ArrayList<>();
        for (GenreItem genre : response.getGenres()) {
            genreList.add(new GenreEntity(genre.getId(), genre.getName()));
        }
        return genreList;
    }

    @NotNull
    public static List<TrailerEntity> videosResponseToTrailerList(@NotNull VideosResponse response) {
        List<TrailerEntity> trailerList = new ArrayList<>();
        for (VideoItem video : response.getResults()) {
            trailerList.add(new TrailerEntity(video.getId(),
                    video.getName(),
                    video.getSite(),
                    video.getType(),
                    video.getKey())
            );
        }
        return trailerList;
    }

    @NotNull
    public static CreditsEntity creditsResponseToCredit(@NotNull CreditsResponse response) {
        List<CastEntity> castList = new ArrayList<>();
        for (CastItem cast : response.getCast()) {
            castList.add(new CastEntity(cast.getId(),
                    cast.getName(),
                    cast.getCreditId(),
                    cast.getProfilePath(),
                    cast.getKnownForDepartment(),
                    cast.getCharacter())
            );
        }

        List<CrewEntity> crewList = new ArrayList<>();
        for (CrewItem crew : response.getCrew()) {
            crewList.add(new CrewEntity(crew.getId(),
                    crew.getName(),
                    crew.getCreditId(),
                    crew.getProfilePath(),
                    crew.getJob())
            );
        }

        return new CreditsEntity(response.getId(), castList, crewList);
    }
}
