package com.example.room_presistance_crud.room;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private String TAG =  this.getClass().getSimpleName();
    private MovieDAO movieDAO;
    private MovieRoomDatabase movieRoomDatabase;
    private LiveData<List<Movie>> movieList;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRoomDatabase = MovieRoomDatabase.getInstance(application);
        movieDAO = movieRoomDatabase.movieDAO();
        movieList = movieDAO.getAllMovies();
    }

    public void insert(Movie movie){
        new InsertAsyncTask(movieDAO).execute(movie);
    }

    public void update(Movie movie){

        new UpdateAsyncTask(movieDAO).execute(movie);
    }

    public void delete(Movie movie){
        new DeleteAsyncTask(movieDAO).execute(movie);}

    public LiveData<List<Movie>> getAllMovies(){
        return movieList;
    }




    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG,"ViewModel Destroy");
    }

    class InsertAsyncTask extends AsyncTask<Movie, Void, Long> {
        MovieDAO mMovieDao;

        public InsertAsyncTask(MovieDAO mMovieDao) {

            this.mMovieDao = mMovieDao;
        }

        @Override
        protected Long doInBackground(Movie... movies) {
            Log.i("INFO","Proses Insert sedang dilakukan....");
            mMovieDao.insert(movies);
            return null;
        }
    }

    private class UpdateAsyncTask extends AsyncTask<Movie, Void, Void>  {
        MovieDAO mMovieDao;

        public UpdateAsyncTask(MovieDAO movieDAO) {
            this.mMovieDao = movieDAO;
        }

            @Override
            protected Void doInBackground(Movie... movies) {
                mMovieDao.update(movies[0]);
                return null;
        }
    }

    private class DeleteAsyncTask extends AsyncTask<Movie, Void, Void> {
        MovieDAO mMovieDao;
        public DeleteAsyncTask(MovieDAO movieDAO) {
            this.mMovieDao = movieDAO;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            mMovieDao.delete(movies[0]);
            return null;
        }
    }
}
