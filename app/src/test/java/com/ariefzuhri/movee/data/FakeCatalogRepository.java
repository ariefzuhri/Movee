package com.ariefzuhri.movee.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ariefzuhri.movee.data.source.remote.RemoteDataSource;
import com.ariefzuhri.movee.data.source.remote.entity.AiredDateEntity;
import com.ariefzuhri.movee.data.source.remote.entity.CastEntity;
import com.ariefzuhri.movee.data.source.remote.entity.CreditsEntity;
import com.ariefzuhri.movee.data.source.remote.entity.CrewEntity;
import com.ariefzuhri.movee.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.movee.data.source.remote.entity.StudioEntity;
import com.ariefzuhri.movee.data.source.remote.entity.TrailerEntity;
import com.ariefzuhri.movee.data.source.remote.response.CastItem;
import com.ariefzuhri.movee.data.source.remote.response.CreditsResponse;
import com.ariefzuhri.movee.data.source.remote.response.CrewItem;
import com.ariefzuhri.movee.data.source.remote.response.GenreItem;
import com.ariefzuhri.movee.data.source.remote.response.GenresResponse;
import com.ariefzuhri.movee.data.source.remote.response.MovieDetailsResponse;
import com.ariefzuhri.movee.data.source.remote.response.MovieItem;
import com.ariefzuhri.movee.data.source.remote.response.MovieResponse;
import com.ariefzuhri.movee.data.source.remote.response.MultiSearchResponse;
import com.ariefzuhri.movee.data.source.remote.response.ProductionCompanyItem;
import com.ariefzuhri.movee.data.source.remote.response.SearchResultItem;
import com.ariefzuhri.movee.data.source.remote.response.TVDetailsResponse;
import com.ariefzuhri.movee.data.source.remote.response.TVItem;
import com.ariefzuhri.movee.data.source.remote.response.TVResponse;
import com.ariefzuhri.movee.data.source.remote.response.VideoItem;
import com.ariefzuhri.movee.data.source.remote.response.VideosResponse;

import java.util.ArrayList;
import java.util.List;

import static com.ariefzuhri.movee.utils.Constants.MEDIA_TYPE_MOVIE;
import static com.ariefzuhri.movee.utils.Constants.MEDIA_TYPE_TV;

public class FakeCatalogRepository implements CatalogDataSource {

    private final RemoteDataSource remoteDataSource;

