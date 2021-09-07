package com.ariefzuhri.movee.core.utils;

import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;

public class ShimmerHelper {

    private final ShimmerFrameLayout shimmer;
    private final View contents;
    private View emptyState;

    public ShimmerHelper(ShimmerFrameLayout shimmer, View contents) {
        this.shimmer = shimmer;
        this.contents = contents;
    }

    public ShimmerHelper(ShimmerFrameLayout shimmer, View contents, View emptyState) {
        this.shimmer = shimmer;
        this.contents = contents;
        this.emptyState = emptyState;
    }

    public void show() {
        shimmer.startShimmer();
        shimmer.setVisibility(View.VISIBLE);
        contents.setVisibility(View.INVISIBLE);
        if (emptyState != null) emptyState.setVisibility(View.INVISIBLE);
    }

    public void hide(boolean isEmpty) {
        shimmer.stopShimmer();
        shimmer.setVisibility(View.GONE);
        if (isEmpty) {
            if (emptyState != null) {
                emptyState.setVisibility(View.VISIBLE);
            }
        } else {
            contents.setVisibility(View.VISIBLE);
        }
    }
}
