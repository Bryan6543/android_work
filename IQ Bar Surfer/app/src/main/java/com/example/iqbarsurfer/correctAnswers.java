package com.example.iqbarsurfer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class correctAnswers extends AppCompatActivity {


    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct_answers);

        btn = findViewById(R.id.back);

        btn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), scoreDisplay.class);
            startActivity(intent);
            finish();
        });
    }
}