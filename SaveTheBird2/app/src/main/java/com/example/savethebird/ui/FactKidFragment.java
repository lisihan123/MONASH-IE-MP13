package com.example.savethebird.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.savethebird.MainActivity;
import com.example.savethebird.R;


public class FactKidFragment extends Fragment {

    public FactKidFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fact_kids, container, false);
        return rootView;

    }

    public void onResume() {
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle("Threats Hoodies Face");

    }

}