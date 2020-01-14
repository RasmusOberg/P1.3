package com.example.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private RPSController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeController();
        startGame();
        Log.d("test", "vaaaa");
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.GRAY);
    }

    private void initializeController(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        RPSPlayer computerPlayer = new RPSPlayer();
        ViewerFragment viewerFragment = (ViewerFragment)fragmentManager.findFragmentById(R.id.fragment);
        InputFragment inputFragment = (InputFragment) fragmentManager.findFragmentById(R.id.fragment2);
        controller = new RPSController(computerPlayer, viewerFragment, inputFragment);
    }

    private void startGame(){
        controller.newGame();
        Log.d("Testing", "controller.newGame() skickad");
    }
}
