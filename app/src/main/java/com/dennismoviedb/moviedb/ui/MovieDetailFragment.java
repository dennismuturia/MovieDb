package com.dennismoviedb.moviedb.ui;


import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dennismoviedb.moviedb.R;
import com.dennismoviedb.moviedb.model.Genre;
import com.dennismoviedb.moviedb.model.Movie;
import com.dennismoviedb.moviedb.services.Genres;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MovieDetailFragment extends Fragment{
    public static final String TAG = MainActivity.class.getSimpleName();// This is useful for logging
@BindView(R.id.movieImageBackView)ImageView movieBack;
@BindView(R.id.movieImageFrontView)ImageView movieFront;
@BindView(R.id.ratingTextView)TextView ratings;
@BindView(R.id.genresLayout)RelativeLayout genreLay;
//@BindView(R.id.genreTextView)TextView genre;
@BindView(R.id.story)TextView movieStory;
//@BindView(R.id.ticketButton)Button ticketButton;

//Declare the new models class
    private Movie mMovie;
    private ArrayList<Genre>theGenre = new ArrayList<>();

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
        getGenres();
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

        for (int i = 0; i < mMovie.getGenres().size(); i++){
            //TextView tx1 = new TextView()
        }
        /*
        if (mMovie.isAdult() != false){
            genre.setText("Adult");
        }else {
            genre.setText("Everyone");
        }
        */


        return view;
    }

    public void getGenres(){
        final Genres myGenres = new Genres();
        myGenres.getGenres(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    //Need to store the list of genres in an arraylist
                    //Loop through the Genres in the movies
                    if(response.isSuccessful()){
                        theGenre = myGenres.processGenres(response);
                        for (int i=0; i < mMovie.getGenres().size(); i++){
                            Log.d(TAG, "onResponse: ", theGenre.get(i));
                        }
                    }

                }
            }
        });
    }
}
