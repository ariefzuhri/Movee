package com.ariefzuhri.movee.ui.main.home.favorite;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.ariefzuhri.movee.data.CatalogRepository;
import com.ariefzuhri.movee.data.source.local.entity.FavoriteWithGenres;
import com.ariefzuhri.movee.utils.FilterFavorite;
import com.ariefzuhri.movee.utils.LiveDataTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FavoriteViewModelTest {

    private FavoriteViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private CatalogRepository repository;

    @Mock
    private Observer<PagedList<FavoriteWithGenres>> observer;

    @Mock
    private PagedList<FavoriteWithGenres> pagedList;

    @Mock
    private FilterFavorite filter;

    @Before
    public void setUp() {
        viewModel = new FavoriteViewModel(repository);
        viewModel.setFilter(filter);
    }

    @Test
    public void getFavorites() {
        PagedList<FavoriteWithGenres> dummyFavorites = pagedList;
        when(dummyFavorites.size()).thenReturn(4);
        MutableLiveData<PagedList<FavoriteWithGenres>> favorites = new MutableLiveData<>();
        favorites.setValue(dummyFavorites);

        when(repository.getFavorites(filter)).thenReturn(favorites);
        List<FavoriteWithGenres> favoriteEntities = LiveDataTestUtil.getValue(viewModel.getFavorites());
        verify(repository).getFavorites(filter);
        assertNotNull(favoriteEntities);
        assertEquals(4, favoriteEntities.size());

        viewModel.getFavorites().observeForever(observer);
        verify(observer).onChanged(dummyFavorites);
    }
}