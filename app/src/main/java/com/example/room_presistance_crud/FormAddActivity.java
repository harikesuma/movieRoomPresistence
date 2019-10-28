package com.example.room_presistance_crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;

import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.room_presistance_crud.adapter.DirectorAdapter;
import com.example.room_presistance_crud.room.Director;
import com.example.room_presistance_crud.room.Movie;
import com.example.room_presistance_crud.room.MovieRoomDatabase;

import java.util.ArrayList;
import java.util.List;

public class FormAddActivity extends AppCompatActivity {
    public Button submit_btn;
    public EditText movieName_et, movieGenere_et;
    public int directorId = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add);

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

               Toast.makeText(FormAddActivity.this, "id adalah " + directorId, Toast.LENGTH_LONG).show();
               Toast.makeText(FormAddActivity.this, "nama adalah " + directorName, Toast.LENGTH_LONG).show();
               Toast.makeText(FormAddActivity.this, "gender adalah " + directorGender, Toast.LENGTH_LONG).show();
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });



        movieName_et = findViewById(R.id.movieName);
        movieGenere_et = findViewById(R.id.movieGenre);
        submit_btn   = findViewById(R.id.btnSubmit);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                if(TextUtils.isEmpty(movieName_et.getText()) || TextUtils.isEmpty(movieGenere_et.getText())){
                    Toast.makeText(getApplicationContext(), "Name or genre cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    String name = movieName_et.getText().toString();
                    String genre = movieGenere_et.getText().toString();
                    resultIntent.putExtra("name", name);
                    resultIntent.putExtra("genre", genre);
                    resultIntent.putExtra("directorId", directorId);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }


            }
        });

    }



}
