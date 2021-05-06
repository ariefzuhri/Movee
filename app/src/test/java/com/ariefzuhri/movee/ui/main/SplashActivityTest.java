package com.ariefzuhri.movee.ui.main;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.ariefzuhri.movee.R;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class SplashActivityTest {

    @Test
    public void shouldChangeTypeface() {
        SplashActivity activity = Robolectric.setupActivity(SplashActivity.class);

        TextView tvAppName = activity.findViewById(R.id.tv_app_name);
        assertEquals(tvAppName.getTypeface(), ResourcesCompat.getFont(activity, R.font.quantify));
    }
}