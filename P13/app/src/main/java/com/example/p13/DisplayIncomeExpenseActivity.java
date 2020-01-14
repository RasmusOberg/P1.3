package com.example.p13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

/**
 * New activity for displaying any specific item clicked
 * @author rasmusoberg
 */
public class DisplayIncomeExpenseActivity extends AppCompatActivity {
    private TextView title, date, price, category;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_income);
        title = findViewById(R.id.tvTitle);
        category = findViewById(R.id.tvCategory);
        price = findViewById(R.id.tvPrice);
        date = findViewById(R.id.tvDate);
        image = findViewById(R.id.image);

        Intent intent = getIntent();
        title.setText(intent.getStringExtra("Title"));
        price.setText(intent.getStringExtra("Price"));
        category.setText(intent.getStringExtra("Category"));
        date.setText(intent.getStringExtra("Date"));
        switch (intent.getStringExtra("Category")){
            case "Lön":
                image.setImageResource(R.drawable.ic_other);
                break;
            case "Livsmedel":
                image.setImageResource(R.drawable.ic_grocery);
                break;
            case "Fritid":
                image.setImageResource(R.drawable.ic_free);
                break;
            case "Resor":
                image.setImageResource(R.drawable.ic_travel);
                break;
            case "Boende":
                image.setImageResource(R.drawable.ic_housing);
                break;
            case "Övrigt":
                image.setImageResource(R.drawable.ic_other);
                break;
        }
    }
}
