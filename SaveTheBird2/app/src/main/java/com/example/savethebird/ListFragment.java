package com.example.savethebird;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.savethebird.ui.FactDogFragment;
import com.example.savethebird.ui.FactKidFragment;
import com.example.savethebird.ui.FactNumberFragment;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    ConstraintLayout mcl1, mcl2, mcl3;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fact_list, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        mcl1 = view.findViewById(R.id.factlist_image1);
        mcl2 = view.findViewById(R.id.factlist_image2);
        mcl3 = view.findViewById(R.id.factlist_image3);
        mcl1.setOnClickListener(new Listener());
        mcl2.setOnClickListener(new Listener());
        mcl3.setOnClickListener(new Listener());


    }

    private class Listener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.factlist_image1:
                    replaceFragment(new FactNumberFragment());
                    break;
                case R.id.factlist_image2:
                    replaceFragment(new FactDogFragment());
                    break;
                case R.id.factlist_image3:
                    replaceFragment(new FactKidFragment());
                    break;


            }
        }
    }

    public void replaceFragment(Fragment newFragment){
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,newFragment).addToBackStack(null).commit();
    }
}