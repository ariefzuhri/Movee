package com.ariefzuhri.blu.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.ariefzuhri.blu.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.Locale;

import static com.bumptech.glide.request.RequestOptions.placeholderOf;

public class AppUtils {
    public static final String LOG = "App log -> ";
    public static Locale locale = new Locale("in", "ID");

    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void loadImage(Context context, Object source, ImageView imageView) {
        Glide.with(context)
                .asBitmap()
                .load(source)
                .apply(getGlideOptions())
                .centerCrop()
                .into(imageView);
    }

    private static RequestOptions getGlideOptions(){
        return RequestOptions.placeholderOf(R.drawable.ic_no_pic)
                .error(R.drawable.ic_no_pic);
    }
}
