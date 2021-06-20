package com.example.budgetme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_screen);

        Button addexpense  = findViewById(R.id.addexpense);

        addexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Addexpense.class));
                Toast.makeText(Home.this,"Add expnse button fdgsg",Toast.LENGTH_LONG).show();
            }
        });

    }
}