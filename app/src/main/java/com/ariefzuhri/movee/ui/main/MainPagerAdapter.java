package com.ariefzuhri.movee.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ariefzuhri.movee.ui.main.home.favorite.FavoriteFragment;
import com.ariefzuhri.movee.ui.main.home.discover.DiscoverFragment;
import com.ariefzuhri.movee.ui.main.home.movie.MovieFragment;
import com.ariefzuhri.movee.ui.main.home.tv.TVFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    public MainPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new MovieFragment();
            case 1: return new TVFragment();
            case 2: return new DiscoverFragment();
            case 3: return new FavoriteFragment();
            default: return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}