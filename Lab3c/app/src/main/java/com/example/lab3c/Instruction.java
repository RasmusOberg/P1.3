package com.example.lab3c;

public class Instruction {
    private String whatToDo;
    private String content;

    public Instruction(String whatToDo, String content){
        this.content = content;
        this.whatToDo = whatToDo;
    }

    public String getWhatToDo(){
        return whatToDo;
    }

    public String getContent(){
        return content;
    }
}
