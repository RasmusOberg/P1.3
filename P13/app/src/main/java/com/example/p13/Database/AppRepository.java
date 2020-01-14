package com.example.p13.Database;

import android.app.Application;
import android.os.AsyncTask;
import com.example.p13.Expense;
import com.example.p13.Income;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

public class AppRepository {
    private Dao dao;
    private LiveData<List<Income>> allIncomes;
    private LiveData<List<Expense>> allExpenses;
    private LiveData<List<Double>> totalIncomes;
    private LiveData<List<Double>> totalExpenses;

    public AppRepository(Application application){
        Database db = Database.getInstance(application);
        dao = db.dao();
        try {
            allIncomes = dao.getAllIncomes();
            allExpenses = dao.getAllExpenses();
            totalIncomes = dao.getTotalIncomes();
            totalExpenses = dao.getTotalExpenses();
        }catch (NullPointerException e){}
    }

    public void insert(Income income){
        new InsertIncomeAsyncTask(dao).execute(income);
    }

    public void insert(Expense expense){
        new InsertExpenseAsyncTask(dao).execute(expense);
    }

    public void delete(Income income){
        new DeleteIncomeAsyncTask(dao).execute(income);
    }

    public void delete(Expense expense){
        new DeleteExpenseAsyncTask(dao).execute(expense);
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
        return totalExpenses;
    }

    private static class InsertIncomeAsyncTask extends AsyncTask<Income, Void, Void>{
        private Dao dao;

        private InsertIncomeAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Income... incomes) {
            dao.insert(incomes[0]);
            return null;
        }
    }

    private static class InsertExpenseAsyncTask extends AsyncTask<Expense, Void, Void>{
        private Dao dao;

        private InsertExpenseAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Expense... expenses) {
            dao.insert(expenses[0]);
            return null;
        }
    }

    private static class DeleteIncomeAsyncTask extends AsyncTask<Income, Void, Void>{
        private Dao dao;

        private DeleteIncomeAsyncTask(Dao dao){
            this.dao = dao;
        }

        protected Void doInBackground(Income... incomes){
            dao.delete(incomes[0]);
            return null;
        }
    }

    private static class DeleteExpenseAsyncTask extends AsyncTask<Expense, Void, Void>{
        private Dao dao;

        private DeleteExpenseAsyncTask(Dao dao){
            this.dao = dao;
        }

        protected Void doInBackground(Expense... expenses){
            dao.delete(expenses[0]);
            return null;
        }
    }


}