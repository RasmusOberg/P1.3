package com.example.p13.ui.main;

import android.app.Application;
import android.util.Log;
import com.example.p13.Database.*;
import com.example.p13.*;
import java.util.List;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainViewModel extends AndroidViewModel {
    private AppRepository repository;
    private LiveData<List<Income>> allIncomes;
    private LiveData<List<Expense>> allExpenses;
    private LiveData<List<Double>> totalIncomes;
    private LiveData<List<Double>> totalExpense;

    public MainViewModel(Application application){
        super(application);
        repository = new AppRepository(application);
        allIncomes = repository.getAllIncomes();
        allExpenses = repository.getAllExpenses();
        totalIncomes = repository.getTotalIncomes();
        totalExpense = repository.getTotalExpenses();
    }


    public void insert(Income income){
        repository.insert(income);
    }

    public void insert(Expense expense){
        repository.insert(expense);
    }

    public void delete(Income income){
        repository.delete(income);
    }

    public void delete(Expense expense){
        repository.delete(expense);
    }

    public LiveData<List<Income>> getAllIncomes(){
        return allIncomes;
    }

    public LiveData<List<Expense>> getAllExpenses(){
        return allExpenses;
    }

    public LiveData<List<Double>> getTotalIncomes(){
        return totalIncomes;
    }

    public LiveData<List<Double>> getTotalExpenses(){
        return totalExpense;
    }


}
