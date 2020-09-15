package com.example.savethebird;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.savethebird.R;
import com.example.savethebird.ui.home.MyAdapter;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.util.ArrayList;
import java.util.List;


public class LifeCycleFragment extends Fragment {
    List<Integer> lstImages = new ArrayList<>();

    private int position = 0;

    public LifeCycleFragment(int position){
        this.position = position;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.life_cycle, container, false);
        initData();
        HorizontalInfiniteCycleViewPager pager = view.findViewById(R.id.horizontal_cycle);
        MyAdapter adapter = new MyAdapter(lstImages, getContext());
        pager.setAdapter(adapter);
        pager.setCurrentItem(position);

        return view;
    }

    private void initData() {
        lstImages.add(R.drawable.lifecycle_bg_eggs);
        lstImages.add(R.drawable.lifecycle_bg_chick);
        lstImages.add(R.drawable.lifecycle_bg_juvenie);
        lstImages.add(R.drawable.lifecycle_bg_adult);

    }

}