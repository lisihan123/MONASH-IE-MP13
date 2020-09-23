package com.example.savethebird.ui.MoreInfo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.savethebird.MainActivity;
import com.example.savethebird.R;
import com.example.savethebird.ui.ExploreFragment;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;


public class MoreInfoFragment extends Fragment {

    private VideoView mvideo;

    public static final String API_KEY = "AIzaSyBai8hbR5hqPQEltv0pxK3WhB3CPtYnpx0";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        initView(root);

//        initVideo(root);

        
        return root;
    }

    public void onResume() {
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle("More Info");

    }

//    private void initYoutube() {
//        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.add(R.id.youtube_layout,  youTubePlayerFragment).commit();
//        youTubePlayerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                if (!b) {
//                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
//                    youTubePlayer.loadVideo("u7wJPZtBdWQ");
//                    youTubePlayer.play();
//                }
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//                String errorMessage = youTubeInitializationResult.toString();
//                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
//                Log.d("errorMessage:", errorMessage);
//            }
//        });
//    }

    private void initView(View view){
        Button mtbn_call = view.findViewById(R.id.btn_more_support);
        Button mtbn_explore = view.findViewById(R.id.btn_more_explore);
//        Button mbtn_about = view.findViewById(R.id.btn_more_about_us);
        mtbn_call.setOnClickListener(new Listener());
        mtbn_explore.setOnClickListener(new Listener());
//        mbtn_about.setOnClickListener(new Listener());



    }

//    private void initVideo(View view){
//        mvideo = view.findViewById(R.id.youtube_layout);
//        String path = "file:///android_asset/HoodedPloverSongVideo.mp4";
//        String url = "android.resource://" + getContext().getPackageName()+"/"+
//        Uri uri = Uri.parse(url);
//        mvideo.setVideoURI(uri);
//        mvideo.setMediaController(new MediaController(getContext()));
//        mvideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mediaPlayer) {
//                mvideo.start();
//            }
//        });
//
//
//
//
//    }

    private class Listener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_more_support:
                    AlertDialog.Builder build = new AlertDialog.Builder(getContext());
                    build.setTitle("Call").setMessage("Contact Birdlife Australia if you want to report a Hooded Plover sighting/incident or are interested in being a part of the project.")
                            .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String number = "0405503888";
                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                    Uri data = Uri.parse("tel:" + number);
                                    intent.setData(data);
                                    startActivity(intent);
                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getContext(),"Cancel",Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                    break;
                case R.id.btn_more_explore:
                    replaceFragment(new ExploreFragment());
                    break;
//                case R.id.btn_more_about_us:
//                    replaceFragment(new ComeSoonFragment());
//                    break;

            }
        }
        @SuppressWarnings("deprecation")
        public void replaceFragment(Fragment newFragment){

            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,newFragment).addToBackStack("tag").commit();

        }




    }}