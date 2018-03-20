package com.dennismoviedb.moviedb.model;

/**
 * Created by dennis on 3/9/18.
 */

public class NowShowingMovie {
    private String movie_title;
    private String movie_poster;

    public NowShowingMovie(String movie_title, String movie_poster){
        this.movie_title = movie_title;
        this.movie_poster = movie_poster;
    }

    public String getMovie_title(){ return movie_title; }

    public String getMovie_poster() {return movie_poster; }
}
