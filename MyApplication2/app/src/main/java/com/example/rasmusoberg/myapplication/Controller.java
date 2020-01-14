package com.example.rasmusoberg.myapplication;

import android.content.res.Resources;

public class Controller {
    private Instruction instructions[] = new Instruction[3];
    private int index = 0;
    private MainActivity ui;

    public Controller(MainActivity ui){
        this.ui = ui;
    }

    private void initializeResources(){
        Resources res = ui.getResources();
        instructions[0] = new Instruction(res.getString(R.string.what_to_do1), res.getString(R.string.content1));
        instructions[1] = new Instruction(res.getString(R.string.what_to_do2), res.getString(R.string.content2));
        instructions[2] = new Instruction(res.getString(R.string.what_to_do3), res.getString(R.string.content3));
    }

    public void previousClick(){
        index--;
        if(index < 0)
            index = instructions.length-1;
        ui.setWhatToDo(instructions[index].getWhatToDo());
        ui.setContent(instructions[index].getContent());
    }

    public void nextClick(){
        index++;
        if(index == instructions.length)
            index = 0;
        ui.setWhatToDo(instructions[index].getWhatToDo());
        ui.setContent(instructions[index].getContent());
    }
}
