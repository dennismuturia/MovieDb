package com.dennismoviedb.moviedb.model;

//Create a model to store the values that we get from the API
//Encapsulation in effect
public class Genre {
    private int id;
    private String name;

    //Constructor for the model
    public Genre(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getGenreId() {
        return id;
    }

    public String getGenreName() {
        return name;
    }
}
