package com.example.savethebird.Fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.savethebird.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class WhiteBoradFragment extends Fragment {

    TabLayout.Tab ti1, ti2, ti3;
    TabLayout tl1;
    HorizontalScrollView h1, h2, h3;
    ImageView miv;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.whiteboard, container, false);
        initView(view);
        initWhiteBoard(view);
        return  view;
    }

    private void initView(View view){
//        ti1 = view.findViewById(R.id.white_board_tab_1);
//        ti2 = view.findViewById(R.id.white_board_tab_2);
//        ti3 = view.findViewById(R.id.white_board_tab_3);
//        TiListner tl = new TiListner();
//        ti1.setOnClickListener(tl);
//        ti2.setOnClickListener(tl);
//        ti3.setOnClickListener(tl);
        h1=view.findViewById(R.id.tab_1_layout);
        h2=view.findViewById(R.id.tab_2_layout);
        h3=view.findViewById(R.id.tab_3_layout);


        tl1 = view.findViewById(R.id.tab_layout);
        tl1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()){
                    case 0:
                        h1.setVisibility(View.VISIBLE);
                        h2.setVisibility(View.GONE);
                        h3.setVisibility(View.GONE);

                        break;
                    case 1:
                        h1.setVisibility(View.GONE);
                        h2.setVisibility(View.VISIBLE);
                        h3.setVisibility(View.GONE);
                        break;
                    case 2:
                        h1.setVisibility(View.GONE);
                        h2.setVisibility(View.GONE);
                        h3.setVisibility(View.VISIBLE);
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    class TiListner implements View.OnClickListener, View.OnTouchListener {
        Bitmap img;
        Canvas canvas;
        Paint p = new Paint();
        float lastx, lasty;
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

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(img == null){//只创建一次对象
                //创建Bitmap
                img = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
                //创建画布
                canvas = new Canvas(img);
            }
            //获取动作类型
            int action = event.getAction();
            //按下
            if(action == MotionEvent.ACTION_DOWN){
                //记录坐标
                lastx = event.getX();
                lasty = event.getY();
            }else if(action == MotionEvent.ACTION_MOVE){
                //获取坐标
                float curx = event.getX();
                float cury = event.getY();
                p.setColor(Color.RED);
                //画线
                canvas.drawLine(lastx, lasty, curx, cury, p);
                //记录坐标
                lastx = curx;
                lasty = cury;
                //显示Bitmap到ImageView上
                ImageView showView = (ImageView)v;
                showView.setImageBitmap(img);
            }
            return true;
        }
    }

    public void replaceFragment(Fragment newFragment){
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.nav_host_fragment,newFragment).commit();
    }

    private void initWhiteBoard(View view){
        miv=view.findViewById(R.id.white_board_image_view);
        TiListner ls = new TiListner();
        miv.setOnTouchListener(ls);
    }
}