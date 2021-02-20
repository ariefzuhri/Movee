package com.ariefzuhri.blu.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ariefzuhri.blu.movie.MovieFragment;
import com.ariefzuhri.blu.movie.TVFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {
    public MainPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MovieFragment();

            case 1:
                return new TVFragment();

            default:
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
