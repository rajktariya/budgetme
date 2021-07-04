package com.example.budgetme;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetme.addexpense.AdapterExpenseAdapter;
import com.example.budgetme.addexpense.AddexpnseModel;

import java.util.List;

public class Deleteexpence extends AppCompatActivity {
    dbHandler mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.deleteexpence);
        RecyclerView recyclerView = findViewById(R.id.expenseList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        mydb = new dbHandler(this);
       List<AddexpnseModel> expenseList = mydb.getAllExpense(dbHandler.EXPENSE_TABLE);

        AdapterExpenseAdapter adapterExpense = new AdapterExpenseAdapter(expenseList);
        recyclerView.setAdapter(adapterExpense);


    }
}