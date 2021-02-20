package com.ariefzuhri.blu.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private int id;
    private String title;
    private String poster;
    private String cover;
    private String score;
    private String type;
    private int episodes;
    private String status;
    private AiredDate airedDate;
    private String studio;
    private String genres;
    private int runtime;
    private String synopsis;
    private String trailer;

    public Movie() {}

    protected Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        poster = in.readString();
        cover = in.readString();
        score = in.readString();
        type = in.readString();
        episodes = in.readInt();
        status = in.readString();
        airedDate = in.readParcelable(AiredDate.class.getClassLoader());
        studio = in.readString();
        genres = in.readString();
        runtime = in.readInt();
        synopsis = in.readString();
        trailer = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(poster);
        dest.writeString(cover);
        dest.writeString(score);
        dest.writeString(type);
        dest.writeInt(episodes);
        dest.writeString(status);
        dest.writeParcelable(airedDate, flags);
        dest.writeString(studio);
        dest.writeString(genres);
        dest.writeInt(runtime);
        dest.writeString(synopsis);
        dest.writeString(trailer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AiredDate getAiredDate() {
        return airedDate;
    }

    public void setAiredDate(AiredDate airedDate) {
        this.airedDate = airedDate;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
}
