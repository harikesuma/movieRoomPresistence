package com.example.room_presistance_crud.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieRoomDatabase extends RoomDatabase {
    public abstract MovieDAO movieDAO();

    private static volatile MovieRoomDatabase movieRoomInstance;

    static MovieRoomDatabase getDatabase(final Context context){
        if (movieRoomInstance == null){
            synchronized (MovieRoomDatabase.class){
                if(movieRoomInstance == null){
                    movieRoomInstance = Room.databaseBuilder(context.getApplicationContext(),
                            MovieRoomDatabase.class,"db_movie").build();
                }
            }
        }
        return movieRoomInstance;
    }
}
