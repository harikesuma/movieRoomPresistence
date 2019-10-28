package com.example.room_presistance_crud;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.room_presistance_crud.adapter.MovieAdapter;
import com.example.room_presistance_crud.room.EditMovieViewModel;
import com.example.room_presistance_crud.room.Movie;
import com.example.room_presistance_crud.room.MovieViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.onDeleteListener {
    private static final int NEW_MOVIE_ACTIVITY_REQUEST_CODE = 1 ;
    public static final int EDIT_MOVIE_ACTIVITY_REQUEST_CODE = 2 ;
    private MovieViewModel movieViewModel;
    private MovieAdapter movieAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        movieAdapter = new MovieAdapter(this,this);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormAddActivity.class);
                startActivityForResult(intent, NEW_MOVIE_ACTIVITY_REQUEST_CODE);
            }
        });

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieAdapter.setMovies(movies);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == NEW_MOVIE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            int directorId = extras.getInt("directorId");

            Movie movie = new Movie(data.getStringExtra("name"),data.getStringExtra("genre"),directorId,getDateTime());
            movieViewModel.insert(movie);
            Toast.makeText(getApplicationContext(), "Movie Added", Toast.LENGTH_SHORT).show();
        }
        else if(requestCode == EDIT_MOVIE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            int sss =  extras.getInt("movie");
            int directorId = extras.getInt("directorId");

            Movie movie = new Movie(sss, data.getStringExtra("name"),data.getStringExtra("genre"),directorId,getDateTime());
            movieViewModel.update(movie);
            Toast.makeText(getApplicationContext(), "Movie Updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Movie not added", Toast.LENGTH_SHORT).show();
        }
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd hh:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Override
    public void onDeleteListener(final Movie movie) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Delete Dialog");
        builder1.setMessage("Are you sure want to delete this movie?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        movieViewModel.delete(movie);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
