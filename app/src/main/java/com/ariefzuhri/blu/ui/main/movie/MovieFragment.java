package com.ariefzuhri.blu.ui.main.movie;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ariefzuhri.blu.data.source.remote.entity.GenreEntity;
import com.ariefzuhri.blu.databinding.FragmentMovieBinding;
import com.ariefzuhri.blu.databinding.LayoutMediaBinding;
import com.ariefzuhri.blu.data.MediaEntity;
import com.ariefzuhri.blu.ui.main.home.MediaAdapter;
import com.ariefzuhri.blu.ui.search.SearchActivity;
import com.ariefzuhri.blu.utils.ShimmerHelper;
import com.ariefzuhri.blu.viewmodel.ViewModelFactory;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.ariefzuhri.blu.ui.main.home.MediaHelper.movieEntitiesToMediaList;
import static com.ariefzuhri.blu.utils.Constants.EXTRA_QUERY_TYPE;
import static com.ariefzuhri.blu.utils.Constants.ORIENTATION_TYPE_HORIZONTAL;
import static com.ariefzuhri.blu.utils.Constants.ORIENTATION_TYPE_VERTICAL;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_MOVIE_NOW_PLAYING;

public class MovieFragment extends Fragment {

    private LayoutMediaBinding binding;
    private MovieViewModel viewModel;

    public MovieFragment() {}

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentMovieBinding fragmentMovieBinding = FragmentMovieBinding.inflate(inflater, container, false);
        binding = fragmentMovieBinding.layoutMedia;
        return fragmentMovieBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rvHoriz.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvHoriz.setHasFixedSize(true);
        MediaAdapter adapterHoriz = new MediaAdapter(ORIENTATION_TYPE_HORIZONTAL);
        binding.rvHoriz.setAdapter(adapterHoriz);

        binding.rvVert.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvVert.setHasFixedSize(true);
        MediaAdapter adapterVert = new MediaAdapter(ORIENTATION_TYPE_VERTICAL);
        binding.rvVert.setAdapter(adapterVert);

        ShimmerHelper shimmerHoriz = new ShimmerHelper(binding.shimmerHoriz, binding.rvHoriz);
        ShimmerHelper shimmerVert = new ShimmerHelper(binding.shimmerVert, binding.rvVert);
        shimmerHoriz.show();
        shimmerVert.show();

        ViewModelFactory factory = ViewModelFactory.getInstance();
        viewModel = new ViewModelProvider(this, factory).get(MovieViewModel.class);
        viewModel.setPage(1);
        viewModel.getGenres().observe(getViewLifecycleOwner(), genresResponse -> {
            List<GenreEntity> genreList = genresResponse.getGenres();
            adapterHoriz.setGenreList(genreList);
            adapterVert.setGenreList(genreList);

            viewModel.getNowPlaying().observe(getViewLifecycleOwner(), movieResponse -> {
                List<MediaEntity> mediaList = movieEntitiesToMediaList(movieResponse.getResults());
                adapterHoriz.setData(mediaList);
                shimmerHoriz.hide();
            });

            viewModel.getTrending().observe(getViewLifecycleOwner(), movieResponse -> {
                List<MediaEntity> mediaList = movieEntitiesToMediaList(movieResponse.getResults());
                adapterVert.setData(mediaList);
                shimmerVert.hide();
            });
        });

        binding.tvViewMore.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            intent.putExtra(EXTRA_QUERY_TYPE, QUERY_TYPE_MOVIE_NOW_PLAYING);
            startActivity(intent);
        });
    }
}