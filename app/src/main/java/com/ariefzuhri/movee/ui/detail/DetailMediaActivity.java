package com.ariefzuhri.movee.ui.detail;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.ariefzuhri.movee.R;
import com.ariefzuhri.movee.data.source.local.entity.FavoriteEntity;
import com.ariefzuhri.movee.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.data.source.remote.entity.TrailerEntity;
import com.ariefzuhri.movee.databinding.ActivityDetailMediaBinding;
import com.ariefzuhri.movee.databinding.ContentDetailMediaBinding;
import com.ariefzuhri.movee.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.movee.ui.main.home.MediaAdapter;
import com.ariefzuhri.movee.ui.search.SearchActivity;
import com.ariefzuhri.movee.utils.ShimmerHelper;
import com.ariefzuhri.movee.viewmodel.ViewModelFactory;
import com.ariefzuhri.movee.vo.Status;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import static com.ariefzuhri.movee.utils.AppUtils.loadImage;
import static com.ariefzuhri.movee.utils.AppUtils.showToast;
import static com.ariefzuhri.movee.utils.Constants.BASE_URL_YOUTUBE;
import static com.ariefzuhri.movee.utils.Constants.EXTRA_MEDIA_ID;
import static com.ariefzuhri.movee.utils.Constants.EXTRA_MEDIA_TYPE;
import static com.ariefzuhri.movee.utils.Constants.EXTRA_QUERY_TYPE;
import static com.ariefzuhri.movee.utils.Constants.IMAGE_SIZE_HIGH;
import static com.ariefzuhri.movee.utils.Constants.IMAGE_SIZE_NORMAL;
import static com.ariefzuhri.movee.utils.Constants.MEDIA_TYPE_MOVIE;
import static com.ariefzuhri.movee.utils.Constants.MEDIA_TYPE_TV;
import static com.ariefzuhri.movee.utils.Constants.MOVIE_STATUS_IN_PRODUCTION;
import static com.ariefzuhri.movee.utils.Constants.MOVIE_STATUS_PLANNED;
import static com.ariefzuhri.movee.utils.Constants.MOVIE_STATUS_POST_PRODUCTION;
import static com.ariefzuhri.movee.utils.Constants.MOVIE_STATUS_PRE_PRODUCTION;
import static com.ariefzuhri.movee.utils.Constants.MOVIE_STATUS_RELEASED;
import static com.ariefzuhri.movee.utils.Constants.ORIENTATION_TYPE_HORIZONTAL;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_MOVIE_RECOMMENDATIONS;
import static com.ariefzuhri.movee.utils.Constants.QUERY_TYPE_TV_RECOMMENDATIONS;
import static com.ariefzuhri.movee.utils.Constants.TV_STATUS_ENDED;
import static com.ariefzuhri.movee.utils.Constants.TV_STATUS_RETURNING_SERIES;
import static com.ariefzuhri.movee.utils.Constants.VIDEO_SITE_YOUTUBE;
import static com.ariefzuhri.movee.utils.Constants.VIDEO_TYPE_TEASER;
import static com.ariefzuhri.movee.utils.Constants.VIDEO_TYPE_TRAILER;
import static com.ariefzuhri.movee.utils.DateHelper.getDateWithoutYear;
import static com.ariefzuhri.movee.utils.DateHelper.getYearOfDate;

