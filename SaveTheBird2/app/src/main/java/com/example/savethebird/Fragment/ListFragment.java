package com.example.savethebird.Fragment;

import android.os.Bundle;

import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.savethebird.MainActivity;
import com.example.savethebird.R;
import com.example.savethebird.Fragment.MoreInfo.MoreInfoFragment;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.view.View.*;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    ConstraintLayout mcl1, mcl2, mcl3;
    ActionMenuItemView btnMore;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fact_list, container, false);
        initView(view);
//        init(view);

        return view;
    }

    // create the info icon button on the top right corner of the screen
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Fragment myFragment = getFragmentManager().findFragmentById(R.id.nav_host_fragment);

        if (myFragment != null && myFragment instanceof ListFragment) {
            inflater.inflate(R.menu.mymenu, menu);

        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    // handle info icon button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mybutton) {

            // do something here
            btnMore = getActivity().findViewById(R.id.mybutton);
            LayoutInflater layoutInflater
                    = (LayoutInflater)getContext()
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = layoutInflater.inflate(R.layout.button_popout, null);
            final PopupWindow popupWindow = new PopupWindow(
                    popupView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setFocusable(true);
            popupWindow.setTouchable(true);
            popupWindow.showAsDropDown(btnMore, 50, -30);
            Button more = popupView.findViewById(R.id.more_info);
            more.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
//                    replaceFragment(new MoreInfoFragment());
                    Navigation.findNavController(getView()).navigate(R.id.navigation_notifications);
                    popupWindow.dismiss();

                }
            });



        }
        return super.onOptionsItemSelected(item);
    }

    private void initView(View view){
        mcl1 = view.findViewById(R.id.factlist_image1);
        mcl2 = view.findViewById(R.id.factlist_image2);
        mcl3 = view.findViewById(R.id.factlist_image3);
        mcl1.setOnClickListener(new Listener());
        mcl2.setOnClickListener(new Listener());
        mcl3.setOnClickListener(new Listener());


    }

    private class Listener implements OnClickListener{

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

    public void onResume() {
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle("Threats To Hoodies");

    }

    @SuppressWarnings("deprecation")
    public void replaceFragment(Fragment newFragment){
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,newFragment).addToBackStack("tag").commit();
    }

}
