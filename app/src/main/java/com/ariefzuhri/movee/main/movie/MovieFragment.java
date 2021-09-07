package com.ariefzuhri.movee.main.movie;

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

import com.ariefzuhri.movee.databinding.FragmentMovieBinding;
import com.ariefzuhri.movee.databinding.LayoutMediaBinding;
import com.ariefzuhri.movee.core.ui.adapter.MediaAdapter;
import com.ariefzuhri.movee.search.SearchActivity;
import com.ariefzuhri.movee.core.utils.ShimmerHelper;
import com.ariefzuhri.movee.core.ui.viewmodel.ViewModelFactory;
import com.ariefzuhri.movee.core.data.repository.Status;

import org.jetbrains.annotations.NotNull;

import static com.ariefzuhri.movee.core.utils.Constants.EXTRA_QUERY_TYPE;
import static com.ariefzuhri.movee.core.utils.Constants.ORIENTATION_TYPE_HORIZONTAL;
import static com.ariefzuhri.movee.core.utils.Constants.ORIENTATION_TYPE_VERTICAL;
import static com.ariefzuhri.movee.core.utils.Constants.QUERY_TYPE_MOVIE_NOW_PLAYING;

public class MovieFragment extends Fragment {

    private LayoutMediaBinding binding;
    private MovieViewModel viewModel;

    public MovieFragment() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentMovieBinding fragmentMovieBinding = FragmentMovieBinding.inflate(inflater, container, false);
        binding = fragmentMovieBinding.lytMedia;
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

        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity().getApplication());
            viewModel = new ViewModelProvider(this, factory).get(MovieViewModel.class);
            int trendingPage = 1;
            viewModel.setTrendingPage(trendingPage);
            viewModel.getGenres().observe(getViewLifecycleOwner(), result -> {
                if (result != null) {
                    if (result.status == Status.SUCCESS) {
                        adapterHoriz.submitGenreList(result.data);
                        adapterVert.submitGenreList(result.data);
                    }
                }

                viewModel.getNowPlaying().observe(getViewLifecycleOwner(), resultMovie -> {
                    if (resultMovie != null) {
                        if (resultMovie.status == Status.SUCCESS) {
                            if (resultMovie.data != null) {
                                adapterHoriz.submitList(resultMovie.data);
                                shimmerHoriz.hide(resultMovie.data.isEmpty());
                            }
                        }
                    }
                });

                viewModel.getTrending().observe(getViewLifecycleOwner(), resultMovie -> {
                    if (resultMovie != null) {
                        if (resultMovie.status == Status.SUCCESS) {
                            if (resultMovie.data != null) {
                                adapterVert.submitList(resultMovie.data);
                                shimmerVert.hide(resultMovie.data.isEmpty());
                            }
                        }
                    }
                });
            });
        }

        binding.tvViewMoreHoriz.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            intent.putExtra(EXTRA_QUERY_TYPE, QUERY_TYPE_MOVIE_NOW_PLAYING);
            startActivity(intent);
        });
    }

    /*trendingPage++;
    viewModel.setTrendingPage(trendingPage);
    Log.d(getClass().getSimpleName(), "Current page: " + trendingPage);*/
}