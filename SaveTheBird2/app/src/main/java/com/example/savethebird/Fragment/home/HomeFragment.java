package com.example.savethebird.Fragment.home;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;

import com.example.savethebird.Fragment.ListFragment;
import com.example.savethebird.Fragment.CommunityFragment;
import com.example.savethebird.Fragment.WhiteBoardFragment;
import com.example.savethebird.MainActivity;
import com.example.savethebird.Fragment.OverviewFragment;



import com.example.savethebird.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;



public class HomeFragment extends Fragment {

    List<Integer> lstImages = new ArrayList<>();

    private HomeViewModel homeViewModel;

    private Banner mBanner;
    private Button mcall, mlc, mwhite, mCommu;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);


       initView(view);

        Fragment myFragment = getFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (myFragment != null && myFragment instanceof HomeFragment) {

            ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

            int count = getFragmentManager().getBackStackEntryCount();
            for(int i = 0; i < count; ++i) {
                FragmentManager.BackStackEntry b = getFragmentManager().getBackStackEntryAt(i);
                getFragmentManager().popBackStack();
                int a = getFragmentManager().getBackStackEntryCount();
            }
        }
        return view;
    }

    public void onResume() {
        super.onResume();

        ((MainActivity) getActivity())
                .setHomeActionBarTitle("Save The Hoodie");

    }


    private void initView(View view){
        mBanner = view.findViewById(R.id.mBanner);
        //Image resource
        int[] imageResourceID = new int[]{R.drawable.hp3,R.drawable.hp,R.drawable.hp2};
        List<Integer> imgeList = new ArrayList<>();
        //slide title
        String[] mtitle = new String[]{"\"Hooded Plover at Haycock Beach, near Eden\" by Leo ", "\"Hooded Plover\" by Laurie R B", "\"Hooded Plover foraging at Haycock Beach\" by Leo"};
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
            mBanner.setBannerTitles(titleList); //Set titles
            //Set the notification position
            mBanner.setIndicatorGravity(BannerConfig.CENTER);
            mBanner.setDelayTime(5000);//Slide time
//            mBanner.setOnBannerListener(new OnBannerListener() {
//                @Override
//                public void OnBannerClick(int position) {
////                    Toast.makeText(getContext(), "You click the no." + (position + 1) + "slide", Toast.LENGTH_SHORT).show();
//                    if(position == 0){
//
//                        replaceFragment(new FactNumberFragment());
//                    }
//                    if (position==1){
//                        replaceFragment(new FactDogFragment());
//                    }
//                    if (position==2){
//                        replaceFragment(new FactKidFragment());
//
//                    }
//                }
//            });//Set listener
            mBanner.start();
            mBanner.startAutoPlay();

            mcall = view.findViewById(R.id.btn_factlist);
            mlc = view.findViewById(R.id.btn_life_cycle);
            Listener ls = new Listener();
            mcall.setOnClickListener(ls);
            mlc.setOnClickListener(ls);
            mwhite = view.findViewById(R.id.btn_poster);
            mwhite.setOnClickListener(ls);
            mCommu = view.findViewById(R.id.btn_community);
            mCommu.setOnClickListener(ls);
        }


    }

    private class Listener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btn_factlist:
                    replaceFragment(new ListFragment());
                    break;
                case R.id.btn_life_cycle:
                    replaceFragment(new OverviewFragment());
                    break;
                case R.id.btn_poster:
                    replaceFragment(new WhiteBoardFragment());
                    break;
                case R.id.btn_community:
                    replaceFragment(new CommunityFragment());
                    break;
            }
        }
    }
        public void replaceFragment(Fragment newFragment){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.nav_host_fragment,newFragment).commit();
        }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBanner.stopAutoPlay();
    }
}
