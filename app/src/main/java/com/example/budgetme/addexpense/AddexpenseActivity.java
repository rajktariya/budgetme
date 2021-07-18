package com.example.budgetme.addexpense;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.budgetme.R;
import com.example.budgetme.dbHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.example.budgetme.dbHandler.AMOUNT;
import static com.example.budgetme.dbHandler.DATE;
import static com.example.budgetme.dbHandler.DAY;
import static com.example.budgetme.dbHandler.DESCRIPTION;
import static com.example.budgetme.dbHandler.EXPENSE_TABLE;
import static com.example.budgetme.dbHandler.MONTH;
import static com.example.budgetme.dbHandler.YEAR;

public class AddexpenseActivity extends Activity {
    String[] categoryListStrings ;
    dbHandler mydb;
    int categorySelectedPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.addexpense);
        mydb = new dbHandler(this);
        categoryListStrings = mydb.getAllCategories().toArray(new String[0]);
        final Calendar myCalendar = Calendar.getInstance();
        EditText price = findViewById(R.id.editTextTextPersonName);
        EditText descriptiontv = findViewById(R.id.editTextDescription);
        TextView pickdate = findViewById(R.id.pickthedate);
        Spinner categoryList = findViewById(R.id.categorylist);
        categoryList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categorySelectedPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categoryListStrings[categorySelectedPosition] == null || categoryListStrings[categorySelectedPosition].isEmpty()) {
                    Toast.makeText(AddexpenseActivity.this, "Select valid category", Toast.LENGTH_LONG).show();
                } else if (price.getText() == null || price.getText().length() == 0) {
                    Toast.makeText(AddexpenseActivity.this, "Enter valid price", Toast.LENGTH_LONG).show();
                } else if (pickdate.getText() == null || pickdate.getText().length() == 0) {
                    Toast.makeText(AddexpenseActivity.this, "Select valid date", Toast.LENGTH_LONG).show();
                } else if (descriptiontv.getText() == null || descriptiontv.getText().length() == 0) {
                    Toast.makeText(AddexpenseActivity.this, "Enter valid description", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(AddexpenseActivity.this, "Data saved successfully", Toast.LENGTH_LONG).show();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("category", categoryListStrings[categorySelectedPosition]);
                    contentValues.put(AMOUNT, price.getText().toString());
//                contentValues.put(DAY,3);
//                contentValues.put(MONTH,4);
//                contentValues.put(YEAR,2021);
                    contentValues.put(DATE, pickdate.getText().toString());
                    contentValues.put(DESCRIPTION, descriptiontv.getText().toString());
                    mydb.insertExpense(EXPENSE_TABLE, contentValues);
                    mydb.close();
                    finish();
                }
            }
        });
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                this.categoryListStrings);
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
                new DatePickerDialog(AddexpenseActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }
}