package com.example.savethebird.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.savethebird.R;


public class Tab2Fragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.whiteboard_tab_2, container, false);
        Toast.makeText(getContext(),"Tab2.fragment",Toast.LENGTH_LONG).show();
        return view;
    }
}