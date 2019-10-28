package com.example.room_presistance_crud.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_director")
public class Director {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    int id;

    @ColumnInfo(name = "director_name")
    String director_name;

    @ColumnInfo(name = "gender")
    String gender;


    public Director( String director_name, String gender) {
        this.director_name = director_name;
        this.gender = gender;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDirector_name() {
        return director_name;
    }

    public void setDirector_name(String director_name) {
        this.director_name = director_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gendrer) {
        this.gender = gendrer;
    }


    public static Director[] populateData() {
        return new Director[] {
                new Director("Nono", "Male"),
                new Director("Michael", "Male"),
                new Director("Nini", "Female"),
                new Director("Nane", "Male"),
                new Director("Noni", "Male"),
                new Director("Nini", "Female"),
                new Director("Nane", "Male"),
                new Director("Noni", "Male")
        };
    }
}
