package com.example.room_presistance_crud.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_movie")
public class Movie {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "name")
    private String movieName;

    @ColumnInfo(name = "genre")
    private String movieGenre;

    @ColumnInfo(name = "timestamp")
    private String timeStamp;

    @Ignore
    public Movie(int id, String movieName, String movieGenre, String timeStamp) {
        this.id = id;
        this.movieName = movieName;
        this.movieGenre = movieGenre;
        this.timeStamp = timeStamp;
    }

    public Movie(String movieName, String movieGenre, String timeStamp) {
        this.movieName = movieName;
        this.movieGenre = movieGenre;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
