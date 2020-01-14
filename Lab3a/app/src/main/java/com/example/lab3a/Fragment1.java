package com.example.lab3a;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {
    private Controller controller;
    private Button btnColor;
    private TextView tvColor;
    private ListView lvColor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        tvColor = (TextView) view.findViewById(R.id.tvColor);
        btnColor = (Button) view.findViewById(R.id.btnColor);
        lvColor = (ListView) view.findViewById(R.id.lvColor);
        btnColor.setOnClickListener(new ButtonListener());
        lvColor.setOnItemClickListener(new ListViewListener());
        return view;
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    public void setColors(String[] colors){
        lvColor.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, colors));
    }

    public void setButtonColor(int color){
        btnColor.setBackgroundColor(color);
    }

    public void setButtonText(String string){
        btnColor.setText(string);
    }

    public void setTextViewColor(int color){
        tvColor.setBackgroundColor(color);
    }

    private class ButtonListener implements View.OnClickListener {
        public void onClick(View view){
            controller.btnColorClicked(btnColor.getText().toString());
        }
    }

    private class ListViewListener implements AdapterView.OnItemClickListener{
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            controller.listItemClicked(i);
        }
    }

}
