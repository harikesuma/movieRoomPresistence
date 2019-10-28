package com.example.room_presistance_crud.room;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "tb_movie", foreignKeys = {@ForeignKey(
        entity = Director.class,
        parentColumns = "id",
        childColumns = "director_id",
        onDelete = CASCADE)})

public class Movie {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    int id;

    @ColumnInfo(name = "movie_name")
    String movie_name;


    @ColumnInfo(name = "movie_genre")
    String movie_genre;

    @Nullable
    @ColumnInfo(name = "director_id", index = true)
    int director_id;


    @ColumnInfo(name = "time_created")
    String time_stamp;

    public Movie(int id, String movie_name, String movie_genre, int director_id, String time_stamp) {
        this.id = id;
        this.movie_name = movie_name;
        this.movie_genre = movie_genre;
        this.director_id = director_id;
        this.time_stamp = time_stamp;
    }

    @Ignore
    public Movie(String movie_name, String movie_genre, int director_id, String time_stamp) {
        this.movie_name = movie_name;
        this.movie_genre = movie_genre;
        this.director_id = director_id;
        this.time_stamp = time_stamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMovie_genre() {
        return movie_genre;
    }

    public void setMovie_genre(String movie_genre) {
        this.movie_genre = movie_genre;
    }

    public int getDirector_id() {
        return director_id;
    }

    public void setDirector_id(int director_id) {
        this.director_id = director_id;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }
}