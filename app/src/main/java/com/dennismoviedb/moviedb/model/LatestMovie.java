package com.dennismoviedb.moviedb.model;

/**
 * Created by dennis on 3/9/18.
 */

public class LatestMovie {
    private String movie_title;
    private String movie_poster;
    private String overview;

    public LatestMovie(String movie_title, String movie_poster, String overview){
        this.movie_title = movie_title;
        this.movie_poster = movie_poster;
        this.overview = overview;
    }

    public String getMovie_title(){ return movie_title; }

    public String getMovie_poster() {return movie_poster; }

    public String getLatestOverview() {
        return overview;
    }
}
