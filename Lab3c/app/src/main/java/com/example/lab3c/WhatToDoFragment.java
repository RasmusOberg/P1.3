package com.example.lab3c;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WhatToDoFragment extends Fragment {
    private WhatToDoAdapter adapter;
    private Controller controller;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_what_to_do, container, false);
        ListView lv = view.findViewById(R.id.lvWhatToDo);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new ItemListener());
        return view;
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    public void setAdapter(WhatToDoAdapter adapter){
        this.adapter = adapter;
    }

    private class ItemListener implements AdapterView.OnItemClickListener{
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            controller.itemClicked(position);
        }
    }

}
