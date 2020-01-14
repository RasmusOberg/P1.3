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
import android.widget.Toast;

import java.util.GregorianCalendar;

/**
 * Activity that shows a form to input new incomes
 * @author rasmusoberg
 */
public class AddIncomeActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_add_income);
        etTitle = findViewById(R.id.etTitle);
        etPrice = findViewById(R.id.etPrice);
        spinner = findViewById(R.id.spinner);
        btn = findViewById(R.id.btnSaveIncome);
        datePicker = findViewById(R.id.dpDate);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = spinner.getSelectedItem().toString();
                saveIncome();
                Log.d("TAG", "Income ivägskickat");
            }
        });

    }

    /**
     * Saves the user input, puts it in an Intent, and sends it back to the main activity
     */
    private void saveIncome(){
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