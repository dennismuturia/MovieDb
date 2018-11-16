package com.dennismoviedb.moviedb.services;

import android.util.Log;

import com.dennismoviedb.moviedb.Constants;
import com.dennismoviedb.moviedb.model.DetailMovie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieDetailService {
    //The method to build the url for the request
   public static void latestMovie(Callback callback){
       OkHttpClient client = new OkHttpClient.Builder()
               .build();

       //Now lets build the site itself
       HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.baseMovieURL).newBuilder();
       //urlBuilder.addPathSegment();
       urlBuilder.addPathSegment("id");//parse ID of movie
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
   //The method to get the individual data from tghe returned JSON data
   public ArrayList<DetailMovie> processResults(Response response){
       String imageUrl = Constants.imageURL;
       ArrayList<DetailMovie> movies = new ArrayList<>();
       try {
           String jsonData = response.body().string();
           if (response.isSuccessful()){
               JSONObject movieJSON = new JSONObject(jsonData);
               for (int j = 0; j < movieJSON.length(); j++){
                   String movie_title = movieJSON.getString("original_title");
                   String movie_poster = imageUrl + movieJSON.getString("poster_path");
                   String movie_overview = movieJSON.getString("overview");
                   String movie_backdropImage = imageUrl + movieJSON.getString("backdrop_path");
                   //Production companies logo. Need to create an Arraylist to fetch all the data
                   ArrayList<String> production_companies = new ArrayList<>();;
                   JSONArray company_logos = movieJSON.getJSONArray("production_companies");
                   for (int i = 0; i < company_logos.length(); i++){
                       JSONObject the_company = new JSONObject();
                       production_companies.add(the_company.get("logo_path").toString());
                   }
                   String released = movieJSON.getString("status");
                   String tagline = movieJSON.getString("tagline");
                   String voteAverage = movieJSON.getString("vote_averege");
                   //Initiate the movie object
                   DetailMovie movie = new DetailMovie(movie_title, movie_poster, movie_overview, movie_backdropImage,
                           production_companies, released, tagline, voteAverage);

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
