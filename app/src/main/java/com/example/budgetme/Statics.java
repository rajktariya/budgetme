package com.example.budgetme;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.budgetme.addexpense.AddexpnseModel;
import com.example.budgetme.piechart.PieHelper;
import com.example.budgetme.piechart.PieView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.example.budgetme.dbHandler.AMOUNT;
import static com.example.budgetme.dbHandler.BUDGET_TABLE;
import static com.example.budgetme.dbHandler.MONTH;
import static com.example.budgetme.dbHandler.YEAR;

public class Statics extends Activity {
    dbHandler mydb;
    final Calendar myCalendar = Calendar.getInstance();
    int month, yearInt;
    TextView errortext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.statics);
        mydb = new dbHandler(this);
        final PieView pieView = (PieView) findViewById(R.id.pie_view);
        Button selectmonth = findViewById(R.id.selectmonth);
        errortext = findViewById(R.id.errortext);


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
                selectmonth.setText(sdf.format(myCalendar.getTime()) + "/" + sdfyy.format(myCalendar.getTime()));
                month = Integer.parseInt(sdf.format(myCalendar.getTime()));
                yearInt = Integer.parseInt(sdfyy.format(myCalendar.getTime()));
                ContentValues contentValues = new ContentValues();
                contentValues.put(MONTH, month);
                contentValues.put(YEAR, yearInt);
                float price = mydb.getBudget(BUDGET_TABLE, contentValues);
                errortext.setText("");
                if (price > 0) {
                    drawChart(pieView, price);
                } else {
                    errortext.setText("No Expenses for this month");
                    Toast.makeText(getBaseContext(), "No Expenses for this month", Toast.LENGTH_LONG).show();
                }
            }

        };
        selectmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Statics.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void drawChart(PieView pieView, float totalBudget) {
        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
//        ArrayList<Integer> intList = new ArrayList<Integer>();
        //int totalNum = (int) (10 * Math.random());

//        int totalInt = 0;
//        for (int i = 0; i < totalNum; i++) {
//            int ranInt = (int) (Math.random() * 10) + 1;
//            intList.add(ranInt);
//            totalInt += ranInt;
//        }
//
//        for (int i = 0; i < totalNum; i++) {
//            pieHelperArrayList.add(new PieHelper(100f * intList.get(i) / totalInt));
//        }

        List<AddexpnseModel> expenseList = mydb.getAllExpense(dbHandler.EXPENSE_TABLE);
        int totalPrice = 0;
        for (int i = 0; i < expenseList.size(); i++) {
            totalPrice +=expenseList.get(i).getPrice();
            pieHelperArrayList.add(new PieHelper(100f * expenseList.get(i).getPrice() / totalBudget, expenseList.get(i).getCategory(), 0));
        }
        if(totalPrice>totalBudget){
            Toast.makeText(getBaseContext(), "Your expenses "+totalPrice +" have exceeded your budget limit by "+(totalPrice-totalBudget), Toast.LENGTH_LONG).show();
            errortext.setText("Your expenses "+totalPrice +" have exceeded your budget limit by "+(totalPrice-totalBudget));
        }

        pieView.selectedPie(PieView.NO_SELECTED_INDEX);
        pieView.showPercentLabel(true);
        pieView.setData(pieHelperArrayList);
    }

}