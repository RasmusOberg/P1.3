package com.example.p13;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
 * Fragment displaying the recyclerview that holds all the income-objects
 */
public class IncomeResultFragment extends Fragment implements IncomeAdapter.OnIncomeListener{
    private MainViewModel mvv;
    private static final String TAG = "IncomeResultFragment";
    public static final int ADD_INCOME = 1;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income_result, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final IncomeAdapter incomeAdapter = new IncomeAdapter();
        recyclerView.setAdapter(incomeAdapter);

        mvv = ViewModelProviders.of(this).get(MainViewModel.class);
        mvv.getAllIncomes().observe(this, new Observer<List<Income>>() {
            @Override
            public void onChanged(List<Income> incomes) {
                incomeAdapter.setIncomes(incomes);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDelete(incomeAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        FloatingActionButton floatButton = view.findViewById(R.id.floatButton);
        floatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddIncomeActivity.class);
                getActivity().startActivityForResult(intent, ADD_INCOME);
            }
        });

        incomeAdapter.setOnItemClickListener(new IncomeAdapter.OnIncomeListener() {
            @Override
            public void onIncomeClick(Income income) {
                Intent intent = new Intent(getActivity(), DisplayIncomeExpenseActivity.class);
                intent.putExtra("Title", income.getTitle());
                intent.putExtra("Category", income.getCategory());
                intent.putExtra("Price", "" + income.getPrice());
                intent.putExtra("Date", income.getYear() + "/" + income.getMonth() + "/" + income.getDay());
                getActivity().startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onIncomeClick(Income income) {}

    /**
     * Adds a swipe-to-delete function on the recyclerview
     */
    private class SwipeToDelete extends ItemTouchHelper.SimpleCallback {
        private IncomeAdapter incomeAdapter;

        public SwipeToDelete(IncomeAdapter adapter){
            super(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
            incomeAdapter = adapter;
        }
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction){
            int position = viewHolder.getAdapterPosition();
            mvv.delete(incomeAdapter.getIncome(position));
        }
    }

}
