package com.example.testing;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {
    private TextView text;
    private EditText edit;
    private Button btn, save;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    private String text1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        text = view.findViewById(R.id.tvName);
        edit = view.findViewById(R.id.etName);
        btn = view.findViewById(R.id.btn);
        save = view.findViewById(R.id.btnSave);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                text.setText(edit.getText().toString());
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        loadData();
        updateViews();

        return view;
    }

    //Saves the data
    public void saveData(){
        SharedPreferences sharedPreferences = this.getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT, text.getText().toString());

        editor.apply();
    }

    //Loads the data
    public void loadData(){
        SharedPreferences sharedPreferences = this.getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        text1 = sharedPreferences.getString(TEXT, "");
    }

    //Updates the views
    public void updateViews(){
        text.setText(text1);
    }

}
