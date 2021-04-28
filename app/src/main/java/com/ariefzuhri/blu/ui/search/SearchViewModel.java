package com.ariefzuhri.blu.ui.search;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ariefzuhri.blu.R;
import com.ariefzuhri.blu.data.source.local.entity.GenreEntity;
import com.ariefzuhri.blu.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.blu.data.CatalogRepository;
import com.ariefzuhri.blu.vo.Resource;

import java.util.List;

import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_MOVIE_LATEST_RELEASE;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_MOVIE_NOW_PLAYING;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_MOVIE_POPULAR;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_MOVIE_RECOMMENDATIONS;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_MOVIE_TOP_RATED;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_MOVIE_TRENDING;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_MOVIE_UPCOMING;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_MULTI_SEARCH;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_TV_LATEST_RELEASE;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_TV_ON_THE_AIR;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_TV_POPULAR;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_TV_RECOMMENDATIONS;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_TV_TOP_RATED;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_TV_TRENDING;

public class SearchViewModel extends AndroidViewModel {

    private final CatalogRepository repository;

    private int page;
    private String query;
    private int mediaId;
    private int queryType;

    public SearchViewModel(Application application, CatalogRepository repository){
        super(application);
        this.repository = repository;
    }

    public void setPage(int page){
        this.page = page;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public void setQueryType(int queryType) {
        this.queryType = queryType;
    }

    private MutableLiveData<String> header;
    private MutableLiveData<List<MediaEntity>> searchResult;
    private LiveData<Resource<List<GenreEntity>>> genres;

    public LiveData<String> getHeader(){
        if (header == null) header = new MutableLiveData<>();
        return header;
    }

    public LiveData<List<MediaEntity>> getSearchResult(){
        if (searchResult == null) {
            switch (queryType){
                case QUERY_TYPE_MULTI_SEARCH:
                    searchResult = repository.getMultiSearch(query, page);
                    header.postValue(getApplication().getString(R.string.search));
                    break;

                case QUERY_TYPE_MOVIE_TRENDING:
                    searchResult = repository.getMovieTrending(page);
                    header.postValue(getApplication().getString(R.string.trending_movies));
                    break;

                case QUERY_TYPE_TV_TRENDING:
                    searchResult = repository.getTVTrending(page);
                    header.postValue(getApplication().getString(R.string.trending_tv_shows));
                    break;

                case QUERY_TYPE_MOVIE_LATEST_RELEASE:
                    searchResult = repository.getMovieLatestRelease(page);
                    header.postValue(getApplication().getString(R.string.latest_release_movies));
                    break;

                case QUERY_TYPE_TV_LATEST_RELEASE:
                    searchResult = repository.getTVLatestRelease(page);
                    header.postValue(getApplication().getString(R.string.latest_release_tv_shows));
                    break;

                case QUERY_TYPE_MOVIE_NOW_PLAYING:
                    searchResult = repository.getMovieNowPlaying(page);
                    header.postValue(getApplication().getString(R.string.now_playing_movies));
                    break;

                case QUERY_TYPE_TV_ON_THE_AIR:
                    searchResult = repository.getTVOnTheAir(page);
                    header.postValue(getApplication().getString(R.string.on_the_air_tv_shows));
                    break;

                case QUERY_TYPE_MOVIE_UPCOMING:
                    searchResult = repository.getMovieUpcoming(page);
                    header.postValue(getApplication().getString(R.string.upcoming_movies));
                    break;

                case QUERY_TYPE_MOVIE_TOP_RATED:
                    searchResult = repository.getMovieTopRated(page);
                    header.postValue(getApplication().getString(R.string.top_rated_movies));
                    break;

                case QUERY_TYPE_TV_TOP_RATED:
                    searchResult = repository.getTVTopRated(page);
                    header.postValue(getApplication().getString(R.string.top_rated_tv_shows));
                    break;

                case QUERY_TYPE_MOVIE_POPULAR:
                    searchResult = repository.getMoviePopular(page);
                    header.postValue(getApplication().getString(R.string.popular_movies));
                    break;

                case QUERY_TYPE_TV_POPULAR:
                    searchResult = repository.getTVPopular(page);
                    header.postValue(getApplication().getString(R.string.popular_tv_shows));
                    break;

                case QUERY_TYPE_MOVIE_RECOMMENDATIONS:
                    searchResult = repository.getMovieRecommendations(mediaId, page);
                    header.postValue(getApplication().getString(R.string.recommendations_movies));
                    break;

                case QUERY_TYPE_TV_RECOMMENDATIONS:
                    searchResult = repository.getTVRecommendations(mediaId, page);
                    header.postValue(getApplication().getString(R.string.recommendations_tv_shows));
                    break;
            }
        }
        return searchResult;
    }

    public LiveData<Resource<List<GenreEntity>>> getGenres(){
        if (genres == null)genres = repository.getGenres();
        return genres;
    }
}
