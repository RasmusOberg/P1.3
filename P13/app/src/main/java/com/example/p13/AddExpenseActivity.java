package com.example.p13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Activity that shows a new inputform for expenses
 * @author rasmusoberg
 */

public class AddExpenseActivity extends AppCompatActivity {
    private EditText etTitle, etPrice;
    private Spinner spinner;
    private String category;
    private Button btn;
    private DatePicker datePicker;
    public static final String EXTRA_TITLE = "com.example.p13.EXTRA_TITLE";
    public static final String EXTRA_CATEGORY = "com.example.p13.EXTRA_CATEGORY";
    public static final String EXTRA_PRICE = "com.example.p13.EXTRA_PRICE";
    public static final String EXTRA_YEAR = "com.exaple.p13.EXTRA_YEAR";
    public static final String EXTRA_MONTH = "com.exaple.p13.EXTRA_MONTH";
    public static final String EXTRA_DAY = "com.exaple.p13.EXTRA_DAY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        etTitle = findViewById(R.id.etTitle);
        etPrice = findViewById(R.id.etPrice);
        spinner = findViewById(R.id.spinner);
        datePicker = findViewById(R.id.dpDate);
        btn = findViewById(R.id.btnSaveIncome);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = spinner.getSelectedItem().toString();
                saveExpense();
            }
        });

    }

    /**
     * Saves the input, puts in an Intent and sends it back to the main activity
     */
    private void saveExpense(){
        String title = etTitle.getText().toString();
        int year = datePicker.getYear();
        String strYear = "" + year;
        int month = datePicker.getMonth()+1;
        String strMonth = "" + month;
        int day = datePicker.getDayOfMonth();
        String strDay = "" + day;
        String strPrice = etPrice.getText().toString();

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_CATEGORY, category);
        data.putExtra(EXTRA_PRICE, strPrice);
        data.putExtra(EXTRA_YEAR, strYear);
        data.putExtra(EXTRA_MONTH, strMonth);
        data.putExtra(EXTRA_DAY, strDay);
        setResult(RESULT_OK, data);
        finish();
    }

}
