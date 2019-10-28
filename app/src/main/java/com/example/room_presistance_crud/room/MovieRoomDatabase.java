package com.example.room_presistance_crud.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = {Movie.class, Director.class}, version = 1)
public abstract class MovieRoomDatabase extends RoomDatabase {
    public abstract MovieDAO movieDAO();
    public abstract DirectorDAO directorDAO();

    private static MovieRoomDatabase INSTANCE;

    public synchronized static MovieRoomDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    private static MovieRoomDatabase  buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                MovieRoomDatabase .class,
                "db_cinema")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance(context).directorDAO().insertAll(Director.populateData());
                            }
                        });
                    }
                })
                .allowMainThreadQueries()
                .build();
    }


}
