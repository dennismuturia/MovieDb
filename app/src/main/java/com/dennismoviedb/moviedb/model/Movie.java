package com.dennismoviedb.moviedb.model;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by dennis on 3/6/18.
 */
//This class will be used to hold our data
@Parcel
public class Movie {
    private String movie_title;
    private String movie_poster;
    private double movie_vote;
    private String movie_backdropImage;
    private String movie_overview;
    private boolean adult;
    private String pageNumber;
    private int id;
    private ArrayList<Integer> theGenres;


    public Movie(String movie_title, String movie_poster, double movie_vote,
                 String movie_backdropImage, String movie_overview, boolean adult, String pageNumber, int id, ArrayList<Integer> theGenres){
        this.movie_title = movie_title;
        this.movie_poster = movie_poster;
        this.movie_vote = movie_vote;
        this.movie_backdropImage = movie_backdropImage;
        this.movie_overview = movie_overview;
        this.adult = adult;
        this.pageNumber = pageNumber;
        this.id = id;
        this.theGenres = theGenres;
    }

    public Movie(){}

    public String getMovie_title() {
        return movie_title;
    }

    public String getMovie_poster() {
        return movie_poster;
    }

    public double getMovie_vote() {
        return movie_vote;
    }

    public String getMovie_backdropImage() {
        return movie_backdropImage;
    }

    public String getMovie_overview() { return movie_overview; }

    public boolean isAdult() {
        return adult;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public int getId() {
        return id;
    }

    public  ArrayList<Integer> getGenres() {
        return theGenres;
    }
}
