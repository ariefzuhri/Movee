package com.ariefzuhri.movee.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.WindowManager;

import com.ariefzuhri.movee.R;
import com.ariefzuhri.movee.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        MainPagerAdapter pagerAdapter = new MainPagerAdapter(this);
        binding.viewPager.setAdapter(pagerAdapter);
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.bottomBar.setSelected(position, false);
            }
        });
        binding.bottomBar.addBubbleListener(id -> {
            if (id == R.id.menu_movie) {
                binding.viewPager.setCurrentItem(0);
            } else if (id == R.id.menu_tv) {
                binding.viewPager.setCurrentItem(1);
            } else if (id == R.id.menu_discover) {
                binding.viewPager.setCurrentItem(2);
            } else if (id == R.id.menu_favorite) {
                binding.viewPager.setCurrentItem(3);
            }
        });
    }
}