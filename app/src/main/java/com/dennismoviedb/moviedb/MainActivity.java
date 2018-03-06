package com.dennismoviedb.moviedb;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends Activity {
    public static final String TAG = MainActivity.class.getSimpleName();

    //This is where the main program runs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Now lets call the method to get the movies
        getMovies();
    }
    //This method will utilizes the MovieService and gets the Api calls
    public void getMovies(){
        final MovieDBService movieDBService = new MovieDBService();
        movieDBService.popularMovies(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().toString();
                Log.v(TAG, jsonData);
            }
        });
    }
}
