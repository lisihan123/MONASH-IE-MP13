package com.example.savethebird.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.savethebird.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class BannerFragment extends Fragment {

    private Banner mBanner;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_banner, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        mBanner = view.findViewById(R.id.mBanner);
        //Image resources
        int[] imageResourceID = new int[]{R.drawable.hp3,R.drawable.hp,R.drawable.hp2};
        List<Integer> imgeList = new ArrayList<>();
        //Slides title
        String[] mtitle = new String[]{"fact1", "fact2", "fact3"};
        List<String> titleList = new ArrayList<>();

        for (int i = 0; i < imageResourceID.length; i++) {
            imgeList.add(imageResourceID[i]);//Put image resources into list
            titleList.add(mtitle[i]);//Put slides title into list
            //Set image loader using glide
            mBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(getContext()).load(path).into(imageView);
                }
            });
            //Set the effect of slides
//          mBanner.setBannerAnimation(Transformer.accordion);
            mBanner.setImages(imgeList);//Set image resources
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);//Set banner style
            mBanner.setBannerTitles(titleList);  //Set titles
            //Set the notification position
            mBanner.setIndicatorGravity(BannerConfig.CENTER);
            mBanner.setDelayTime(5000);//Slide time
            mBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(getContext(), "You click the no." + (position + 1) + "slide", Toast.LENGTH_SHORT).show();
                }
            });//Set listener
            mBanner.start();
        }

    }


}