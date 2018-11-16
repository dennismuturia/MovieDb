package com.dennismoviedb.moviedb.model;

import java.util.ArrayList;

/**
 * Created by dennis on 3/9/18.
 */

public class DetailMovie {
    private String movie_title;
    private String movie_poster;
    private String overview;
    private String backdrop;
    private String tagline;
    private String vote_average;
    private String released;
    private ArrayList<String> company_logos;

    public DetailMovie(String movie_title, String movie_poster, String overview, String backdrop, ArrayList<String> company_logos,
                       String released, String vote_average, String tagline){
        this.movie_title = movie_title;
        this.movie_poster = movie_poster;
        this.overview = overview;
        this.backdrop = backdrop;
        this.company_logos = company_logos;
        this.released = released;
        this.vote_average = vote_average;
        this.tagline = tagline;
    }

    public String getMovie_title(){ return movie_title; }

    public String getMovie_poster() {return movie_poster; }

    public String getLatestOverview() { return overview; }

    public String getBackdrop() { return backdrop; }

    public String getTagline() {
        return tagline;
    }

    public ArrayList<String> getCompany_logos() {
        return company_logos;
    }

    public String getReleased() {
        return released;
    }

    public String getVote_average() {
        return vote_average;
    }
}
