package com.dennismoviedb.moviedb.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dennismoviedb.moviedb.model.Movie;
import com.dennismoviedb.moviedb.ui.MovieDetailFragment;

import java.util.ArrayList;

/**
 * Created by dennis on 5/11/18.
 */

public class MoviePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Movie>mMovie;

    public MoviePagerAdapter(FragmentManager fm, ArrayList<Movie>movie) {
        super(fm);
        mMovie = movie;
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
}
