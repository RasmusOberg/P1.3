package com.example.rockpaperscissors;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.view.View.OnClickListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {
    private RPSInputListener controller;
    private Button btnRock, btnScissor, btnPaper, btnNewGame;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        initializeComponents(view);
        registerListeners();
        return view;
    }

    public void enableChoiceButtons(boolean b){
        btnRock.setEnabled(b);
        btnPaper.setEnabled(b);
        btnScissor.setEnabled(b);
    }

    private void initializeComponents(View view){
        btnNewGame = view.findViewById(R.id.btnNewGame);
        btnPaper = view.findViewById(R.id.btnPaper);
        btnRock = view.findViewById(R.id.btnRock);
        btnScissor = view.findViewById(R.id.btnScissor);
    }

    private void registerListeners(){
        OnClickListener choiceButtons = new ChoiceButtonListener();
        OnClickListener newGameButton = new GameButtonListener();
        btnNewGame.setOnClickListener(newGameButton);
        btnRock.setOnClickListener(choiceButtons);
        btnScissor.setOnClickListener(choiceButtons);
        btnPaper.setOnClickListener(choiceButtons);
    }

    public void setController(RPSInputListener controller){
        this.controller = controller;
        Log.d("Testing", "Controller satt");
    }

    //Inner listener that listens to which of the choices was pressed
    private class ChoiceButtonListener implements OnClickListener {
        public void onClick(View view){
            switch (view.getId()){
                case R.id.btnRock:
                    controller.newChoice(RPSController.ROCK);
                    Log.d("1", "Sten");
                    break;
                case R.id.btnPaper:
                    controller.newChoice(RPSController.PAPER);
                    break;
                case R.id.btnScissor:
                    controller.newChoice(RPSController.SCISSOR);
                    break;
            }
        }
    }

    //The listener for the NewGame-button
    private class GameButtonListener implements OnClickListener{
        public void onClick(View view){
            controller.newGame();
            //Calls the controller and asks for a new game
        }
    }

    public interface RPSInputListener{
        int ROCK=0, PAPER=1, SCISSOR=2;
        void newGame();
        void newChoice(int choice);
    }

}
