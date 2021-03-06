package com.dennismoviedb.moviedb.services;

import android.util.Log;

import com.dennismoviedb.moviedb.Constants;
import com.dennismoviedb.moviedb.model.Movie;

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

public class MovieDBService {
    public static void popularMovies(okhttp3.Callback callback){
        //This okhttpclient is just used to build the urls to get the api's
        Movie movie = new Movie();//This is for getting the page number
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        //Now lets build the site itself
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.baseMovieURL).newBuilder();
        urlBuilder.addPathSegment("popular");
        urlBuilder.addQueryParameter(Constants.Api, Constants.movieApi);
        String url = urlBuilder.build().toString();

        Log.d("url",url);

        //This is the request section
        Request request = new Request.Builder()
                .url(url)
                .build();

        //Lets call this Asynchronously
        Call call = client.newCall(request);
        call.enqueue(callback);

    }
    //Now lets create a method to fetch the data from the JSON file
    public ArrayList<Movie> processResults(Response response){
        String imageUrl = Constants.imageURL;
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()){
                JSONObject movieJSON = new JSONObject(jsonData);
                String pageNumber = movieJSON.getString("page");
                JSONArray resultsJSON = movieJSON.getJSONArray("results");
                for (int i = 0; i < resultsJSON.length(); i++){
                    JSONObject theMoviesJson = resultsJSON.getJSONObject(i);
                    int id = theMoviesJson.getInt("id");
                    String movie_title = theMoviesJson.getString("title");
                    String movie_poster = imageUrl + theMoviesJson.getString("poster_path");
                    double movie_vote = theMoviesJson.getDouble("vote_average");
                    //Get the genres
                    ArrayList<Integer>theGenres = new ArrayList<>();
                    JSONArray genreArray = theMoviesJson.getJSONArray("genre_ids");
                    for(int j = 0; j < genreArray.length(); j++){
                        theGenres.add(genreArray.getInt(j));
                    }
                    String movie_backdropImage = imageUrl + theMoviesJson.getString("backdrop_path");
                    String movie_overview = theMoviesJson.getString("overview");
                    boolean adults = theMoviesJson.getBoolean("adult");
                    //Initiate the movie object
                    Movie movie = new Movie(movie_title, movie_poster,movie_vote,
                            movie_backdropImage,movie_overview, adults, pageNumber, id, theGenres);

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
