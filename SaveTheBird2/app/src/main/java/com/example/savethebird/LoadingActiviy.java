package com.example.savethebird;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

public class LoadingActiviy extends AppCompatActivity {
    private ImageView megg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_activiy);

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