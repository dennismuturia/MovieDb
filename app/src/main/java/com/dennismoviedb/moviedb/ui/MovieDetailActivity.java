package com.dennismoviedb.moviedb.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dennismoviedb.moviedb.R;
import com.dennismoviedb.moviedb.adapters.MoviePagerAdapter;
import com.dennismoviedb.moviedb.model.Movie;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager)ViewPager myPager;
    private MoviePagerAdapter moviePagerAdapter;
    ArrayList<Movie>mMovies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ButterKnife.bind(this);

        mMovies = Parcels.unwrap(getIntent().getParcelableExtra("movies"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        moviePagerAdapter = new MoviePagerAdapter(getSupportFragmentManager(), mMovies);
        myPager.setAdapter(moviePagerAdapter);
        myPager.setCurrentItem(startingPosition);
    }
}
