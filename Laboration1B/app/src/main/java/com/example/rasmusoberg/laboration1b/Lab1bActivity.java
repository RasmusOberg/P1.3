package com.example.rasmusoberg.laboration1b;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Lab1bActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etPhone;
    private EditText etEmail;
    private TextView tvSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1b);
        initializeComponents();
        registerListeners();
        }

    private void registerListeners() {
        Button btnSummary = findViewById(R.id.btnSummary);
        btnSummary.setOnClickListener(new ButtonListener());
    }

    private void initializeComponents() {
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById((R.id.etEmail));
        tvSummary = findViewById(R.id.tvDash);

    }

    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            String str = summary();
            tvSummary.setText(str);
        }

        private String summary(){
            String res;
            Resources resources = getResources();
            String name = resources.getString(R.string.name);
            String phone = resources.getString(R.string.phone);
            String email = resources.getString(R.string.mail);
            res = name + " " + etName.getText().toString() + "\n"
                    + phone + " " + etPhone.getText().toString() + "\n"
                    + email + " " + etEmail.getText().toString();
            return res;
        }
    }
}
