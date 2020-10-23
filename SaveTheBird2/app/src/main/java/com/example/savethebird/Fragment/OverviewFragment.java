package com.example.savethebird.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.savethebird.MainActivity;
import com.example.savethebird.R;


public class OverviewFragment extends Fragment {
    ImageView migl1, migl2,migl3,migl4;


    public OverviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.lifecycle_overview, container, false);
        initView(view);
        return view;
    }

    public void onResume() {
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle("Life Cycle Overview");

    }

    private  void initView(View view){
        migl1=view.findViewById(R.id.imageView_egg);
        migl2=view.findViewById(R.id.imageView_chick);
        migl3=view.findViewById(R.id.imageView_juvenie);
        migl4 = view.findViewById(R.id.imageView_adult);
        OverListener ls = new OverListener();
        migl1.setOnClickListener(ls);
        migl2.setOnClickListener(ls);
        migl3.setOnClickListener(ls);
        migl4.setOnClickListener(ls);

    }

    class OverListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.imageView_egg:
                    replaceFragment(new LifeCycleFragment(0));
                    break;
                case R.id.imageView_chick:
                    replaceFragment(new LifeCycleFragment(1));
                    break;
                case R.id.imageView_juvenie:
                    replaceFragment(new LifeCycleFragment(2));
                    break;
                case R.id.imageView_adult:
                    replaceFragment(new LifeCycleFragment(3));
                    break;


            }
        }
    }

    public void replaceFragment(Fragment newFragment){
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.nav_host_fragment,newFragment).commit();
    }
}