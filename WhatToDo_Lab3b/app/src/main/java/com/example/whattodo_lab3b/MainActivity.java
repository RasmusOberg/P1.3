package com.example.whattodo_lab3b;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize(){
        FragmentManager fm = getSupportFragmentManager();
        WhatToDoFragment whatToDoFragment = (WhatToDoFragment) fm.findFragmentById(R.id.fragment_whatToDo);
        InstructionFragment instructionFragment = (InstructionFragment) fm.findFragmentById(R.id.fragment_instruction);
        Controller controller = new Controller(this, whatToDoFragment, instructionFragment);
    }
}
