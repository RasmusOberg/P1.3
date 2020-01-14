package com.example.lab3c;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class InstructionFragment extends Fragment {
    private TextView tvWhatToDo;
    private TextView tvContent;
    private String whatToDo = "";
    private String content = "";


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_instruction, container, false);
        tvWhatToDo = view.findViewById(R.id.tvWhatToDo);
        tvContent = view.findViewById(R.id.tvContent);
        return view;
    }

    public void setWhatToDo(String string){
        this.whatToDo = string;
    }

    public void setContent(String string){
        this.content = string;
    }

    public void onResume(){
        super.onResume();
        tvWhatToDo.setText(whatToDo);
        tvContent.setText(content);
    }

}
