package com.ariefzuhri.blu.detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.ariefzuhri.blu.R;
import com.ariefzuhri.blu.databinding.ActivityDetailBinding;
import com.ariefzuhri.blu.model.Movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.ariefzuhri.blu.utils.AppUtils.loadImage;
import static com.ariefzuhri.blu.utils.Constants.EXTRA_MOVIE;
import static com.ariefzuhri.blu.utils.Constants.STATUS_CURRENTLY;
import static com.ariefzuhri.blu.utils.Constants.STATUS_FINISHED;
import static com.ariefzuhri.blu.utils.Constants.STATUS_NOT_YET;
import static com.ariefzuhri.blu.utils.Constants.TYPE_MOVIE;
import static com.ariefzuhri.blu.utils.Constants.TYPE_TV;
import static com.ariefzuhri.blu.utils.DateUtils.getDateWithoutYear;
import static com.ariefzuhri.blu.utils.DateUtils.getYearOfDate;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private Movie movie;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.ariefzuhri.blu.databinding.ActivityDetailBinding binding = ActivityDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);

        binding.rvGenre.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rvGenre.setHasFixedSize(true);
        GenreAdapter genreAdapter = new GenreAdapter();
        binding.rvGenre.setAdapter(genreAdapter);

        binding.btnTrailer.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_MOVIE)){
            movie = intent.getParcelableExtra(EXTRA_MOVIE);

            binding.toolbarLayout.setTitle(movie.getTitle());
            loadImage(this, movie.getCover(), binding.imgCover);
            loadImage(this, movie.getPoster(), binding.imgPoster);
            binding.tvScore.setText(movie.getScore());
            binding.tvRuntime.setText(movie.getRuntime() + " menit/episode");
            binding.tvTitle.setText(movie.getTitle());
            binding.tvReleaseYear.setText(getYearOfDate(movie.getAiredDate().getStartDate()));
            binding.tvReleaseDate.setText(" | " + getDateWithoutYear(movie.getAiredDate().getStartDate()));
            binding.tvSynopsis.setText(movie.getSynopsis());

            String type = null;
            if (movie.getType().equals(TYPE_MOVIE)) type = "Film";
            else if (movie.getType().equals(TYPE_TV)) type = "Serial";

            String status = null;
            switch (movie.getStatus()) {
                case STATUS_FINISHED: status = "Selesai tayang"; break;
                case STATUS_CURRENTLY: status = "Sedang tayang"; break;
                case STATUS_NOT_YET: status = "Belum tayang"; break;
            }

            binding.tvType.setText(type + ", " + movie.getEpisodes() + " episode - " + status);

            String[] genreList = movie.getGenres().split(", ");
            genreAdapter.setData(genreList);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_trailer){
            if (movie != null){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://youtu.be/" + movie.getTrailer()));
                startActivity(intent);
            }
        }
    }
}