package com.example.lab3a;

import androidx.fragment.app.Fragment;
import android.graphics.Color;

public class Controller {
    private Fragment1 fragment1;
    private String[] colorNames = {"RÖD", "BLÅ", "GUL", "GRÖN"};
    private int[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};

    public Controller(Fragment1 fragment){
        this.fragment1 = fragment;
        fragment1.setController(this);
        fragment1.setColors(colorNames); //sends the array of colornames
        fragment1.setButtonText(colorNames[0]); //sets the buttontext
        fragment1.setTextViewColor(colors[0]); //sets the backgroundcolor of textview
    }

    public void listItemClicked(int position){
        fragment1.setButtonText(colorNames[position]);
        fragment1.setButtonColor(colors[position]);
    }

    public void btnColorClicked(String colorString){
        int pos = -1;
        for(int i = 0; i < colorNames.length; i++){
            if(colorNames[i].equals(colorString)){
                pos = i;
                fragment1.setTextViewColor(colors[pos]);
                break;
            }
        }
    }
}
