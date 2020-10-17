package com.example.savethebird.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.example.savethebird.MainActivity;
import com.example.savethebird.R;

import net.cachapa.expandablelayout.ExpandableLayout;


public class CommunityFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.community_list, container, false);

        initView(rootView);
        return rootView;
    }


    private  void initView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new SimpleAdapter(recyclerView));
    }



    public void onResume() {
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle("Join Communities");

    }


    private static class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {
        private static final int UNSELECTED = -1;

        private RecyclerView recyclerView;
        private int selectedItem = UNSELECTED;

        public SimpleAdapter(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;


        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView;
            if (viewType == 0) { // for call layout
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_0, parent, false);
                return new ViewHolder(itemView);

            }
            else if (viewType == 1) { // for call layout
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_1, parent, false);
                return new ViewHolder(itemView);

            }
            else if (viewType == 2) { // for call layout
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_2, parent, false);
                return new ViewHolder(itemView);

            }
            else if (viewType == 3) { // for call layout
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_3, parent, false);
                return new ViewHolder(itemView);

            } else { // for email layout
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_4, parent, false);
                return new ViewHolder(itemView);
            }

//            View itemView = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.recycler_item_0, parent, false);


//            return new ViewHolder(itemView);
        }


        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind();
        }

        @Override
        public int getItemCount() {
            return 5;
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ExpandableLayout.OnExpansionUpdateListener {
            private ExpandableLayout expandableLayout;
            private TextView expandButton;

            public ViewHolder(View itemView) {
                super(itemView);

                expandableLayout = itemView.findViewById(R.id.expandable_layout);
                expandableLayout.setInterpolator(new OvershootInterpolator());
                expandableLayout.setOnExpansionUpdateListener(this);
                expandButton = itemView.findViewById(R.id.expand_button);

                expandButton.setOnClickListener(this);
            }

            public void bind() {
                int position = getAdapterPosition();
                boolean isSelected = position == selectedItem;
                String[] title = new String[]{};
                switch (position){
                    case 0:
                        expandButton.setText(R.string.community_0_title);
                        break;
                    case 1:
                        expandButton.setText(R.string.community_1_title);
                        break;
                    case 2:
                        expandButton.setText(R.string.community_2_title);
                        break;
                    case 3:
                        expandButton.setText(R.string.community_3_title);
                        break;
                    case 4:
                        expandButton.setText(R.string.community_4_title);
                        break;


                }

//                expandButton.setText(position + ". Tap to expand");
                expandButton.setSelected(isSelected);
                expandableLayout.setExpanded(isSelected, false);
            }

            @Override
            public void onClick(View view) {
                ViewHolder holder = (ViewHolder) recyclerView.findViewHolderForAdapterPosition(selectedItem);
                if (holder != null) {
                    holder.expandButton.setSelected(false);
                    holder.expandableLayout.collapse();
                }

                int position = getAdapterPosition();
                if (position == selectedItem) {
                    selectedItem = UNSELECTED;
                } else {
                    expandButton.setSelected(true);
                    expandableLayout.expand();
                    selectedItem = position;
                }
            }

            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
                Log.d("ExpandableLayout", "State: " + state);
                if (state == ExpandableLayout.State.EXPANDING) {
                    recyclerView.smoothScrollToPosition(getAdapterPosition());
                }
            }
        }
    }
}