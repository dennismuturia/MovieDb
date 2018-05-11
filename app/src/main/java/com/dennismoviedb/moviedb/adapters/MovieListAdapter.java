package com.dennismoviedb.moviedb.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dennismoviedb.moviedb.R;
import com.dennismoviedb.moviedb.model.Movie;
import com.dennismoviedb.moviedb.ui.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dennis on 3/20/18.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private ArrayList<Movie> mMovie = new ArrayList<>();
    private Context mContext;
    private static final int MAX_HEIGHT = 400;
    private static final int MAX_WIDTH = 300;

    public MovieListAdapter(Context context, ArrayList<Movie> movies){
        mContext = context;
        mMovie = movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bindMovie(mMovie.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovie.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.myImage) ImageView PosterImage;
        @BindView(R.id.mainText) TextView Title;
        //@BindView(R.id.overview)TextView Overview;

        private Context mContext;

        public MovieViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindMovie(Movie movie){
            Title.setText(movie.getMovie_title());
            //Overview.setText(movie.getMovie_overview());
            Picasso.with(mContext).load(movie.getMovie_poster()).resize(MAX_WIDTH,MAX_HEIGHT).centerCrop().into(PosterImage);
        }

        @Override
        public void onClick(View view) {
            int itemPOsition = getLayoutPosition();
            Intent intent = new Intent(mContext, MovieDetailActivity.class);
            intent.putExtra("position",itemPOsition);
            intent.putExtra("movies", Parcels.wrap(mMovie));
            mContext.startActivity(intent);
        }
    }
}
