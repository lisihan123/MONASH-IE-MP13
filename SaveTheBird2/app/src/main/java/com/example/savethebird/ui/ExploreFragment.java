package com.example.savethebird.ui;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.savethebird.R;
import com.facebook.share.model.ShareLinkContent;

//https://blog.csdn.net/qq_41545435/article/details/88601716
//https://developers.facebook.com/docs/sharing/android
public class SupportFragment extends Fragment {


    public SupportFragment(){

    }

    @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
              View rootView = inflater.inflate(R.layout.support_support, container, false);
              return rootView;

      }
//      public void fbShare(){
//          ShareLinkContent content = new ShareLinkContent.Builder()
//                  .setContentUrl(Uri.parse("https://developers.facebook.com"))
//                  .build();
//
//      }
}