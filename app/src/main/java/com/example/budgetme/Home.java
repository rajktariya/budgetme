package com.example.budgetme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);

        Button addexpencebutton=findViewById(R.id.addexpense);
        addexpencebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(Home.this,Addexpence.class));
            }
        });
 Button addbudgetbutton=findViewById(R.id.addbudget);
        addbudgetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(Home.this,Addbudget.class));
            }
        });
 Button deleteexpencebutton=findViewById(R.id.deleteexpense);
        deleteexpencebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(Home.this,Deleteexpence.class));
            }
        });
 Button categoriesbutton=findViewById(R.id.categories);
        categoriesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(Home.this,Categories.class));
            }
        });
        Button staticsbutton=findViewById(R.id.statics);
        staticsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(Home.this,Statics.class));
            }
        });
        Button tasktodobutton=findViewById(R.id.taskstodo);
        tasktodobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(Home.this,Taskstodo.class));
                    finish();
            }
        });



    }
}