package com.ariefzuhri.blu.data;

import com.ariefzuhri.blu.data.source.remote.entity.GenreEntity;
import com.ariefzuhri.blu.data.source.remote.entity.ProductionCompanyEntity;
import com.ariefzuhri.blu.data.source.remote.entity.VideoEntity;

import java.util.List;

public class MediaEntity {
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
    private final AiredDate airedDate;
    private List<ProductionCompanyEntity> studios;
    private List<Integer> genreIds;
    private List<GenreEntity> genres;
    private int runtime;
    private final String synopsis;
    private List<VideoEntity> trailer;

    public MediaEntity(int id, String title, String poster, String cover, double scoreAverage, int scoreCount, double popularity, String type, AiredDate airedDate, List<Integer> genreIds, String synopsis) {
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

    public MediaEntity(int id, String title, String poster, String cover, double scoreAverage, int scoreCount, double popularity, String type, int episodes, String status, AiredDate airedDate, List<ProductionCompanyEntity> studios, List<GenreEntity> genres, int runtime, String synopsis, List<VideoEntity> trailer) {
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

    public AiredDate getAiredDate() {
        return airedDate;
    }

    public List<ProductionCompanyEntity> getStudios() {
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

    public List<VideoEntity> getTrailer() {
        return trailer;
    }

    public void setTrailer(List<VideoEntity> trailer) {
        this.trailer = trailer;
    }
}