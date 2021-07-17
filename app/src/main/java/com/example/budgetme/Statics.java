package com.example.budgetme;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.budgetme.piechart.PieHelper;
import com.example.budgetme.piechart.PieView;

import java.util.ArrayList;

public class Statics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.statics);

        TextView textView = (TextView) findViewById(R.id.textView);
        final PieView pieView = (PieView) findViewById(R.id.pie_view);
        Button button = (Button) findViewById(R.id.pie_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomSet(pieView);
            }
        });
        randomSet(pieView);


    }

    private void randomSet(PieView pieView) {
        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
        ArrayList<Integer> intList = new ArrayList<Integer>();
        int totalNum = (int) (10 * Math.random());

        int totalInt = 0;
        for (int i = 0; i < totalNum; i++) {
            int ranInt = (int) (Math.random() * 10) + 1;
            intList.add(ranInt);
            totalInt += ranInt;
        }
        for (int i = 0; i < totalNum; i++) {
            pieHelperArrayList.add(new PieHelper(100f * intList.get(i) / totalInt));
        }

        pieView.selectedPie(PieView.NO_SELECTED_INDEX);
        pieView.showPercentLabel(true);
        pieView.setData(pieHelperArrayList);
    }

}