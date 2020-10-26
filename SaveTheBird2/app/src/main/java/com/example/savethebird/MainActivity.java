package com.example.savethebird;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.rest.RestOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Todo;
import com.example.savethebird.Fragment.ExploreFragment;
import com.example.savethebird.Fragment.MoreInfo.MoreInfoFragment;
import com.example.savethebird.Fragment.MovedImageView;
import com.example.savethebird.Fragment.home.HomeFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private String URL = "http://13.210.103.77/api/distance?lng=%f&lat=%f";
    Location location = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if(getActionBar() !=null)
//        {
//            getActionBar().hide();
//        }
//
//        if(getSupportActionBar() !=null){
//            getSupportActionBar().hide();
//        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        //menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

//        double distance = compareDB(location);

//        buildTodo();

//        Amplify.DataStore.observe(Todo.class,
//                started -> Log.i("Tutorial", "Observation began."),
//                change -> Log.i("Tutorial", change.item().toString()),
//                failure -> Log.e("Tutorial", "Observation failed.", failure),
//                () -> Log.i("Tutorial", "Observation complete.")
//        );
//        navView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1000);
        printKeyHash();

    }

    private void printKeyHash() {
        try{
            PackageInfo info = getPackageManager().getPackageInfo("com.example.savethebird",
                    PackageManager.GET_SIGNATURES);
            for(Signature signature : info.signatures){
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));

            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    public void setActionBarTitle(String title) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
    }

    public void setHomeActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onResume() {
        super.onResume();
        getMyLocation();
        Fragment myFragment = getSupportFragmentManager().findFragmentById(R.id.homelayout);
        if (myFragment != null && myFragment.isVisible()) {
//            getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            FragmentManager fm = getSupportFragmentManager();
            int count = fm.getBackStackEntryCount();
            for (int i = 0; i < count; ++i) {
                fm.popBackStack();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //Title bar back press triggers onBackPressed()
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Log.d("stack", "number "+ getSupportFragmentManager().getBackStackEntryCount());

//        getSupportFragmentManager().popBackStackImmediate();
        Fragment current = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (current != null && current instanceof HomeFragment) {
            Log.d("Curretnt", "Home ");
            super.onBackPressed();
        }

        else {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
        }
        super.onBackPressed();

//        else {
//            super.onBackPressed();
//        }
    }



    public void getMyLocation() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // 获取所有可用的位置提供器
        List<String> providerList = locationManager.getProviders(true);
        String provider;
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            // 当没有可用的位置提供器时,弹出Toast提示用户
            Toast.makeText(this, "No location provider to use",
                    Toast.LENGTH_SHORT).show();
            return;

        }
        Log.e("location", provider);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location = getLastKnownLocation();
        Log.e("location", "last known location=" + location);
        if (location != null) {
            // 显示当前设备的位置信息
            showLocation(location);
        }
        locationManager.requestLocationUpdates(provider, 5000, 0.5f, locationListener);
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onStatusChanged(String provider, int status, Bundle
                extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onLocationChanged(Location location) {
            // 更新当前设备的位置信息
            showLocation(location);
        }
    };

    private Location getLastKnownLocation() {
        LocationManager mLocationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            @SuppressLint("MissingPermission") Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }
        }
        return bestLocation;
    }

    private void showLocation(Location location) {
        String currentPosition = "latitude = " + location.getLatitude() + ","
                + "longitude = " + location.getLongitude();
        Log.e("location", currentPosition);
//        Toast.makeText(this, currentPosition, Toast.LENGTH_SHORT).show();
        requestApi(location.getLongitude(), location.getLatitude());
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        double radLat1 = rad(latitude1);
        double radLat2 = rad(latitude2);
        double a = radLat1 - radLat2;
        double b = rad(longitude1) - rad(longitude2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        distance = distance * 6378.137;
        Map<String, Object> map = new HashMap<String, Object>();
        BigDecimal decimal = new BigDecimal(distance);
        //结果保留2位小数
        distance = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        map.put("units", "km");
        map.put("distance", distance);

        return distance;
    }

//    public void notifyLocation(double distance){
//        if(distance <=5){
//            Toast.makeText(MainActivity.this, "Warning", Toast.LENGTH_LONG);
//            AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
//            build.setTitle("Alert!").setMessage("You are near a Hooded Plover habitat. Please be aware.").setPositiveButton("I Understand", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    Log.d("Ok","aok");
//                    flag = 1;
//                }
//            }).show();
//        }
//    }

    private void showNotice()
    {
        AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
        build.setTitle("Alert!").setMessage("You are near a Hooded Plover habitat. Please be aware.")
                .setPositiveButton("I Understand", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }
                }).show();
    }





    private void requestApi(double lng,double lat)
    {
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = String.format(URL,lng,lat);
        Log.d("test","url==>"+url);
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
                try {
                    JSONObject obj = new JSONObject(result);
                    int state = obj.optInt("state");
                    double distance = obj.optDouble("distance");
                    Message msg = Message.obtain();
                    msg.what = 111;
                    msg.obj = distance;
                    mHandler.sendMessage(msg);
                    Log.d("test","result==> state="+state+",distance="+distance);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private Handler mHandler = new Handler(){
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == 111){
                double d = (Double) msg.obj;
                if(d<=1) {
                    showNotice();
                }
            }
        }
    };

    public Location getLocation(){
        return location;
    }

}