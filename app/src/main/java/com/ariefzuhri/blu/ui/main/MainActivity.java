package com.ariefzuhri.blu.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ariefzuhri.blu.R;
import com.ariefzuhri.blu.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        binding.viewPager.setAdapter(pagerAdapter);

        binding.bottomBar.setupBubbleTabBar(binding.viewPager);
        binding.bottomBar.addBubbleListener(id -> {
            if (id == R.id.menu_movie) {
                binding.viewPager.setCurrentItem(0);
            } else if (id == R.id.menu_tv) {
                binding.viewPager.setCurrentItem(1);
            }
        });
    }
}