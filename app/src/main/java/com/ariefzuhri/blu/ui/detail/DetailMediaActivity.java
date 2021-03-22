package com.ariefzuhri.blu.ui.detail;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.ariefzuhri.blu.R;
import com.ariefzuhri.blu.data.GenreEntity;
import com.ariefzuhri.blu.data.TrailerEntity;
import com.ariefzuhri.blu.databinding.ActivityDetailMediaBinding;
import com.ariefzuhri.blu.databinding.ContentDetailMediaBinding;
import com.ariefzuhri.blu.data.MediaEntity;
import com.ariefzuhri.blu.ui.main.home.MediaAdapter;
import com.ariefzuhri.blu.ui.search.SearchActivity;
import com.ariefzuhri.blu.utils.ShimmerHelper;
import com.ariefzuhri.blu.viewmodel.ViewModelFactory;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import static com.ariefzuhri.blu.utils.AppUtils.loadImage;
import static com.ariefzuhri.blu.utils.AppUtils.showToast;
import static com.ariefzuhri.blu.utils.Constants.BASE_URL_YOUTUBE;
import static com.ariefzuhri.blu.utils.Constants.EXTRA_MEDIA_ID;
import static com.ariefzuhri.blu.utils.Constants.EXTRA_MEDIA_TYPE;
import static com.ariefzuhri.blu.utils.Constants.EXTRA_QUERY_TYPE;
import static com.ariefzuhri.blu.utils.Constants.IMAGE_SIZE_HIGH;
import static com.ariefzuhri.blu.utils.Constants.IMAGE_SIZE_NORMAL;
import static com.ariefzuhri.blu.utils.Constants.MEDIA_TYPE_MOVIE;
import static com.ariefzuhri.blu.utils.Constants.MEDIA_TYPE_TV;
import static com.ariefzuhri.blu.utils.Constants.MOVIE_STATUS_IN_PRODUCTION;
import static com.ariefzuhri.blu.utils.Constants.MOVIE_STATUS_PLANNED;
import static com.ariefzuhri.blu.utils.Constants.MOVIE_STATUS_POST_PRODUCTION;
import static com.ariefzuhri.blu.utils.Constants.MOVIE_STATUS_PRE_PRODUCTION;
import static com.ariefzuhri.blu.utils.Constants.MOVIE_STATUS_RELEASED;
import static com.ariefzuhri.blu.utils.Constants.ORIENTATION_TYPE_HORIZONTAL;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_MOVIE_RECOMMENDATIONS;
import static com.ariefzuhri.blu.utils.Constants.QUERY_TYPE_TV_RECOMMENDATIONS;
import static com.ariefzuhri.blu.utils.Constants.TV_STATUS_ENDED;
import static com.ariefzuhri.blu.utils.Constants.TV_STATUS_RETURNING_SERIES;
import static com.ariefzuhri.blu.utils.Constants.VIDEO_SITE_YOUTUBE;
import static com.ariefzuhri.blu.utils.Constants.VIDEO_TYPE_TEASER;
import static com.ariefzuhri.blu.utils.Constants.VIDEO_TYPE_TRAILER;
import static com.ariefzuhri.blu.utils.DateUtils.getDateWithoutYear;
import static com.ariefzuhri.blu.utils.DateUtils.getYearOfDate;

