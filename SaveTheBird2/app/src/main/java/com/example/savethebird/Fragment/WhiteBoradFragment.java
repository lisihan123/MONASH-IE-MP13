package com.example.savethebird.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.savethebird.R;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class WhiteBoradFragment extends Fragment {

    TabLayout tl1;
    HorizontalScrollView h1, h2, h3;
    ViewGroup containerVg;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.whiteboard, container, false);
        initWhiteBoard(view);
        initView(view);

        return  view;
    }

    private void initView(View view){

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









    private void initWhiteBoard(View view){
        ImageView egg = view.findViewById(R.id.white_board_tab_1_item_1);
        containerVg=view.findViewById(R.id.white_board_image_view);
        egg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovedImageView image = new MovedImageView(getContext());
                image.setImageResource(R.drawable.item1_egg);
                image.setLongClickable(true);
                image.setOnLongClick(new MovedImageView.OnLongClickListener() {
                    @Override
                    public void onLongClick(View view) {
                        questionDelete(view);
                    }
                });
                containerVg.addView(image);
            }
        });


    }

    private void questionDelete(final View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Prompt");
        builder.setMessage("do you want delete this image?");
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
        String path = context.getExternalFilesDir("image").getAbsolutePath();
        File sd = new File(path);
        if (!sd.exists()){
            sd.mkdirs();
        }
        File file = new File(path, System.currentTimeMillis()+".jpg");
        FileOutputStream fileOutputStream = null;
        try {
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                if(!file.exists()){
                    file.createNewFile();
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

}