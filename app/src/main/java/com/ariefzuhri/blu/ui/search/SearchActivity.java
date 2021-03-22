package com.ariefzuhri.blu.ui.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.ariefzuhri.blu.R;
import com.ariefzuhri.blu.databinding.ActivitySearchBinding;
import com.ariefzuhri.blu.data.MediaEntity;
import com.ariefzuhri.blu.ui.main.home.MediaAdapter;
import com.ariefzuhri.blu.utils.ShimmerHelper;
import com.ariefzuhri.blu.viewmodel.ViewModelFactory;

import java.util.List;

import static com.ariefzuhri.blu.ui.main.home.MediaHelper.movieEntitiesToMediaList;
import static com.ariefzuhri.blu.ui.main.home.MediaHelper.searchResultEntitiesToMediaList;
import static com.ariefzuhri.blu.ui.main.home.MediaHelper.tvEntitiesToMediaList;
import static com.ariefzuhri.blu.utils.Constants.EXTRA_MEDIA_ID;
import static com.ariefzuhri.blu.utils.Constants.EXTRA_QUERY;
import static com.ariefzuhri.blu.utils.Constants.EXTRA_QUERY_TYPE;
import static com.ariefzuhri.blu.utils.Constants.MEDIA_TYPE_MOVIE;
import static com.ariefzuhri.blu.utils.Constants.MEDIA_TYPE_TV;
import static com.ariefzuhri.blu.utils.Constants.ORIENTATION_TYPE_VERTICAL;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_MOVIE_LATEST_RELEASE;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_MOVIE_NOW_PLAYING;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_MOVIE_POPULAR;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_MOVIE_RECOMMENDATIONS;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_MOVIE_TOP_RATED;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_MOVIE_TRENDING;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_MOVIE_UPCOMING;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_TV_LATEST_RELEASE;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_TV_ON_THE_AIR;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_TV_POPULAR;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_TV_RECOMMENDATIONS;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_TV_TOP_RATED;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_TV_TRENDING;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ActivitySearchBinding binding;
    private MediaAdapter adapter;
    private SearchViewModel viewModel;
    private ShimmerHelper shimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.fabBack.setOnClickListener(view -> onBackPressed());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        adapter = new MediaAdapter(ORIENTATION_TYPE_VERTICAL);
        binding.recyclerView.setAdapter(adapter);

        shimmer = new ShimmerHelper(binding.shimmer, binding.recyclerView);
        shimmer.show();

        ViewModelFactory factory = ViewModelFactory.getInstance();
        viewModel = new ViewModelProvider(this, factory).get(SearchViewModel.class);
        viewModel.setPage(1);

        viewModel.getGenres(MEDIA_TYPE_MOVIE).observe(this, genresResponse ->
                adapter.insertGenreList(genresResponse.getGenres()));
        viewModel.getGenres(MEDIA_TYPE_TV).observe(this, genresResponse ->
                adapter.insertGenreList(genresResponse.getGenres()));

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_QUERY)){
            String query = intent.getStringExtra(EXTRA_QUERY);
            binding.searchView.setQuery(query, true);
            performQuery(query);
        } else if (intent.hasExtra(EXTRA_QUERY_TYPE)){
            String header = null;
            int type = intent.getIntExtra(EXTRA_QUERY_TYPE, 0);
            int mediaId = intent.getIntExtra(EXTRA_MEDIA_ID, 0);
            switch (type) {
                case QUERY_TYPE_MOVIE_TRENDING:
                    header = getString(R.string.trending_movies);
                    viewModel.getMovieTrending().observe(this, movieResponse -> {
                        List<MediaEntity> mediaList = movieEntitiesToMediaList(
                                movieResponse.getResults());
                        adapter.setData(mediaList);
                        shimmer.hide();
                    });
                    break;

                case QUERY_TYPE_MOVIE_LATEST_RELEASE:
                    header = getString(R.string.latest_release_movies);
                    viewModel.getMovieLatestRelease().observe(this, movieResponse -> {
                        List<MediaEntity> mediaList = movieEntitiesToMediaList(
                                movieResponse.getResults());
                        adapter.setData(mediaList);
                        shimmer.hide();
                    });
                    break;

                case QUERY_TYPE_MOVIE_NOW_PLAYING:
                    header = getString(R.string.now_playing_movies);
                    viewModel.getMovieNowPlaying().observe(this, movieResponse -> {
                        List<MediaEntity> mediaList = movieEntitiesToMediaList(
                                movieResponse.getResults());
                        adapter.setData(mediaList);
                        shimmer.hide();
                    });
                    break;

                case QUERY_TYPE_MOVIE_UPCOMING:
                    header = getString(R.string.upcoming_movies);
                    viewModel.getMovieUpcoming().observe(this, movieResponse -> {
                        List<MediaEntity> mediaList = movieEntitiesToMediaList(
                                movieResponse.getResults());
                        adapter.setData(mediaList);
                        shimmer.hide();
                    });
                    break;

                case QUERY_TYPE_MOVIE_TOP_RATED:
                    header = getString(R.string.top_rated_movies);
                    viewModel.getMovieTopRated().observe(this, movieResponse -> {
                        List<MediaEntity> mediaList = movieEntitiesToMediaList(
                                movieResponse.getResults());
                        adapter.setData(mediaList);
                        shimmer.hide();
                    });
                    break;

                case QUERY_TYPE_MOVIE_POPULAR:
                    header = getString(R.string.popular_movies);
                    viewModel.getMoviePopular().observe(this, movieResponse -> {
                        List<MediaEntity> mediaList = movieEntitiesToMediaList(
                                movieResponse.getResults());
                        adapter.setData(mediaList);
                        shimmer.hide();
                    });
                    break;

                case QUERY_TYPE_MOVIE_RECOMMENDATIONS:
                    header = getString(R.string.recommendations_movies);
                    viewModel.getMovieRecommendations(mediaId).observe(this, movieResponse -> {
                        List<MediaEntity> mediaList = movieEntitiesToMediaList(
                                movieResponse.getResults());
                        adapter.setData(mediaList);
                        shimmer.hide();
                    });
                    break;

                case QUERY_TYPE_TV_TRENDING:
                    header = getString(R.string.trending_tv_shows);
                    viewModel.getTVTrending().observe(this, tvResponse -> {
                        List<MediaEntity> mediaList = tvEntitiesToMediaList(
                                tvResponse.getResults());
                        adapter.setData(mediaList);
                        shimmer.hide();
                    });
                    break;

                case QUERY_TYPE_TV_LATEST_RELEASE:
                    header = getString(R.string.latest_release_tv_shows);
                    viewModel.getTVLatestRelease().observe(this, tvResponse -> {
                        List<MediaEntity> mediaList = tvEntitiesToMediaList(
                                tvResponse.getResults());
                        adapter.setData(mediaList);
                        shimmer.hide();
                    });
                    break;

                case QUERY_TYPE_TV_ON_THE_AIR:
                    header = getString(R.string.on_the_air_tv_shows);
                    viewModel.getTVOnTheAir().observe(this, tvResponse -> {
                        List<MediaEntity> mediaList = tvEntitiesToMediaList(
                                tvResponse.getResults());
                        adapter.setData(mediaList);
                        shimmer.hide();
                    });
                    break;

                case QUERY_TYPE_TV_TOP_RATED:
                    header = getString(R.string.top_rated_tv_shows);
                    viewModel.getTVTopRated().observe(this, tvResponse -> {
                        List<MediaEntity> mediaList = tvEntitiesToMediaList(
                                tvResponse.getResults());
                        adapter.setData(mediaList);
                        shimmer.hide();
                    });
                    break;

                case QUERY_TYPE_TV_POPULAR:
                    header = getString(R.string.popular_tv_shows);
                    viewModel.getTVPopular().observe(this, tvResponse -> {
                        List<MediaEntity> mediaList = tvEntitiesToMediaList(
                                tvResponse.getResults());
                        adapter.setData(mediaList);
                        shimmer.hide();
                    });
                    break;

                case QUERY_TYPE_TV_RECOMMENDATIONS:
                    header = getString(R.string.recommendations_tv_shows);
                    viewModel.getTVRecommendations(mediaId).observe(this, tvResponse -> {
                        List<MediaEntity> mediaList = tvEntitiesToMediaList(
                                tvResponse.getResults());
                        adapter.setData(mediaList);
                        shimmer.hide();
                    });
                    break;
            }
            binding.tvHeader.setText(header);
        }

        binding.searchView.setOnQueryTextListener(this);

        binding.swipeRefreshLayout.setOnRefreshListener(() -> binding.swipeRefreshLayout.setRefreshing(false));
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        binding.scrollView.scrollTo(0, 0);
        binding.appBar.setExpanded(true);
        performQuery(query);
        binding.searchView.clearFocus();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    private void performQuery(String query){
        binding.tvHeader.setText(R.string.search);
        shimmer.show();
        viewModel.getMultiSearch(query).observe(this, multiSearchResponse -> {
            List<MediaEntity> mediaList = searchResultEntitiesToMediaList(
                    multiSearchResponse.getResults());
            adapter.setData(mediaList);
            shimmer.hide();
        });
    }
}