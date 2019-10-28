package com.example.room_presistance_crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.room_presistance_crud.adapter.DirectorAdapter;
import com.example.room_presistance_crud.room.Director;
import com.example.room_presistance_crud.room.EditMovieViewModel;
import com.example.room_presistance_crud.room.Movie;
import com.example.room_presistance_crud.room.MovieRoomDatabase;
import com.example.room_presistance_crud.room.MovieViewModel;

import java.util.List;

public class FormEditActivity extends AppCompatActivity {
    public static final String MOVIE_ID = "TES";
    private EditMovieViewModel movieViewModel;
    public Button update_btn;
    public EditText movieName_et, movieGenere_et;
    public Bundle bundle;
    public int movie_id;
    public LiveData<Movie> movie;
    public int directorId = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_edit);

        List<Director> languages = MovieRoomDatabase.getInstance(this).directorDAO().getAllDirectors();

        Spinner spinner = findViewById(R.id.spinnerName);
        // Pass your list as the third parameter. No need to convert it to List<String>
        DirectorAdapter myAdapter = new DirectorAdapter(this, languages);
        spinner.setAdapter(myAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Director director = (Director) parent.getItemAtPosition(position);
                String directorName = director.getDirector_name();
                directorId = director.getId();
                String directorGender = director.getGender();

                Toast.makeText(FormEditActivity.this, "id adalah " + directorId, Toast.LENGTH_LONG).show();
                Toast.makeText(FormEditActivity.this, "nama adalah " + directorName, Toast.LENGTH_LONG).show();
                Toast.makeText(FormEditActivity.this, "gender adalah " + directorGender, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
                movieName_et.setText(movie.getMovie_name());
                movieGenere_et.setText(movie.getMovie_genre());

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
                resultIntent.putExtra("directorId", directorId);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
