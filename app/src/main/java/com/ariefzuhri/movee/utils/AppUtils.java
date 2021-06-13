package com.ariefzuhri.movee.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.ariefzuhri.movee.R;
import com.ariefzuhri.movee.data.source.local.entity.FavoriteEntity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import static com.ariefzuhri.movee.utils.Constants.BASE_URL_TMDB_IMAGE;

public class AppUtils {

    public static boolean equalsFavoriteObjects(FavoriteEntity o1, FavoriteEntity o2){
        return o1.getId().equals(o2.getId()) &&
                o1.getType().equals(o2.getType()) &&
                o1.getTitle().equals(o2.getTitle()) &&
                o1.getPoster().equals(o2.getPoster()) &&
                o1.getScoreAverage() == o2.getScoreAverage() &&
                o1.getStartDate().equals(o2.getStartDate()) &&
                o1.getGenres().size() == o2.getGenres().size();
    }

    public static void loadImage(Context context, String size, Object source, ImageView imageView) {
        Glide.with(context)
                .asBitmap()
                .load(BASE_URL_TMDB_IMAGE + size + source)
                .apply(myGlideOptions())
                .centerCrop()
                .into(imageView);
    }

    private static RequestOptions myGlideOptions(){
        return RequestOptions.placeholderOf(R.drawable.ic_no_pic)
                .error(R.drawable.ic_no_pic);
    }

    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
