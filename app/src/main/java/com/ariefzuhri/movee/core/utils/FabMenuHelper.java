package com.ariefzuhri.movee.core.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.ariefzuhri.movee.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

public class FabMenuHelper {

    private boolean isClosed = false;

    private Animation rotateOpen;
    private Animation rotateClose;
    private Animation fromBottom;
    private Animation toBottom;

    public FabMenuHelper(Context context, FloatingActionButton parent, @NotNull FloatingActionButton... child) {
        for (FloatingActionButton c : child) c.setVisibility(View.INVISIBLE);
        initializeAnimation(context);
        initializeOnClickListener(parent, child);
    }

    private void initializeAnimation(Context context) {
        rotateOpen = AnimationUtils.loadAnimation(context, R.anim.rotate_open_anim);
        rotateClose = AnimationUtils.loadAnimation(context, R.anim.rotate_close_anim);
        fromBottom = AnimationUtils.loadAnimation(context, R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(context, R.anim.to_bottom_anim);
    }

    private void initializeOnClickListener(@NotNull FloatingActionButton parent, FloatingActionButton... child) {
        parent.setOnClickListener(view -> {
            setVisibility(isClosed, child);
            setAnimation(isClosed, parent, child);
            isClosed = !isClosed;
        });

    }

    private void setVisibility(boolean isClosed, FloatingActionButton... child) {
        if (!isClosed) {
            for (FloatingActionButton c : child) c.setVisibility(View.VISIBLE);
        } else {
            for (FloatingActionButton c : child) c.setVisibility(View.INVISIBLE);
        }
    }

    private void setAnimation(boolean isClosed, FloatingActionButton parent, FloatingActionButton... child) {
        if (!isClosed) {
            for (FloatingActionButton c : child) c.startAnimation(fromBottom);
            parent.startAnimation(rotateOpen);
        } else {
            for (FloatingActionButton c : child) c.startAnimation(toBottom);
            parent.startAnimation(rotateClose);
        }
    }
}