    public FakeCatalogRepository(@NonNull RemoteDataSource remoteDataSource){
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public MutableLiveData<List<MediaEntity>> getMultiSearch(String query, int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getMultiSearch(query, page, response -> {
            List<MediaEntity> mediaList = multiSearchResponseToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public MutableLiveData<MediaEntity> getMovieDetails(int movieId) {
        MutableLiveData<MediaEntity> result = new MutableLiveData<>();
        remoteDataSource.getMovieDetails(movieId, response -> {
            MediaEntity media = movieDetailsResponseToMedia(response);
            result.postValue(media);
        });
        return result;
    }

    @Override
    public MutableLiveData<MediaEntity> getTVDetails(int tvId) {
        MutableLiveData<MediaEntity> result = new MutableLiveData<>();
        remoteDataSource.getTVDetails(tvId, response -> {
            MediaEntity media = tvDetailsResponseToMedia(response);
            result.postValue(media);
        });
        return result;
    }

    @Override
    public MutableLiveData<List<MediaEntity>> getMovieTrending(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getMovieTrending(page, response -> {
            List<MediaEntity> mediaList = movieResponseToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public MutableLiveData<List<MediaEntity>> getTVTrending(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getTVTrending(page, response -> {
            List<MediaEntity> mediaList = tvResponsesToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public MutableLiveData<List<MediaEntity>> getMovieLatestRelease(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getMovieLatestRelease(page, response -> {
            List<MediaEntity> mediaList = movieResponseToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public MutableLiveData<List<MediaEntity>> getTVLatestRelease(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getTVLatestRelease(page, response -> {
            List<MediaEntity> mediaList = tvResponsesToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public MutableLiveData<List<MediaEntity>> getMovieNowPlaying(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getMovieNowPlaying(page, response -> {
            List<MediaEntity> mediaList = movieResponseToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public MutableLiveData<List<MediaEntity>> getTVOnTheAir(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getTVOnTheAir(page, response -> {
            List<MediaEntity> mediaList = tvResponsesToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public MutableLiveData<List<MediaEntity>> getMovieUpcoming(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getMovieUpcoming(page, response -> {
            List<MediaEntity> mediaList = movieResponseToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public MutableLiveData<List<MediaEntity>> getMovieTopRated(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getMovieTopRated(page, response -> {
            List<MediaEntity> mediaList = movieResponseToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public MutableLiveData<List<MediaEntity>> getTVTopRated(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getTVTopRated(page, response -> {
            List<MediaEntity> mediaList = tvResponsesToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public MutableLiveData<List<MediaEntity>> getMoviePopular(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getMoviePopular(page, response -> {
            List<MediaEntity> mediaList = movieResponseToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public MutableLiveData<List<MediaEntity>> getTVPopular(int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getTVPopular(page, response -> {
            List<MediaEntity> mediaList = tvResponsesToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public MutableLiveData<List<MediaEntity>> getMovieRecommendations(int movieId, int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getMovieRecommendations(movieId, page, response -> {
            List<MediaEntity> mediaList = movieResponseToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public MutableLiveData<List<MediaEntity>> getTVRecommendations(int tvId, int page) {
        MutableLiveData<List<MediaEntity>> result = new MutableLiveData<>();
        remoteDataSource.getTVRecommendations(tvId, page, response -> {
            List<MediaEntity> mediaList = tvResponsesToMediaList(response);
            result.postValue(mediaList);
        });
        return result;
    }

    @Override
    public MutableLiveData<List<GenreEntity>> getGenres(String mediaType) {
        MutableLiveData<List<GenreEntity>> result = new MutableLiveData<>();
        remoteDataSource.getGenres(mediaType, response -> {
            List<GenreEntity> genreList = genreResponseToGenreList(response);
            result.postValue(genreList);
        });
        return result;
    }

    @Override
    public MutableLiveData<List<TrailerEntity>> getVideos(String mediaType, int mediaId) {
        MutableLiveData<List<TrailerEntity>> result = new MutableLiveData<>();
        remoteDataSource.getVideos(mediaType, mediaId, response -> {
            List<TrailerEntity> trailerList = videosResponseToTrailerList(response);
            result.postValue(trailerList);
        });
        return result;
    }

    @Override
    public MutableLiveData<CreditsEntity> getCredits(String mediaType, int mediaId) {
        MutableLiveData<CreditsEntity> result = new MutableLiveData<>();
        remoteDataSource.getCredits(mediaType, mediaId, response -> {
            CreditsEntity credit = creditsResponseToCredit(response);
            result.postValue(credit);
        });
        return result;
    }

    /* Untuk konversi */
    private MediaEntity movieItemToMedia(MovieItem item){
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

    private MediaEntity tvItemToMedia(TVItem item){
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

    public List<MediaEntity> movieResponseToMediaList(MovieResponse response){
        List<MediaEntity> mediaList = new ArrayList<>();
        for (MovieItem movie : response.getResults()){
            mediaList.add(movieItemToMedia(movie));
        }
        return mediaList;
    }

    public List<MediaEntity> tvResponsesToMediaList(TVResponse response){
        List<MediaEntity> mediaList = new ArrayList<>();
        for (TVItem tv : response.getResults()){
            mediaList.add(tvItemToMedia(tv));
        }
        return mediaList;
    }

    public List<MediaEntity> multiSearchResponseToMediaList(MultiSearchResponse response){
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
                        new AiredDateEntity(searchResult.getReleaseDate()),
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
                        new AiredDateEntity(searchResult.getFirstAirDate(), null),
                        searchResult.getGenreIds(),
                        searchResult.getOverview()));
            }
        }
        return mediaList;
    }

    public MediaEntity movieDetailsResponseToMedia(MovieDetailsResponse response){
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

    public MediaEntity tvDetailsResponseToMedia(TVDetailsResponse response){
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

    private List<StudioEntity> productionCompaniesItemToStudioList(List<ProductionCompanyItem> items){
        List<StudioEntity> studioList = new ArrayList<>();
        for (ProductionCompanyItem studio : items){
            studioList.add(new StudioEntity(studio.getId(), studio.getName(), studio.getLogoPath()));
        }
        return studioList;
    }

    private List<GenreEntity> genresItemToGenreList(List<GenreItem> items){
        List<GenreEntity> genreList = new ArrayList<>();
        for (GenreItem genre : items){
            genreList.add(new GenreEntity(genre.getId(), genre.getName()));
        }
        return genreList;
    }

    public List<GenreEntity> genreResponseToGenreList(GenresResponse response){
        List<GenreEntity> genreList = new ArrayList<>();
        for (GenreItem genre : response.getGenres()) {
            genreList.add(new GenreEntity(genre.getId(), genre.getName()));
        }
        return genreList;
    }

    public List<TrailerEntity> videosResponseToTrailerList(VideosResponse response){
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

    public CreditsEntity creditsResponseToCredit(CreditsResponse response){
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
