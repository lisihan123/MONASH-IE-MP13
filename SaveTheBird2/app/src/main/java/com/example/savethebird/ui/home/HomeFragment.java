package com.example.savethebird.ui.home;

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
import com.example.savethebird.R;
import com.example.savethebird.ui.ComeSoonFragment;
import com.example.savethebird.ui.FactDogFragment;
import com.example.savethebird.ui.FactKidFragment;
import com.example.savethebird.ui.FactNumberFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private Banner mBanner;
    private Button mcall, mlc;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);


       initView(view);
        return view;
    }

//    private void initView(View view){
//        mtg1 = view.findViewById(R.id.btn_tag_1);
//        mtg2 = view.findViewById(R.id.btn_tag_2);
//        mtg3 = view.findViewById(R.id.btn_tag_3);
//        mtg4 = view.findViewById(R.id.btn_tag_4);
//        mtg5 = view.findViewById(R.id.btn_tag_5);
//        mtg6 = view.findViewById(R.id.btn_tag_6);
//        mtg7 = view.findViewById(R.id.btn_tag_7);
//        mtg2b = view.findViewById(R.id.btn_tag_2back);
//        mtg1b = view.findViewById(R.id.btn_tag_1back);
//        mtg3b = view.findViewById(R.id.btn_tag_3back);
//        mtg4b = view.findViewById(R.id.btn_tag_4back);
//        mtg5b = view.findViewById(R.id.btn_tag_5back);
//        mtg6b = view.findViewById(R.id.btn_tag_6back);
//        mtg7b = view.findViewById(R.id.btn_tag_7back);
//        mtg1.setOnClickListener(new Listener());
//        mtg2.setOnClickListener(new Listener());
//        mtg3.setOnClickListener(new Listener());
//        mtg4.setOnClickListener(new Listener());
//        mtg5.setOnClickListener(new Listener());
//        mtg6.setOnClickListener(new Listener());
//        mtg7.setOnClickListener(new Listener());
//        mtg1b.setOnClickListener(new Listener());
//        mtg2b.setOnClickListener(new Listener());
//        mtg3b.setOnClickListener(new Listener());
//        mtg4b.setOnClickListener(new Listener());
//        mtg5b.setOnClickListener(new Listener());
//        mtg6b.setOnClickListener(new Listener());
//        mtg7b.setOnClickListener(new Listener());
//        mswich1= view.findViewById(R.id.home_switch_hp);
//        mswich1.setOnCheckedChangeListener(new Listener());
//        mbtf1 = view.findViewById(R.id.fact_1);
//        mbtf2 = view.findViewById(R.id.fact_2);
//        mbtf3 = view.findViewById(R.id.fact_3);
//        mbtf4 = view.findViewById(R.id.fact_4);
//        mbtf1.setOnClickListener(new Listener());
//        mbtf2.setOnClickListener(new Listener());
//        mbtf3.setOnClickListener(new Listener());
//        mbtf4.setOnClickListener(new Listener());
//
//    }

    private void initView(View view){
        mBanner = view.findViewById(R.id.mBanner);
        //图片资源
        int[] imageResourceID = new int[]{R.drawable.f1,R.drawable.f2,R.drawable.kids};
        List<Integer> imgeList = new ArrayList<>();
        //轮播标题
        String[] mtitle = new String[]{"Only 550 Hooded Plover in Victoria", "Threats from dogs and horses", "Threats from Kids and Pedestrians"};
        List<String> titleList = new ArrayList<>();

        for (int i = 0; i < imageResourceID.length; i++) {
            imgeList.add(imageResourceID[i]);//把图片资源循环放入list里面
            titleList.add(mtitle[i]);//把标题循环设置进列表里面
            //设置图片加载器，通过Glide加载图片
            mBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(getContext()).load(path).into(imageView);
                }
            });
            //设置轮播的动画效果,里面有很多种特效,可以到GitHub上查看文档。
//          mBanner.setBannerAnimation(Transformer.accordion);
            mBanner.setImages(imgeList);//设置图片资源
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);//设置banner显示样式（带标题的样式）
            mBanner.setBannerTitles(titleList); //设置标题集合（当banner样式有显示title时）
            //设置指示器位置（即图片下面的那个小圆点）
            mBanner.setIndicatorGravity(BannerConfig.CENTER);
            mBanner.setDelayTime(5000);//设置轮播时间3秒切换下一图
            mBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
//                    Toast.makeText(getContext(), "你点击了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
                    if(position == 0){

                        replaceFragment(new FactNumberFragment());
                    }
                    if (position==1){
                        replaceFragment(new FactDogFragment());
                    }
                    if (position==2){
                        replaceFragment(new FactKidFragment());

                    }
                }
            });//设置监听
            mBanner.start();//开始进行banner渲染
            mBanner.startAutoPlay();

            mcall = view.findViewById(R.id.btn_call_bird_life);
            mlc = view.findViewById(R.id.btn_life_cycle);
            Listener ls = new Listener();
            mcall.setOnClickListener(ls);
            mlc.setOnClickListener(ls);
        }


    }

    private class Listener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btn_call_bird_life:
                    replaceFragment(new ComeSoonFragment());
                    break;
                case R.id.btn_life_cycle:
                    replaceFragment(new ComeSoonFragment());
                    break;
            }
        }
    }
