package com.example.savethebird.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.savethebird.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button mtg1, mtg2;
    private Button mtg1b;
    private boolean IsBack = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        initView(view);
        setOnClick();
        return view;
    }

    private void initView(View view){
        mtg1 = view.findViewById(R.id.btn_tag_1);
        mtg2 = view.findViewById(R.id.btn_tag_2);
        mtg1b = view.findViewById(R.id.btn_tag_1back);
    }

    private void setOnClick(){
        mtg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(IsBack){
                    //第一阶段翻转
                    applyRotation(1,0,90);
                    IsBack = true;
                }else{
                    //第一阶段翻转
                    applyRotation(0,0,-90);
                    IsBack= false;
                }
            }
        });
    }

    /**
     * 执行翻转第一阶段翻转动画
     * @param tag view索引
     * @param start 起始角度
     * @param end 结束角度
     */
    private void applyRotation(int tag, float start, float end) {
        // 得到中心点(以中心翻转)
        //X轴中心点
        final float centerX = mtg1.getWidth() / 2.0f;
        //Y轴中心点
        final float centerY = mtg1.getHeight() / 2.0f;
        //Z轴中心点
        final float depthZ = 500.0f;
        // 根据参数创建一个新的三维动画,并且监听触发下一个动画
        final Rotate3d rotation = new Rotate3d(start, end, centerX, centerY,depthZ, true);
        rotation.setDuration(300);//设置动画持续时间
        rotation.setInterpolator(new AccelerateInterpolator());//设置动画变化速度
        rotation.setAnimationListener(new DisplayNextView(tag));//设置第一阶段动画监听器
        mtg1.startAnimation(rotation);


    }

    /**
     * 第一阶段动画监听器
     *
     */
    private final class DisplayNextView implements Animation.AnimationListener {
        private final int tag;

        private DisplayNextView(int tag) {
            this.tag = tag;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            //第一阶段动画结束时，也就是整个Activity垂直于手机屏幕，
            //执行第二阶段动画
            mtg1.setVisibility(View.GONE);
            mtg1b.setVisibility(View.VISIBLE);
            if(mtg2.getVisibility()==View.VISIBLE)
            {System.out.println("显示");}
            //调整两个界面各自的visibility
//            adjustVisiable();
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }








    }
