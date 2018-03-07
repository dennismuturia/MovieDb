package com.dennismoviedb.moviedb;

/**
 * Created by dennis on 3/6/18.
 */
//This class will be used to hold our data
public class Movie {
    private String movie_title;
    private String movie_poster;
    private int movie_vote;
    private String movie_backdropImage;
    private String movie_overview;

    public Movie(String movie_title, String movie_poster, int movie_vote,
                 String movie_backdropImage, String movie_overview){
        this.movie_title = movie_title;
        this.movie_poster = movie_poster;
        this.movie_vote = movie_vote;
        this.movie_backdropImage = movie_backdropImage;
        this.movie_overview = movie_overview;

    }

    public String getMovie_title() {
        return movie_title;
    }

    public String getMovie_poster() {
        return movie_poster;
    }

    public int getMovie_vote() {
        return movie_vote;
    }

    public String getMovie_backdropImage() {
        return movie_backdropImage;
    }

    public String getMovie_overview() {return movie_overview;}

}