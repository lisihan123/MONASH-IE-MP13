package com.example.savethebird.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.savethebird.R;
import com.google.android.material.tabs.TabItem;


public class WhiteBoradFragment extends Fragment {

    TabItem ti1, ti2, ti3;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.whiteboard, container, false);
        initView(view);
        return  view;
    }

    private void initView(View view){
        ti1 = view. findViewById(R.id.white_board_tab_1);
        ti2 = view.findViewById(R.id.white_board_tab_2);
        ti3 = view.findViewById(R.id.white_board_tab_3);
        TiListner tl = new TiListner();
        ti1.setOnClickListener(tl);
        ti2.setOnClickListener(tl);
        ti3.setOnClickListener(tl);
    }

    class TiListner implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.white_board_tab_1:
                    replaceFragment(new Tab1Fragment());
                    break;
                case R.id.white_board_tab_2:
                    replaceFragment(new Tab2Fragment());
                    break;
                case R.id.white_board_tab_3:
                    replaceFragment(new Tab3Fragment());
                    break;

            }
        }
    }

    public void replaceFragment(Fragment newFragment){
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.nav_host_fragment,newFragment).commit();
    }
}