package com.ariefzuhri.movee.core.utils;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class FilterFavorite implements Parcelable {

    private boolean sortedByTitle;
    private boolean sortedByRating;
    private boolean sortedByReleaseDate;
    private boolean showAllMediaType;
    private boolean showMovieOnly;
    private boolean showTVOnly;

    public FilterFavorite() {
        this.sortedByTitle = true;
        this.sortedByRating = false;
        this.sortedByReleaseDate = false;
        this.showAllMediaType = true;
        this.showMovieOnly = false;
        this.showTVOnly = false;
    }

    protected FilterFavorite(@NotNull Parcel in) {
        sortedByTitle = in.readByte() != 0;
        sortedByRating = in.readByte() != 0;
        sortedByReleaseDate = in.readByte() != 0;
        showAllMediaType = in.readByte() != 0;
        showMovieOnly = in.readByte() != 0;
        showTVOnly = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(@NotNull Parcel dest, int flags) {
        dest.writeByte((byte) (sortedByTitle ? 1 : 0));
        dest.writeByte((byte) (sortedByRating ? 1 : 0));
        dest.writeByte((byte) (sortedByReleaseDate ? 1 : 0));
        dest.writeByte((byte) (showAllMediaType ? 1 : 0));
        dest.writeByte((byte) (showMovieOnly ? 1 : 0));
        dest.writeByte((byte) (showTVOnly ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FilterFavorite> CREATOR = new Creator<FilterFavorite>() {
        @NotNull
        @Override
        public FilterFavorite createFromParcel(Parcel in) {
            return new FilterFavorite(in);
        }

        @NotNull
        @Contract(value = "_ -> new", pure = true)
        @Override
        public FilterFavorite[] newArray(int size) {
            return new FilterFavorite[size];
        }
    };

    public boolean isSortedByTitle() {
        return sortedByTitle;
    }

    public void setSortedByTitle(boolean sortedByTitle) {
        this.sortedByTitle = sortedByTitle;
    }

    public boolean isSortedByRating() {
        return sortedByRating;
    }

    public void setSortedByRating(boolean sortedByRating) {
        this.sortedByRating = sortedByRating;
    }

    public boolean isSortedByReleaseDate() {
        return sortedByReleaseDate;
    }

    public void setSortedByReleaseDate(boolean sortedByReleaseDate) {
        this.sortedByReleaseDate = sortedByReleaseDate;
    }

    public boolean isShowAllMediaType() {
        return showAllMediaType;
    }

    public void setShowAllMediaType(boolean showAllMediaType) {
        this.showAllMediaType = showAllMediaType;
    }

    public boolean isShowMovieOnly() {
        return showMovieOnly;
    }

    public void setShowMovieOnly(boolean showMovieOnly) {
        this.showMovieOnly = showMovieOnly;
    }

    public boolean isShowTVOnly() {
        return showTVOnly;
    }

    public void setShowTVOnly(boolean showTVOnly) {
        this.showTVOnly = showTVOnly;
    }
}
