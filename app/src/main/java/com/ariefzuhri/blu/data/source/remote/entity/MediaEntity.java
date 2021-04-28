package com.ariefzuhri.blu.data.source.remote.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.ariefzuhri.blu.data.source.local.entity.GenreEntity;

import java.util.List;

public class MediaEntity implements Parcelable {

    private final int id;
    private final String title;
    private final String poster;
    private final String cover;
    private final double scoreAverage;
    private final int scoreCount;
    private final double popularity;
    private final String type;
    private int episodes;
    private String status;
    private AiredDateEntity airedDate;
    private List<StudioEntity> studios;
    private List<Integer> genreIds;
    private List<GenreEntity> genres;
    private int runtime;
    private final String synopsis;
    private List<TrailerEntity> trailer;

    public MediaEntity(int id, String title, String poster, String cover, double scoreAverage, int scoreCount, double popularity, String type, AiredDateEntity airedDate, List<Integer> genreIds, String synopsis) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.cover = cover;
        this.scoreAverage = scoreAverage;
        this.scoreCount = scoreCount;
        this.popularity = popularity;
        this.type = type;
        this.airedDate = airedDate;
        this.genreIds = genreIds;
        this.synopsis = synopsis;
    }

    public MediaEntity(int id, String title, String poster, String cover, double scoreAverage, int scoreCount, double popularity, String type, int episodes, String status, AiredDateEntity airedDate, List<StudioEntity> studios, List<GenreEntity> genres, int runtime, String synopsis, List<TrailerEntity> trailer) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.cover = cover;
        this.scoreAverage = scoreAverage;
        this.scoreCount = scoreCount;
        this.popularity = popularity;
        this.type = type;
        this.episodes = episodes;
        this.status = status;
        this.airedDate = airedDate;
        this.studios = studios;
        this.genres = genres;
        this.runtime = runtime;
        this.synopsis = synopsis;
        this.trailer = trailer;
    }

    protected MediaEntity(Parcel in) {
        id = in.readInt();
        title = in.readString();
        poster = in.readString();
        cover = in.readString();
        scoreAverage = in.readDouble();
        scoreCount = in.readInt();
        popularity = in.readDouble();
        type = in.readString();
        episodes = in.readInt();
        status = in.readString();
        runtime = in.readInt();
        synopsis = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(poster);
        dest.writeString(cover);
        dest.writeDouble(scoreAverage);
        dest.writeInt(scoreCount);
        dest.writeDouble(popularity);
        dest.writeString(type);
        dest.writeInt(episodes);
        dest.writeString(status);
        dest.writeInt(runtime);
        dest.writeString(synopsis);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MediaEntity> CREATOR = new Creator<MediaEntity>() {
        @Override
        public MediaEntity createFromParcel(Parcel in) {
            return new MediaEntity(in);
        }

        @Override
        public MediaEntity[] newArray(int size) {
            return new MediaEntity[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getCover() {
        return cover;
    }

    public double getScoreAverage() {
        return scoreAverage;
    }

    public int getScoreCount() {
        return scoreCount;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getType() {
        return type;
    }

    public int getEpisodes() {
        return episodes;
    }

    public String getStatus() {
        return status;
    }

    public AiredDateEntity getAiredDate() {
        return airedDate;
    }

    public List<StudioEntity> getStudios() {
        return studios;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public List<GenreEntity> getGenres() {
        return genres;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public List<TrailerEntity> getTrailer() {
        return trailer;
    }

    public void setTrailer(List<TrailerEntity> trailer) {
        this.trailer = trailer;
    }
}