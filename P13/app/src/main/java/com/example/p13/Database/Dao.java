package com.example.p13.Database;
import com.example.p13.Expense;
import com.example.p13.Income;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import static com.example.p13.Expense.PRICE_EXP;
import static com.example.p13.Income.PRICE;

@androidx.room.Dao
public interface Dao {

    @Insert
    void insert(Income income);

    @Insert
    void insert(Expense expense);

    @Delete
    void delete(Income income);

    @Delete
    void delete(Expense expense);

    @Query("SELECT * FROM income_table")
    LiveData<List<Income>> getAllIncomes();

    @Query("SELECT * FROM expense_table")
    LiveData<List<Expense>> getAllExpenses();

    @Query("SELECT " + PRICE  + " FROM income_table")
    LiveData<List<Double>> getTotalIncomes();

    @Query("SELECT " + PRICE_EXP + " FROM expense_table")
    LiveData<List<Double>> getTotalExpenses();
}
