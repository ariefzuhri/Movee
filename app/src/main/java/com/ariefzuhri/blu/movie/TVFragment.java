package com.ariefzuhri.blu.movie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ariefzuhri.blu.databinding.FragmentTvBinding;

import org.jetbrains.annotations.NotNull;

import static com.ariefzuhri.blu.utils.Constants.TYPE_TV;

public class TVFragment extends Fragment {

    private FragmentTvBinding binding;

    public TVFragment() {}

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTvBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rvMovie1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvMovie1.setHasFixedSize(true);
        Movie1Adapter movie1Adapter = new Movie1Adapter();
        binding.rvMovie1.setAdapter(movie1Adapter);

        binding.rvMovie2.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.rvMovie2.setHasFixedSize(true);
        Movie2Adapter movie2Adapter = new Movie2Adapter();
        binding.rvMovie2.setAdapter(movie2Adapter);

        MovieViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);
        movie1Adapter.setData(viewModel.getMovies(TYPE_TV));
        movie2Adapter.setData(viewModel.getMovies(TYPE_TV));
    }
}