package com.ariefzuhri.blu.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.ariefzuhri.blu.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import static com.ariefzuhri.blu.utils.AppUtils.showToast;

public class ShimmerHelper {

    private final Context context;
    private final ShimmerFrameLayout shimmer;
    private final RecyclerView recyclerView;
    private ImageView imgEmpty;

    public ShimmerHelper(Context context, ShimmerFrameLayout shimmer, RecyclerView recyclerView){
        this.context = context;
        this.shimmer = shimmer;
        this.recyclerView = recyclerView;
        initOnClickListener();
    }

    public ShimmerHelper(Context context, ShimmerFrameLayout shimmer, RecyclerView recyclerView, ImageView imgEmpty){
        this.context = context;
        this.shimmer = shimmer;
        this.recyclerView = recyclerView;
        this.imgEmpty = imgEmpty;
        initOnClickListener();
    }

    private void initOnClickListener(){
        shimmer.setOnClickListener(view -> showToast(context, context.getString(R.string.toast_data_loading)));
    }

    public void show(){
        shimmer.startShimmer();
        shimmer.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        if (imgEmpty != null) imgEmpty.setVisibility(View.GONE);
    }

    public void hide(){
        shimmer.stopShimmer();
        shimmer.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
