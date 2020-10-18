package com.example.savethebird.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.savethebird.MainActivity;
import com.example.savethebird.R;
import com.google.android.material.tabs.TabLayout;
import com.mapbox.api.geocoding.v5.GeocodingCriteria;
import com.mapbox.api.geocoding.v5.MapboxGeocoding;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.api.geocoding.v5.models.GeocodingResponse;
import com.mapbox.core.exceptions.ServicesException;
import com.mapbox.geojson.Point;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class WhiteBoradFragment extends Fragment {

    ActionMenuItemView btnMore;
    TabLayout tl1;
    HorizontalScrollView h1, h2, h3;
    ViewGroup containerVg;
    List<ImageView> list = new ArrayList<ImageView>();
    ImageView mtiA1, mtiA2, mtiA3, mtiA4, mtiA5, mtiB1, mtiB2, mtiB3, mtiB4, mtiB5, mtiB6, mtiB7, mtiB8, mtiB9, mtiB10, mtiC1, mtiC2, mtiC3, mtiC4, mtiC5;
    private static final String MAPBOX_ACCESS_TOKEN = "pk.eyJ1IjoibHd1dTAwMjEiLCJhIjoiY2tlZmYwcXR4MGsyODMzdXEyeGhlM21taiJ9.V4hkxkJ5mhH0NMCWoldlyw";
    TextView mtextPlace;
    String placeName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.whiteboard, container, false);
        initWhiteBoard(view);
        initView(view);
        addToImageList(view);
        Geocoding();

        mtextPlace.setText(placeName);

        return  view;
    }



    private void initView(View view){

        h1=view.findViewById(R.id.tab_1_layout);
        h2=view.findViewById(R.id.tab_2_layout);
        h3=view.findViewById(R.id.tab_3_layout);
        mtextPlace = view.findViewById(R.id.white_board_location);

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


    // create the info icon button on the top right corner of the screen
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Fragment myFragment = getFragmentManager().findFragmentById(R.id.nav_host_fragment);

        if (myFragment != null && myFragment instanceof WhiteBoradFragment) {
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
            View popupView = layoutInflater.inflate(R.layout.button_popout_whiteboard, null);
            final PopupWindow popupWindow = new PopupWindow(
                    popupView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setFocusable(true);
            popupWindow.setTouchable(true);
            popupWindow.showAsDropDown(btnMore, 50, -30);
//            Button more = popupView.findViewById(R.id.more_info);
//            more.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    replaceFragment(new MoreInfoFragment());
//                    popupWindow.dismiss();
//                }
//            });



        }
        return super.onOptionsItemSelected(item);
    }
    private void addToImageList(View view){
        mtiA1 = view.findViewById(R.id.white_board_tab_1_item_1);
        mtiA2 = view.findViewById(R.id.white_board_tab_1_item_2);
        mtiA3= view.findViewById(R.id.white_board_tab_1_item_3);
        mtiA4= view.findViewById(R.id.white_board_tab_1_item_4);
        mtiA5 = view.findViewById(R.id.white_board_tab_1_item_5);
        mtiB1 = view.findViewById(R.id.white_board_tab_2_item_1);
        mtiB2 = view.findViewById(R.id.white_board_tab_2_item_2);
        mtiB3= view.findViewById(R.id.white_board_tab_2_item_3);
        mtiB4= view.findViewById(R.id.white_board_tab_2_item_4);
        mtiB5 = view.findViewById(R.id.white_board_tab_2_item_6);
        mtiB6 = view.findViewById(R.id.white_board_tab_2_item_7);
        mtiB7 = view.findViewById(R.id.white_board_tab_2_item_8);
        mtiB8= view.findViewById(R.id.white_board_tab_2_item_9);
        mtiB9= view.findViewById(R.id.white_board_tab_2_item_10);
        mtiB10 = view.findViewById(R.id.white_board_tab_2_item_5);
        mtiC1 = view.findViewById(R.id.white_board_tab_3_item_1);
        mtiC2 = view.findViewById(R.id.white_board_tab_3_item_2);
        mtiC3= view.findViewById(R.id.white_board_tab_3_item_3);
        mtiC4= view.findViewById(R.id.white_board_tab_3_item_4);
        mtiC5 = view.findViewById(R.id.white_board_tab_3_item_5);
        list.add(mtiA1);
        list.add(mtiA2);
        list.add(mtiA3);
        list.add(mtiA4);
        list.add(mtiA5);
        list.add(mtiB1);
        list.add(mtiB2);
        list.add(mtiB3);
        list.add(mtiB4);
        list.add(mtiB5);
        list.add(mtiB6);
        list.add(mtiB7);
        list.add(mtiB8);
        list.add(mtiB9);
        list.add(mtiB10);
        list.add(mtiC1);
        list.add(mtiC2);
        list.add(mtiC3);
        list.add(mtiC4);
        list.add(mtiC5);
        ImageListener ls = new ImageListener();
        for(int i=0; i<list.size(); i++){
            list.get(i).setOnClickListener(ls);
        }




    }


    private class ImageListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.white_board_tab_1_item_1:
                    putWhiteBoard(R.drawable.item1_egg);
                    break;
                case R.id.white_board_tab_1_item_2:
                    putWhiteBoard(R.drawable.item1_chick);
                    break;
                case R.id.white_board_tab_1_item_3:
                    putWhiteBoard(R.drawable.item1_hoodiefamily);
                    break;
                case R.id.white_board_tab_1_item_4:
                    putWhiteBoard(R.drawable.item1_hoodie);
                    break;
                case R.id.white_board_tab_1_item_5:
                    putWhiteBoard(R.drawable.item1_hoodielogo);
                    break;
                case R.id.white_board_tab_2_item_1:
                    putWhiteBoard(R.drawable.item2_beach1);
                    break;
                case R.id.white_board_tab_2_item_2:
                    putWhiteBoard(R.drawable.item2_trash);
                    break;
                case R.id.white_board_tab_2_item_3:
                    putWhiteBoard(R.drawable.item2_dog1);
                    break;
                case R.id.white_board_tab_2_item_4:
                    putWhiteBoard(R.drawable.item2_man1);
                    break;
                case R.id.white_board_tab_2_item_5:
                    putWhiteBoard(R.drawable.item2_man2);
                    break;
                case R.id.white_board_tab_2_item_6:
                    putWhiteBoard(R.drawable.item3_raven);
                    break;
                case R.id.white_board_tab_2_item_7:
                    putWhiteBoard(R.drawable.item3_poster);
                    break;
                case R.id.white_board_tab_2_item_8:
                    putWhiteBoard(R.drawable.item3_footprint);
                    break;
                case R.id.white_board_tab_2_item_9:
                    putWhiteBoard(R.drawable.item3_horse);
                    break;
                case R.id.white_board_tab_2_item_10:
                    putWhiteBoard(R.drawable.item3_fox);
                    break;
                case R.id.white_board_tab_3_item_1:
                    putWhiteBoard(R.drawable.slogan1);
                    break;
                case R.id.white_board_tab_3_item_2:
                    putWhiteBoard(R.drawable.slogan2);
                    break;
                case R.id.white_board_tab_3_item_3:
                    putWhiteBoard(R.drawable.slogan3);
                    break;
                case R.id.white_board_tab_3_item_4:
                    putWhiteBoard(R.drawable.slogan4);
                    break;
                case R.id.white_board_tab_3_item_5:
                    putWhiteBoard(R.drawable.slogan5);
                    break;

            }
        }
    }


    public void replaceFragment(Fragment newFragment){
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,newFragment).addToBackStack("tag").commit();
    }


    public void onResume() {
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle("Create Custom Poster");

    }




    private void initWhiteBoard(View view){





        containerVg=view.findViewById(R.id.white_board_image_view);


        Button msave = view.findViewById(R.id.button_save_picture);
        msave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveViewAsPic(getContext(),containerVg);
            }
        });


    }

    private void putWhiteBoard(int rid){
        MovedImageView image = new MovedImageView(getContext());
        image.setImageResource(rid);
        image.setLongClickable(true);
        image.setOnLongClick(new MovedImageView.OnLongClickListener() {
            @Override
            public void onLongClick(View view) {
                questionDelete(view);
            }
        });
        containerVg.addView(image);
    }

    private void questionDelete(final View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Prompt");
        builder.setMessage("Do you want delete this image?");
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                containerVg.removeView(view);
            }
        });
        builder.show();
    }



    /**
     * 将未显示在屏幕上的view存到sd卡
     */
    public static void saveViewAsPic(Context context, View view){
        final boolean drawingCacheEnabled = true;
        view.setDrawingCacheEnabled(drawingCacheEnabled);
        view.buildDrawingCache(drawingCacheEnabled);
        final Bitmap drawingCache = view.getDrawingCache();
        Bitmap cacheBitmapFromView = null;
        if (drawingCache != null) {
            cacheBitmapFromView = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
        }
        if(cacheBitmapFromView != null) {
            saveBitmapToSdCard(context, cacheBitmapFromView);
        }
    }


    public static boolean saveBitmapToSdCard(Context context, Bitmap bitmap){
        boolean result = false;
//        String path = context.getExternalFilesDir("image").getAbsolutePath();
        String path = Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM
                + File.separator + "Camera" + File.separator;
        String imagePath = path + System.currentTimeMillis()+".jpeg";

        Log.d("sss","image path:"+imagePath);
        File file = new File(imagePath);
        FileOutputStream fileOutputStream = null;
        try {
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                File tempPath = new File(file.getParent());
                if (!tempPath.exists()) {
                    boolean res = tempPath.mkdirs();
                    Log.d("sss","create dir:"+ tempPath.getAbsolutePath()+",res="+res);
                }
                if(!file.exists()){
                    boolean res = file.createNewFile();
                    Log.d("sss","create file:"+ res);
                }
                fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                //update gallery
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri uri = Uri.fromFile(file);
                intent.setData(uri);
                context.sendBroadcast(intent);
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(file.getPath()))));
                Toast.makeText(context,"save to system image album success",Toast.LENGTH_SHORT).show();
                result = true;
                Log.d("sss","save path:"+file.getAbsolutePath());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void Geocoding(){

        String url = "https://api.mapbox.com/geocoding/v5/mapbox.places/-122.084000,37.421998.json?access_token=pk.eyJ1IjoibHd1dTAwMjEiLCJhIjoiY2tlZmYwcXR4MGsyODMzdXEyeGhlM21taiJ9.V4hkxkJ5mhH0NMCWoldlyw";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("test","requestApi==>"+e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Log.d("test","requestApi==>"+ result);
                try{
                    JSONObject jo = new JSONObject(result);
                    String feature = jo.optString("features");
                    JSONArray ja = new JSONArray(feature);
                    JSONObject jo2 = (JSONObject) ja.get(0);
                    placeName = jo2.optString("place_name");


                    Log.d("test","requestApi==>"+ placeName);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }



}