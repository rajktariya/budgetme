package com.example.budgetme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetme.addexpense.AdapterExpenseAdapter;
import com.example.budgetme.addexpense.AdapterTaskTodo;
import com.example.budgetme.addexpense.AddexpnseModel;

import java.util.List;

public class Taskstodo extends Activity {
    dbHandler mydb;
    AdapterTaskTodo adapterExpense;
    RecyclerView recyclerView;
    RelativeLayout parentLayout;
    List<AddexpnseModel> taskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.taskstodo);
        Button addTask = findViewById(R.id.addTask);
        mydb = new dbHandler(this);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        recyclerView = findViewById(R.id.taskList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        taskList = mydb.getAllTaskList();

        adapterExpense = new AdapterTaskTodo(taskList,mydb);
        recyclerView.setAdapter(adapterExpense);

    }
    private void addTask(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Taskstodo.this);
        alertDialog.setTitle("Add New Task To Do!");
        alertDialog.setMessage("what's the new expense?");

        final EditText input = new EditText(Taskstodo.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        alertDialog.setPositiveButton("Add",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                     String   task = input.getText().toString();

                        mydb.insertTask(task);
                        taskList.clear();
                        taskList.addAll(mydb.getAllTaskList());
                        adapterExpense.notifyDataSetChanged();
                        dialog.cancel();
                    }
                });

        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();



    }
}