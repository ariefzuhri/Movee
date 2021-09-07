package com.ariefzuhri.movee.core.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.ariefzuhri.movee.R;
import com.ariefzuhri.movee.core.data.source.local.entity.FavoriteEntity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.jetbrains.annotations.NotNull;

import static com.ariefzuhri.movee.core.utils.Constants.BASE_URL_TMDB_IMAGE;

public class AppUtils {

    public static boolean equalsFavoriteObjects(FavoriteEntity o1, FavoriteEntity o2) {
        try {
            return o1.getId().equals(o2.getId()) &&
                    o1.getType().equals(o2.getType()) &&
                    o1.getTitle().equals(o2.getTitle()) &&
                    o1.getPoster().equals(o2.getPoster()) &&
                    o1.getScoreAverage() == o2.getScoreAverage() &&
                    o1.getStartDate().equals(o2.getStartDate()) &&
                    o1.getGenres().size() == o2.getGenres().size();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void loadImage(ImageView imageView, Object source, String size) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(BASE_URL_TMDB_IMAGE + size + source)
                .apply(myGlideOptions())
                .centerCrop()
                .into(imageView);
    }

    @NotNull
    private static RequestOptions myGlideOptions() {
        return RequestOptions.placeholderOf(R.drawable.ic_placeholder_96)
                .error(R.drawable.ic_placeholder_96);
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
