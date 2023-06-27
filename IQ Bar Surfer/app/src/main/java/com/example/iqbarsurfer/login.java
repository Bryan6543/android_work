package com.example.iqbarsurfer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    TextInputEditText edittextpassword, edittextemail;
    Button buttonLog;
    FirebaseAuth mAuth;
    ProgressBar progressb;
    TextView textview;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        edittextemail = findViewById(R.id.email);
        edittextpassword = findViewById(R.id.password);
        buttonLog = findViewById(R.id.btn_login);
        progressb = findViewById(R.id.Login_progressbar);
        textview = findViewById(R.id.tv_clickregister);
        textview.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), register.class);
            startActivity(intent);
            finish();
        });

        buttonLog.setOnClickListener(v -> {
            progressb.setVisibility(View.VISIBLE);
            String semail, spassword;
            semail = String.valueOf(edittextemail.getText());
            spassword = String.valueOf(edittextpassword.getText());


            if (TextUtils.isEmpty(semail)){
                Toast.makeText(login.this, "Enter Email", Toast.LENGTH_SHORT).show();
                progressb.setVisibility(View.GONE);
                return;
            }

            if (TextUtils.isEmpty(spassword)){
                Toast.makeText(login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                progressb.setVisibility(View.GONE);
                return;
            }

            mAuth.signInWithEmailAndPassword(semail, spassword)
                    .addOnCompleteListener(task -> {
                        progressb.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    });
        });
    }
}