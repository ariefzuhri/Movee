package com.ariefzuhri.blu.detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.ariefzuhri.blu.R;
import com.ariefzuhri.blu.databinding.ActivityDetailMovieBinding;
import com.ariefzuhri.blu.model.Movie;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import static com.ariefzuhri.blu.utils.AppUtils.loadImage;
import static com.ariefzuhri.blu.utils.AppUtils.showToast;
import static com.ariefzuhri.blu.utils.Constants.EXTRA_MOVIE_ID;
import static com.ariefzuhri.blu.utils.Constants.STATUS_CURRENTLY;
import static com.ariefzuhri.blu.utils.Constants.STATUS_FINISHED;
import static com.ariefzuhri.blu.utils.Constants.STATUS_NOT_YET;
import static com.ariefzuhri.blu.utils.Constants.TYPE_MOVIE;
import static com.ariefzuhri.blu.utils.Constants.TYPE_TV;
import static com.ariefzuhri.blu.utils.DateUtils.getDateWithoutYear;
import static com.ariefzuhri.blu.utils.DateUtils.getYearOfDate;

public class DetailMovieActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityDetailMovieBinding binding;
    private Movie movie;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailMovieBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.fabBack.setOnClickListener(this);
        binding.ibMoreTitle.setOnClickListener(this);
        binding.btnTrailer.setOnClickListener(this);
        binding.tvMoreSynopsis.setOnClickListener(this);

        DetailMovieViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DetailMovieViewModel.class);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            int movieId = bundle.getInt(EXTRA_MOVIE_ID);
            viewModel.setSelectedMovie(movieId);
            populateMovie(viewModel.getMovie());
        }
    }

    private void populateMovie(Movie movie){
        this.movie = movie;

        loadImage(this, movie.getCover(), binding.imgCover);
        loadImage(this, movie.getPoster(), binding.imgPoster);
        binding.tvScore.setText(movie.getScore());
        binding.tvTitle.setText(movie.getTitle());
        binding.tvReleaseYear.setText(getYearOfDate(movie.getAiredDate().getStartDate()));
        binding.tvReleaseDate.setText(" | " + getDateWithoutYear(movie.getAiredDate().getStartDate()));
        binding.tvSynopsis.setText(movie.getSynopsis());

        String type = null;
        if (movie.getType().equals(TYPE_MOVIE)){
            type = "Film";
            binding.tvRuntime.setText(movie.getRuntime() + " m");
        }
        else if (movie.getType().equals(TYPE_TV)) {
            type = "Serial";
            binding.tvRuntime.setText(movie.getRuntime() + " m/eps");
        }

        String status = null;
        switch (movie.getStatus()) {
            case STATUS_FINISHED: status = "Selesai"; break;
            case STATUS_CURRENTLY: status = "Tayang"; break;
            case STATUS_NOT_YET: status = "Segera"; break;
        }

        binding.tvType.setText(type + ", " + movie.getEpisodes() + " eps - " + status);

        String[] genreList = movie.getGenres().split(", ");
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        binding.rvGenre.setLayoutManager(layoutManager);
        binding.rvGenre.setHasFixedSize(true);
        GenreAdapter genreAdapter = new GenreAdapter();
        binding.rvGenre.setAdapter(genreAdapter);
        genreAdapter.setData(genreList);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab_back:
                onBackPressed();
                break;

            case R.id.ib_more_title:
                if (movie != null) showToast(this, movie.getTitle());
                break;

            case R.id.btn_trailer:
                if (movie != null){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://youtu.be/" + movie.getTrailer()));
                    startActivity(intent);
                }
                break;

            case R.id.tv_more_synopsis:
                binding.tvSynopsis.setMaxLines(Integer.MAX_VALUE);
                binding.tvMoreSynopsis.setVisibility(View.GONE);
                break;
        }
    }
}