public class DetailMediaActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityDetailMediaBinding activityBinding;
    private ContentDetailMediaBinding contentBinding;

    private DetailMediaViewModel viewModel;
    private MediaEntity media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = ActivityDetailMediaBinding.inflate(getLayoutInflater());
        setContentView(activityBinding.getRoot());
        contentBinding = activityBinding.contentDetailMedia;

        setSupportActionBar(activityBinding.toolbar);
        activityBinding.appBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) { // Collapsed
                activityBinding.appBar.setBackground(new ColorDrawable(getResources().getColor(R.color.blue)));
                activityBinding.tvToolbarTitle.setVisibility(View.VISIBLE);
                activityBinding.fabBack.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
                activityBinding.fabBack.setElevation(0);
            } else { //Expanded
                activityBinding.appBar.setBackground(new ColorDrawable(getResources().getColor(R.color.white)));
                activityBinding.tvToolbarTitle.setVisibility(View.INVISIBLE);
                activityBinding.fabBack.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                activityBinding.fabBack.setElevation(8);
            }
        });

        contentBinding.scrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener)
                (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                    if (scrollY == 0){
                        contentBinding.viewShadow.setVisibility(View.INVISIBLE);
                    } else {
                        contentBinding.viewShadow.setVisibility(View.VISIBLE);
                    }
                });

        activityBinding.fabBack.setOnClickListener(this);
        contentBinding.ibMoreTitle.setOnClickListener(this);
        activityBinding.btnTrailer.setOnClickListener(this);
        contentBinding.tvViewMoreSynopsis.setOnClickListener(this);
        contentBinding.tvViewMoreRecommendation.setOnClickListener(this);

        ShimmerHelper shimmerRecommendation = new ShimmerHelper(contentBinding.shimmerRecommendation, contentBinding.rvRecommendation);
        shimmerRecommendation.show();

        ViewModelFactory factory = ViewModelFactory.getInstance();
        viewModel = new ViewModelProvider(this, factory).get(DetailMediaViewModel.class);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String mediaType = bundle.getString(EXTRA_MEDIA_TYPE);
            int mediaId = bundle.getInt(EXTRA_MEDIA_ID);
            viewModel.setSelectedMedia(mediaType, mediaId);

            if (mediaType.equals(MEDIA_TYPE_MOVIE)){
                viewModel.getMovieDetails().observe(this, this::populateMedia);
            } else if (mediaType.equals(MEDIA_TYPE_TV)){
                viewModel.getTVDetails().observe(this, this::populateMedia);
            }

            viewModel.getCredits().observe(this, creditsResponse -> {

            });

            viewModel.getGenres().observe(this, resultGenre -> {
                if (mediaType.equals(MEDIA_TYPE_MOVIE)){
                    viewModel.getMovieRecommendations().observe(this, resultMovie -> {
                        populateRecommendations(resultGenre, resultMovie);
                        shimmerRecommendation.hide();
                    });
                } else if (mediaType.equals(MEDIA_TYPE_TV)){
                    viewModel.getTVRecommendations().observe(this, resultTV -> {
                        populateRecommendations(resultGenre, resultTV);
                        shimmerRecommendation.hide();
                    });
                }
            });
        }
    }

    private void populateMedia(MediaEntity media){
        this.media = media;

        activityBinding.tvToolbarTitle.setText(media.getTitle());
        loadImage(this, IMAGE_SIZE_HIGH, media.getCover() == null ? media.getPoster() : media.getCover(), activityBinding.imgCover);
        loadImage(this, IMAGE_SIZE_NORMAL, media.getPoster(), contentBinding.imgPoster);
        contentBinding.tvScoreAverage.setText(String.valueOf(media.getScoreAverage()));
        contentBinding.tvScoreCount.setText(String.valueOf(media.getScoreCount()));
        contentBinding.tvTitle.setText(media.getTitle());
        contentBinding.tvReleaseYear.setText(getYearOfDate(media.getAiredDate().getStartDate()));
        contentBinding.tvReleaseDate.setText(getResources().getString(R.string.release_date, getDateWithoutYear(media.getAiredDate().getStartDate())));
        contentBinding.tvSynopsis.setText(media.getSynopsis());

        if (!media.getStudios().isEmpty()){
            contentBinding.tvStudio.setText(media.getStudios().get(0).getName());
        } else {
            contentBinding.tvStudio.setVisibility(View.GONE);
        }

        String type = null;
        if (media.getType().equals(MEDIA_TYPE_MOVIE)){
            type = getResources().getString(R.string.movie);
            contentBinding.tvRuntime.setText(getResources().getString(R.string.runtime_movie, media.getRuntime()));
        }
        else if (media.getType().equals(MEDIA_TYPE_TV)) {
            type = getResources().getString(R.string.series);
            contentBinding.tvRuntime.setText(getResources().getString(R.string.runtime_tv, media.getRuntime()));
        }

        String status;
        switch (media.getStatus()) {
            case MOVIE_STATUS_PLANNED: status = MOVIE_STATUS_PLANNED; break;
            case MOVIE_STATUS_PRE_PRODUCTION: status = MOVIE_STATUS_PRE_PRODUCTION; break;
            case MOVIE_STATUS_IN_PRODUCTION: status = MOVIE_STATUS_IN_PRODUCTION; break;
            case MOVIE_STATUS_POST_PRODUCTION: status = MOVIE_STATUS_POST_PRODUCTION; break;
            case MOVIE_STATUS_RELEASED: status = MOVIE_STATUS_RELEASED; break;
            case TV_STATUS_RETURNING_SERIES: status = TV_STATUS_RETURNING_SERIES; break;
            case TV_STATUS_ENDED: status = TV_STATUS_ENDED; break;
            default: status = media.getStatus();
        }
        contentBinding.tvType.setText(getResources().getString(R.string.type, type,
                media.getEpisodes(), status));

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        contentBinding.rvGenre.setLayoutManager(layoutManager);
        contentBinding.rvGenre.setHasFixedSize(true);
        GenreAdapter genreAdapter = new GenreAdapter();
        contentBinding.rvGenre.setAdapter(genreAdapter);

        List<String> genreStringList = new ArrayList<>();
        for (GenreEntity genre : media.getGenres()){
            genreStringList.add(genre.getName());
        }
        genreAdapter.setData(genreStringList);

        viewModel.getVideos().observe(this, media::setTrailer);
    }

    private void populateRecommendations(List<GenreEntity> genreList, List<MediaEntity> mediaList) {
        if (mediaList.isEmpty()){
            contentBinding.layoutRecommendation.setVisibility(View.GONE);
        } else {
            contentBinding.rvRecommendation.setLayoutManager(new LinearLayoutManager(this,
                    LinearLayoutManager.HORIZONTAL, false));
            contentBinding.rvRecommendation.setHasFixedSize(true);
            MediaAdapter adapter = new MediaAdapter(ORIENTATION_TYPE_HORIZONTAL);
            contentBinding.rvRecommendation.setAdapter(adapter);
            adapter.setGenreList(genreList);
            adapter.setData(mediaList);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.fab_back) {
            onBackPressed();
        } else if (id == R.id.ib_more_title) {
            if (media != null) showToast(this, media.getTitle());
        } else if (id == R.id.btn_trailer) {
            if (media != null && media.getTrailer() != null) {
                if (!media.getTrailer().isEmpty()){
                    for (TrailerEntity trailer : media.getTrailer()){
                        if ((trailer.getType().equals(VIDEO_TYPE_TRAILER) || trailer.getType().equals(VIDEO_TYPE_TEASER)) &&
                                trailer.getSite().equals(VIDEO_SITE_YOUTUBE)){
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(BASE_URL_YOUTUBE + trailer.getKey()));
                            startActivity(intent);
                            break;
                        }
                    }
                } else {
                    showToast(this, getString(R.string.hint_empty_trailer));
                }
            }
        } else if (id == R.id.tv_view_more_synopsis) {
            contentBinding.tvSynopsis.setMaxLines(Integer.MAX_VALUE);
            contentBinding.tvViewMoreSynopsis.setVisibility(View.GONE);
        } else if (id == R.id.tv_view_more_recommendation) {
            if (media != null) {
                Intent intent = new Intent(this, SearchActivity.class);
                intent.putExtra(EXTRA_MEDIA_ID, media.getId());
                if (media.getType().equals(MEDIA_TYPE_MOVIE)){
                    intent.putExtra(EXTRA_QUERY_TYPE, QUERY_TYPE_MOVIE_RECOMMENDATIONS);
                } else if (media.getType().equals(MEDIA_TYPE_TV)){
                    intent.putExtra(EXTRA_QUERY_TYPE, QUERY_TYPE_TV_RECOMMENDATIONS);
                }
                startActivity(intent);
            }
        }
    }
}