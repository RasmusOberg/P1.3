package com.example.p13;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.p13.ui.main.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Fragment that holds a recyclerview that holds all the expense-objects
 * Responsible for opening a new activity for creating new expenses
 * @author rasmusoberg
 */
public class ExpenseResultFragment extends Fragment implements ExpenseAdapter.OnExpenseListener{
    private MainViewModel mvv;
    public static final int ADD_EXPENSE = 2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense_result, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final ExpenseAdapter expenseAdapter = new ExpenseAdapter();
        recyclerView.setAdapter(expenseAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDelete(expenseAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        mvv = ViewModelProviders.of(this).get(MainViewModel.class);
        mvv.getAllExpenses().observe(this, new Observer<List<Expense>>() {
            @Override
            public void onChanged(List<Expense> expenses) {
                expenseAdapter.setExpenses(expenses);
            }
        });

        FloatingActionButton floatButton = view.findViewById(R.id.floatButton);
        floatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddExpenseActivity.class);
                getActivity().startActivityForResult(intent, ADD_EXPENSE);
            }
        });

        expenseAdapter.setOnItemClickListener(new ExpenseAdapter.OnExpenseListener() {
            @Override
            public void OnExpenseClick(Expense expense) {
                Intent intent = new Intent(getActivity(), DisplayIncomeExpenseActivity.class);
                intent.putExtra("Title", expense.getTitle());
                intent.putExtra("Category", expense.getCategory());
                intent.putExtra("Price", "" + expense.getPrice());
                intent.putExtra("Date", expense.getYear() + "/" + expense.getMonth() + "/" + expense.getDay());
                getActivity().startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void OnExpenseClick(Expense expense) {

    }

    /**
     * Adds a swipe-to-delete function on the recyclerview
     */
    private class SwipeToDelete extends ItemTouchHelper.SimpleCallback {
        private ExpenseAdapter expenseAdapter;

        public SwipeToDelete(ExpenseAdapter adapter){
            super(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
            expenseAdapter = adapter;
        }
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction){
            int position = viewHolder.getAdapterPosition();
            mvv.delete(expenseAdapter.getExpense(position));
        }
    }

}