public class DetailMediaActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();

    private ActivityDetailMediaBinding activityBinding;
    private ContentDetailMediaBinding contentBinding;

    private DetailMediaViewModel viewModel;
    private FavoriteEntity favoriteInDb;
    private Intent trailerIntent;
    private MediaEntity media;

    private boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = ActivityDetailMediaBinding.inflate(getLayoutInflater());
        setContentView(activityBinding.getRoot());
        contentBinding = activityBinding.contentDetailMedia;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        setSupportActionBar(activityBinding.toolbar);
        activityBinding.appBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) { // Collapsed
                activityBinding.appBar.setBackground(new ColorDrawable(ContextCompat.getColor(this, R.color.blue)));
                activityBinding.tvToolbarTitle.setVisibility(View.VISIBLE);
                activityBinding.fabBack.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.transparent));
                activityBinding.fabBack.setElevation(0);
                activityBinding.fabFavorite.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.transparent));
                activityBinding.fabFavorite.setElevation(0);
            } else { //Expanded
                activityBinding.appBar.setBackground(new ColorDrawable(ContextCompat.getColor(this, R.color.white)));
                activityBinding.tvToolbarTitle.setVisibility(View.INVISIBLE);
                activityBinding.fabBack.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.white));
                activityBinding.fabBack.setElevation(8);
                activityBinding.fabFavorite.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.white));
                activityBinding.fabFavorite.setElevation(8);
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
        activityBinding.fabFavorite.setOnClickListener(this);
        contentBinding.ibMoreTitle.setOnClickListener(this);
        activityBinding.btnTrailer.setOnClickListener(this);
        contentBinding.tvViewMoreSynopsis.setOnClickListener(this);
        contentBinding.tvViewMoreRecommendation.setOnClickListener(this);

        ShimmerHelper shimmerRecommendation = new ShimmerHelper(this, contentBinding.shimmerRecommendation, contentBinding.rvRecommendation);
        shimmerRecommendation.show();

        ViewModelFactory factory = ViewModelFactory.getInstance(getApplication());
        viewModel = new ViewModelProvider(this, factory).get(DetailMediaViewModel.class);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String mediaType = bundle.getString(EXTRA_MEDIA_TYPE);
            int mediaId = bundle.getInt(EXTRA_MEDIA_ID);

            viewModel.setMedia(mediaType, mediaId);

            viewModel.getMediaDetails().observe(this, result -> {
                if (result.status == Status.SUCCESS) {
                    if (result.data != null) {
                        populateMedia(result.data);
                    }
                }
            });
            viewModel.getCredits().observe(this, result -> {});
            viewModel.getGenres().observe(this, resultGenre -> {
                if (resultGenre != null) {
                    switch (resultGenre.status) {
                        case LOADING: break;
                        case SUCCESS:
                            viewModel.getRecommendations().observe(this, resultMedia -> {
                                if (resultMedia != null){
                                    if (resultMedia.status == Status.SUCCESS) {
                                        if (resultMedia.data != null) {
                                            populateRecommendations(resultGenre.data, resultMedia.data);
                                            shimmerRecommendation.hide();
                                        }
                                    }
                                }
                            });
                            break;
                        case ERROR: break;
                    }
                }
            });
        }
    }

    private void populateMedia(MediaEntity media){
        this.media = media;

        viewModel.getFavoriteWithGenres().observe(this, result -> {
            isFavorite = result != null;
            setFavoriteState(isFavorite);
            if (isFavorite) {
                if (result != null) {
                    favoriteInDb = result.favorite;
                    favoriteInDb.setGenres(result.genres);
                }
            }
        });

        activityBinding.tvToolbarTitle.setText(media.getTitle());
        loadImage(this, IMAGE_SIZE_HIGH, media.getCover() == null ? media.getPoster() : media.getCover(), activityBinding.imgCover);
        loadImage(this, IMAGE_SIZE_NORMAL, media.getPoster(), contentBinding.imgPoster);
        contentBinding.tvScoreAverage.setText(String.valueOf(media.getScoreAverage()));
        contentBinding.tvScoreCount.setText(String.valueOf(media.getScoreCount()));
        contentBinding.tvTitle.setText(media.getTitle());
        contentBinding.tvReleaseYear.setText(getYearOfDate(media.getAiredDate().getStartDate()));
        contentBinding.tvReleaseDate.setText(getResources().getString(R.string.formatted_release_date, getDateWithoutYear(media.getAiredDate().getStartDate())));
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
                media.getEpisodes(),
                getResources().getQuantityString(R.plurals.number_of_episodes, media.getEpisodes()),
                status));

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

        viewModel.getTrailers().observe(this, result -> {
            if (result.status == Status.SUCCESS) {
                if (result.data != null) {
                    media.setTrailer(result.data);
                    initTrailerIntent();
                }
            }
        });
    }

    private void initTrailerIntent() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String backupKey = "";
        if (!media.getTrailer().isEmpty()){
            for (TrailerEntity trailer : media.getTrailer()){
                if (trailer.getSite().equals(VIDEO_SITE_YOUTUBE)){
                    if (trailer.getType().equals(VIDEO_TYPE_TRAILER) || trailer.getType().equals(VIDEO_TYPE_TEASER)){
                        intent.setData(Uri.parse(BASE_URL_YOUTUBE + trailer.getKey()));
                        break;
                    } else if (backupKey.isEmpty()){
                        backupKey = trailer.getKey();
                    }
                }
            }
        }
        if (intent.getData() == null){
            if (!backupKey.isEmpty()) {
                intent.setData(Uri.parse(BASE_URL_YOUTUBE + backupKey));
            } else {
                intent.setData(Uri.parse(getResources().getString(
                        R.string.youtube_search_query, media.getTitle(),
                        getYearOfDate(media.getAiredDate().getStartDate()))));
            }
        }
        trailerIntent = intent;
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
        } else if (id == R.id.fab_favorite) {
            if (media != null) setFavorite(media, isFavorite);
            else showToast(this, getString(R.string.toast_data_loading));
        } else if (id == R.id.ib_more_title) {
            if (media != null) showToast(this, media.getTitle());
            else showToast(this, getString(R.string.toast_data_loading));
        } else if (id == R.id.btn_trailer) {
            if (trailerIntent != null) startActivity(trailerIntent);
            else showToast(this, getString(R.string.toast_data_loading));
        } else if (id == R.id.tv_view_more_synopsis) {
            contentBinding.tvSynopsis.setMaxLines(Integer.MAX_VALUE);
            contentBinding.tvViewMoreSynopsis.setVisibility(View.GONE);
        } else if (id == R.id.tv_view_more_recommendation) {
            viewMoreRecommendations(media);
        }
    }

    private void viewMoreRecommendations(MediaEntity media){
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

    private void setFavorite(MediaEntity media, boolean isFavorite) {
        FavoriteEntity favorite = createFavoriteObject(media);

        boolean addFavorite = !isFavorite;
        if (addFavorite) viewModel.insertFavorite(favorite);
        else viewModel.deleteFavorite(favorite);
        setFavoriteState(isFavorite);
    }

    private void setFavoriteState(boolean state){
        if (state) {
            activityBinding.fabFavorite.setImageDrawable(ContextCompat
                    .getDrawable(this, R.drawable.ic_bookmark));
        } else {
            activityBinding.fabFavorite.setImageDrawable(ContextCompat
                    .getDrawable(this, R.drawable.ic_bookmark_outline));
        }
    }

    private FavoriteEntity createFavoriteObject(MediaEntity media){
        return new FavoriteEntity(media.getId(),
                media.getType(),
                media.getTitle(),
                media.getPoster(),
                media.getScoreAverage(),
                media.getAiredDate().getStartDate(),
                media.getGenres());
    }

    private boolean equalsFavoriteObjects(FavoriteEntity o1, FavoriteEntity o2){
        return o1.getId().equals(o2.getId()) &&
                o1.getType().equals(o2.getType()) &&
                o1.getTitle().equals(o2.getTitle()) &&
                o1.getPoster().equals(o2.getPoster()) &&
                o1.getScoreAverage() == o2.getScoreAverage() &&
                o1.getStartDate().equals(o2.getStartDate()) &&
                o1.getGenres().size() == o2.getGenres().size();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Update favorit di database jika ada satu nilai atribut yang tidak sama
        if (isFavorite && favoriteInDb != null){
            FavoriteEntity updatedFavorite = createFavoriteObject(media);
            boolean equalsObjects = equalsFavoriteObjects(favoriteInDb, updatedFavorite);
            Log.d(TAG, "equalsFavoriteObjects: " + equalsObjects);
            if (!equalsObjects) viewModel.updateFavorite(updatedFavorite);
            else Log.d(TAG, "favoriteInDb not need updated");
        }
    }
}