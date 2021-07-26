package com.example.budgetme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CategoryActivity extends Activity {
    dbHandler mydb;
    int categorySelectedPosition;
    String[] categoryListStrings ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.categories);
        mydb = new dbHandler(this);
       EditText categoryNameToAdd = findViewById(R.id.newcategorytext);
       Button addcategoryButton = findViewById(R.id.addcategoryButton);
       Button deleteCategory = findViewById(R.id.deleteCategory);
        categoryListStrings = mydb.getAllCategories().toArray(new String[0]);
        addcategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(categoryNameToAdd.getText().toString().length()>0){
                   mydb.insertCategory(categoryNameToAdd.getText().toString());
                    Toast.makeText(getBaseContext(),"Category Added",Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    Toast.makeText(getBaseContext(),"Enter valid category name",Toast.LENGTH_LONG).show();
                }
            }
        });
        Spinner categoryListSpinner = findViewById(R.id.cateogrySpinner);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                categoryListStrings);
        categoryListSpinner.setAdapter(adapter);
        categoryListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categorySelectedPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        deleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mydb.delete(categoryListStrings[categorySelectedPosition]);
                    Toast.makeText(getBaseContext(),"Category Deleted",Toast.LENGTH_LONG).show();
                    finish();
            }
        });

    }
}