package com.dennismoviedb.moviedb.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.dennismoviedb.moviedb.R;
import com.dennismoviedb.moviedb.adapters.MovieListAdapter;
import com.dennismoviedb.moviedb.model.Genre;
import com.dennismoviedb.moviedb.model.Movie;
import com.dennismoviedb.moviedb.services.Genres;
import com.dennismoviedb.moviedb.services.MovieDBService;


import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
//Facebook imports
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class MainActivity extends Activity{



    public static final String TAG = MainActivity.class.getSimpleName();// This is useful for logging
    @BindView(R.id.recyclerView)RecyclerView mRecyclerView;

    private  MovieListAdapter mAdapter;
    //This will be used get the data gathered from the arrayList
    public ArrayList<Movie> myMovies = new ArrayList<>();
    public ArrayList<Genre>myGenresDetail = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;
    private ActionBar toolbar;
    /*
    *We will be using this functionality later in the course
    *  public ArrayList<DetailMovie> latestMovie = new ArrayList<>();
     */
    //This is where the main program runs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting the navigationbar
        //Now lets call the method to get the movies
        ButterKnife.bind(this);
        getMovies();
        getGenres();

        /*
        *Deal with the Deprication of the Facebook application. Will update this soon
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        */
    }
    /*
    public void logSentFriendRequestEvent () {
        AppEventsLogger logger = AppEventsLogger.newLogger(this);
        logger.logEvent("sentFriendRequest");
    }
    */
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

                if (response.isSuccessful()){
                    myMovies = movieDBService.processResults(response);

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter = new MovieListAdapter(getApplicationContext(), myMovies);
                            mRecyclerView.setAdapter(mAdapter);
                            gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
                            mRecyclerView.setLayoutManager(gridLayoutManager);
                            mRecyclerView.hasFixedSize();
                        }
                    });
                }
            }
        });
    }

    //Throw this to the fragment
    public void getGenres(){
        final Genres myGenres = new Genres();
        myGenres.getGenres(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //On a scenario of a failure of the response what to happen.
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    myGenresDetail = myGenres.processGenres(response);
                }
            }
        });
    }

}

