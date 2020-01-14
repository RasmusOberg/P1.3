package com.example.p13;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.p13.ui.main.MainViewModel;
import java.text.DecimalFormat;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import static android.content.Context.MODE_PRIVATE;

/**
 * Fragment used to display an overview of all the incomes/expenses
 * @author rasmusoberg
 */
public class OverviewFragment extends Fragment {
    private TextView tvName, tvIncome, tvExpense, tvTotal;
    public static final String SHARED_PREFS = "sharedPrefs";
    private MainViewModel mvv;
    private double totalIncome, totalExpense;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        tvName = view.findViewById(R.id.tvFirst);
        tvIncome = view.findViewById(R.id.tvIncome);
        tvExpense = view.findViewById(R.id.tvExpense);
        tvTotal = view.findViewById(R.id.tvTotal);

        SharedPreferences sharedPreferences = this.getContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String firstName = sharedPreferences.getString(LogInFragment.FIRSTNAME, "");
        String lastName = sharedPreferences.getString(LogInFragment.LASTNAME, "");
        tvName.setText("Välkommen " + firstName + " " + lastName + "!");

        mvv = ViewModelProviders.of(this).get(MainViewModel.class);
        mvv.getTotalIncomes().observe(this, new Observer<List<Double>>() {
            @Override
            public void onChanged(List<Double> doubles) {
                double temp = 0;
                for(int i = 0; i < doubles.size(); i++){
                    temp += doubles.get(i);
                }
                totalIncome = temp;
                DecimalFormat df = new DecimalFormat("0.00");
                df.format(totalIncome);
                tvIncome.setText("Totala inkomster: " + df.format(totalIncome) + ":-");
                tvTotal.setText("Totalt under/överskott: " + df.format(totalIncome-totalExpense) + ":-");
            }
        });
        mvv.getTotalExpenses().observe(this, new Observer<List<Double>>() {
            @Override
            public void onChanged(List<Double> doubles) {
                double temp = 0;
                for(int i = 0; i < doubles.size(); i++){
                    temp += doubles.get(i);
                }
                totalExpense = temp;
                DecimalFormat df = new DecimalFormat("0.00");
                df.format(totalExpense);
                tvExpense.setText("Totala utgifter: " + df.format(totalExpense) + ":-");
                tvTotal.setText("Totalt under/överskott: " + df.format(totalIncome-totalExpense) + ":-");
            }
        });
        return view;
    }
}
