package com.example.room_presistance_crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.room_presistance_crud.room.EditMovieViewModel;
import com.example.room_presistance_crud.room.Movie;
import com.example.room_presistance_crud.room.MovieViewModel;

public class FormEditActivity extends AppCompatActivity {
    public static final String MOVIE_ID = "TES";
    private EditMovieViewModel movieViewModel;
    public Button update_btn;
    public EditText movieName_et, movieGenere_et;
    public Bundle bundle;
    public int movie_id;
    public LiveData<Movie> movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_edit);

        movieName_et = findViewById(R.id.movieName);
        movieGenere_et = findViewById(R.id.movieGenre);
        update_btn = findViewById(R.id.btnUpdate);
        bundle = getIntent().getExtras();

        if(bundle != null){
            movie_id = bundle.getInt("movie_id");
        }


        movieViewModel = ViewModelProviders.of(this).get(EditMovieViewModel.class);
        movie = movieViewModel.getMovie(movie_id);
        movie.observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                movieName_et.setText(movie.getMovieName());
                movieGenere_et.setText(movie.getMovieGenre());
            }
        });

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = movieName_et.getText().toString();
                String genre = movieGenere_et.getText().toString();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("movie", movie_id);
                resultIntent.putExtra("name", name);
                resultIntent.putExtra("genre", genre);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
