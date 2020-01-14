package com.example.whattodo_lab3b;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class InstructionFragment extends Fragment {
    private TextView tvWhatToDo;
    private TextView tvContent;
    private String whatToDo = "";
    private String content = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_instruction, container, false);
        tvContent = view.findViewById(R.id.tvContent);
        tvWhatToDo = view.findViewById(R.id.tvWhatToDo);
        return view;
    }

    public void setWhatToDo(String string){
        tvWhatToDo.setText(string);
    }

    public void setContent(String string){
        tvContent.setText(string);
    }

}
