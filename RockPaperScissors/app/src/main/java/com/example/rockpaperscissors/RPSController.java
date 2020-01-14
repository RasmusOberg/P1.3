package com.example.rockpaperscissors;

import android.util.Log;

public class RPSController implements InputFragment.RPSInputListener {
    private RPSPlayer computerPlayer;
    private InputFragment inputFragment;
    private ViewerFragment viewerFragment;
    private int humanPoints, computerPoints;

    //When mainActivity instantiates the controller --> it binds the fragments and controller
    public RPSController(RPSPlayer computerPlayer, ViewerFragment viewerFragment, InputFragment inputFragment){
        this.computerPlayer = computerPlayer;
        this.viewerFragment = viewerFragment;
        this.inputFragment = inputFragment;
        this.inputFragment.setController(this);
        Log.d("Testing", "controller skapad");
    }

    public void newGame(){
        initializeGame();
    }

    //Updates the viewerfragment with the choices made
    public void newChoice(int humanChoice){
        int computerChoice = computerPlayer.newChoice();
        rules(humanChoice, computerChoice);
        switch (humanChoice){
            case ROCK:
                viewerFragment.setHumanChoice(ViewerFragment.Choice.ROCK);
                Log.d("0", "Controller mottagit playerChoice");
                break;
            case PAPER:
                viewerFragment.setHumanChoice(ViewerFragment.Choice.PAPER);
                break;
            case SCISSOR:
                viewerFragment.setHumanChoice(ViewerFragment.Choice.SCISSOR);
                break;
        }
        switch (computerChoice){
            case ROCK:
                viewerFragment.setComputerChoice(ViewerFragment.Choice.ROCK);
                Log.d("1", "comp = rock");
                break;
            case PAPER:
                viewerFragment.setComputerChoice(ViewerFragment.Choice.PAPER);
                Log.d("1", "comp = paper");
                break;
            case SCISSOR:
                viewerFragment.setComputerChoice(ViewerFragment.Choice.SCISSOR);
                Log.d("1", "comp = scissor");
                break;
        }
        viewerFragment.setHumanPoints(humanPoints);
        viewerFragment.setComputerPoints(computerPoints);
        winner();
    }

    //Compares the choices to see who won
    private void rules(int human, int computer){
        if(human != computer){
            if((human == ROCK && computer == SCISSOR) ||
                    (human == PAPER && computer == ROCK) ||
                    (human == SCISSOR && computer == PAPER)){
                humanPoints++;
            }else
                computerPoints++;
        }
    }

    private void initializeGame(){
        humanPoints = computerPoints = 0;
        inputFragment.enableChoiceButtons(true);
        viewerFragment.setHumanPoints(humanPoints);
        viewerFragment.setComputerPoints(computerPoints);
        viewerFragment.setHumanChoice(ViewerFragment.Choice.EMPTY);
        viewerFragment.setComputerChoice(ViewerFragment.Choice.EMPTY);
        viewerFragment.resetTvWinner();
    }

    //updates the viewerFrag with the winner
    private void winner(){
        if((humanPoints == 3) || (computerPoints == 3)){
            if(humanPoints == 3)
                viewerFragment.setTvWinner(true);
            else if(computerPoints == 3)
                viewerFragment.setTvWinner(false);
        }
    }
}
