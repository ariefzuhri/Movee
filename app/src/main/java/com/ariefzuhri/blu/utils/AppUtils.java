package com.ariefzuhri.blu.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.ariefzuhri.blu.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class AppUtils {

    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void loadImage(Context context, String size, Object source, ImageView imageView) {
        Glide.with(context)
                .asBitmap()
                .load("https://image.tmdb.org/t/p/" + size + source)
                .apply(getGlideOptions())
                .centerCrop()
                .into(imageView);
    }

    private static RequestOptions getGlideOptions(){
        return RequestOptions.placeholderOf(R.drawable.ic_no_pic)
                .error(R.drawable.ic_no_pic);
    }
}
