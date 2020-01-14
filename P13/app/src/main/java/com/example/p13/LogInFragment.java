package com.example.p13;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.p13.ui.main.MainViewModel;


/**
 * Fragment used for loggin in, saving the name+lastname in SharedPreferences
 * @author rasmusoberg
 */
public class LogInFragment extends Fragment {
    private EditText etFirstName;
    private EditText etLastName;
    private Button btnLogin;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String FIRSTNAME = "first";
    public static final String LASTNAME = "last";
    private String firstName;
    private String lastName;
    private MainViewModel mvv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);
        etFirstName = view.findViewById(R.id.etFirstName);
        etLastName = view.findViewById(R.id.etLastName);
        btnLogin = view.findViewById(R.id.btnLogIn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                saveData();
                mvv = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
                FragmentChangeListener fCL =  (FragmentChangeListener)getActivity();
                fCL.replaceFragment();
            }
        });
        loadData();
        updateViews();
        return view;
    }

    public void saveData(){
        SharedPreferences sharedPreferences = this.getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FIRSTNAME, etFirstName.getText().toString());
        editor.putString(LASTNAME, etLastName.getText().toString());
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = this.getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        firstName = sharedPreferences.getString(FIRSTNAME, "");
        lastName = sharedPreferences.getString(LASTNAME, "");
    }

    public void updateViews(){
        etFirstName.setText(firstName);
        etLastName.setText(lastName);
    }

    public interface FragmentChangeListener{
        void replaceFragment();
    }

}
