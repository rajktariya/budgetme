package com.example.budgetme;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        EditText username = findViewById(R.id.userName);
        EditText password = findViewById(R.id.password);
        Button button = findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidUser(username.getText().toString(), password.getText().toString())) {
                    startActivity(new Intent(Login.this, Home.class));
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "Invalid Username or password", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private boolean isValidUser(String username, String password) {
        if (username.equals("rasika") && password.equals("rasika123")) {
            return true;
        }

        if (username.equals("kalyani") && password.equals("kalyani123")) {
            return true;
        }

        if (username.equals("abc") && password.equals("abc")) {
            return true;
        }

        return false;
    }
}