package com.example.p13;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Adapter for the recyclerview used to display the expenses
 * @author rasmusoberg
 */
public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseHolder> {
    private List<Expense> expenses = new ArrayList<>();
    private OnExpenseListener listener;

    public ExpenseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ExpenseHolder(itemView);
    }

    public Expense getExpense(int position){
        return expenses.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseHolder holder, int position) {
        Expense currentExpense = expenses.get(position);
        holder.category.setText(currentExpense.getCategory());
        holder.title.setText(currentExpense.getTitle());
        holder.price.setText(currentExpense.getPrice() + ":-");
        holder.date.setText((currentExpense.getYear() + "/" + currentExpense.getMonth() + "/" + currentExpense.getDay()));
        switch (currentExpense.getCategory()){
            case "Livsmedel":
                holder.image.setImageResource(R.drawable.ic_grocery);
                break;
            case "Fritid":
                holder.image.setImageResource(R.drawable.ic_free);
                break;
            case "Resor":
                holder.image.setImageResource(R.drawable.ic_travel);
                break;
            case "Boende":
                holder.image.setImageResource(R.drawable.ic_housing);
                break;
            case "Övrigt":
                holder.image.setImageResource(R.drawable.ic_other);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public void setExpenses(List<Expense> expenses){
        this.expenses = expenses;
        notifyDataSetChanged();
    }

    class ExpenseHolder extends RecyclerView.ViewHolder {
        private TextView title, category, price, date;
        private ImageView image;

        public ExpenseHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            category = itemView.findViewById(R.id.tvCategory);
            price = itemView.findViewById(R.id.tvPrice);
            date = itemView.findViewById(R.id.tvDate);
            image = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.OnExpenseClick(expenses.get(position));
                }
            });
        }
    }

    public interface OnExpenseListener{
        void OnExpenseClick(Expense expense);
    }

    public void setOnItemClickListener(OnExpenseListener listener) {
        this.listener = listener;
    }

}
