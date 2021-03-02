package com.ariefzuhri.blu.movie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ariefzuhri.blu.databinding.FragmentMovieBinding;
import com.ariefzuhri.blu.model.Movie;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.ariefzuhri.blu.utils.Constants.EXTRA_MOVIE_TYPE;

public class MovieFragment extends Fragment {

    private FragmentMovieBinding binding;

    public MovieFragment() {}

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMovieBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MovieViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);

        Bundle bundle = getArguments();
        if (bundle != null){
            String movieType = bundle.getString(EXTRA_MOVIE_TYPE);
            if (movieType != null){
                viewModel.setSelectedMovies(movieType);
                populateRecyclerView(viewModel.getMovies());
            }
        }
    }

    private void populateRecyclerView(ArrayList<Movie> movieList){
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setHasFixedSize(true);
        MovieAdapter adapter = new MovieAdapter();
        binding.recyclerView.setAdapter(adapter);
        adapter.setData(movieList);
    }
}