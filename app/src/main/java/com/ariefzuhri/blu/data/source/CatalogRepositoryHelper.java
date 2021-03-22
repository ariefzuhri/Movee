package com.ariefzuhri.blu.data.source;

import com.ariefzuhri.blu.data.CreditEntity;
import com.ariefzuhri.blu.data.CreditEntity.Cast;
import com.ariefzuhri.blu.data.CreditEntity.Crew;
import com.ariefzuhri.blu.data.GenreEntity;
import com.ariefzuhri.blu.data.MediaEntity;
import com.ariefzuhri.blu.data.MediaEntity.AiredDate;
import com.ariefzuhri.blu.data.MediaEntity.Studio;
import com.ariefzuhri.blu.data.TrailerEntity;
import com.ariefzuhri.blu.data.source.remote.response.CastItem;
import com.ariefzuhri.blu.data.source.remote.response.CreditsResponse;
import com.ariefzuhri.blu.data.source.remote.response.CrewItem;
import com.ariefzuhri.blu.data.source.remote.response.GenreItem;
import com.ariefzuhri.blu.data.source.remote.response.GenresResponse;
import com.ariefzuhri.blu.data.source.remote.response.MovieItem;
import com.ariefzuhri.blu.data.source.remote.response.MovieResponse;
import com.ariefzuhri.blu.data.source.remote.response.MultiSearchResponse;
import com.ariefzuhri.blu.data.source.remote.response.ProductionCompanyItem;
import com.ariefzuhri.blu.data.source.remote.response.SearchResultItem;
import com.ariefzuhri.blu.data.source.remote.response.TVItem;
import com.ariefzuhri.blu.data.source.remote.response.MovieDetailsResponse;
import com.ariefzuhri.blu.data.source.remote.response.TVDetailsResponse;
import com.ariefzuhri.blu.data.source.remote.response.TVResponse;
import com.ariefzuhri.blu.data.source.remote.response.VideoItem;
import com.ariefzuhri.blu.data.source.remote.response.VideosResponse;

import java.util.ArrayList;
import java.util.List;

import static com.ariefzuhri.blu.utils.Constants.MEDIA_TYPE_MOVIE;
import static com.ariefzuhri.blu.utils.Constants.MEDIA_TYPE_TV;

class CatalogRepositoryHelper {

    private static MediaEntity movieItemToMedia(MovieItem item){
        return new MediaEntity(item.getId(),
                item.getTitle(),
                item.getPosterPath(),
                item.getBackdropPath(),
                item.getVoteAverage(),
                item.getVoteCount(),
                item.getPopularity(),
                MEDIA_TYPE_MOVIE,
                new AiredDate(item.getReleaseDate()),
                item.getGenreIds(),
                item.getOverview());
    }

    private static MediaEntity tvItemToMedia(TVItem item){
        return new MediaEntity(item.getId(),
                item.getName(),
                item.getPosterPath(),
                item.getBackdropPath(),
                item.getVoteAverage(),
                item.getVoteCount(),
                item.getPopularity(),
                MEDIA_TYPE_TV,
                new AiredDate(item.getFirstAirDate(), null),
                item.getGenreIds(),
                item.getOverview());
    }

    public static List<MediaEntity> movieResponseToMediaList(MovieResponse response){
        List<MediaEntity> mediaList = new ArrayList<>();
        for (MovieItem movie : response.getResults()){
            mediaList.add(movieItemToMedia(movie));
        }
        return mediaList;
    }

    public static List<MediaEntity> tvResponsesToMediaList(TVResponse response){
        List<MediaEntity> mediaList = new ArrayList<>();
        for (TVItem tv : response.getResults()){
            mediaList.add(tvItemToMedia(tv));
        }
        return mediaList;
    }

    public static List<MediaEntity> multiSearchResponseToMediaList(MultiSearchResponse response){
        List<MediaEntity> mediaList = new ArrayList<>();
        for (SearchResultItem searchResult : response.getResults()){
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

    public static MediaEntity movieDetailsResponseToMedia(MovieDetailsResponse response){
        List<GenreEntity> genreList = genresItemToGenreList(response.getGenres());
        List<Studio> studioList = productionCompaniesItemToStudioList(
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
                new AiredDate(response.getReleaseDate()),
                studioList,
                genreList,
                response.getRuntime(),
                response.getOverview(),
                null);
    }

    public static MediaEntity tvDetailsResponseToMedia(TVDetailsResponse response){
        List<GenreEntity> genreList = genresItemToGenreList(response.getGenres());
        List<Studio> studioList = productionCompaniesItemToStudioList(
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
                new AiredDate(response.getFirstAirDate(), response.getLastAirDate()),
                studioList,
                genreList,
                (response.getEpisodeRunTime().isEmpty()) ? 0 : response.getEpisodeRunTime().get(0),
                response.getOverview(),
                null);
    }

    private static List<Studio> productionCompaniesItemToStudioList(List<ProductionCompanyItem> items){
        List<Studio> studioList = new ArrayList<>();
        for (ProductionCompanyItem studio : items){
            studioList.add(new Studio(studio.getId(), studio.getName(), studio.getLogoPath()));
        }
        return studioList;
    }

    private static List<GenreEntity> genresItemToGenreList(List<GenreItem> items){
        List<GenreEntity> genreList = new ArrayList<>();
        for (GenreItem genre : items){
            genreList.add(new GenreEntity(genre.getId(), genre.getName()));
        }
        return genreList;
    }

    public static List<GenreEntity> genreResponseToGenreList(GenresResponse response){
        List<GenreEntity> genreList = new ArrayList<>();
        for (GenreItem genre : response.getGenres()) {
            genreList.add(new GenreEntity(genre.getId(), genre.getName()));
        }
        return genreList;
    }

    public static List<TrailerEntity> videosResponseToTrailerList(VideosResponse response){
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

    public static CreditEntity creditsResponseToCredit(CreditsResponse response){
        List<Cast> castList = new ArrayList<>();
        for (CastItem cast : response.getCast()) {
            castList.add(new Cast(cast.getId(),
                    cast.getName(),
                    cast.getCreditId(),
                    cast.getProfilePath(),
                    cast.getKnownForDepartment(),
                    cast.getCharacter())
            );
        }

        List<Crew> crewList = new ArrayList<>();
        for (CrewItem crew : response.getCrew()) {
            crewList.add(new Crew(crew.getId(),
                    crew.getName(),
                    crew.getCreditId(),
                    crew.getProfilePath(),
                    crew.getJob())
            );
        }

        return new CreditEntity(response.getId(), castList, crewList);
    }
}
