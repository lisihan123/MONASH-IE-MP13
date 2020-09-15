package com.example.savethebird;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.savethebird.R;
import com.example.savethebird.ui.home.MyAdapter;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.gigamole.infinitecycleviewpager.OnInfiniteCyclePageTransformListener;

import java.util.ArrayList;
import java.util.List;


public class LifeCycleFragment extends Fragment {
    List<Integer> lstImages = new ArrayList<>();

    private int position = 0;
    TextView msg1,msg2,msg3,msg4;

    public LifeCycleFragment(int position){
        this.position = position;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.life_cycle, container, false);
        initData(view);
        HorizontalInfiniteCycleViewPager pager = view.findViewById(R.id.horizontal_cycle);
        MyAdapter adapter = new MyAdapter(lstImages, getContext());
        pager.setAdapter(adapter);
        pager.setCurrentItem(position);
//        initText();
        pager.setOnInfiniteCyclePageTransformListener(new OnInfiniteCyclePageTransformListener() {
            @Override
            public void onPreTransform(View page, float position) {

            }

            @Override
            public void onPostTransform(View page, float position) {
                if(pager.getRealItem()==0){
            msg1.setVisibility(View.VISIBLE);
            msg2.setVisibility(View.GONE);
            msg3.setVisibility(View.GONE);
            msg4.setVisibility(View.GONE);
        }

        if(pager.getRealItem()==1){
            msg1.setVisibility(View.GONE);
            msg2.setVisibility(View.VISIBLE);
            msg3.setVisibility(View.GONE);
            msg4.setVisibility(View.GONE);
        }
        if(pager.getRealItem()==2){
            msg1.setVisibility(View.GONE);
            msg2.setVisibility(View.GONE);
            msg3.setVisibility(View.VISIBLE);
            msg4.setVisibility(View.GONE);
        }
        if(pager.getRealItem()==3){
            msg1.setVisibility(View.GONE);
            msg2.setVisibility(View.GONE);
            msg3.setVisibility(View.GONE);
            msg4.setVisibility(View.VISIBLE);
        }
            }
        });



        return view;
    }

    public void onResume() {
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle("Life Cycle");

    }

    private void initData(View view) {
        lstImages.add(R.drawable.lifecycle_bg_eggs);
        lstImages.add(R.drawable.lifecycle_bg_chick);
        lstImages.add(R.drawable.lifecycle_bg_juvenie);
        lstImages.add(R.drawable.lifecycle_bg_adult);
        msg1 = view.findViewById(R.id.tv_stage_1);
        msg2 = view.findViewById(R.id.tv_stage_2);
        msg3 = view.findViewById(R.id.tv_stage_3);
        msg4 = view.findViewById(R.id.tv_stage_4);


    }



}