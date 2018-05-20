package com.dennismoviedb.moviedb.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.dennismoviedb.moviedb.R;
import com.dennismoviedb.moviedb.adapters.MovieListAdapter;
import com.dennismoviedb.moviedb.model.Movie;
import com.dennismoviedb.moviedb.model.NowShowingMovie;
import com.dennismoviedb.moviedb.services.MovieDBService;


import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends Activity{



    public static final String TAG = MainActivity.class.getSimpleName();// This is useful for logging
    @BindView(R.id.recyclerView)RecyclerView mRecyclerView;

    private  MovieListAdapter mAdapter;
    //This will be used get the data gathered from the arrayList
    public ArrayList<Movie> myMovies = new ArrayList<>();
    /*
    *We will be using this functionality later in the course
    *  public ArrayList<NowShowingMovie> latestMovie = new ArrayList<>();
     */
    //This is where the main program runs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Now lets call the method to get the movies
        ButterKnife.bind(this);
        getMovies();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.searchmenu, menu);
        getMenuInflater().inflate(R.menu.searchmenu, menu);
        MenuItem menuItem = menu.findItem(R.id.searchMovieandTV);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String User = query;
                Intent intent = new Intent(MainActivity.this, ListOfResults.class);
                intent.putExtra("User", User);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return  super.onCreateOptionsMenu(menu);
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

                if (response.isSuccessful()){
                    myMovies = movieDBService.processResults(response);

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter = new MovieListAdapter(getApplicationContext(), myMovies);
                            mRecyclerView.setAdapter(mAdapter);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.hasFixedSize();
                        }
                    });
                }
            }
        });
    }
}
