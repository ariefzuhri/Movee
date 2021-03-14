package com.ariefzuhri.blu.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ariefzuhri.blu.ui.movie.MovieFragment;

import static com.ariefzuhri.blu.utils.Constants.EXTRA_MOVIE_TYPE;
import static com.ariefzuhri.blu.utils.Constants.TYPE_MOVIE;
import static com.ariefzuhri.blu.utils.Constants.TYPE_TV;

public class MainPagerAdapter extends FragmentPagerAdapter {
    public MainPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new MovieFragment();
        Bundle bundle = new Bundle();

        switch (position){
            case 0:
                bundle.putString(EXTRA_MOVIE_TYPE, TYPE_MOVIE);
                break;

            case 1:
                bundle.putString(EXTRA_MOVIE_TYPE, TYPE_TV);
                break;
        }

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
