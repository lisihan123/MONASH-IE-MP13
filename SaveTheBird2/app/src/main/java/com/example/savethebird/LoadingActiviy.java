package com.example.savethebird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class LoadingActiviy extends AppCompatActivity {
    private ImageView megg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_activity);

        final Intent intent = new Intent(this,MainActivity.class);



        initView(intent);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        startActivity(intent);
// //       add finish method to close this activity
//        finish();

    }

    private  void initView(Intent intent){
        megg = (ImageView) findViewById(R.id.imageView9);
        load(megg);
        megg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
                finish();
            }
        });
    }
    private void load(ImageView image) {
        String url = "";
         Glide.with(LoadingActiviy.this).load(R.drawable.egg).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(image);
    }
}