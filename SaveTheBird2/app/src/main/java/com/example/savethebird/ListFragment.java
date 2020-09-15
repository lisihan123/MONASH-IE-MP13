package com.example.savethebird;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.barnettwong.dragfloatactionbuttonlibrary.view.DragFloatActionButton;
import com.example.savethebird.ui.FactDogFragment;
import com.example.savethebird.ui.FactKidFragment;
import com.example.savethebird.ui.FactNumberFragment;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    ConstraintLayout mcl1, mcl2, mcl3;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fact_list, container, false);
        initView(view);
        init(view);
        return view;
    }

    private void initView(View view){
        mcl1 = view.findViewById(R.id.factlist_image1);
        mcl2 = view.findViewById(R.id.factlist_image2);
        mcl3 = view.findViewById(R.id.factlist_image3);
        mcl1.setOnClickListener(new Listener());
        mcl2.setOnClickListener(new Listener());
        mcl3.setOnClickListener(new Listener());


    }

    private class Listener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.factlist_image1:
                    replaceFragment(new FactNumberFragment());
                    break;
                case R.id.factlist_image2:
                    replaceFragment(new FactDogFragment());
                    break;
                case R.id.factlist_image3:
                    replaceFragment(new FactKidFragment());
                    break;


            }
        }
    }

    @SuppressWarnings("deprecation")
    public void replaceFragment(Fragment newFragment){
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,newFragment).addToBackStack("tag").commit();
    }

    private void init(View view)
    {
        DragFloatActionButton btnOpenPopup = view.findViewById(R.id.circle_button);
        btnOpenPopup.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater
                        = (LayoutInflater)getContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.button_popout, null);
                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);

//                Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
//                btnDismiss.setOnClickListener(new Button.OnClickListener(){
//
//                    @Override
//                    public void onClick(View v) {
//                        // TODO Auto-generated method stub
//                        popupWindow.dismiss();
//                    }});

                popupWindow.showAsDropDown(btnOpenPopup, 50, -30);

            }});
    }

}
