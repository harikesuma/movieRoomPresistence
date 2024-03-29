package com.example.room_presistance_crud.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Movie movie);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Movie... movies);

    @Query("SELECT * FROM tb_movie")
    LiveData<List<Movie>> getAllMovies();

    @Query("SELECT * FROM tb_movie where id =:movie_id")
    LiveData<Movie> getMovie(int movie_id);

    @Update
    void update(Movie movie);

    @Delete
    int delete(Movie movie);


}
