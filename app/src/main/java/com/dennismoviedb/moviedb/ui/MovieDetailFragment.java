package com.dennismoviedb.moviedb.ui;


import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dennismoviedb.moviedb.R;
import com.dennismoviedb.moviedb.model.Movie;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailFragment extends Fragment{
@BindView(R.id.movieImageBackView)ImageView movieBack;
@BindView(R.id.movieImageFrontView)ImageView movieFront;
@BindView(R.id.ratingTextView)TextView ratings;
//@BindView(R.id.genreTextView)TextView genre;
@BindView(R.id.story)TextView movieStory;
//@BindView(R.id.ticketButton)Button ticketButton;

//Declare the new models class
    private Movie mMovie;

    public static MovieDetailFragment newInstance(Movie movie){
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("movie", Parcels.wrap(movie));
        movieDetailFragment.setArguments(args);
        return movieDetailFragment;
    }

    public MovieDetailFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mMovie = Parcels.unwrap(getArguments().getParcelable("movie"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);

        //Using the picasso to load the movie Images
        Picasso.with(view.getContext()).load(mMovie.getMovie_backdropImage()).into(movieBack);
        Picasso.with(view.getContext()).load(mMovie.getMovie_poster()).into(movieFront);

        movieStory.setText(mMovie.getMovie_overview());
        //movieName.setText(mMovie.getMovie_title());
        ratings.setText("IMdb: " + mMovie.getMovie_vote());
        /*
        if (mMovie.isAdult() != false){
            genre.setText("Adult");
        }else {
            genre.setText("Everyone");
        }
        */


        return view;
    }

}
