package com.example.budgetme.addexpense;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetme.R;
import com.example.budgetme.dbHandler;

import java.util.List;

public class AdapterTaskTodo extends RecyclerView.Adapter<AdapterTaskTodo.ViewHolder> {

    private List<AddexpnseModel> localDataSet;
    dbHandler mydb;
    public AdapterTaskTodo(List<AddexpnseModel> taskList, dbHandler mydb) {
        localDataSet = taskList;
        this.mydb = mydb;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView taskname;
        private final Button done;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            taskname = (TextView) view.findViewById(R.id.taskname);
            done = (Button) view.findViewById(R.id.done);
            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mydb.deleteTaskTodo(localDataSet.get(getAdapterPosition()).getId());
                    localDataSet.remove(getAdapterPosition());
                    notifyDataSetChanged();
                }
            });
        }

    }



    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tasktodoitem, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.taskname.setText(localDataSet.get(position).getCategory());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}