package com.example.budgetme.addexpense;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetme.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterExpenseAdapter extends RecyclerView.Adapter<AdapterExpenseAdapter.ViewHolder> {

    private List<AddexpnseModel> localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView category,price,date,description;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            category = (TextView) view.findViewById(R.id.category);
            price = (TextView) view.findViewById(R.id.price);
            date = (TextView) view.findViewById(R.id.date);
            description = (TextView) view.findViewById(R.id.description);
        }

    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public AdapterExpenseAdapter(List<AddexpnseModel> dataSet) {
        localDataSet = dataSet;
    }
    public void removeItem(int position) {
        localDataSet.remove(position);
        notifyItemRemoved(position);
    }
    public List<AddexpnseModel> getData() {
        return localDataSet;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.deleteexpenseitem, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.category.setText(localDataSet.get(position).getCategory());
        viewHolder.price.setText(localDataSet.get(position).price+"");
        viewHolder.description.setText(localDataSet.get(position).description);
        viewHolder.date.setText(localDataSet.get(position).date);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}