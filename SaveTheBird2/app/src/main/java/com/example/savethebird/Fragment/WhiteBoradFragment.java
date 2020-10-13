package com.example.savethebird.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.HorizontalScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.savethebird.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import net.cachapa.expandablelayout.ExpandableLayout;


public class WhiteBoradFragment extends Fragment {

    TabLayout.Tab ti1, ti2, ti3;
    TabLayout tl1;
    HorizontalScrollView h1, h2, h3;


 // Move it to Community
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.recycler_view_fragment, container, false);
//
//        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(new SimpleAdapter(recyclerView));
//
//        return rootView;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.whiteboard, container, false);
        initView(view);
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


        // Move it to community
//        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(new SimpleAdapter(recyclerView));

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

    class TiListner implements View.OnClickListener{
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
    }

    public void replaceFragment(Fragment newFragment){
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.nav_host_fragment,newFragment).commit();
    }



    // Move it to Community
//    private static class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {
//        private static final int UNSELECTED = -1;
//
//        private RecyclerView recyclerView;
//        private int selectedItem = UNSELECTED;
//
//        public SimpleAdapter(RecyclerView recyclerView) {
//            this.recyclerView = recyclerView;
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View itemView = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.recycler_item, parent, false);
//            return new ViewHolder(itemView);
//        }
//
//        @Override
//        public void onBindViewHolder(ViewHolder holder, int position) {
//            holder.bind();
//        }
//
//        @Override
//        public int getItemCount() {
//            return 100;
//        }
//
//        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ExpandableLayout.OnExpansionUpdateListener {
//            private ExpandableLayout expandableLayout;
//            private TextView expandButton;
//
//            public ViewHolder(View itemView) {
//                super(itemView);
//
//                expandableLayout = itemView.findViewById(R.id.expandable_layout);
//                expandableLayout.setInterpolator(new OvershootInterpolator());
//                expandableLayout.setOnExpansionUpdateListener(this);
//                expandButton = itemView.findViewById(R.id.expand_button);
//
//                expandButton.setOnClickListener(this);
//            }
//
//            public void bind() {
//                int position = getAdapterPosition();
//                boolean isSelected = position == selectedItem;
//
//                expandButton.setText(position + ". Tap to expand");
//                expandButton.setSelected(isSelected);
//                expandableLayout.setExpanded(isSelected, false);
//            }
//
//            @Override
//            public void onClick(View view) {
//                ViewHolder holder = (ViewHolder) recyclerView.findViewHolderForAdapterPosition(selectedItem);
//                if (holder != null) {
//                    holder.expandButton.setSelected(false);
//                    holder.expandableLayout.collapse();
//                }
//
//                int position = getAdapterPosition();
//                if (position == selectedItem) {
//                    selectedItem = UNSELECTED;
//                } else {
//                    expandButton.setSelected(true);
//                    expandableLayout.expand();
//                    selectedItem = position;
//                }
//            }
//
//            @Override
//            public void onExpansionUpdate(float expansionFraction, int state) {
//                Log.d("ExpandableLayout", "State: " + state);
//                if (state == ExpandableLayout.State.EXPANDING) {
//                    recyclerView.smoothScrollToPosition(getAdapterPosition());
//                }
//            }
//        }
//    }
}