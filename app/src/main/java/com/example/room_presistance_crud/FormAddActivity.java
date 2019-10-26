package com.example.room_presistance_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormAddActivity extends AppCompatActivity {
    public Button submit_btn;
    public EditText movieName_et, movieGenere_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add);

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
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }


            }
        });

    }
}
