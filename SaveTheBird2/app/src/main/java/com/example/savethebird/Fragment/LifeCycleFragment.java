package com.example.savethebird.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

//import com.example.savethebird.Fragment.home.MyAdapter;
import com.example.savethebird.MainActivity;
import com.example.savethebird.R;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.gigamole.infinitecycleviewpager.OnInfiniteCyclePageTransformListener;

import java.util.ArrayList;
import java.util.List;


public class LifeCycleFragment extends Fragment {
    List<Integer> lstImages = new ArrayList<>();

    private int position = 0;
    TextView msg1,msg2,msg3,msg4;
    TextView msgt1, msgt2, msgt3, msgt4;
    ConstraintLayout ml1, ml2, ml3,ml4;
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
        com.example.savethebird.ui.home.MyAdapter adapter = new com.example.savethebird.ui.home.MyAdapter(lstImages, getContext());
        pager.setAdapter(adapter);
        pager.setCurrentItem(position);
        initText(pager);
        pager.setOnInfiniteCyclePageTransformListener(new OnInfiniteCyclePageTransformListener() {
            @Override
            public void onPreTransform(View page, float position) {

            }

            @Override
            public void onPostTransform(View page, float position) {
                if(pager.getRealItem()==0){
//            msg1.setVisibility(View.VISIBLE);
//            msg2.setVisibility(View.GONE);
//            msg3.setVisibility(View.GONE);
//            msg4.setVisibility(View.GONE);
//            msgt1.setVisibility(View.VISIBLE);
//            msgt2.setVisibility(View.GONE);
//            msgt3.setVisibility(View.GONE);
//            msgt4.setVisibility(View.GONE);
            ml1.setVisibility(View.VISIBLE);
            ml2.setVisibility(View.GONE);
            ml3.setVisibility(View.GONE);
            ml4.setVisibility(View.GONE);
        }

        if(pager.getRealItem()==1){
//            msg1.setVisibility(View.GONE);
//            msg2.setVisibility(View.VISIBLE);
//            msg3.setVisibility(View.GONE);
//            msg4.setVisibility(View.GONE);
//            msgt1.setVisibility(View.GONE);
//            msgt2.setVisibility(View.VISIBLE);
//            msgt3.setVisibility(View.GONE);
//            msgt4.setVisibility(View.GONE);
            ml1.setVisibility(View.GONE);
            ml2.setVisibility(View.VISIBLE);
            ml3.setVisibility(View.GONE);
            ml4.setVisibility(View.GONE);
        }
        if(pager.getRealItem()==2){
//            msg1.setVisibility(View.GONE);
//            msg2.setVisibility(View.GONE);
//            msg3.setVisibility(View.VISIBLE);
//            msg4.setVisibility(View.GONE);
//            msgt1.setVisibility(View.GONE);
//            msgt2.setVisibility(View.GONE);
//            msgt3.setVisibility(View.VISIBLE);
//            msgt4.setVisibility(View.GONE);
            ml1.setVisibility(View.GONE);
            ml2.setVisibility(View.GONE);
            ml3.setVisibility(View.VISIBLE);
            ml4.setVisibility(View.GONE);
        }
        if(pager.getRealItem()==3){
//            msg1.setVisibility(View.GONE);
//            msg2.setVisibility(View.GONE);
//            msg3.setVisibility(View.GONE);
//            msg4.setVisibility(View.VISIBLE);
//            msgt1.setVisibility(View.GONE);
//            msgt2.setVisibility(View.GONE);
//            msgt3.setVisibility(View.GONE);
//            msgt4.setVisibility(View.VISIBLE);
            ml1.setVisibility(View.GONE);
            ml2.setVisibility(View.GONE);
            ml3.setVisibility(View.GONE);
            ml4.setVisibility(View.VISIBLE);
        }
            }
        });
        return view;
    }

    public void onResume() {
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle("Analysis For Each Stage");

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
        msgt1 = view.findViewById(R.id.tv_eggs_title);
        msgt2 = view.findViewById(R.id.tv_chick_title);
        msgt3 = view.findViewById(R.id.tv_juvenile_title);
        msgt4 = view.findViewById(R.id.tv_adult_title);
        ml1=view.findViewById(R.id.eggs_details);
        ml3 = view.findViewById(R.id.juvenile_details);
        ml2 = view.findViewById(R.id.chick_details);
        ml4 = view.findViewById(R.id.adult_details);


    }

    private void initText(HorizontalInfiniteCycleViewPager pager){
        if(pager.getRealItem()==0){
//            msg1.setVisibility(View.VISIBLE);
//            msg2.setVisibility(View.GONE);
//            msg3.setVisibility(View.GONE);
//            msg4.setVisibility(View.GONE);
//            msgt1.setVisibility(View.VISIBLE);
//            msgt2.setVisibility(View.GONE);
//            msgt3.setVisibility(View.GONE);
//            msgt4.setVisibility(View.GONE);
            ml1.setVisibility(View.VISIBLE);
            ml2.setVisibility(View.GONE);
            ml3.setVisibility(View.GONE);
            ml4.setVisibility(View.GONE);
        }

        if(pager.getRealItem()==1){
//            msg1.setVisibility(View.GONE);
//            msg2.setVisibility(View.VISIBLE);
//            msg3.setVisibility(View.GONE);
//            msg4.setVisibility(View.GONE);
//            msgt1.setVisibility(View.GONE);
//            msgt2.setVisibility(View.VISIBLE);
//            msgt3.setVisibility(View.GONE);
//            msgt4.setVisibility(View.GONE);
            ml1.setVisibility(View.GONE);
            ml2.setVisibility(View.VISIBLE);
            ml3.setVisibility(View.GONE);
            ml4.setVisibility(View.GONE);
        }
        if(pager.getRealItem()==2){
//            msg1.setVisibility(View.GONE);
//            msg2.setVisibility(View.GONE);
//            msg3.setVisibility(View.VISIBLE);
//            msg4.setVisibility(View.GONE);
//            msgt1.setVisibility(View.GONE);
//            msgt2.setVisibility(View.GONE);
//            msgt3.setVisibility(View.VISIBLE);
//            msgt4.setVisibility(View.GONE);
            ml1.setVisibility(View.GONE);
            ml2.setVisibility(View.GONE);
            ml3.setVisibility(View.VISIBLE);
            ml4.setVisibility(View.GONE);
        }
        if(pager.getRealItem()==3){
//            msg1.setVisibility(View.GONE);
//            msg2.setVisibility(View.GONE);
//            msg3.setVisibility(View.GONE);
//            msg4.setVisibility(View.VISIBLE);
//            msgt1.setVisibility(View.GONE);
//            msgt2.setVisibility(View.GONE);
//            msgt3.setVisibility(View.GONE);
//            msgt4.setVisibility(View.VISIBLE);
            ml1.setVisibility(View.GONE);
            ml2.setVisibility(View.GONE);
            ml3.setVisibility(View.GONE);
            ml4.setVisibility(View.VISIBLE);
        }

    }



}