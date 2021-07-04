package com.example.budgetme;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.example.budgetme.dbHandler.AMOUNT;
import static com.example.budgetme.dbHandler.BUDGET_TABLE;
import static com.example.budgetme.dbHandler.MONTH;
import static com.example.budgetme.dbHandler.YEAR;

public class Addbudget extends AppCompatActivity {
    final Calendar myCalendar = Calendar.getInstance();
    dbHandler mydb;
    int month, year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.addbudget);
        mydb = new dbHandler(this);
        Button selectmonth = findViewById(R.id.selectmonth);
        EditText editTextBudget = findViewById(R.id.editTextNumber);
        Button setbudget = findViewById(R.id.setbudget);
        TextView selectedbudget = findViewById(R.id.selectedbudget);

        setbudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextBudget.getText().toString().length()>0){
                    if(selectmonth.getText().toString().length()>0){
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(AMOUNT,editTextBudget.getText().toString());
                        contentValues.put(MONTH,month);
                        contentValues.put(YEAR,year);
                                mydb.insertBudget(BUDGET_TABLE,contentValues);
                        selectedbudget.setVisibility(View.VISIBLE);
                        selectedbudget.setText("Selected months budget : "+editTextBudget.getText().toString());
                        Toast.makeText(Addbudget.this,"Budget set for the selected month",Toast.LENGTH_LONG).show();
                        editTextBudget.setText("");
                    }else{
                        Toast.makeText(Addbudget.this,"Enter valid month",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(Addbudget.this,"Enter valid amount",Toast.LENGTH_LONG).show();
                }
            }
        });
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM"; //In which you need put here
                String myFormatyy = "yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                SimpleDateFormat sdfyy = new SimpleDateFormat(myFormatyy, Locale.US);
                selectmonth.setText(sdf.format(myCalendar.getTime())+"/"+sdfyy.format(myCalendar.getTime()));
                month  = Integer.parseInt(sdf.format(myCalendar.getTime()));
                year  = Integer.parseInt(sdfyy.format(myCalendar.getTime()));
            }

        };
        selectmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Addbudget.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
}