//        @Override
//        public void onClick(View view) {
//            switch (view.getId()){
//                case R.id.btn_tag_1:
//                    applyRotation(1,0,90,mtg1,mtg1b);
//                    break;
//
//                case R.id.btn_tag_1back:
//                    applyRotation(0,0,-90,mtg1b,mtg1);
//                    break;
//                case R.id.btn_tag_2:
//                    applyRotation(1,0,90,mtg2,mtg2b);
//                    break;
//
//                case R.id.btn_tag_2back:
//                    applyRotation(0,0,-90,mtg2b,mtg2);
//                    break;
//                case R.id.btn_tag_3:
//                    applyRotation(1,0,90,mtg3,mtg3b);
//                    break;
//
//                case R.id.btn_tag_3back:
//                    applyRotation(0,0,-90,mtg3b,mtg3);
//                    break;
//                case R.id.btn_tag_4:
//                    applyRotation(1,0,90,mtg4,mtg4b);
//                    break;
//
//                case R.id.btn_tag_4back:
//                    applyRotation(0,0,-90,mtg4b,mtg4);
//                    break;
//                case R.id.btn_tag_5:
//                    applyRotation(1,0,90,mtg5,mtg5b);
//                    break;
//
//                case R.id.btn_tag_5back:
//                    applyRotation(0,0,-90,mtg5b,mtg5);
//                    break;
//                case R.id.btn_tag_6:
//                    applyRotation(1,0,90,mtg6,mtg6b);
//                    break;
//
//                case R.id.btn_tag_6back:
//                    applyRotation(0,0,-90,mtg6b,mtg6);
//                    break;
//
//                case R.id.btn_tag_7:
//                    applyRotation(1,0,90,mtg7,mtg7b);
//                    break;
//
//                case R.id.btn_tag_7back:
//                    applyRotation(0,0,-90,mtg7b,mtg7);
//                    break;
//                case R.id.fact_1:
//                    replaceFragment(new BannerFragment());
//                    Toast.makeText(getContext(),"Come soon",Toast.LENGTH_LONG);
//                    break;
//                case R.id.fact_2:
//                    Toast.makeText(getContext(),"Come soon",Toast.LENGTH_LONG);
//                    replaceFragment(new FactKidFragment());
//                    break;
//                case R.id.fact_3:
//                    Toast.makeText(getContext(),"Come soon",Toast.LENGTH_LONG);
//                   replaceFragment(new FactDogFragment());
//                    break;
//                case R.id.fact_4:
//                    Toast.makeText(getContext(),"Come soon",Toast.LENGTH_LONG);
//                    replaceFragment(new FactNumberFragment());
//                    break;
//
//
//
//            }
//        }

        public void replaceFragment(Fragment newFragment){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.nav_host_fragment,newFragment).commit();
        }
//
//        @Override
//        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//            if(b){
//                mtg1.setVisibility(View.GONE);
//                mtg2.setVisibility(View.GONE);
//                mtg3.setVisibility(View.GONE);
//                mtg4.setVisibility(View.GONE);
//                mtg5.setVisibility(View.GONE);
//                mtg6.setVisibility(View.GONE);
//                mtg7.setVisibility(View.GONE);
//                mtg1b.setVisibility(View.GONE);
//                mtg2b.setVisibility(View.GONE);
//                mtg3b.setVisibility(View.GONE);
//                mtg4b.setVisibility(View.GONE);
//                mtg5b.setVisibility(View.GONE);
//                mtg6b.setVisibility(View.GONE);
//                mtg7b.setVisibility(View.GONE);
//            }
//                else{
//                mtg1.setVisibility(View.VISIBLE);
//                mtg2.setVisibility(View.VISIBLE);
//                mtg3.setVisibility(View.VISIBLE);
//                mtg4.setVisibility(View.VISIBLE);
//                mtg5.setVisibility(View.VISIBLE);
//                mtg6.setVisibility(View.VISIBLE);
//                mtg7.setVisibility(View.VISIBLE);
//                mtg1b.setVisibility(View.GONE);
//                mtg2b.setVisibility(View.GONE);
//                mtg3b.setVisibility(View.GONE);
//                mtg4b.setVisibility(View.GONE);
//                mtg5b.setVisibility(View.GONE);
//                mtg6b.setVisibility(View.GONE);
//                mtg7b.setVisibility(View.GONE);
//
//                }
//
//
//        }
//    }

    /**
     * 执行翻转第一阶段翻转动画
     * @param tag view索引
     * @param start 起始角度
     * @param end 结束角度
     */
//    private void applyRotation(int tag, float start, float end, Button front, Button back) {
//        // 得到中心点(以中心翻转)
//        //X轴中心点
//        final float centerX = mtg1.getWidth() / 2.0f;
//        //Y轴中心点
//        final float centerY = mtg1.getHeight() / 2.0f;
//        //Z轴中心点
//        final float depthZ = 500.0f;
//        // 根据参数创建一个新的三维动画,并且监听触发下一个动画
//        final Rotate3d rotation = new Rotate3d(start, end, centerX, centerY,depthZ, true);
//        rotation.setDuration(300);//设置动画持续时间
//        rotation.setInterpolator(new AccelerateInterpolator());//设置动画变化速度
//        rotation.setAnimationListener(new DisplayNextView(tag,front,back));//设置第一阶段动画监听器
//        front.startAnimation(rotation);
//
//
//    }

    /**
     * 第一阶段动画监听器
     *
     */
//    private final class DisplayNextView implements Animation.AnimationListener {
//        private final int tag;
//        private Button front;
//        private Button back;
//
//        private DisplayNextView(int tag, Button front, Button back) {
//            this.tag = tag;
//            this.front = front;
//            this.back = back;
//        }
//
//        public void onAnimationStart(Animation animation) {
//        }
//
//        public void onAnimationEnd(Animation animation) {
//
//            front.setVisibility(View.GONE);
//            back.setVisibility(View.VISIBLE);
//
//        }
//
//        public void onAnimationRepeat(Animation animation) {
//        }
//    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        mBanner.stopAutoPlay();
    }
}
