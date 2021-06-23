package com.ariefzuhri.movee.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.ariefzuhri.movee.data.source.local.entity.FavoriteEntity;
import com.ariefzuhri.movee.data.source.local.entity.FavoriteWithGenres;
import com.ariefzuhri.movee.data.source.remote.entity.CreditsEntity;
import com.ariefzuhri.movee.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.movee.data.source.remote.entity.TrailerEntity;
import com.ariefzuhri.movee.data.CatalogRepository;
import com.ariefzuhri.movee.utils.DataDummy;
import com.ariefzuhri.movee.utils.LiveDataTestUtil;
import com.ariefzuhri.movee.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.ariefzuhri.movee.utils.Constants.MEDIA_TYPE_MOVIE;
import static com.ariefzuhri.movee.utils.Constants.MEDIA_TYPE_TV;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailMediaViewModelTest {

    private DetailMediaViewModel viewModel;
    private final MediaEntity dummyMovieDetails = DataDummy.generateDummyMovieDetails();
    private final int movieId = dummyMovieDetails.getId();
    private final MediaEntity dummyTVDetails = DataDummy.generateDummyTVDetails();
    private final int tvId = dummyTVDetails.getId();
    private final int page = 1;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private CatalogRepository catalogRepository;

    @Mock
    private Observer<Resource<MediaEntity>> mediaEntityObserver;

    @Mock
    private Observer<Resource<List<MediaEntity>>> mediaEntitiesObserver;

    @Mock
    private Observer<Resource<List<TrailerEntity>>> trailerEntitiesObserver;

    @Mock
    private Observer<Resource<CreditsEntity>> creditsEntityObserver;

    @Mock
    private Observer<Resource<List<GenreEntity>>> genreEntitiesObserver;

    @Mock
    private Observer<FavoriteWithGenres> favoriteEntitiesObserver;

    @Before
    public void setUp(){
        viewModel = new DetailMediaViewModel(catalogRepository);
    }

    @Test
    public void getMovieDetails() {
        viewModel.setMedia(MEDIA_TYPE_MOVIE, movieId);

        MutableLiveData<Resource<MediaEntity>> movieDetails = new MutableLiveData<>();
        movieDetails.setValue(Resource.success(dummyMovieDetails));

        when(catalogRepository.getMovieDetails(movieId)).thenReturn(movieDetails);
        Resource<MediaEntity> movieEntity = LiveDataTestUtil.getValue(viewModel.getMediaDetails());
        verify(catalogRepository).getMovieDetails(movieId);

        assertNotNull(movieEntity.data);
        assertEquals(dummyMovieDetails.getId(), movieEntity.data.getId());
        assertEquals(dummyMovieDetails.getTitle(), movieEntity.data.getTitle());
        assertEquals(dummyMovieDetails.getPoster(), movieEntity.data.getPoster());
        assertEquals(dummyMovieDetails.getCover(), movieEntity.data.getCover());
        assertEquals(dummyMovieDetails.getScoreAverage(), movieEntity.data.getScoreAverage(), 0);
        assertEquals(dummyMovieDetails.getScoreCount(), movieEntity.data.getScoreCount());
        assertEquals(dummyMovieDetails.getPopularity(), movieEntity.data.getPopularity(), 0);
        assertEquals(dummyMovieDetails.getType(), movieEntity.data.getType());
        assertEquals(dummyMovieDetails.getEpisodes(), movieEntity.data.getEpisodes());
        assertEquals(dummyMovieDetails.getStatus(), movieEntity.data.getStatus());
        assertEquals(dummyMovieDetails.getAiredDate(), movieEntity.data.getAiredDate());
        assertEquals(dummyMovieDetails.getStudios(), movieEntity.data.getStudios());
        assertEquals(dummyMovieDetails.getGenres(), movieEntity.data.getGenres());
        assertEquals(dummyMovieDetails.getRuntime(), movieEntity.data.getRuntime());
        assertEquals(dummyMovieDetails.getSynopsis(), movieEntity.data.getSynopsis());
        assertEquals(dummyMovieDetails.getTrailers(), movieEntity.data.getTrailers());

        viewModel.getMediaDetails().observeForever(mediaEntityObserver);
        verify(mediaEntityObserver).onChanged(Resource.success(dummyMovieDetails));
    }

    @Test
    public void getTVDetails() {
        viewModel.setMedia(MEDIA_TYPE_TV, tvId);

        MutableLiveData<Resource<MediaEntity>> tvDetails = new MutableLiveData<>();
        tvDetails.setValue(Resource.success(dummyTVDetails));

        when(catalogRepository.getTVDetails(tvId)).thenReturn(tvDetails);
        Resource<MediaEntity> tvEntity = LiveDataTestUtil.getValue(viewModel.getMediaDetails());
        verify(catalogRepository).getTVDetails(tvId);

        assertNotNull(tvEntity.data);
        assertEquals(dummyTVDetails.getId(), tvEntity.data.getId());
        assertEquals(dummyTVDetails.getTitle(), tvEntity.data.getTitle());
        assertEquals(dummyTVDetails.getPoster(), tvEntity.data.getPoster());
        assertEquals(dummyTVDetails.getCover(), tvEntity.data.getCover());
        assertEquals(dummyTVDetails.getScoreAverage(), tvEntity.data.getScoreAverage(), 0);
        assertEquals(dummyTVDetails.getScoreCount(), tvEntity.data.getScoreCount());
        assertEquals(dummyTVDetails.getPopularity(), tvEntity.data.getPopularity(), 0);
        assertEquals(dummyTVDetails.getType(), tvEntity.data.getType());
        assertEquals(dummyTVDetails.getEpisodes(), tvEntity.data.getEpisodes());
        assertEquals(dummyTVDetails.getStatus(), tvEntity.data.getStatus());
        assertEquals(dummyTVDetails.getAiredDate(), tvEntity.data.getAiredDate());
        assertEquals(dummyTVDetails.getStudios(), tvEntity.data.getStudios());
        assertEquals(dummyTVDetails.getGenres(), tvEntity.data.getGenres());
        assertEquals(dummyTVDetails.getRuntime(), tvEntity.data.getRuntime());
        assertEquals(dummyTVDetails.getSynopsis(), tvEntity.data.getSynopsis());
        assertEquals(dummyTVDetails.getTrailers(), tvEntity.data.getTrailers());

        viewModel.getMediaDetails().observeForever(mediaEntityObserver);
        verify(mediaEntityObserver).onChanged(Resource.success(dummyTVDetails));
    }

    @Test
    public void getMovieVideos() {
        viewModel.setMedia(MEDIA_TYPE_MOVIE, movieId);

        Resource<List<TrailerEntity>> dummyMovieVideos = Resource.success(DataDummy.generateDummyMovieVideos());
        MutableLiveData<Resource<List<TrailerEntity>>> movieVideos = new MutableLiveData<>();
        movieVideos.setValue(dummyMovieVideos);

        when(catalogRepository.getVideos(MEDIA_TYPE_MOVIE, movieId)).thenReturn(movieVideos);
        Resource<List<TrailerEntity>> movieTrailerEntities = LiveDataTestUtil.getValue(viewModel.getTrailers());
        verify(catalogRepository).getVideos(MEDIA_TYPE_MOVIE, movieId);

        assertNotNull(dummyMovieVideos.data);
        assertNotNull(movieTrailerEntities.data);
        assertEquals(dummyMovieVideos.data.size(), movieTrailerEntities.data.size());

        viewModel.getTrailers().observeForever(trailerEntitiesObserver);
        verify(trailerEntitiesObserver).onChanged(dummyMovieVideos);
    }

    @Test
    public void getTVVideos() {
        viewModel.setMedia(MEDIA_TYPE_TV, tvId);

        Resource<List<TrailerEntity>> dummyTVVideos = Resource.success(DataDummy.generateDummyTVVideos());
        MutableLiveData<Resource<List<TrailerEntity>>> tvVideos = new MutableLiveData<>();
        tvVideos.setValue(dummyTVVideos);

        when(catalogRepository.getVideos(MEDIA_TYPE_TV, tvId)).thenReturn(tvVideos);
        Resource<List<TrailerEntity>> tvTrailerEntities = LiveDataTestUtil.getValue(viewModel.getTrailers());
        verify(catalogRepository).getVideos(MEDIA_TYPE_TV, tvId);

        assertNotNull(dummyTVVideos.data);
        assertNotNull(tvTrailerEntities.data);
        assertEquals(dummyTVVideos.data.size(), tvTrailerEntities.data.size());

        viewModel.getTrailers().observeForever(trailerEntitiesObserver);
        verify(trailerEntitiesObserver).onChanged(dummyTVVideos);
    }

    @Test
    public void getMovieCredits() {
        viewModel.setMedia(MEDIA_TYPE_MOVIE, movieId);

        Resource<CreditsEntity> dummyMovieCredits = Resource.success(DataDummy.generateDummyMovieCredits());
        MutableLiveData<Resource<CreditsEntity>> movieCredits = new MutableLiveData<>();
        movieCredits.setValue(dummyMovieCredits);

        when(catalogRepository.getCredits(MEDIA_TYPE_MOVIE, movieId)).thenReturn(movieCredits);
        Resource<CreditsEntity> movieCreditsEntity = LiveDataTestUtil.getValue(viewModel.getCredits());
        verify(catalogRepository).getCredits(MEDIA_TYPE_MOVIE, movieId);

        assertNotNull(dummyMovieCredits.data);
        assertNotNull(movieCreditsEntity.data);
        assertEquals(dummyMovieCredits.data, movieCreditsEntity.data);

        viewModel.getCredits().observeForever(creditsEntityObserver);
        verify(creditsEntityObserver).onChanged(dummyMovieCredits);
    }

    @Test
    public void getTVCredits() {
        viewModel.setMedia(MEDIA_TYPE_TV, tvId);

        Resource<CreditsEntity> dummyTVCredits = Resource.success(DataDummy.generateDummyTVCredits());
        MutableLiveData<Resource<CreditsEntity>> tvCredits = new MutableLiveData<>();
        tvCredits.setValue(dummyTVCredits);

        when(catalogRepository.getCredits(MEDIA_TYPE_TV, tvId)).thenReturn(tvCredits);
        Resource<CreditsEntity> tvCreditsEntity = LiveDataTestUtil.getValue(viewModel.getCredits());
        verify(catalogRepository).getCredits(MEDIA_TYPE_TV, tvId);

        assertNotNull(dummyTVCredits.data);
        assertNotNull(tvCreditsEntity.data);
        assertEquals(dummyTVCredits.data, tvCreditsEntity.data);

        viewModel.getCredits().observeForever(creditsEntityObserver);
        verify(creditsEntityObserver).onChanged(dummyTVCredits);
    }

    @Test
    public void getMovieRecommendations() {
        viewModel.setMedia(MEDIA_TYPE_MOVIE, movieId);

        Resource<List<MediaEntity>> dummyMovieRecommendations = Resource.success(DataDummy.generateDummyMovieRecommendations());
        MutableLiveData<Resource<List<MediaEntity>>> movieRecommendations = new MutableLiveData<>();
        movieRecommendations.setValue(dummyMovieRecommendations);

        when(catalogRepository.getMovieRecommendations(movieId, page)).thenReturn(movieRecommendations);
        Resource<List<MediaEntity>> movieEntities = LiveDataTestUtil.getValue(viewModel.getRecommendations());
        verify(catalogRepository).getMovieRecommendations(movieId, page);

        assertNotNull(dummyMovieRecommendations.data);
        assertNotNull(movieEntities.data);
        assertEquals(dummyMovieRecommendations.data.size(), movieEntities.data.size());

        viewModel.getRecommendations().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieRecommendations);
    }

    @Test
    public void getTVRecommendations() {
        viewModel.setMedia(MEDIA_TYPE_TV, tvId);

        Resource<List<MediaEntity>> dummyTVRecommendations = Resource.success(DataDummy.generateDummyTVRecommendations());
        MutableLiveData<Resource<List<MediaEntity>>> tvRecommendations = new MutableLiveData<>();
        tvRecommendations.setValue(dummyTVRecommendations);

        when(catalogRepository.getTVRecommendations(tvId, page)).thenReturn(tvRecommendations);
        Resource<List<MediaEntity>> tvEntities = LiveDataTestUtil.getValue(viewModel.getRecommendations());
        verify(catalogRepository).getTVRecommendations(tvId, page);

        assertNotNull(tvEntities.data);
        assertNotNull(dummyTVRecommendations.data);
        assertEquals(dummyTVRecommendations.data.size(), tvEntities.data.size());

        viewModel.getRecommendations().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVRecommendations);
    }

    @Test
    public void getGenres() {
        Resource<List<GenreEntity>> dummyGenres = Resource.success(DataDummy.generateDummyGenres());
        MutableLiveData<Resource<List<GenreEntity>>> movieGenres = new MutableLiveData<>();
        movieGenres.setValue(dummyGenres);

        when(catalogRepository.getGenres()).thenReturn(movieGenres);
        Resource<List<GenreEntity>> movieGenreEntities = LiveDataTestUtil.getValue(viewModel.getGenres());
        verify(catalogRepository).getGenres();

        assertNotNull(movieGenreEntities.data);
        assertNotNull(dummyGenres.data);
        assertEquals(dummyGenres.data.size(), movieGenreEntities.data.size());

        viewModel.getGenres().observeForever(genreEntitiesObserver);
        verify(genreEntitiesObserver).onChanged(dummyGenres);
    }

    @Test
    public void getFavorite(){
        viewModel.setMedia(MEDIA_TYPE_TV, tvId);

        FavoriteWithGenres dummyFavorite = DataDummy.generateDummyFavorite(dummyTVDetails);
        MutableLiveData<FavoriteWithGenres> favorite = new MutableLiveData<>();
        favorite.setValue(dummyFavorite);

        when(catalogRepository.getFavorite(tvId, MEDIA_TYPE_TV)).thenReturn(favorite);
        FavoriteWithGenres favoriteEntity = LiveDataTestUtil.getValue(viewModel.getFavorite());
        verify(catalogRepository).getFavorite(tvId, MEDIA_TYPE_TV);

        assertNotNull(favoriteEntity);
        assertEquals(Integer.valueOf(dummyTVDetails.getId()), favoriteEntity.favorite.getId());
        assertEquals(dummyTVDetails.getTitle(), favoriteEntity.favorite.getTitle());
        assertEquals(dummyTVDetails.getPoster(), favoriteEntity.favorite.getPoster());
        assertEquals(dummyTVDetails.getScoreAverage(), favoriteEntity.favorite.getScoreAverage(), 0);
        assertEquals(dummyTVDetails.getType(), favoriteEntity.favorite.getType());
        assertEquals(dummyTVDetails.getAiredDate().getStartDate(), favoriteEntity.favorite.getStartDate());
        assertEquals(dummyTVDetails.getGenres(), favoriteEntity.genres);

        viewModel.getFavorite().observeForever(favoriteEntitiesObserver);
        verify(favoriteEntitiesObserver).onChanged(dummyFavorite);
    }

    @Test
    public void setFavorite(){
        viewModel.setMedia(MEDIA_TYPE_TV, tvId);

        FavoriteWithGenres dummyFavoriteWithGenres = DataDummy.generateDummyFavorite(dummyTVDetails);

        MutableLiveData<FavoriteWithGenres> favorite = new MutableLiveData<>();
        favorite.setValue(null);
        when(catalogRepository.getFavorite(tvId, MEDIA_TYPE_TV)).thenReturn(favorite);
        viewModel.setFavoriteLiveData(catalogRepository.getFavorite(tvId, MEDIA_TYPE_TV));
        FavoriteWithGenres favoriteEntity = LiveDataTestUtil.getValue(viewModel.getFavorite());

        FavoriteEntity dummyFavorite = dummyFavoriteWithGenres.favorite;
        dummyFavorite.setGenres(dummyFavoriteWithGenres.genres);
        viewModel.setFavorite(dummyFavorite, favoriteEntity != null);
        verify(catalogRepository).setFavorite(dummyFavorite, true);

        verifyNoMoreInteractions(favoriteEntitiesObserver);
    }

    @Test
    public void updateFavorite(){
        viewModel.setMedia(MEDIA_TYPE_TV, tvId);

        FavoriteWithGenres dummyFavoriteWithGenres = DataDummy.generateDummyFavorite(dummyTVDetails);

        MutableLiveData<FavoriteWithGenres> favorite = new MutableLiveData<>();
        favorite.setValue(new FavoriteWithGenres(new FavoriteEntity(dummyFavoriteWithGenres.favorite.getId(),
                dummyFavoriteWithGenres.favorite.getType(),
                dummyFavoriteWithGenres.favorite.getTitle(),
                dummyFavoriteWithGenres.favorite.getPoster(),
                dummyFavoriteWithGenres.favorite.getScoreAverage(),
                dummyFavoriteWithGenres.favorite.getStartDate(),
                new ArrayList<>()), new ArrayList<>()));
        when(catalogRepository.getFavorite(tvId, MEDIA_TYPE_TV)).thenReturn(favorite);
        viewModel.setFavoriteLiveData(catalogRepository.getFavorite(tvId, MEDIA_TYPE_TV));
        FavoriteWithGenres favoriteEntityInDb = LiveDataTestUtil.getValue(viewModel.getFavorite());

        FavoriteEntity dummyFavorite = dummyFavoriteWithGenres.favorite;
        dummyFavorite.setGenres(dummyFavoriteWithGenres.genres);
        viewModel.updateFavorite(favoriteEntityInDb, dummyFavorite);
        verify(catalogRepository).updateFavorite(dummyFavorite);

        verifyNoMoreInteractions(favoriteEntitiesObserver);
    }
}