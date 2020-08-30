package com.example.savethebird.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.example.savethebird.R;
import com.example.savethebird.ui.notifications.NotificationsFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button mtg1, mtg2,mtg3,mtg4,mtg5,mtg6,mtg7;
    private Button mtg1b,mtg2b,mtg3b,mtg4b,mtg5b,mtg6b,mtg7b;
    private Button mbtf1,mbtf2,mbtf3,mbtf4;
    private Switch mswich1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        initView(view);
        return view;
    }

    private void initView(View view){
        mtg1 = view.findViewById(R.id.btn_tag_1);
        mtg2 = view.findViewById(R.id.btn_tag_2);
        mtg3 = view.findViewById(R.id.btn_tag_3);
        mtg4 = view.findViewById(R.id.btn_tag_4);
        mtg5 = view.findViewById(R.id.btn_tag_5);
        mtg6 = view.findViewById(R.id.btn_tag_6);
        mtg7 = view.findViewById(R.id.btn_tag_7);
        mtg2b = view.findViewById(R.id.btn_tag_2back);
        mtg1b = view.findViewById(R.id.btn_tag_1back);
        mtg3b = view.findViewById(R.id.btn_tag_3back);
        mtg4b = view.findViewById(R.id.btn_tag_4back);
        mtg5b = view.findViewById(R.id.btn_tag_5back);
        mtg6b = view.findViewById(R.id.btn_tag_6back);
        mtg7b = view.findViewById(R.id.btn_tag_7back);
        mtg1.setOnClickListener(new Listener());
        mtg2.setOnClickListener(new Listener());
        mtg3.setOnClickListener(new Listener());
        mtg4.setOnClickListener(new Listener());
        mtg5.setOnClickListener(new Listener());
        mtg6.setOnClickListener(new Listener());
        mtg7.setOnClickListener(new Listener());
        mtg1b.setOnClickListener(new Listener());
        mtg2b.setOnClickListener(new Listener());
        mtg3b.setOnClickListener(new Listener());
        mtg4b.setOnClickListener(new Listener());
        mtg5b.setOnClickListener(new Listener());
        mtg6b.setOnClickListener(new Listener());
        mtg7b.setOnClickListener(new Listener());
        mswich1= view.findViewById(R.id.home_switch_hp);
        mswich1.setOnCheckedChangeListener(new Listener());
        mbtf1 = view.findViewById(R.id.fact_1);
        mbtf2 = view.findViewById(R.id.fact_2);
        mbtf3 = view.findViewById(R.id.fact_3);
        mbtf4 = view.findViewById(R.id.fact_4);
        mbtf1.setOnClickListener(new Listener());
        mbtf2.setOnClickListener(new Listener());
        mbtf3.setOnClickListener(new Listener());
        mbtf4.setOnClickListener(new Listener());

    }

    private class Listener implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_tag_1:
                    applyRotation(1,0,90,mtg1,mtg1b);
                    break;

                case R.id.btn_tag_1back:
                    applyRotation(0,0,-90,mtg1b,mtg1);
                    break;
                case R.id.btn_tag_2:
                    applyRotation(1,0,90,mtg2,mtg2b);
                    break;

                case R.id.btn_tag_2back:
                    applyRotation(0,0,-90,mtg2b,mtg2);
                    break;
                case R.id.btn_tag_3:
                    applyRotation(1,0,90,mtg3,mtg3b);
                    break;

                case R.id.btn_tag_3back:
                    applyRotation(0,0,-90,mtg3b,mtg3);
                    break;
                case R.id.btn_tag_4:
                    applyRotation(1,0,90,mtg4,mtg4b);
                    break;

                case R.id.btn_tag_4back:
                    applyRotation(0,0,-90,mtg4b,mtg4);
                    break;
                case R.id.btn_tag_5:
                    applyRotation(1,0,90,mtg5,mtg5b);
                    break;

                case R.id.btn_tag_5back:
                    applyRotation(0,0,-90,mtg5b,mtg5);
                    break;
                case R.id.btn_tag_6:
                    applyRotation(1,0,90,mtg6,mtg6b);
                    break;

                case R.id.btn_tag_6back:
                    applyRotation(0,0,-90,mtg6b,mtg6);
                    break;

                case R.id.btn_tag_7:
                    applyRotation(1,0,90,mtg7,mtg7b);
                    break;

                case R.id.btn_tag_7back:
                    applyRotation(0,0,-90,mtg7b,mtg7);
                    break;
                case R.id.fact_1:
                    replaceFragment(new NotificationsFragment());

            }
        }

        public void replaceFragment(Fragment replaceFragment){
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,replaceFragment).commit();
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b){
                mtg1.setVisibility(View.GONE);
                mtg2.setVisibility(View.GONE);
                mtg3.setVisibility(View.GONE);
                mtg4.setVisibility(View.GONE);
                mtg5.setVisibility(View.GONE);
                mtg6.setVisibility(View.GONE);
                mtg7.setVisibility(View.GONE);
                mtg1b.setVisibility(View.GONE);
                mtg2b.setVisibility(View.GONE);
                mtg3b.setVisibility(View.GONE);
                mtg4b.setVisibility(View.GONE);
                mtg5b.setVisibility(View.GONE);
                mtg6b.setVisibility(View.GONE);
                mtg7b.setVisibility(View.GONE);
            }
                else{
                mtg1.setVisibility(View.VISIBLE);
                mtg2.setVisibility(View.VISIBLE);
                mtg3.setVisibility(View.VISIBLE);
                mtg4.setVisibility(View.VISIBLE);
                mtg5.setVisibility(View.VISIBLE);
                mtg6.setVisibility(View.VISIBLE);
                mtg7.setVisibility(View.VISIBLE);
                mtg1b.setVisibility(View.GONE);
                mtg2b.setVisibility(View.GONE);
                mtg3b.setVisibility(View.GONE);
                mtg4b.setVisibility(View.GONE);
                mtg5b.setVisibility(View.GONE);
                mtg6b.setVisibility(View.GONE);
                mtg7b.setVisibility(View.GONE);

                }


        }
    }

    /**
     * 执行翻转第一阶段翻转动画
     * @param tag view索引
     * @param start 起始角度
     * @param end 结束角度
     */
    private void applyRotation(int tag, float start, float end, Button front, Button back) {
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
        rotation.setAnimationListener(new DisplayNextView(tag,front,back));//设置第一阶段动画监听器
        front.startAnimation(rotation);


    }

    /**
     * 第一阶段动画监听器
     *
     */
    private final class DisplayNextView implements Animation.AnimationListener {
        private final int tag;
        private Button front;
        private Button back;

        private DisplayNextView(int tag, Button front, Button back) {
            this.tag = tag;
            this.front = front;
            this.back = back;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {

            front.setVisibility(View.GONE);
            back.setVisibility(View.VISIBLE);

        }

        public void onAnimationRepeat(Animation animation) {
        }
    }








    }
