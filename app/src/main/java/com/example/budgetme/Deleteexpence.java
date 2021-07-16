package com.example.budgetme;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetme.addexpense.AdapterExpenseAdapter;
import com.example.budgetme.addexpense.AddexpnseModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class Deleteexpence extends AppCompatActivity {
    dbHandler mydb;
    AdapterExpenseAdapter adapterExpense;
    RecyclerView recyclerView;
    RelativeLayout parentLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.deleteexpence);
        parentLayout = findViewById(R.id.parentLayout);
        recyclerView = findViewById(R.id.expenseList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        mydb = new dbHandler(this);
       List<AddexpnseModel> expenseList = mydb.getAllExpense(dbHandler.EXPENSE_TABLE);

        adapterExpense = new AdapterExpenseAdapter(expenseList);
        recyclerView.setAdapter(adapterExpense);
        enableSwipeToDeleteAndUndo();
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final AddexpnseModel item = adapterExpense.getData().get(position);

                adapterExpense.removeItem(position);


                Snackbar snackbar = Snackbar
                        .make(parentLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);

                snackbar.show();
                mydb.deleteExpense(item.getId());


            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

}