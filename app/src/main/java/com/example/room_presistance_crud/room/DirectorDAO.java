package com.example.room_presistance_crud.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static android.icu.text.MessagePattern.ArgType.SELECT;

@Dao
public interface DirectorDAO {


    @Insert
    void insertAll(Director... directors);

    @Query("SELECT * FROM tb_director")
    List<Director> getAllDirectors();

    @Query("SELECT director_name from tb_director where id=:director_id")
    String getDirectorName(int director_id);
}
