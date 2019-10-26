package com.example.room_presistance_crud.room;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class EditMovieViewModel extends AndroidViewModel {
    private String TAG =  this.getClass().getSimpleName();
    private MovieDAO movieDAO;
    private MovieRoomDatabase movieRoomDatabase;

    public EditMovieViewModel(@NonNull Application application) {
        super(application);
        Log.i(TAG, "Edit ViewModel");
        movieRoomDatabase = MovieRoomDatabase.getDatabase(application);
        movieDAO = movieRoomDatabase.movieDAO();
    }

    public LiveData<Movie> getMovie(int movie_id){
        return  movieDAO.getMovie(movie_id);
    }
}
