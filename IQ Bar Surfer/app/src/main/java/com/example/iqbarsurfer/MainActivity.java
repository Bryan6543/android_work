package com.example.iqbarsurfer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    TextView tv;
    Button btn;
    ImageButton btn2;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        btn = findViewById(R.id.btn_logout);
        tv = findViewById(R.id.tv_something);
        btn2 = findViewById(R.id.btn_takeQuest);
        user = auth.getCurrentUser();

        btn2.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), quiz.class);
            startActivity(intent);
            finish();
        });


        if (user == null){
            Intent intent = new Intent(getApplicationContext(), login.class);
            startActivity(intent);
            finish();
        }
        else {
            tv.setText(user.getEmail());
        }

        btn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), login.class);
            startActivity(intent);
            finish();
        });
    }
}