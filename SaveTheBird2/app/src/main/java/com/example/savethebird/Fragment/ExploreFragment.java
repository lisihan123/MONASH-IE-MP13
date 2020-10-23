package com.example.savethebird.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.savethebird.MainActivity;
import com.example.savethebird.R;

public class ExploreFragment extends Fragment {
    ImageView mig1, mig2;

    public ExploreFragment(){

    }

    @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
              View rootView = inflater.inflate(R.layout.support_explore, container, false);
              initView(rootView);
              return rootView;

      }

    public void onResume() {
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle("Connect with Organisations");

    }

    private void initView(View view){
        mig1 = view.findViewById(R.id.bilrdlife_logo);
        mig2 = view.findViewById(R.id.ebird_logo);
        mig1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://birdlife.org.au/bird-profile/hooded-plover"));
                startActivity(intent);

            }
        });
        mig2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://ebird.org/species/hooplo2"));
                startActivity(intent);
            }
        });
    }

}