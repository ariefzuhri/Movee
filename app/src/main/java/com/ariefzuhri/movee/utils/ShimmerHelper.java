package com.ariefzuhri.movee.utils;

import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;

public class ShimmerHelper {

    private final ShimmerFrameLayout shimmer;
    private final RecyclerView recyclerView;
    private LinearLayout layoutEmpty;

    public ShimmerHelper(ShimmerFrameLayout shimmer, RecyclerView recyclerView){
        this.shimmer = shimmer;
        this.recyclerView = recyclerView;
    }

    public ShimmerHelper(ShimmerFrameLayout shimmer, RecyclerView recyclerView, LinearLayout layoutEmpty){
        this.shimmer = shimmer;
        this.recyclerView = recyclerView;
        this.layoutEmpty = layoutEmpty;
    }

    public void show(){
        shimmer.startShimmer();
        shimmer.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        if (layoutEmpty != null) layoutEmpty.setVisibility(View.INVISIBLE);
    }

    public void hide(boolean isEmpty){
        shimmer.stopShimmer();
        shimmer.setVisibility(View.GONE);
        if (isEmpty) {
            if (layoutEmpty != null) {
                layoutEmpty.setVisibility(View.VISIBLE);
            }
        } else {
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
