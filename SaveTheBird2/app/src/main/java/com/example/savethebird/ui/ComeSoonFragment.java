package com.example.savethebird.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.savethebird.R;
import com.example.savethebird.ui.notifications.NotificationsFragment;


public class ComeSoonFragment extends Fragment {

    private boolean hadIntercept;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.coming_soon, container, false);
//        view.setFocusable(true);
//        view.setFocusableInTouchMode(true);
//        view.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN){
//                    if(i == KeyEvent.KEYCODE_BACK){
//                        getFragmentManager().popBackStack();
//                    return true;
//                    }
//                    return false;
//                }
//                return false;
//            }
//        });
        return view;
    }




}