package com.example.p13;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.p13.ui.main.MainViewModel;
import com.google.android.material.navigation.NavigationView;
import static com.example.p13.AddIncomeActivity.EXTRA_DAY;
import static com.example.p13.AddIncomeActivity.EXTRA_MONTH;
import static com.example.p13.AddIncomeActivity.EXTRA_PRICE;
import static com.example.p13.AddIncomeActivity.EXTRA_YEAR;

/**
 * MainActivity-class holding the navigationview, also responsible for creating the new incomes/expenses
 * and forwarding to the DB
 * @author rasmusoberg
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LogInFragment.FragmentChangeListener {
    public static final int ADD_INCOME = 1;
    public static final int ADD_EXPENSE = 2;
    private DrawerLayout drawer;
    private MainViewModel viewModel;
    private LogInFragment logInFragment = new LogInFragment();
    private IncomeResultFragment incomeResultFragment = new IncomeResultFragment();
    private ExpenseResultFragment expenseResultFragment = new ExpenseResultFragment();
    private OverviewFragment overviewFragment = new OverviewFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, logInFragment).commit();
        }

    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_show_income:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, incomeResultFragment).commit();
                break;
            case R.id.nav_show_expense:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, expenseResultFragment).commit();
                break;
            case R.id.nav_overview:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, overviewFragment).commit();
                break;
        }
        menuItem.setChecked(true);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_INCOME && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddIncomeActivity.EXTRA_TITLE);
            String category = data.getStringExtra(AddIncomeActivity.EXTRA_CATEGORY);
            double price = Double.parseDouble(data.getStringExtra(EXTRA_PRICE));
            int year = Integer.parseInt(data.getStringExtra(EXTRA_YEAR));
            int month = Integer.parseInt(data.getStringExtra(EXTRA_MONTH));
            int day = Integer.parseInt(data.getStringExtra(EXTRA_DAY));
            Income income = new Income(title, category, price, year, month, day);
            viewModel.insert(income);
        } else if (requestCode == ADD_EXPENSE && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddIncomeActivity.EXTRA_TITLE);
            String category = data.getStringExtra(AddIncomeActivity.EXTRA_CATEGORY);
            double price = Double.parseDouble(data.getStringExtra(EXTRA_PRICE));
            int year = Integer.parseInt(data.getStringExtra(EXTRA_YEAR));
            int month = Integer.parseInt(data.getStringExtra(EXTRA_MONTH));
            int day = Integer.parseInt(data.getStringExtra(EXTRA_DAY));
            Expense expense = new Expense(title, category, price, year, month, day);
            viewModel.insert(expense);
        }
    }

    @Override
    public void replaceFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, overviewFragment).commit();
    }
}

