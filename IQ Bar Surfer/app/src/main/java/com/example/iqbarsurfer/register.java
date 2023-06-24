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

public class register extends AppCompatActivity {

    TextInputEditText edittextpassword, edittextemail;
    Button  buttonReg;
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
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        edittextemail = findViewById(R.id.email);
        edittextpassword = findViewById(R.id.password);
        buttonReg = findViewById(R.id.btn_register);
        progressb = findViewById(R.id.R_progressbar);
        textview = findViewById(R.id.tv_clickLogin);
        textview.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), login.class);
            startActivity(intent);
            finish();
        });

        buttonReg.setOnClickListener(v -> {
            progressb.setVisibility(View.VISIBLE);
            String semail, spassword;
            semail = String.valueOf(edittextemail.getText());
            spassword = String.valueOf(edittextpassword.getText());

            if (TextUtils.isEmpty(semail)){
                Toast.makeText(register.this, "Enter Email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(spassword)){
                Toast.makeText(register.this, "Enter Password", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(semail, spassword)
                    .addOnCompleteListener(task -> {
                        progressb.setVisibility(View.GONE);
                        if (task.isSuccessful()) {

                            Toast.makeText(register.this, "Account Created.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), login.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });



    }
}