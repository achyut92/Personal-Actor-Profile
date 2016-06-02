package com.touche.achyut.paytouch.domain.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

public class Actor implements Serializable{

    private String name;
    private double popularity;
    private String profile_pic;
    private String location;
    private String description;
    private boolean top;
    private ArrayList<Film> films;

    public Actor(String description, ArrayList<Film> films, String location, String name, float popularity, String profile_pic, boolean top) {
        this.description = description;
        this.films = films;
        this.location = location;
        this.name = name;
        this.popularity = popularity;
        this.profile_pic = profile_pic;
        this.top = top;
    }

    public Actor() {
    }

    public ArrayList<Film> getFilms() {
        return films;
    }

    public void setFilms(ArrayList<Film> films) {
        this.films = films;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPopularity() {
        return Double.parseDouble(new DecimalFormat("#.##").format(popularity));
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }


}
