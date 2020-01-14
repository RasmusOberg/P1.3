package com.example.rockpaperscissors;

import java.util.Random;
import com.example.rockpaperscissors.InputFragment.RPSInputListener;

class RPSPlayer {
    private Random random = new Random();

    public int newChoice(){
        int choice = random.nextInt(3);
        if(choice == 0){
            return RPSInputListener.ROCK;
        }else if(choice == 1)
            return RPSInputListener.PAPER;
        else
            return RPSInputListener.SCISSOR;
    }
}
