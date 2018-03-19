package com.dennismoviedb.moviedb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dennis on 3/6/18.
 */

public class MovieDBService {
    public static void popularMovies(okhttp3.Callback callback){
        //This okhttpclient is just used to build the urls to get the api's
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        //Now lets build the site itself
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.baseMovieURL).newBuilder();
        urlBuilder.addQueryParameter(Constants.Api, Constants.movieApi);
        String url = urlBuilder.build().toString();

        //Log.d("url",url);

        //This is the request section
        Request request = new Request.Builder()
                .url(url)
                .build();

        //Lets call this Asynchronously
        Call call = client.newCall(request);
        call.enqueue(callback);

    }
    //Now lets get the latest movie
    public ArrayList<NowShowingMovie> latestMovieProcess(Response response){
        ArrayList<NowShowingMovie> latestMovie = new ArrayList<>();
        try{
            String latestData = response.body().string();
            if (response.isSuccessful()){
                JSONObject myObjectNow = new JSONObject(latestData);
                String movie_title = myObjectNow.getString("title");
                String movie_poster = myObjectNow.getString("poster_path");


                NowShowingMovie nowShowingMovie = new NowShowingMovie(movie_title, movie_poster);

                latestMovie.add(nowShowingMovie);
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return latestMovie;
    }
    //Now lets create a method to fetch the data from the JSON file
    public ArrayList<Movie> processResults(Response response){
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()){
                JSONObject movieJSON = new JSONObject(jsonData);
                JSONArray resultsJSON = movieJSON.getJSONArray("results");
                for (int i = 0; i < resultsJSON.length(); i++){
                    JSONObject theMoviesJson = resultsJSON.getJSONObject(i);
                    String movie_title = theMoviesJson.getString("title");
                    String movie_poster = theMoviesJson.getString("poster_path");
                    int movie_vote = theMoviesJson.getInt("vote_average");
                    String movie_backdropImage = theMoviesJson.getString("backdrop_path");
                    String movie_overview = theMoviesJson.getString("overview");

                    //Initiate the movie object
                    Movie movie = new Movie(movie_title, movie_poster,movie_vote,
                            movie_backdropImage,movie_overview);

                    //Add them to the model
                    movies.add(movie);
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return movies;
    }
}
