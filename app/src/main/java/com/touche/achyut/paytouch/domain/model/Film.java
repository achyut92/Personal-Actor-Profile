package com.touche.achyut.paytouch.domain.model;

import java.io.Serializable;

public class Film implements Serializable{

    private String title;
    private String poster;
    private String release_date;
    private int avg_count;
    private int avg_vote;

    public int getAvg_count() {
        return avg_count;
    }

    public void setAvg_count(int avg_count) {
        this.avg_count = avg_count;
    }

    public int getAvg_vote() {
        return avg_vote;
    }

    public void setAvg_vote(int avg_vote) {
        this.avg_vote = avg_vote;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
