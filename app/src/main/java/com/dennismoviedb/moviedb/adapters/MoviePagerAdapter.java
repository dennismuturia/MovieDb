package com.dennismoviedb.moviedb.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dennismoviedb.moviedb.model.DetailMovie;
import com.dennismoviedb.moviedb.model.Movie;
import com.dennismoviedb.moviedb.ui.MovieDetailFragment;

import java.util.ArrayList;

/**
 * Created by dennis muturia on 5/11/18.
 */

public class MoviePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Movie>mMovie;
    private ArrayList<DetailMovie>detailMovies;

    public MoviePagerAdapter(FragmentManager fm, ArrayList<Movie>movie, ArrayList<DetailMovie>dmovies) {
        super(fm);
        mMovie = movie;
        detailMovies = dmovies;
    }

    @Override
    public Fragment getItem(int position) {
        return MovieDetailFragment.newInstance(mMovie.get(position));
    }
    @Override
    public int getCount() {
        return mMovie.size();
    }
    @Override
    public CharSequence getPageTitle(int position){
        return  mMovie.get(position).getMovie_title();
    }
    public int getMovieID(int id){
        return mMovie.get(id).getId();
    }

    //public int postMovieId(int id){return  detailMovies }
}
