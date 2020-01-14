package com.example.rasmusoberg.myapplication;

public class Instruction {
    private String whatToDo;
    private String content;

    public Instruction(String string, String string1){
        this.whatToDo = string;
        this.content = string1;
    }

    public String getWhatToDo(){
        return this.whatToDo;
    }

    public String getContent(){
        return this.content;
    }
}
