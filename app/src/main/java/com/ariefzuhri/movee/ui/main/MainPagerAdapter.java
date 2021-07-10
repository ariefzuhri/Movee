package com.ariefzuhri.movee.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ariefzuhri.movee.ui.main.home.favorite.FavoriteFragment;
import com.ariefzuhri.movee.ui.main.home.discover.DiscoverFragment;
import com.ariefzuhri.movee.ui.main.home.movie.MovieFragment;
import com.ariefzuhri.movee.ui.main.home.tv.TVFragment;

import org.jetbrains.annotations.NotNull;

public class MainPagerAdapter extends FragmentStateAdapter {

    private static final int TRANSACTION_SCREENS_NUMBER = 4;

    public MainPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new MovieFragment();
            case 1: return new TVFragment();
            case 2: return new DiscoverFragment();
            case 3: return new FavoriteFragment();
            default: throw new IllegalStateException("Invalid adapter position");
        }
    }

    @Override
    public int getItemCount() {
        return TRANSACTION_SCREENS_NUMBER;
    }
}
