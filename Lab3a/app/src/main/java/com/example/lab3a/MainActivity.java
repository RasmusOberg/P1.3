package com.example.lab3a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeController();
    }

    private void initializeController(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment1 fragment1 = (Fragment1) fm.findFragmentById(R.id.fragment1);
        controller = new Controller(fragment1);
    }
}
