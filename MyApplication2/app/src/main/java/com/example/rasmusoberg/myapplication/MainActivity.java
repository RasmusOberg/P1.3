package com.example.rasmusoberg.myapplication;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Controller controller;
    private Button btnPrevious;
    private Button btnNext;
    private TextView tvWhatToDo;
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        registerListeners();
        controller = new Controller(this);
    }

    private void initializeComponents(){
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        tvWhatToDo = findViewById(R.id.tvWhatToDo);
        tvContent = findViewById(R.id.tvContent);
    }

    private void registerListeners(){
        btnNext.setOnClickListener(new NextListener());
        btnPrevious.setOnClickListener(new NextListener());
    }

    public void setWhatToDo(String whatToDo) {
        tvWhatToDo.setText(whatToDo);
    }

    public void setContent(String content) {
        tvContent.setText(content);
    }

    public void previousInstruction(View view){
        controller.previousClick();
    }

    private class NextListener implements View.OnClickListener {
        public void onClick(View v){
           controller.nextClick();
        }
    }

}
