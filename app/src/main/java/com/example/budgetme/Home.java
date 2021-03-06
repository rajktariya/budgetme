package com.example.budgetme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.budgetme.addexpense.AddexpenseActivity;

public class Home extends android.app.Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);

        Button addexpencebutton = findViewById(R.id.addexpense);
        addexpencebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, AddexpenseActivity.class));
            }
        });
        Button addbudgetbutton = findViewById(R.id.addbudget);
        addbudgetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Addbudget.class));
            }
        });
        Button deleteexpencebutton = findViewById(R.id.deleteexpense);
        deleteexpencebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Deleteexpence.class));
            }
        });
        Button categoriesbutton = findViewById(R.id.categories);
        categoriesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, CategoryActivity.class));
            }
        });
        Button staticsbutton = findViewById(R.id.statics);
        staticsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Statics.class));
            }
        });
        Button tasktodobutton = findViewById(R.id.taskstodo);
        tasktodobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Taskstodo.class));
            }
        });


    }
}