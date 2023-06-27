package com.example.iqbarsurfer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class scoreDisplay extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    TextView tv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        btn = findViewById(R.id.btn_logout);
        tv = findViewById(R.id.tv_something);

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

        // Retrieve the score from the intent
        int score = getIntent().getIntExtra("score", 0);

        // Display the score
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + score);

        // Handle restart button click
        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(v -> {
            // Restart the quiz by launching MainActivity again
            Intent intent = new Intent(scoreDisplay.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
