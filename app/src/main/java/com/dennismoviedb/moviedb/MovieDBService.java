package com.dennismoviedb.moviedb;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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

        //This is the request section
        Request request = new Request.Builder().url(url).build();

        //Lets call this Asynchronously
        Call call = client.newCall(request);
        call.enqueue(callback);

    }
}
