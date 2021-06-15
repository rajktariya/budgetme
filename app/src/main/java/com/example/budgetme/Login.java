package com.example.budgetme;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
        EditText username=findViewById(R.id.userName);
        EditText password=findViewById(R.id.password);
        Button button=findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("rasika")
                        && password.getText().toString().equals("rasika123")){
                    startActivity(new Intent(Login.this,Home.class));
                }
            }
        });

    }
}