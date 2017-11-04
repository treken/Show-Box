package com.example.lenovo.showbox.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.showbox.Networking.Movies1;
import com.example.lenovo.showbox.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Lenovo on 04-11-2017.
 */

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.MovieViewHolder> {

    private Context mContext;
    private Movies1.Movies mMovies[];
    private MovieClickListener mListener;

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.row_layout,parent,false);
        return new MovieViewHolder(itemView,mListener);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        Movies1.Movies movie = mMovies[position];
        holder.titleTextView.setText(movie.getTitle());
        holder.dateTextView.setText(movie.getRelease_date());
        if(movie.getAdult()==false){
            holder.adultTextView.setText("U/A");
        }
        else{
            holder.adultTextView.setText("ADULT");
        }
        holder.voteTextView.setText(movie.getVote_average() + "");
        Picasso.with(mContext).load(movie.getPoster()).resize(2300, 1000).centerInside().into(holder.poster);


    }

    @Override
    public int getItemCount() {
        return mMovies.length;
    }

    public interface MovieClickListener {
        void onItemClick(View view,int position);

    }


    public DiscoverAdapter(Context context,Movies1.Movies movies1[],MovieClickListener mListener){
        mContext = context;
        this.mMovies = movies1;
        this.mListener = mListener;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView titleTextView;
        TextView voteTextView;
        TextView adultTextView;
        TextView dateTextView;
        ImageView poster;
        MovieClickListener mMovieClickListener;

        public MovieViewHolder(View itemView,MovieClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            mMovieClickListener = listener;
            titleTextView = (TextView) itemView.findViewById(R.id.title);
            voteTextView = (TextView) itemView.findViewById(R.id.vote);
            adultTextView = (TextView) itemView.findViewById(R.id.adult);
            dateTextView = (TextView) itemView.findViewById(R.id.date);
            poster = (ImageView) itemView.findViewById(R.id.poster);
        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                mMovieClickListener.onItemClick(view,position);

            }

        }
    }
}
