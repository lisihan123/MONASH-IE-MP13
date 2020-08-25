package com.example.savethebird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoadingActiviy extends AppCompatActivity {
    private Button mbtloading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_activiy);
        mbtloading = (Button) findViewById(R.id.btn_loading);
        final Intent intent = new Intent(this,MainActivity.class);
//        mbtloading.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(intent);
//            }
//        });
        // after image gif is finished then start main activity;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        startActivity(intent);
        //add finish method to close this activity
        finish();

    }
}