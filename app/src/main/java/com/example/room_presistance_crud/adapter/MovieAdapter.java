package com.example.room_presistance_crud.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room_presistance_crud.FormEditActivity;
import com.example.room_presistance_crud.MainActivity;
import com.example.room_presistance_crud.R;
import com.example.room_presistance_crud.room.Movie;
import com.example.room_presistance_crud.room.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;
    private List<Movie> movieList;
    private MovieViewModel movieViewModel;
    private onDeleteListener onDeleteListener;

    public interface onDeleteListener{
        void onDeleteListener(Movie movie);
    }

    public MovieAdapter(Context context, onDeleteListener listener){
        this.context = context;
        this.onDeleteListener = listener;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.list_row, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(itemView);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, final int position) {
        if(movieList != null){
            holder.movieName_tv.setText(movieList.get(position).getMovieName());
            holder.movieGenre_tv.setText(movieList.get(position).getMovieGenre());
            holder.timeStamp_tv.setText(movieList.get(position).getTimeStamp());
            holder.edit_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FormEditActivity.class);
                    intent.putExtra("movie_id",  movieList.get(position).getId());
                    ((Activity)context).startActivityForResult(intent, MainActivity.EDIT_MOVIE_ACTIVITY_REQUEST_CODE);
                }
            });

            holder.delete_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteListener.onDeleteListener(movieList.get(position));
                }
            });
        }
        else if(movieList == null){
            holder.movieName_tv.setText(R.string.no_data);
        }

    }

    @Override
    public int getItemCount() {
        if (movieList != null){
            return movieList.size();
        }
        else{
            return 0;
        }
    }

    public void setMovies(List<Movie> movies){
        movieList = movies;
        notifyDataSetChanged();
    }



    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView movieName_tv;
        TextView movieGenre_tv;
        TextView timeStamp_tv;
        ImageView edit_iv;
        ImageView delete_iv;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieName_tv    = itemView.findViewById(R.id.movieName);
            movieGenre_tv   = itemView.findViewById(R.id.movieGenre);
            timeStamp_tv    = itemView.findViewById(R.id.timestamp);
            edit_iv         = itemView.findViewById(R.id.edit);
            delete_iv       = itemView.findViewById(R.id.delete);
        }
    }
}
