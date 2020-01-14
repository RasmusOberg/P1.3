package com.example.laboration2b;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ButtonFragment.OnFragmentInteraction, ViewerFragment.VFragmentInterface{

    private TextView tview;
    private TextView tview2;
    private ViewerFragment viewerFragment;
    private ButtonFragment buttonFragment;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewerFragment = new ViewerFragment();
        buttonFragment = new ButtonFragment();

        tview = findViewById(R.id.tview);
    }

    @Override
    public void onFragmentInterAction() {
        Log.d("myTag", "PRESSED");
        tview.setText("Button was pressed!");
        try {
            viewerFragment.updateText("Button was pressed");
        }catch(Exception e){
            Log.d("Heh", e.getMessage());
        }
    }

    public void test(){
        viewerFragment.updateText("Button was pressed");
    }
}
