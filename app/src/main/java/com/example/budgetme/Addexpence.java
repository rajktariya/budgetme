package com.example.budgetme;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Addexpence extends AppCompatActivity {
    String[] courses = { "Food", "Data structures",
            "Interview prep", "Algorithms",
            "DSA with java", "OS" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.addexpence);
        final Calendar myCalendar = Calendar.getInstance();
        TextView pickdate =findViewById(R.id.pickthedate);
        Spinner categoryList =findViewById(R.id.categorylist);


        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,
                courses );
        categoryList.setAdapter(adapter);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                pickdate.setTextColor(Color.BLACK);
                pickdate.setText(sdf.format(myCalendar.getTime()));
            }

        };
        pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Addexpence.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
             }
        });



    }
}