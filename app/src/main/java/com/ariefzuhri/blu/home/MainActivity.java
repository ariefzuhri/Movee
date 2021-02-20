package com.ariefzuhri.blu.home;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.ariefzuhri.blu.R;
import com.ariefzuhri.blu.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        binding.viewPager.setAdapter(pagerAdapter);

        binding.bottomBar.setupBubbleTabBar(binding.viewPager);
        binding.bottomBar.addBubbleListener(id -> {
            switch (id){
                case R.id.menu_movie:
                    binding.viewPager.setCurrentItem(0);
                    break;

                case R.id.menu_tv:
                    binding.viewPager.setCurrentItem(1);
                    break;
            }
        });
    }
}