package com.example.iqbarsurfer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class loading extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(loading.this,login.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}