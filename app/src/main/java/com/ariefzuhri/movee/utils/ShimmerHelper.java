package com.ariefzuhri.movee.utils;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.ariefzuhri.movee.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import static com.ariefzuhri.movee.utils.AppUtils.showToast;

public class ShimmerHelper {

    private final Context context;
    private final ShimmerFrameLayout shimmer;
    private final RecyclerView recyclerView;
    private LinearLayout layoutEmpty;

    public ShimmerHelper(Context context, ShimmerFrameLayout shimmer, RecyclerView recyclerView){
        this.context = context;
        this.shimmer = shimmer;
        this.recyclerView = recyclerView;
        initOnClickListener();
    }

    public ShimmerHelper(Context context, ShimmerFrameLayout shimmer, RecyclerView recyclerView, LinearLayout layoutEmpty){
        this.context = context;
        this.shimmer = shimmer;
        this.recyclerView = recyclerView;
        this.layoutEmpty = layoutEmpty;
        initOnClickListener();
    }

    private void initOnClickListener(){
        shimmer.setOnClickListener(view -> showToast(context, context.getString(R.string.toast_data_loading)));
    }

    public void show(){
        shimmer.startShimmer();
        shimmer.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        if (layoutEmpty != null) layoutEmpty.setVisibility(View.INVISIBLE);
    }

    public void hide(){
        shimmer.stopShimmer();
        shimmer.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    public void hide(boolean isEmpty){
        shimmer.stopShimmer();
        shimmer.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        if (layoutEmpty != null && !isEmpty) layoutEmpty.setVisibility(View.VISIBLE);
    }
}
