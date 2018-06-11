package com.dennismoviedb.moviedb.services;

import com.dennismoviedb.moviedb.Constants;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class MovieSearchService {
    public static void searchMovies(){
        //Build a url for the search url
        OkHttpClient client = new OkHttpClient.Builder().build();

        //Building the site itself
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.searchMovieURL).newBuilder();
        //Passing the queries
        urlBuilder.addQueryParameter(Constants.Api, Constants.movieApi);
        //urlBuilder.addQueryParameter(Constants.searchQuery, [what to be searched]);
        String myUrl = urlBuilder.build().toString();
    }
}
