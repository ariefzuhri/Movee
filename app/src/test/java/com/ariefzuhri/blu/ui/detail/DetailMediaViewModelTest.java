package com.ariefzuhri.blu.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.ariefzuhri.blu.data.CreditsEntity;
import com.ariefzuhri.blu.data.GenreEntity;
import com.ariefzuhri.blu.data.MediaEntity;
import com.ariefzuhri.blu.data.TrailerEntity;
import com.ariefzuhri.blu.data.source.CatalogRepository;
import com.ariefzuhri.blu.utils.DataDummy;
import com.ariefzuhri.blu.utils.LiveDataTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.ariefzuhri.blu.utils.Constants.MEDIA_TYPE_MOVIE;
import static com.ariefzuhri.blu.utils.Constants.MEDIA_TYPE_TV;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
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
    private Observer<MediaEntity> mediaEntityObserver;

    @Mock
    private Observer<List<MediaEntity>> mediaEntitiesObserver;

    @Mock
    private Observer<List<TrailerEntity>> trailerEntitiesObserver;

    @Mock
    private Observer<CreditsEntity> creditsEntityObserver;

    @Mock
    private Observer<List<GenreEntity>> genreEntitiesObserver;

    @Before
    public void setUp(){
        viewModel = new DetailMediaViewModel(catalogRepository);
    }

    @Test
    public void getMovieDetails() {
        viewModel.setSelectedMedia(MEDIA_TYPE_MOVIE, movieId);

        MutableLiveData<MediaEntity> movieDetails = new MutableLiveData<>();
        movieDetails.setValue(dummyMovieDetails);

        when(catalogRepository.getMovieDetails(movieId)).thenReturn(movieDetails);
        MediaEntity movieEntity = LiveDataTestUtil.getValue(viewModel.getMovieDetails());
        verify(catalogRepository).getMovieDetails(movieId);
        assertNotNull(movieEntity);
        assertEquals(dummyMovieDetails.getId(), movieEntity.getId());
        assertEquals(dummyMovieDetails.getTitle(), movieEntity.getTitle());
        assertEquals(dummyMovieDetails.getPoster(), movieEntity.getPoster());
        assertEquals(dummyMovieDetails.getCover(), movieEntity.getCover());
        assertEquals(dummyMovieDetails.getScoreAverage(), movieEntity.getScoreAverage(), 0);
        assertEquals(dummyMovieDetails.getScoreCount(), movieEntity.getScoreCount());
        assertEquals(dummyMovieDetails.getPopularity(), movieEntity.getPopularity(), 0);
        assertEquals(dummyMovieDetails.getType(), movieEntity.getType());
        assertEquals(dummyMovieDetails.getEpisodes(), movieEntity.getEpisodes());
        assertEquals(dummyMovieDetails.getStatus(), movieEntity.getStatus());
        assertEquals(dummyMovieDetails.getAiredDate(), movieEntity.getAiredDate());
        assertEquals(dummyMovieDetails.getStudios(), movieEntity.getStudios());
        assertEquals(dummyMovieDetails.getGenres(), movieEntity.getGenres());
        assertEquals(dummyMovieDetails.getRuntime(), movieEntity.getRuntime());
        assertEquals(dummyMovieDetails.getSynopsis(), movieEntity.getSynopsis());
        assertEquals(dummyMovieDetails.getTrailer(), movieEntity.getTrailer());

        viewModel.getMovieDetails().observeForever(mediaEntityObserver);
        verify(mediaEntityObserver).onChanged(dummyMovieDetails);
    }

    @Test
    public void getTVDetails() {
        viewModel.setSelectedMedia(MEDIA_TYPE_TV, tvId);

        MutableLiveData<MediaEntity> tvDetails = new MutableLiveData<>();
        tvDetails.setValue(dummyTVDetails);

        when(catalogRepository.getTVDetails(tvId)).thenReturn(tvDetails);
        MediaEntity tvEntity = LiveDataTestUtil.getValue(viewModel.getTVDetails());
        verify(catalogRepository).getTVDetails(tvId);
        assertNotNull(tvEntity);
        assertEquals(dummyTVDetails.getId(), tvEntity.getId());
        assertEquals(dummyTVDetails.getTitle(), tvEntity.getTitle());
        assertEquals(dummyTVDetails.getPoster(), tvEntity.getPoster());
        assertEquals(dummyTVDetails.getCover(), tvEntity.getCover());
        assertEquals(dummyTVDetails.getScoreAverage(), tvEntity.getScoreAverage(), 0);
        assertEquals(dummyTVDetails.getScoreCount(), tvEntity.getScoreCount());
        assertEquals(dummyTVDetails.getPopularity(), tvEntity.getPopularity(), 0);
        assertEquals(dummyTVDetails.getType(), tvEntity.getType());
        assertEquals(dummyTVDetails.getEpisodes(), tvEntity.getEpisodes());
        assertEquals(dummyTVDetails.getStatus(), tvEntity.getStatus());
        assertEquals(dummyTVDetails.getAiredDate(), tvEntity.getAiredDate());
        assertEquals(dummyTVDetails.getStudios(), tvEntity.getStudios());
        assertEquals(dummyTVDetails.getGenres(), tvEntity.getGenres());
        assertEquals(dummyTVDetails.getRuntime(), tvEntity.getRuntime());
        assertEquals(dummyTVDetails.getSynopsis(), tvEntity.getSynopsis());
        assertEquals(dummyTVDetails.getTrailer(), tvEntity.getTrailer());

        viewModel.getTVDetails().observeForever(mediaEntityObserver);
        verify(mediaEntityObserver).onChanged(dummyTVDetails);
    }

    @Test
    public void getVideos() {
        List<TrailerEntity> dummyMovieVideos = DataDummy.generateDummyMovieVideos();
        List<TrailerEntity> dummyTVVideos = DataDummy.generateDummyTVVideos();

        viewModel.setSelectedMedia(MEDIA_TYPE_MOVIE, movieId);

        MutableLiveData<List<TrailerEntity>> movieVideos = new MutableLiveData<>();
        movieVideos.setValue(dummyMovieVideos);

        when(catalogRepository.getVideos(MEDIA_TYPE_MOVIE, movieId)).thenReturn(movieVideos);
        List<TrailerEntity> movieTrailerEntities = LiveDataTestUtil.getValue(viewModel.getVideos());
        verify(catalogRepository).getVideos(MEDIA_TYPE_MOVIE, movieId);
        assertNotNull(movieTrailerEntities);
        assertEquals(dummyMovieVideos.size(), movieTrailerEntities.size());

        viewModel.getVideos().observeForever(trailerEntitiesObserver);
        verify(trailerEntitiesObserver).onChanged(dummyMovieVideos);

        viewModel.setSelectedMedia(MEDIA_TYPE_TV, tvId);

        MutableLiveData<List<TrailerEntity>> tvVideos = new MutableLiveData<>();
        tvVideos.setValue(dummyTVVideos);

        when(catalogRepository.getVideos(MEDIA_TYPE_TV, tvId)).thenReturn(tvVideos);
        List<TrailerEntity> tvTrailerEntities = LiveDataTestUtil.getValue(viewModel.getVideos());
        verify(catalogRepository).getVideos(MEDIA_TYPE_TV, tvId);
        assertNotNull(tvTrailerEntities);
        assertEquals(dummyTVVideos.size(), tvTrailerEntities.size());

        viewModel.getVideos().observeForever(trailerEntitiesObserver);
        verify(trailerEntitiesObserver).onChanged(dummyTVVideos);
    }

    @Test
    public void getCredits() {
        CreditsEntity dummyMovieCredits = DataDummy.generateDummyMovieCredits();
        CreditsEntity dummyTVCredits = DataDummy.generateDummyTVCredits();

        viewModel.setSelectedMedia(MEDIA_TYPE_MOVIE, movieId);

        MutableLiveData<CreditsEntity> movieCredits = new MutableLiveData<>();
        movieCredits.setValue(dummyMovieCredits);

        when(catalogRepository.getCredits(MEDIA_TYPE_MOVIE, movieId)).thenReturn(movieCredits);
        CreditsEntity movieCreditsEntity = LiveDataTestUtil.getValue(viewModel.getCredits());
        verify(catalogRepository).getCredits(MEDIA_TYPE_MOVIE, movieId);
        assertNotNull(movieCreditsEntity);
        assertEquals(dummyMovieCredits, movieCreditsEntity);

        viewModel.getCredits().observeForever(creditsEntityObserver);
        verify(creditsEntityObserver).onChanged(dummyMovieCredits);

        viewModel.setSelectedMedia(MEDIA_TYPE_TV, tvId);

        MutableLiveData<CreditsEntity> tvCredits = new MutableLiveData<>();
        tvCredits.setValue(dummyTVCredits);

        when(catalogRepository.getCredits(MEDIA_TYPE_TV, tvId)).thenReturn(tvCredits);
        CreditsEntity tvCreditsEntity = LiveDataTestUtil.getValue(viewModel.getCredits());
        verify(catalogRepository).getCredits(MEDIA_TYPE_TV, tvId);
        assertNotNull(tvCreditsEntity);
        assertEquals(dummyTVCredits, tvCreditsEntity);

        viewModel.getCredits().observeForever(creditsEntityObserver);
        verify(creditsEntityObserver).onChanged(dummyTVCredits);
    }

    @Test
    public void getMovieRecommendations() {
        List<MediaEntity> dummyMovieRecommendations = DataDummy.generateDummyMovieRecommendations();
        viewModel.setSelectedMedia(MEDIA_TYPE_MOVIE, movieId);

        MutableLiveData<List<MediaEntity>> movieRecommendations = new MutableLiveData<>();
        movieRecommendations.setValue(dummyMovieRecommendations);

        when(catalogRepository.getMovieRecommendations(movieId, page)).thenReturn(movieRecommendations);
        List<MediaEntity> movieEntities = LiveDataTestUtil.getValue(viewModel.getMovieRecommendations());
        verify(catalogRepository).getMovieRecommendations(movieId, page);
        assertNotNull(movieEntities);
        assertEquals(dummyMovieRecommendations.size(), movieEntities.size());

        viewModel.getMovieRecommendations().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyMovieRecommendations);
    }

    @Test
    public void getTVRecommendations() {
        List<MediaEntity> dummyTVRecommendations = DataDummy.generateDummyTVRecommendations();
        viewModel.setSelectedMedia(MEDIA_TYPE_TV, tvId);

        MutableLiveData<List<MediaEntity>> tvRecommendations = new MutableLiveData<>();
        tvRecommendations.setValue(dummyTVRecommendations);

        when(catalogRepository.getTVRecommendations(tvId, page)).thenReturn(tvRecommendations);
        List<MediaEntity> tvEntities = LiveDataTestUtil.getValue(viewModel.getTVRecommendations());
        verify(catalogRepository).getTVRecommendations(tvId, page);
        assertNotNull(tvEntities);
        assertEquals(dummyTVRecommendations.size(), tvEntities.size());

        viewModel.getTVRecommendations().observeForever(mediaEntitiesObserver);
        verify(mediaEntitiesObserver).onChanged(dummyTVRecommendations);
    }

    @Test
    public void getGenres() {
        List<GenreEntity> dummyMovieGenres = DataDummy.generateDummyMovieGenres();
        List<GenreEntity> dummyTVGenres = DataDummy.generateDummyTVGenres();

        viewModel.setSelectedMedia(MEDIA_TYPE_MOVIE, movieId);

        MutableLiveData<List<GenreEntity>> movieGenres = new MutableLiveData<>();
        movieGenres.setValue(dummyMovieGenres);

        when(catalogRepository.getGenres(MEDIA_TYPE_MOVIE)).thenReturn(movieGenres);
        List<GenreEntity> movieGenreEntities = LiveDataTestUtil.getValue(viewModel.getGenres());
        verify(catalogRepository).getGenres(MEDIA_TYPE_MOVIE);
        assertNotNull(movieGenreEntities);
        assertEquals(dummyMovieGenres.size(), movieGenreEntities.size());

        viewModel.getGenres().observeForever(genreEntitiesObserver);
        verify(genreEntitiesObserver).onChanged(dummyMovieGenres);

        viewModel.setSelectedMedia(MEDIA_TYPE_TV, tvId);

        MutableLiveData<List<GenreEntity>> tvGenres = new MutableLiveData<>();
        tvGenres.setValue(dummyTVGenres);

        when(catalogRepository.getGenres(MEDIA_TYPE_TV)).thenReturn(tvGenres);
        List<GenreEntity> tvGenreEntities = LiveDataTestUtil.getValue(viewModel.getGenres());
        verify(catalogRepository).getGenres(MEDIA_TYPE_TV);
        assertNotNull(tvGenreEntities);
        assertEquals(dummyTVGenres.size(), tvGenreEntities.size());

        viewModel.getGenres().observeForever(genreEntitiesObserver);
        verify(genreEntitiesObserver).onChanged(dummyTVGenres);
    }
}