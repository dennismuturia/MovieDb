package com.dennismoviedb.moviedb.services;

import android.util.Log;

import com.dennismoviedb.moviedb.Constants;
import com.dennismoviedb.moviedb.model.Genre;

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

public class Genres {
    //First build the url
    public static void getGenres(okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder builder = HttpUrl.parse(Constants.genres).newBuilder();
        builder.addQueryParameter(Constants.Api, Constants.movieApi);
        String url = builder.build().toString();

        Log.d("url",url);


        //This is the request section
        Request request = new Request.Builder()
                .url(url)
                .build();

        //Lets call this Asynchronously
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    //Now process the results
    //This is just a bad implementation of the code. Avoid nesting for loops like this. It will result to the application being slow
    public ArrayList<Genre> processGenres(Response response){
        ArrayList<Genre> movies = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()){
                JSONObject theObject = new JSONObject(jsonData);
                JSONArray genreArray = theObject.getJSONArray("genres");
                for (int i = 0; i < genreArray.length(); i++){
                    JSONObject theMoviesJson = genreArray.getJSONObject(i);
                    for (int j=0; j < theMoviesJson.length(); j++){
                        int id = theMoviesJson.getInt("id");
                        String name = theMoviesJson.getString("name");
                        //Store the values gotten to the model
                        Genre genre = new Genre(id, name);
                        movies.add(genre);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }

}
