package com.example.whattodo_lab3b;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WhatToDoFragment extends Fragment {
    private Controller controller;
    private ListView lvWhatToDo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_what_to_do, container, false);
        lvWhatToDo = view.findViewById(R.id.lvWhatToDo);
        lvWhatToDo.setOnItemClickListener(new ItemListener());
        return view;
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    public void setAdapter(WhatToDoAdapter adapter){
        lvWhatToDo.setAdapter(adapter);
    }

    private class ItemListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            controller.itemClicked(position);
        }
    }

}
