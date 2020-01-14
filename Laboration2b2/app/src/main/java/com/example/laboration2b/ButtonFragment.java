package com.example.laboration2b;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.widget.Button;



/**
 * A simple {@link Fragment} subclass.
 */
public class ButtonFragment extends Fragment {
    private Button button;
    private OnFragmentInteraction bListener;

    public ButtonFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_button, container, false);

        button = (Button) view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onButtonPressed();
            }
        });

        return view;
    }

    public void onButtonPressed(){
        if(bListener != null){
            bListener.onFragmentInterAction();
        }
    }

    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof OnFragmentInteraction){
            bListener = (OnFragmentInteraction) context;
        }
        else{
            throw new RuntimeException(context.toString() + "must implement OnFragmentInteraction");
        }
    }

    public interface OnFragmentInteraction{
        void onFragmentInterAction();
    }


}
