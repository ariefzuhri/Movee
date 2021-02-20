package com.ariefzuhri.blu.movie;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ariefzuhri.blu.databinding.ItemMovie2Binding;
import com.ariefzuhri.blu.detail.DetailActivity;
import com.ariefzuhri.blu.model.Movie;

import java.util.ArrayList;

import static com.ariefzuhri.blu.utils.AppUtils.loadImage;
import static com.ariefzuhri.blu.utils.Constants.EXTRA_MOVIE;
import static com.ariefzuhri.blu.utils.DateUtils.getYearOfDate;

public class Movie2Adapter extends RecyclerView.Adapter<Movie2Adapter.ViewHolder> {
    private final ArrayList<Movie> movieList = new ArrayList<>();

    public Movie2Adapter(){}

    public void setData(ArrayList<Movie> movieList){
        this.movieList.clear();
        this.movieList.addAll(movieList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovie2Binding binding = ItemMovie2Binding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemMovie2Binding binding;

        public ViewHolder(@NonNull ItemMovie2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Movie movie) {
            loadImage(itemView.getContext(), movie.getPoster(), binding.imgPoster);
            binding.tvScore.setText(movie.getScore());
            binding.tvReleaseYear.setText(getYearOfDate(movie.getAiredDate().getStartDate()));

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra(EXTRA_MOVIE, movie);
                view.getContext().startActivity(intent);
            });
        }
    }
}
