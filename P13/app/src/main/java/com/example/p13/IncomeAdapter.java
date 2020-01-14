package com.example.p13;

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
 * Adapter for the recyclerview holding income-objects
 * @author rasmusoberg
 */
public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.IncomeHolder> {
    private List<Income> incomes = new ArrayList<>();
    private OnIncomeListener listener;

    @NonNull
    @Override
    public IncomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new IncomeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeHolder holder, int position) {
        Income currentIncome = incomes.get(position);
        holder.title.setText(currentIncome.getTitle());
        holder.category.setText(currentIncome.getCategory());
        holder.price.setText(currentIncome.getPrice() + ":-");
        holder.date.setText((currentIncome.getYear() + "/" + currentIncome.getMonth() + "/" + currentIncome.getDay()));
        switch (currentIncome.getCategory()){
            case "Lön":
                holder.image.setImageResource(R.drawable.ic_other);
                break;
            case "Övrigt":
                holder.image.setImageResource(R.drawable.ic_other_inc);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return incomes.size();
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
        notifyDataSetChanged();
    }

    public Income getIncome(int position){
        return incomes.get(position);
    }

    class IncomeHolder extends RecyclerView.ViewHolder{
        private TextView title, category, price, date;
        private ImageView image;

        public IncomeHolder(@NonNull View itemView) {
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
                        listener.onIncomeClick(incomes.get(position));
                }
            });
        }

    }

    public interface OnIncomeListener {
        void onIncomeClick(Income income);
    }

    public void setOnItemClickListener(OnIncomeListener listener) {
        this.listener = listener;
    }

}
