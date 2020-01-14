package com.example.rockpaperscissors;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ViewerFragment extends Fragment {
    private TextView tvHumanPoints;
    private TextView tvComputerPoints;
    private ImageView ivHumanChoice;
    private ImageView ivComputerChoice;
    private TextView tvWinner;
    //private Drawable winner, loser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewer, container, false);
        initializeComponents(view);
        initializeResources();
        return view;
    }

    //Binds the XML-objects to the Java-objects
    private void initializeComponents(View view){
        tvHumanPoints = view.findViewById(R.id.tvHumanPoints);
        tvComputerPoints = view.findViewById(R.id.tvComputerPoints);
        ivHumanChoice = view.findViewById(R.id.ivHumanChoice);
        ivComputerChoice = view.findViewById(R.id.ivComputerChoice);
        tvWinner = view.findViewById(R.id.tvWinner);
    }

    //Binds this frag with the activity
    private void initializeResources(){
        Activity activity = getActivity();
       // winner = ContextCompat.getDrawable(activity, R.drawable.winner);
       // loser = ContextCompat.getDrawable(activity, R.drawable.loser);
    }

    //Called by controller to update humanPoints
    public void setHumanPoints(int points){
        tvHumanPoints.setText(String.valueOf(points));
    }

    //Used by controller to update computerPoints
    public void setComputerPoints(int points){
        tvComputerPoints.setText(String.valueOf(points));
    }

    public void setTvWinner(Boolean win){
        if(win)
            tvWinner.setText("You win!");
        else
            tvWinner.setText("You lose!");
    }

    public void resetTvWinner(){
        tvWinner.setText("Reach 3 to win!");
    }

    //Updates the imageView with the players choice
    public void setHumanChoice(Choice choice){
        switch (choice){
            case ROCK:
               // ivHumanChoice.setImageResource(R.drawable.rockleft);
                break;
            case PAPER:
               // ivHumanChoice.setImageResource(R.drawable.paperleft);
                break;
            case SCISSOR:
               // ivHumanChoice.setImageResource(R.drawable.scissorleft);
                break;
            case EMPTY:
               // ivHumanChoice.setImageResource(R.drawable.empty);
                break;
        }
    }

    //Used to update the imageView for the computers choice
    public void setComputerChoice(Choice choice){
        switch(choice){
            case ROCK:
               // ivComputerChoice.setImageResource(R.drawable.rockright);
                break;
            case PAPER:
              //  ivComputerChoice.setImageResource(R.drawable.paperright);
                break;
            case SCISSOR:
              //  ivComputerChoice.setImageResource(R.drawable.scissorright);
                break;
            case EMPTY:
              //  ivComputerChoice.setImageResource(R.drawable.empty);
                break;
        }
    }

    public void humanWinner(){

    }

    public void computerWinner(){

    }

    public enum Choice{ROCK, PAPER, SCISSOR, EMPTY};

}
