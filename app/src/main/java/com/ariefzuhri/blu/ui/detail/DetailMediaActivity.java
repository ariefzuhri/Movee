package com.ariefzuhri.blu.ui.detail;

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

public class DetailMediaActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityDetailMovieBinding binding;
    private Movie movie;

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

        DetailMediaViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DetailMediaViewModel.class);

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
        binding.tvReleaseDate.setText(getResources().getString(R.string.release_date, getDateWithoutYear(movie.getAiredDate().getStartDate())));
        binding.tvSynopsis.setText(movie.getSynopsis());

        String type = null;
        if (movie.getType().equals(TYPE_MOVIE)){
            type = getResources().getString(R.string.movie);
            binding.tvRuntime.setText(getResources().getString(R.string.runtime_movie, movie.getRuntime()));
        }
        else if (movie.getType().equals(TYPE_TV)) {
            type = getResources().getString(R.string.series);
            binding.tvRuntime.setText(getResources().getString(R.string.runtime_tv, movie.getRuntime()));
        }

        String status = null;
        switch (movie.getStatus()) {
            case STATUS_FINISHED: status = getResources().getString(R.string.finished); break;
            case STATUS_CURRENTLY: status = getResources().getString(R.string.currently); break;
            case STATUS_NOT_YET: status = getResources().getString(R.string.not_yet); break;
        }

        binding.tvType.setText(getResources().getString(R.string.type, type, movie.getEpisodes(), status));

        String[] genreList = movie.getGenres().split(", ");
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        binding.rvGenre.setLayoutManager(layoutManager);
        binding.rvGenre.setHasFixedSize(true);
        GenreAdapter genreAdapter = new GenreAdapter(R.dimen.font_nm);
        binding.rvGenre.setAdapter(genreAdapter);
        genreAdapter.setData(genreList);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.fab_back) {
            onBackPressed();
        } else if (id == R.id.ib_more_title) {
            if (movie != null) showToast(this, movie.getTitle());
        } else if (id == R.id.btn_trailer) {
            if (movie != null) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://youtu.be/" + movie.getTrailer()));
                startActivity(intent);
            }
        } else if (id == R.id.tv_more_synopsis) {
            binding.tvSynopsis.setMaxLines(Integer.MAX_VALUE);
            binding.tvMoreSynopsis.setVisibility(View.GONE);
        }
    }
}