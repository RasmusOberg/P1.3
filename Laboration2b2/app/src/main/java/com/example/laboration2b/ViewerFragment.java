package com.example.laboration2b;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewerFragment extends Fragment {
    private VFragmentInterface vListener;
    private TextView textView;

    public ViewerFragment() {
        // Required empty public constructor
    }

    public interface VFragmentInterface{
        void test();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewer, container, false);
        //Connects the XML-file to this java-file

        textView = (TextView) view.findViewById(R.id.tv2);
        //Connects the XML-tv to the java-tv

        return view;
    }

    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof VFragmentInterface){
            vListener = (VFragmentInterface) context;
        }else{
            throw new RuntimeException(context.toString() + "must implement VFragmentInterface");

        }
    }

    public void updateText(String string){
        textView.setText(string);
    }

}
